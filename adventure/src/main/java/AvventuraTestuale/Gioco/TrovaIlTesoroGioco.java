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
import java.io.PrintStream;
import java.util.Iterator;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author pierpaolo
 */
public class TrovaIlTesoroGioco extends GameDescription {

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
        Comandi push = new Comandi(TipoComandi.PUSH, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getCommands().add(push);
        //Rooms
        Stanze salotto = new Stanze(0, "salotto", "Tuo zio Ernesto è morto da poco e tu sei entrato in casa sua per trovare e riscuotere la sua ricca eredità,gira per la casa e vedi di trovarla...");
        salotto.setLook("Sei nel salotto, vedi un divano e una poltrona ,ma non è il momento per mettersi comodi!");
        Stanze cucina = new Stanze(1, "cucina", "Ti trovi in cucina.\n");
        cucina.setLook("La cucina stranamente è pulita c'è un foglietto sul forno microonde e vedi anche una porta del quale non ricordi a che stanza porta. ");
        Stanze bagno = new Stanze(2, "bagno", "\nPiù che un bagno sembra una reggia , tuo zio si era comprato anche una yacuzzi.\n");
        bagno.setLook("Non trovi nulla di interressante nel bagno.");
        Stanze cameraDaLetto = new Stanze(3, "Camera Da letto", "Sei nella camera da letto.\nQuante sborrate si sarà fatto tuo zio qui dentro!!");
        cameraDaLetto.setLook("C'è un comodino con il cassetto leggermente aperto.");
        Stanze corridoio = new Stanze(4, "Corridoio", "é un corridoio molto lungo \nCosì lungo da collegare solamente due stanze al salotto.");
        corridoio.setLook("Il corridoio è poco illuminato ma vedi abbastanza da capire che non c'è nulla.");
        Stanze stanzetta = new Stanze(4, "Stanzetta", "Questa è la stanza per gli ospiti\nTuo zio non aveva molti amici ..infatti la stanza è come nuova.");
        stanzetta.setLook("C'è un armadio bianco, sarerbbe opportuno darli un occhiata.");
        Stanze studio = new Stanze(4, "studio", "Lo studio dello zio\nIn questo studio lo zio ha scritto alcuni dei suoi più grandi articoli.");
        studio.setLook("C'è una scrivania piena di penne e di fogli ma noti anche un quadro con la sua faccia ,lo zio non te lo ricordavi così egocentrico.");
        Stanze cabinaArmadio = new Stanze(4, "Cabina Armadio", "Una comunissima cabina armadio....piena di vestiti tutti dello stesso colore ...il grigio.");
        cabinaArmadio.setLook("Tra questi vedi la famosa giacca che metteva spesso tuo zio ,dalla tasca spunta un fogliettino.");
        Stanze sgabuzzino = new Stanze(4, "sgabuzzino", "Lo sgabuzzino!\nQuesta stanza non te la ricordi...ma pensandoci su non l'hai mai vista...è molto sporca e  c'è di tutto dal cibo in scatola ai giocattoli di quando lo zio era un bambino,ogni cosa che non sapeva dove metterla la infilava quì.");
        sgabuzzino.setLook("Vedi che all'interno di un boccaccio trasparente c'è un fogliettino!.");
        
        //map
        
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
       
        //creazione fogliettini
        Oggetti fogliettino2 = new Oggetti(2,"Fogliettino n.2","Il fogliettino n.2!! ,Sul fogliettino ci sono le lettere 4 e 7");
        Oggetti fogliettino1 = new Oggetti(1,"Fogliettino n.1","Sul fogliettino ci sono le lettere 3 e 1");
        Oggetti fogliettino3 = new Oggetti(3,"Fogliettino n.3","Sul fogliettino ci sono le lettere 9 e 5");
        Oggetti fogliettino4 = new Oggetti(4,"Fogliettino n.4","Sul fogliettino ci sono le lettere 6 e 6");
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


        ContenitoreOggetti armadio = new ContenitoreOggetti(7, "armadio", "Un armadio ");
        Oggetti chiave = new Oggetti(7, "chiave", "Una semplice chiave come molte altre.");
        armadio.setAlias(new String[]{"key"});
        armadio.setOpenable(true);
        armadio.setPickupable(false);
        armadio.setOpen(false);
        stanzetta.getObjects().add(armadio);
        chiave.setPush(false);
        chiave.setPushable(false);
        chiave.setPickupable(true);
        armadio.add(chiave);
        



        //oggetti da eliminare
        Oggetti battery = new Oggetti(1, "batteria", "Un pacco di batterie, chissà se sono cariche.");
        battery.setAlias(new String[]{"batterie", "pile", "pila"});
        bagno.getObjects().add(battery);
        ContenitoreOggetti wardrobe = new ContenitoreOggetti(2, "armadio", "Un semplice armadio.");
        wardrobe.setAlias(new String[]{"guardaroba", "vestiario"});
        wardrobe.setOpenable(true);
        wardrobe.setPickupable(false);
        wardrobe.setOpen(false);
        stanzetta.getObjects().add(wardrobe);
        Oggetti toy = new Oggetti(3, "giocattolo", "Il gioco che ti ha regalato zia Lina.");
        toy.setAlias(new String[]{"gioco", "robot"});
        toy.setPushable(true);
        toy.setPush(false);
        wardrobe.add(toy);
        Oggetti kkey = new Oggetti(4, "chiave", "Usa semplice chiave come tante altre.");
        toy.setAlias(new String[]{"key"});
        toy.setPushable(false);
        toy.setPush(false);
        cucina.getObjects().add(kkey);
        //set starting room
        setCurrentRoom(salotto);
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
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
                        setCurrentRoom(getCurrentRoom().getSouth());
                        //se sei in cucina e hai la chiave
                        move = true;
                    }
                    else if(getCurrentRoom()==getRooms().get(3) && !getInventory().contains(p.getObject())){ 
                        // se sei in cucina e non hai la chiave 
                        System.out.println("non hai la chiave");
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
                out.println("Nel tuo inventario ci sono:");
                for (Oggetti o : getInventory()) {
                    out.println(o.getName() + ": " + o.getDescription());
                }
            } else if (p.getCommand().getType() == TipoComandi.LOOK_AT) {
                if (getCurrentRoom().getLook() != null) {
                    out.println(getCurrentRoom().getLook());
                } else {
                    out.println("Non c'è niente di interessante qui.");
                }
            } else if (p.getCommand().getType() == TipoComandi.PICK_UP) {
                if (p.getObject() != null) {
                    if (p.getObject().isPickupable()) {
                        getInventory().add(p.getObject());
                        getCurrentRoom().getObjects().remove(p.getObject());
                        out.println("Hai raccolto: " + p.getObject().getDescription());
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } else if (p.getCommand().getType() == TipoComandi.OPEN) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpenable() && p.getObject().isOpen() == false) {
                            if (p.getObject() instanceof ContenitoreOggetti) {
                                out.println("Hai aperto: " + p.getObject().getName());
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<Oggetti> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        Oggetti next = it.next();
                                        getCurrentRoom().getObjects().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getObject().setOpen(true);
                            } else {
                                out.println("Hai aperto: " + p.getObject().getName());
                                p.getObject().setOpen(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    if (p.getInvObject() != null) {
                        if (p.getInvObject().isOpenable() && p.getInvObject().isOpen() == false) {
                            if (p.getInvObject() instanceof ContenitoreOggetti) {
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getInvObject();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getName() + " contiene:");
                                    Iterator<Oggetti> it = c.getList().iterator();
                                    while (it.hasNext()) {
                                        Oggetti next = it.next();
                                        getInventory().add(next);
                                        out.print(" " + next.getName());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getInvObject().setOpen(true);
                            } else {
                                p.getInvObject().setOpen(true);
                            }
                            out.println("Hai aperto nel tuo inventario: " + p.getInvObject().getName());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getCommand().getType() == TipoComandi.PUSH) {
                //ricerca oggetti pushabili
                if (p.getObject() != null && p.getObject().isPushable()) {
                    out.println("Hai premuto: " + p.getObject().getName());
                    if (p.getObject().getId() == 3) {
                        end(out);
                    }
                } else if (p.getInvObject() != null && p.getInvObject().isPushable()) {
                    out.println("Hai premuto: " + p.getInvObject().getName());
                    if (p.getInvObject().getId() == 3) {
                        end(out);
                    }
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            }
            if (noroom) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                out.println(getCurrentRoom().getDescription());
            }
        }
    }

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}