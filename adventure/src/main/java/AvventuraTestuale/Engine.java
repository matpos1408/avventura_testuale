
package AvventuraTestuale;

import AvventuraTestuale.Gioco.TrovaIlTesoroGioco;
import AvventuraTestuale.Parser.Parser;
import AvventuraTestuale.Parser.ParserOutput;
import AvventuraTestuale.Tipo.TipoComandi;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 *
 * @author vito e mattia
 */
public class Engine {

    private final GameDescription game;

    private Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
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

    public void execute() {
        System.out.println("=========================================");
        System.out.println("* Avventura Testuale v. 0.1 - 2022-2023 *");
        System.out.println("=========================================");
        System.out.println(game.getCurrentRoom().getName());
        System.out.println();
        System.out.println(game.getCurrentRoom().getDescription());
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p == null || p.getCommand() == null) {
                System.out.println("Non capisco quello che mi vuoi dire.");
            } else if (p.getCommand() != null && p.getCommand().getType() == TipoComandi.END) {
                System.out.println("Addio!");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Engine engine = new Engine(new TrovaIlTesoroGioco());
        engine.execute();
    }

}
