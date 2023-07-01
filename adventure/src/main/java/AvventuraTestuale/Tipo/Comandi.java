
package AvventuraTestuale.Tipo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author vito e mattia
 */
public class Comandi {

    private final TipoComandi type;

    private final String name;

    private Set<String> alias;

    public Comandi(TipoComandi type, String name) {
        this.type = type;
        this.name = name;
    }

    public Comandi(TipoComandi type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public TipoComandi getType() {
        return type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.type);
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
        final Comandi other = (Comandi) obj;
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

}
