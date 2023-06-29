/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AvventuraTestuale.Tipo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class Stanze {

    private final int id;

    private String name;

    private String description;

    private String look;

    private boolean visible = true;

    private Stanze south = null;

    private Stanze north = null;

    private Stanze east = null;

    private Stanze west = null;
    
    private final List<Oggetti> objects=new ArrayList<>();

    public Stanze(int id) {
        this.id = id;
    }

    public Stanze(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Stanze getSouth() {
        return south;
    }

    public void setSouth(Stanze south) {
        this.south = south;
    }

    public Stanze getNorth() {
        return north;
    }

    public void setNorth(Stanze north) {
        this.north = north;
    }

    public Stanze getEast() {
        return east;
    }

    public void setEast(Stanze east) {
        this.east = east;
    }

    public Stanze getWest() {
        return west;
    }

    public void setWest(Stanze west) {
        this.west = west;
    }

    public List<Oggetti> getObjects() {
        return objects;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stanze other = (Stanze) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

}
