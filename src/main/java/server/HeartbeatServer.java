package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HeartbeatServer implements Runnable{

    private ServerStuff stuff;
    private byte[] ping = "AppleFritters".getBytes();
    public HeartbeatServer(){}

    @Override
    public void run() {

        System.out.println("START HEART");

        DataInputStream reader = ServerStuff.reader;
        DataOutputStream writer = ServerStuff.writer;

        while (true) {
            try {


                InetAddress address = ServerStuff.clientSocket.getInetAddress();

                int read = reader.read();
                System.out.println(read);

                if (!address.isReachable(8000) || read == -1) {
                    System.out.println("Host address is UNREACHABLE");
                    ServerStuff.stopServer();
                }




                System.out.println("pong!");
                Thread.sleep(5000);

            } catch (IOException e) {
                System.out.println(e.getMessage());
                if (e.getMessage().equals("Read timed out")) run();

                try {

                    ServerStuff.stopServer();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
