package server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;

public class ServerStuff {




    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static DataOutputStream writer;
    public static DataInputStream reader;

    public static void openServer(int port) throws IOException {
        try {
            out.println("opening server");

            serverSocket = new ServerSocket(port);

            out.println("new server with port: " + serverSocket.getLocalPort() + " and address: " + serverSocket.getInetAddress());

            clientSocket = serverSocket.accept();
            clientSocket.setSoTimeout(29000);
            serverSocket.setSoTimeout(29000);

            System.out.println("new server client with port: " + clientSocket.getPort() + " and address: " + clientSocket.getInetAddress());

            writer = new DataOutputStream(clientSocket.getOutputStream());
            reader = new DataInputStream(clientSocket.getInputStream());

            Thread heartBeatThread = new Thread(new HeartbeatServer());
            heartBeatThread.run();

        } catch (Exception e) {
            out.println(e.getMessage());

        }

//        String inputLine;
//        while ((inputLine = reader.readLine()) != null) {
//
//
//
//            if (".".equals(inputLine)) {
//                out.println("good bye");
//                 break;
//            }
//            writer.println(inputLine);
//           //  out.println(inputLine);
//        }
//            stopServer();
        }


    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void stopServer() throws IOException {

        writer.close();
        reader.close();
        clientSocket.close();
        serverSocket.close();
        out.println("SERVER TERMINATED");
        openServer(154);
    }






}
