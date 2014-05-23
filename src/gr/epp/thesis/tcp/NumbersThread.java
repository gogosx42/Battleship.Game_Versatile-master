package gr.epp.thesis.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */
/**
 * Deals with incoming and outgoing data from/to the clients.
 */
public class NumbersThread extends Thread implements Runnable {

    private Socket clientSocket;
    private static NumbersThread[] threads;
    private int maxClientsCount;            //Total number of clients connected to the server in the current session (even the disconnected).
    private String value;
    private int hh;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public NumbersThread(Socket clientSocket, NumbersThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    /**
     * Thread to read incoming data from a client and send data to the rest of
     * the clients.
     */
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            while (true) {
                synchronized (this) {
                    value = in.readLine();

                    // Send to all clients except itself.
                    for (int i = 0; i < maxClientsCount; i++) {
                        if (threads[i] != null && threads[i] != this) {
                            System.out.println("Sending " + value + " to PC " + i);
                            threads[i].out.println(value);
                        }
                    }
                }
            }
        } catch (IOException ex) {

            //When a user disconnects, its position is emptied
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }
        }
    }
}
