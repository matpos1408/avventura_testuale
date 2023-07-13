/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

/**
 *
 * @author Vito e Mattia
 */


import AvventuraTestuale.Gioco.TrovaIlTesoroGioco;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;




import java.io.PrintWriter;



import java.util.regex.Pattern;


import java.util.regex.Matcher;

import java.net.Socket;



/**
 *
 * @author vito e mattia
 */
public class LogicaServer extends Thread {

    private final Socket socket;

    private boolean run = true;

    private final Utenti md;

    private PrintWriter out = null;

    /**
     *
     * @param socket
     * @param md
     */
    public LogicaServer(Socket socket, Utenti md) {
        this.socket = socket;
        this.md = md;
    }

    /**
     *
     * @param socket
     * @param md
     * @param name
     */
    public LogicaServer(Socket socket, Utenti md, String name) {
        this(socket, md);
        this.setName(name);
    }

  

    /**
     *
     */
    @Override
    public void run() {
        try {
            System.out.println("Connessione accettata: " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            while(run){
                String str = in.readLine();
                if (str != null) {
                    str = str.trim();
                    Pattern pattern = Pattern.compile("\\S+");
                    Matcher matcher = pattern.matcher(str);
                    boolean findcmd = matcher.find();
                    if (findcmd && matcher.group().equalsIgnoreCase("#name")) {
                        if (matcher.find()) {
                            String name = matcher.group();
                            try {
                                md.addUser(name, this);
                                out.println("\n#username valido");
                                Engine engine = new Engine(new TrovaIlTesoroGioco(socket),socket);
                                engine.execute();
                            } catch (Exception ex) {
                                out.println("#error " + ex.getMessage());
                            }
                        }
                    } else if (findcmd && !matcher.group().equalsIgnoreCase("#name")) {
                        out.println("inserisci il comando corretto");
                    } 
                }
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            System.out.println("closing...");
            try {
                socket.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}