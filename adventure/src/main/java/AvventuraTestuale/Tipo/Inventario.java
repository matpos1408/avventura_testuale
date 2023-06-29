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
public class Inventario {

    private List<Oggetti> list = new ArrayList<>();

    public List<Oggetti> getList() {
        return list;
    }

    public void setList(List<Oggetti> list) {
        this.list = list;
    }

    public void add(Oggetti o) {
        list.add(o);
    }

    public void remove(Oggetti o) {
        list.remove(o);
    }
}
