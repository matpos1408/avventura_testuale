/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AvventuraTestuale.Gioco;

import AvventuraTestuale.GameDescription;
import AvventuraTestuale.Parser.ParserOutput;
import AvventuraTestuale.Tipo.Oggetti;
import AvventuraTestuale.Tipo.ContenitoreOggetti;
import AvventuraTestuale.Tipo.Comandi;
import AvventuraTestuale.Tipo.TipoComandi;
import AvventuraTestuale.Tipo.Stanze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author vito e mattia
 */
public class TrovaIlTesoroGioco extends GameDescription {
    
    private final Socket socket;

    public TrovaIlTesoroGioco(Socket socket){
        this.socket = socket;
    }

     @Override
    public void init() throws Exception {
        //Commands
        Comandi nord = new Comandi(TipoComandi.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getCommands().add(nord);
        Comandi iventory = new Comandi(TipoComandi.INVENTORY, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getCommands().add(iventory);
        Comandi sud = new Comandi(TipoComandi.SOUTH, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getCommands().add(sud);
        Comandi est = new Comandi(TipoComandi.EAST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getCommands().add(est);
        Comandi ovest = new Comandi(TipoComandi.WEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getCommands().add(ovest);
        Comandi end = new Comandi(TipoComandi.END, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getCommands().add(end);
        Comandi look = new Comandi(TipoComandi.LOOK_AT, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getCommands().add(look);
        Comandi pickup = new Comandi(TipoComandi.PICK_UP, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getCommands().add(pickup);
        Comandi open = new Comandi(TipoComandi.OPEN, "apri");
        open.setAlias(new String[]{});
        getCommands().add(open);
        Comandi move = new Comandi(TipoComandi.MOVE, "sposta");
        move.setAlias(new String[]{"leva", "rimuovi"});
        getCommands().add(move);

        //stanze
        Stanze salotto = new Stanze(0, "salotto", "Tuo zio Ernesto è morto da poco e tu sei entrato in casa sua per trovare e riscuotere la sua ricca eredità,gira per la casa e vedi di trovarla...");
        salotto.setLook("Sei nel salotto, vedi un divano e una poltrona ,ma non è il momento per mettersi comodi!");
        Stanze cucina = new Stanze(1, "cucina", "Ti trovi in cucina.\n");
        cucina.setLook("La cucina stranamente è pulita ,C'è un foglietto sul forno microonde "+"\nVedi anche una porta del quale non ricordi a che stanza porta. ");
        Stanze bagno = new Stanze(2, "bagno", "\nPiù che un bagno sembra una reggia , tuo zio si era comprato anche una yacuzzi.\n");
        bagno.setLook("Non trovi nulla di interessante nel bagno.");
        Stanze cameraDaLetto = new Stanze(3, "Camera Da letto", "Sei nella camera da letto.\nIn questa stanza tuo zio si faceva lunghi pisolini");
        cameraDaLetto.setLook("C'è un comodino con il cassetto leggermente aperto.");
        Stanze corridoio = new Stanze(4, "Corridoio", "é un corridoio molto lungo \nCosì lungo da collegare solamente due stanze al salotto.");
        corridoio.setLook("Il corridoio è poco illuminato ma vedi abbastanza da capire che non c'è nulla.");
        Stanze stanzetta = new Stanze(4, "Stanzetta", "Questa è la stanza per gli ospiti\nTuo zio non aveva molti amici ..infatti la stanza è come nuova.");
        stanzetta.setLook("C'è un armadio bianco, sarebbe opportuno darli un occhiata.");
        Stanze studio = new Stanze(4, "studio", "Lo studio dello zio\nIn questo studio lo zio ha scritto alcuni dei suoi più grandi articoli.");
        studio.setLook("C'è una scrivania piena di penne e di fogli ma noti anche un quadro con la sua faccia ,lo zio non te lo ricordavi così egocentrico.");
        Stanze cabinaArmadio = new Stanze(4, "Cabina Armadio", "Una comunissima cabina armadio....piena di vestiti tutti dello stesso colore ...il grigio.");
        cabinaArmadio.setLook("Tra questi vedi la famosa giacca che metteva spesso tuo zio ,dalla tasca spunta un fogliettino.");
        Stanze sgabuzzino = new Stanze(4, "sgabuzzino", "\nQuesta stanza non te la ricordi...probabilmente perchè non ci sei mai entrato."+"\nLa stanza è poco illuminata e piena di polvere");
        sgabuzzino.setLook("Vedi sopra la credenza un barattolo trasparente con all'interno un fogliettino!.");
        
        //mappa
        
        salotto.setSouth(bagno);
        salotto.setEast(cucina);
        salotto.setWest(cameraDaLetto);
        salotto.setNorth(corridoio);
        bagno.setNorth(salotto);
        cameraDaLetto.setSouth(cabinaArmadio);
        cameraDaLetto.setEast(salotto);
        corridoio.setEast(studio);
        corridoio.setWest(stanzetta);
        corridoio.setSouth(salotto);
        cucina.setSouth(sgabuzzino);
        cucina.setWest(salotto);
        stanzetta.setEast(corridoio);
        studio.setWest(corridoio);
        cabinaArmadio.setNorth(cameraDaLetto);
        sgabuzzino.setNorth(cucina);
        getRooms().add(salotto);
        getRooms().add(cameraDaLetto);
        getRooms().add(corridoio);
        getRooms().add(cucina);
        getRooms().add(stanzetta);
        getRooms().add(studio);
        getRooms().add(cabinaArmadio);
        getRooms().add(sgabuzzino);
        getRooms().add(bagno);
        setCurrentRoom(salotto);
        //creazione fogliettini
        Oggetti fogliettino2 = new Oggetti(2,"Fogliettino n.2","Il fogliettino n.2!! ,Sul fogliettino ci sono le lettere 4 e 7");
        Oggetti fogliettino1 = new Oggetti(1,"Fogliettino n.1","Il fogliettino n.1!! ,Sul fogliettino ci sono le lettere 3 e 1");
        Oggetti fogliettino3 = new Oggetti(3,"Fogliettino n.3","Il fogliettino n.3!! ,Sul fogliettino ci sono le lettere 9 e 5");
        Oggetti fogliettino4 = new Oggetti(4,"Fogliettino n.4","Il fogliettino n.4!! ,Sul fogliettino ci sono le lettere 6 e 6");
        fogliettino1.setAlias(new String[]{"foglio", "foglietto", "fogliettino"});
        fogliettino2.setAlias(new String[]{"foglio", "foglietto", "fogliettino"});
        fogliettino3.setAlias(new String[]{"foglio", "foglietto", "fogliettino"});
        fogliettino4.setAlias(new String[]{"foglio", "foglietto", "fogliettino"});
        cameraDaLetto.getObjects().add(fogliettino2);
        cucina.getObjects().add(fogliettino4);
        cabinaArmadio.getObjects().add(fogliettino1);
        sgabuzzino.getObjects().add(fogliettino3);
        
        //creazione del cassetto col fogliettino
        ContenitoreOggetti comodino = new ContenitoreOggetti(5, "comodino", "Un comodino con il cassetto semi aperto.");
        comodino.setOpenable(true);
        comodino.setAlias(new String[]{"comò", "cassettiera","credenza","cassetto"});
        comodino.setOpenable(true);
        comodino.setPickupable(false);
        comodino.setOpen(false);
        cameraDaLetto.getObjects().add(comodino);
        fogliettino2.setPushable(false);
        fogliettino2.setPush(false);
        comodino.add(fogliettino2);

        //creazione barattolo nello sgabuzzino   
        ContenitoreOggetti barattolo = new ContenitoreOggetti(6, "barattolo", "Un barattolo con all'interno un fogliettino.");
        barattolo.setOpenable(true);
        barattolo.setAlias(new String[]{"vasetto","bicchiere"});
        barattolo.setPickupable(false);
        barattolo.setOpen(false);
        sgabuzzino.getObjects().add(barattolo);
        fogliettino3.setPushable(false);
        fogliettino3.setPush(false);
        barattolo.add(fogliettino3);


        ContenitoreOggetti armadio = new ContenitoreOggetti(9, "armadio", "Un armadio ");
        Oggetti chiave = new Oggetti(7, "chiave", "Chiave. Questa chiave probabilmente serve ad aprire una porta della casa.");
        armadio.setAlias(new String[]{"mobile"});
        armadio.setOpenable(true);
        armadio.setPickupable(false);
        armadio.setOpen(false);
        stanzetta.getObjects().add(armadio);
        chiave.setPush(false);
        chiave.setPushable(false);
        chiave.setPickupable(true);
        armadio.add(chiave);

        //creazione quadro nello studio
        Oggetti quadro = new Oggetti(8, "quadro", "Il quadro con la raffigurazione di tuo zio.");
        quadro.setAlias(new String[]{"dipinto"});
        studio.getObjects().add(quadro);
        quadro.setPush(false);
        quadro.setPushable(false);

       //creazione cassaforte
        ContenitoreOggetti cassaforte = new ContenitoreOggetti(10, "cassaforte", "Una cassaforte ");
        Oggetti tesoro = new Oggetti(11, "tesoro", "Una pergamena.");
        cassaforte.setAlias(new String[]{"cassa"});
        cassaforte.setOpenable(true);
        cassaforte.setPickupable(false);
        cassaforte.setOpen(false);
        studio.getObjects().add(cassaforte);
        tesoro.setPush(false);
        tesoro.setPushable(false);
        tesoro.setPickupable(true);
        cassaforte.add(tesoro);

        
    }

    @Override
    public void nextMove(ParserOutput p) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);



        Oggetti quadro = new Oggetti(8, "quadro", "Il quadro con la raffigurazione di tuo zio.");
        if (p.getCommand() == null) {
            out1.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        }else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == TipoComandi.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    noroom = true;
                } 
                //comando sud
            } else if (p.getCommand().getType() == TipoComandi.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    Oggetti chiave = new Oggetti(7, "chiave", "Una semplice chiave come molte altre.");
                    if(getCurrentRoom()==getRooms().get(3) && getInventory().contains(chiave)){
                        out1.println("Utilizzi la chiave che hai trovato per aprire la porta!");
                        setCurrentRoom(getCurrentRoom().getSouth());
                        //se sei in cucina e hai la chiave
                        move = true;
                    
                    }
                    else if(getCurrentRoom()==getRooms().get(3) && !getInventory().contains(p.getObject())){ 
                        // se sei in cucina e non hai la chiave 
                        out1.println("la porta sembrerebbe essere chiusa e tu non hai la chiave...");
                    }
                    else if(getCurrentRoom()!=getRooms().get(3)){
                        setCurrentRoom(getCurrentRoom().getSouth());
                        move = true;
                        
                    }
                } else {
                    noroom = true;
                }
                // fine comando sud
            } else if (p.getCommand().getType() == TipoComandi.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                    
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == TipoComandi.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == TipoComandi.INVENTORY) {
                out1.println("Nel tuo inventario ci sono:");
                for (Oggetti o : getInventory()) {
                    out1.println(o.getName() + ": " + o.getDescription());
                }
            } 
            //COMANDO SPOSTA
            else if (p.getCommand().getType() == TipoComandi.MOVE) {
                    if (p.getObject() != null) {
                        if (p.getObject().isPickupable()) {
                            getCurrentRoom().getObjects().remove(p.getObject());
                            out1.println("il quadro è stato spostato!!!");
                         }
                    } 
                }else if (p.getCommand().getType() == TipoComandi.LOOK_AT) {
                    //sei nello studio e hai spostato il quadro
                
                    if (getCurrentRoom().getLook() != null) {
                        if(getCurrentRoom()==getRooms().get(5) && !getCurrentRoom().getObjects().contains(quadro)){
                            out1.println("Dietro al Quadro c'è una cassaforte!!!");
                        }
                    //sei nello studio e non hai spostato il quadro
                     else if(getCurrentRoom()==getRooms().get(5) && getCurrentRoom().getObjects().contains(quadro)) {
                        out1.println(getCurrentRoom().getLook());
                    //non sei nello studio
                    }else if(getCurrentRoom()!=getRooms().get(5)){
                        out1.println(getCurrentRoom().getLook());
                        }
                    }
                    else{
                        out1.println("non c'è niente di interessante qui");
                    }
                } else if (p.getCommand().getType() == TipoComandi.PICK_UP) {
                if (p.getObject() != null) {
                        if (p.getObject().isPickupable()) {
                            getInventory().add(p.getObject());
                            getCurrentRoom().getObjects().remove(p.getObject());
                            out1.println("Hai raccolto: " + p.getObject().getDescription());
                            Oggetti tesoro = new Oggetti(11, "tesoro", "Una pergamena.");
                            if(getCurrentRoom()==getRooms().get(5) && !getCurrentRoom().getObjects().contains(tesoro)){
                                new TrovaIlTesoroGioco(socket).end(out1);
                            }
                        } else {
                            out1.println("Non puoi raccogliere questo oggetto.");
                        }  
                } else {
                    out1.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == TipoComandi.OPEN) {
                if (p.getObject() == null && p.getInvObject() == null) {
                    out1.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof ContenitoreOggetti) {
                                //si trova nello studio con quadro spostato
                                if(getCurrentRoom()==getRooms().get(5) && !getCurrentRoom().getObjects().contains(quadro)){
                                    out1.println("Inserisci la combinazione ");
                                    String numero = in.readLine();
                                    String combinazione="31479566";
                                    if(numero.equals(combinazione)){
                                        out1.println("Hai aperto: " + p.getObject().getName());
                                        ContenitoreOggetti c = (ContenitoreOggetti) p.getObject();
                                    
                                        if (!c.getList().isEmpty()) {
                                            out1.print(c.getName() + " contiene:");
                                            Iterator<Oggetti> it = c.getList().iterator();
                                            while (it.hasNext()) {
                                                Oggetti next = it.next();
                                                getCurrentRoom().getObjects().add(next);
                                                out1.print(" " + next.getName());
                                                it.remove();
                                            }
                                            out1.println();
                                            
                                        }
                                        p.getObject().setOpen(true);
                                    }
                                    else{
                                            out1.println("combinazione sbagliata");
                                        } 
                                }else if(getCurrentRoom()==getRooms().get(5) && getCurrentRoom().getObjects().contains(quadro)){
                                    out1.println("Non vedo nessuna cassaforte");
                                }
                                    //non si trova nello studio
                                    else if(getCurrentRoom()!=getRooms().get(5)){
                                    out1.println("Hai aperto: " + p.getObject().getName());
                                    ContenitoreOggetti c = (ContenitoreOggetti) p.getObject();
                                    if (!c.getList().isEmpty()) {
                                        out1.print(c.getName() + " contiene:");
                                        Iterator<Oggetti> it = c.getList().iterator();
                                        while (it.hasNext()) {
                                            Oggetti next = it.next();
                                            getCurrentRoom().getObjects().add(next);
                                            out1.print(" " + next.getName());
                                            it.remove();
                                        }
                                        out1.println();
                                    }
                                    p.getObject().setOpen(true);
                                }
                                
                            } else {
                                out1.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out1.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof ContenitoreOggetti) {
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out1.print(c.getName() + " contiene:");
                                    Iterator<Oggetti> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        Oggetti next = it.next();
                                        getInventory().add(next);
                                        out1.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out1.println();
                                }
                                p.getInvObject().setOpen(true);
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out1.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out1.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } if (noroom) {
                out1.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out1.println(getCurrentRoom().getName());
                out1.println("================================================");
                out1.println(getCurrentRoom().getDescription());
            }
        }
    }
    //DA MODIFICAREEE
    private void end(PrintWriter out1) {
        out1.println("La pergamena scritta da tuo zio che dice....L'amore per famiglia è più importante di qualsiasi tesoro....Tuo zio te l'ha fatta un altra volta!!"+"\nFINE");
        //System.exit(0);
    }
}