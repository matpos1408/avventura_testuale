
package AvventuraTestuale.Parser;

import AvventuraTestuale.Tipo.Oggetti;
import AvventuraTestuale.Tipo.Comandi;

/**
 *
 * @author vito e mattia
 */
public class ParserOutput {

    private Comandi command;

    private Oggetti object;
    
    private Oggetti invObject;

    public ParserOutput(Comandi command, Oggetti object) {
        this.command = command;
        this.object = object;
    }

    public ParserOutput(Comandi command, Oggetti object, Oggetti invObejct) {
        this.command = command;
        this.object = object;
        this.invObject = invObejct;
    }

    public Comandi getCommand() {
        return command;
    }

    public void setCommand(Comandi command) {
        this.command = command;
    }

    public Oggetti getObject() {
        return object;
    }

    public void setObject(Oggetti object) {
        this.object = object;
    }

    public Oggetti getInvObject() {
        return invObject;
    }

    public void setInvObject(Oggetti invObject) {
        this.invObject = invObject;
    }

}
