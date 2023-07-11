/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

/**
 *
 * @author UTENTE
 */
/*
 * Copyright (C) 2020 pierpaolo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
            Boolean flag=false;
            do{
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
                                out.println("#ok");
                                flag=true;
                                Engine engine = new Engine(new TrovaIlTesoroGioco(socket),socket);
                                engine.execute();
                            } catch (Exception ex) {
                                out.println("#error " + ex.getMessage());
                            }
                        }
                    } /*else if (findcmd && !matcher.group().equalsIgnoreCase("#name")) {
                        out.println("inserisci il comando corretto");
                    } */
                }
            }while(flag==false);
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