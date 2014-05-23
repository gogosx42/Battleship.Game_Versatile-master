package gr.epp.thesis.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */
public class Server {

    private static ServerSocket serverSocket = null;
    private static Socket clientSocket = null;
    private static final int maxClientsCount = 2; //Maximum number of connections supported by the server.
    private static final NumbersThread[] threads = new NumbersThread[maxClientsCount];

    public static void main(String[] args) {
        int portNumber = 1501;
        int portNumber2 = 1502;

        if (args.length < 1) {
            System.out.println("Now using port number=" + portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }
        try {
            //Opens a server socket in ports 1501 (portNumber).
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        /**
         * Creates a client socket for each connection and starts two threads.
         * One for reading incoming data(NumbersThread) and another for
         * calculating the number of connected clients(ClientThread).
         */
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("User Connected in port: " + portNumber);
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new NumbersThread(clientSocket, threads)).start();
                        break;
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
