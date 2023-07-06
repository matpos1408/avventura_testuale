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