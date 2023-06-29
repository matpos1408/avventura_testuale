/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AvventuraTestuale;

import AvventuraTestuale.Parser.ParserOutput;
import AvventuraTestuale.Tipo.Oggetti;
import AvventuraTestuale.Tipo.Comandi;
import AvventuraTestuale.Tipo.Stanze;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public abstract class GameDescription {

    private final List<Stanze> rooms = new ArrayList<>();

    private final List<Comandi> commands = new ArrayList<>();

    private final List<Oggetti> inventory = new ArrayList<>();

    private Stanze currentRoom;

    public List<Stanze> getRooms() {
        return rooms;
    }

    public List<Comandi> getCommands() {
        return commands;
    }

    public Stanze getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Stanze currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<Oggetti> getInventory() {
        return inventory;
    }

    public abstract void init() throws Exception;

    public abstract void nextMove(ParserOutput p, PrintStream out);

}
