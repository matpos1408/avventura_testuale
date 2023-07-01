
package AvventuraTestuale.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vito e mattia
 */
public class ContenitoreOggetti extends Oggetti {

    private List<Oggetti> list = new ArrayList<>();

    public ContenitoreOggetti(int id) {
        super(id);
    }

    public ContenitoreOggetti(int id, String name) {
        super(id, name);
    }

    public ContenitoreOggetti(int id, String name, String description) {
        super(id, name, description);
    }

    public ContenitoreOggetti(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

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
