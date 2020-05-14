package com.inteagle.tcpserver.task;

import com.inteagle.tcpserver.tcp.SocketTransceiver;
import com.inteagle.tcpserver.tcp.server.TcpServer;
import com.inteagle.tcpserver.util.ByteHexUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Slf4j
public class TcpTask {

    private static final Logger logger = LoggerFactory.getLogger(TcpTask.class);

    @PostConstruct
    public void init() {
        //启动tcp server
        connectTcpServer();
    }

    static void connectTcpServer() {
        int port = 1369;
        TcpServer server = new TcpServer(port) {

            @Override
            public void onConnect(SocketTransceiver client) {
                printInfo(client, "Connect");
            }

            @Override
            public void onConnectFailed() {
                logger.warn("Client Connect Failed");
            }

            @Override
            public void onReceive(SocketTransceiver client, String data) {
                /**
                 * 收到字节数据,做相应处理
                 */
                printData(client, data);
                /**
                 * 服务端下发收到的数据
                 */
                logger.warn("Send data " + data.replace(" ", ""));
                /**
                 * 字节数组 client.send(ByteHexUtil.stringToByteArr(data));
                 *
                 * 16进制字符串  client.send(data.replace(" ",""));
                 *
                 * 16进制字符串转16进制
                 */
                client.send(data.replace(" ", ""));
            }

            @Override
            public void onDisconnect(SocketTransceiver client) {
                printInfo(client, "Disconnect");
            }

            @Override
            public void onServerStop() {
                logger.warn("--------Server Stopped--------");
            }
        };
        logger.warn("--------Server Started--------");
        server.start();
    }

    static void printInfo(SocketTransceiver st, String msg) {
        logger.warn("Client " + st.getInetAddress().getHostAddress());
        logger.warn("  " + msg);
    }

    static void printData(SocketTransceiver st, String data) {
        logger.warn("Client " + st.getInetAddress().getHostAddress());
        logger.warn("Receive data " + data);
    }



}
