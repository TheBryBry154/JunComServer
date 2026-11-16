package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

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
                InetSocketAddress adress = new InetSocketAddress(address,154);


                if (!address.isReachable(8000)) {
                    System.out.println("UNREACHABLE");
                    ServerStuff.stopServer();
                }
                System.out.println("pong!");
                Thread.sleep(5000);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


//                int length = reader.readInt();
//                System.out.println("read");
//
//                byte[] pong = new byte[length];
//                System.out.println("pong " +  length);
//
//                reader.readFully(pong);
//                System.out.println("full");
//
//                System.out.println("a " + new String(pong));
//
//                if(!new String(pong).equals("AppleFritter")){
//
//                    ServerStuff.stopServer();
//
//                    System.out.println("Closed Server, failed to Heartbeat");
//                    System.out.println("reopening");
//                }
//
//                writer.writeInt(ping.length);
//                System.out.println("int " + ping.length);
//
//                writer.write(ping);
//                System.out.println("write");
//
//                writer.flush();
//                System.out.println("flush");
//
//                Thread.sleep(5000);
//            } catch (IOException e) {
//                System.out.println("IO error : " + e.getMessage());
//                try {
//                    ServerStuff.stopServer();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            } catch (InterruptedException e) {
//                System.out.println("Interrupt error : " + e.getMessage());
//                try {
//                    ServerStuff.stopServer();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }

        }
    }

}
