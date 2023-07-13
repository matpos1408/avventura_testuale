
package AvventuraTestuale.Tipo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vito e mattia
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

    public boolean isNew(){
        return list != null;
    }
}
