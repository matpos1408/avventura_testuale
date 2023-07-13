
package AvventuraTestuale;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 *
 * @author vito e mattia
 */
public class ServerStart {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Utenti md = new Utenti();
        ServerSocket s = new ServerSocket(6666);
        System.out.println("Started: " + s);
        try {
            while (true) {
                Socket socket = s.accept();
                //UUID è utilizzato per creare un id univoco da utilizzare come nome del Thread
                Thread t = new LogicaServer(socket, md, UUID.randomUUID().toString());
                t.start();
            }
        } finally {
            s.close();
        }
    }

}
