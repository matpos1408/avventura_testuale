/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

/**
 *
 * @author UTENTE
 */


import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vito e mattia
 */
public class Utenti {

    private final Map<String, LogicaServer> clients = new HashMap<>();

    /**
     *
     * @param username
     * @param thread
     * @throws Exception
     */
    public synchronized void addUser(String username, LogicaServer thread) throws Exception {
        if (clients.containsKey(username)) {
            throw new Exception("Utente già esistente");
        } else {
            clients.put(username, thread);
        }
    }

}