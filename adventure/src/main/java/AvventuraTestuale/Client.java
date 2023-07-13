/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

/**
 *
 * @author Vito e Mattia
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author vito e mattia
 */
public class Client {

   
    private static class ClientThread extends Thread {

        private final BufferedReader in;

        private boolean run = true;

        public ClientThread(BufferedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            
            while (run) {
                try {
                        System.out.println(in.readLine());
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }

        public void setRun(boolean run) {
            this.run = run;
        }

    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // indirizzo riservato al localhost 127.0.0.1
        InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        //Creazione socket
        Socket socket = new Socket(addr, 6666);
        System.out.println("socket = " + socket);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            Scanner scanner = new Scanner(System.in);
            String user = null;
            do {
                System.out.println("Inserisci il tuo nome utente");
                user = scanner.nextLine();
                out.println(user);
                String ans = in.readLine();
                System.out.println(ans);
                if (!ans.equals("#ok")) {
                    user = null;
                }
            } while (user == null);
            ClientThread ct = new ClientThread(in);
            ct.start();
            while (true) {
                
                String cmd = scanner.nextLine();
                if (cmd.equals("#exit")) {
                    out.println(cmd);
                    break;
                } else {
                    out.println(cmd);
                }
                
            }
            //Termino il Thread che si occupa di stampare i messaggi sul terminale
            ct.setRun(false);
            scanner.close();
            try {
                ct.join();
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }

}
