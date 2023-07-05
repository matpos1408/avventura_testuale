
package AvventuraTestuale;

import AvventuraTestuale.Gioco.TrovaIlTesoroGioco;
import AvventuraTestuale.Parser.Parser;
import AvventuraTestuale.Parser.ParserOutput;
import AvventuraTestuale.Tipo.TipoComandi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

/**
 *
 *
 * @author vito e mattia
 */
public class Engine {

    private final GameDescription game;

    private Parser parser;

    private final Socket socket;
    
    private boolean run = true;

    public Engine(GameDescription game, Socket socket) {
        this.game = game;
        this.socket=socket;
        try {
            this.game.init();
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadFileListInSet(new File("./resources/stopwords"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void execute() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter  out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        out.println("=========================================");
        out.println("* Avventura Testuale v. 0.1 - 2022-2023 *");
        out.println("=========================================");
        out.println(game.getCurrentRoom().getName());
        out.println();
        out.println(game.getCurrentRoom().getDescription());
        out.println();
        
        while (run) {
            String command = in.readLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == TipoComandi.END) {
                out.println("Addio!");
                break;
                
            }else {
                game.nextMove(p);
                out.println();
            }
        }
    }
}
