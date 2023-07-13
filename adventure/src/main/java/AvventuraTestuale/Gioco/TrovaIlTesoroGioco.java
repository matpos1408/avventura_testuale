package AvventuraTestuale.Gioco;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import AvventuraTestuale.GameDescription;
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

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;


/**
 *
 * @author vito e mattia
 */
public class TrovaIlTesoroGioco extends GameDescription {
    private static String password;
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
        iventory.setAlias(new String[]{"inv, borsa"});
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
        end.setAlias(new String[]{"end", "fine", "esci", "ucciditi", "suicidati", "exit"});
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
        move.setAlias(new String[]{"leva", "rimuovi, togli, rompi, sfascia"});
        getCommands().add(move);

        //stanze
        Stanze salotto = new Stanze(0, "salotto", "Tuo zio Ernesto è morto da poco e tu sei entrato in casa sua per trovare e riscuotere la sua ricca eredità, cerca in giro per la casa per trovarla...");
        salotto.setLook("Sei nel salotto, vedi un divano ma non è il momento per mettersi comodi!\nIl gatto di tuo zio è ancora qui... chissà chi gli dà da mangiare...");
        Stanze cucina = new Stanze(1, "cucina", "Ti trovi in cucina.\n");
        cucina.setLook("La cucina stranamente è pulita, potrebbe esserci qualcosa di interessante in giro "+"\nVedi anche una porta ma non ricordi a quale stanza conduce. ");
        Stanze bagno = new Stanze(2, "bagno", "\nUn comunissimo bagno... \n");
        bagno.setLook("Non trovi nulla di interessante nel bagno.");
        Stanze cameraDaLetto = new Stanze(3, "Camera Da letto", "Sei nella camera da letto.\nChissà perché una persona così sola aveva un letto così grande...");
        cameraDaLetto.setLook("Il comodino ha il cassetto leggermente aperto.");
        Stanze corridoio = new Stanze(4, "Corridoio", "Sei nel corridoio, quanta luce entra dalle finestre!");
        corridoio.setLook("Il corridoio è così vuoto da capire subito che non c'è nulla di interessante");
        Stanze stanzetta = new Stanze(5, "Stanzetta", "Questa è la stanza per gli ospiti\nTuo zio non aveva molti amici.. infatti la stanza è come nuova.");
        stanzetta.setLook("C'è un armadio molto grande, sarebbe opportuno dargli un occhiata.");
        Stanze studio = new Stanze(6, "studio", "Lo studio dello zio \nIn questo studio lo zio ha scritto alcuni dei suoi più grandi articoli.");
        studio.setLook("C'è una scrivania con un computer ma noti anche un quadro di un ritratto di tuo zio, non te lo ricordavi così egocentrico.");
        Stanze cabinaArmadio = new Stanze(7, "Cabina Armadio", "Una comunissima cabina armadio.... piena di vestiti tutti dello stesso colore.");
        cabinaArmadio.setLook("Tra tutti i vestiti vedi la famosa giacca che metteva spesso tuo zio, dalla tasca spunta un fogliettino.");
        Stanze sgabuzzino = new Stanze(8, "sgabuzzino", "\nQuesta stanza non te la ricordi... probabilmente non ci sei mai entrato."+"\nLa stanza è poco illuminata e piena di polvere");
        sgabuzzino.setLook("Sopra la credenza c'è un barattolo sospetto, potrebbe esserci qualcosa di interessante all'interno");
        
         //inizializzazione immagini
         
         salotto.setImages("salotto");
         corridoio.setImages("corridoio");
         studio.setImages("studio");
         stanzetta.setImages("stanzetta");
         cucina.setImages("cucina");
         bagno.setImages("bagno");
         cameraDaLetto.setImages("cameraDaLetto");
         sgabuzzino.setImages("sgabuzzino");
         cabinaArmadio.setImages("cabinaArmadio");
        
        
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
        
        
        //Interrogazione database con settaggio domande nei fogliettini e combinazione con le risposte
        JDBC jdbc = new JDBC();
        Object[] quesiti = new Object[4];
        quesiti = jdbc.getIndovinelli();
        String combinazione = "";
        String[] Domande;
        Domande = new String[4];
        int counter=0;    
            for (Object item : quesiti) {
            if (item instanceof Indovinelli) {
                Indovinelli domanda = (Indovinelli) item;
                Domande[counter] = domanda.getIndovinello();
                String risultato =domanda.getRisposta();
                combinazione= combinazione + risultato;
                counter++;
            }
        }
           password=combinazione;
        
 
        Oggetti fogliettino1 = new Oggetti(1,"Fogliettino n.1","Sopra c'è un indovinello: \n" + Domande[0]);
        Oggetti fogliettino2 = new Oggetti(2,"Fogliettino n.2","Sopra c'è un indovinello: \n" + Domande[1]);
        Oggetti fogliettino3 = new Oggetti(3,"Fogliettino n.3","Sopra c'è un indovinello: \n" + Domande[2]);
        Oggetti fogliettino4 = new Oggetti(4,"Fogliettino n.4","Sopra c'è un indovinello: \n" + Domande[3]);

    //creazione fogliettini
       
        fogliettino1.setAlias(new String[]{"foglio", "foglietto", "fogliettino", "biglietto", "bigliettino"});
        fogliettino2.setAlias(new String[]{"foglio", "foglietto", "fogliettino", "biglietto", "bigliettino"});
        fogliettino3.setAlias(new String[]{"foglio", "foglietto", "fogliettino", "biglietto", "bigliettino"});
        fogliettino4.setAlias(new String[]{"foglio", "foglietto", "fogliettino", "biglietto", "bigliettino"});
        cameraDaLetto.getObjects().add(fogliettino2);
        cucina.getObjects().add(fogliettino4);
        cabinaArmadio.getObjects().add(fogliettino1);
        sgabuzzino.getObjects().add(fogliettino3);
        
        //creazione del cassetto col fogliettino
        ContenitoreOggetti comodino = new ContenitoreOggetti(5, "comodino", "Un comodino con il cassetto semi aperto.");
        comodino.setOpenable(true);
        comodino.setAlias(new String[]{"comò", "cassettiera","credenza","cassetto"});
        comodino.setPickupable(false);
        comodino.setOpen(false);
        cameraDaLetto.getObjects().add(comodino);
        comodino.add(fogliettino2);

        //creazione barattolo nello sgabuzzino   
        ContenitoreOggetti barattolo = new ContenitoreOggetti(6, "barattolo", "Un barattolo con all'interno un fogliettino.");
        barattolo.setOpenable(true);
        barattolo.setAlias(new String[]{"vasetto","bicchiere"});
        barattolo.setPickupable(false);
        barattolo.setOpen(false);
        sgabuzzino.getObjects().add(barattolo);
        barattolo.add(fogliettino3);


        ContenitoreOggetti armadio = new ContenitoreOggetti(9, "armadio", "Un armadio ");
        Oggetti chiave = new Oggetti(7, "chiave", "Questa chiave probabilmente serve ad aprire una porta della casa.");
        armadio.setAlias(new String[]{"key"});
        armadio.setOpenable(true);
        armadio.setPickupable(false);
        armadio.setOpen(false);
        stanzetta.getObjects().add(armadio);
        chiave.setPickupable(true);
        armadio.add(chiave);

        //creazione quadro nello studio
        Oggetti quadro = new Oggetti(8, "quadro", "Il quadro con la raffigurazione di tuo zio.");
        quadro.setAlias(new String[]{"dipinto"});
        studio.getObjects().add(quadro);
        
       //creazione cassaforte
        ContenitoreOggetti cassaforte = new ContenitoreOggetti(10, "cassaforte", "Una cassaforte ");
        Oggetti tesoro = new Oggetti(11, "tesoro", "Una pergamena.");
        cassaforte.setAlias(new String[]{"cassa"});
        cassaforte.setOpenable(true);
        cassaforte.setPickupable(false);
        cassaforte.setOpen(false);
        studio.getObjects().add(cassaforte);
        tesoro.setPickupable(true);
        cassaforte.add(tesoro);
        
    }

    @Override
    public void nextMove(ParserOutput p) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
       
        

        Oggetti quadro = new Oggetti(8, "quadro", "Il quadro col ritratto di tuo zio.");
    
        if (p.getCommand() == null) {
            out1.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        }else {
            //move
            boolean noroom = false;
            boolean move = false;
            if (p.getCommand().getType() == TipoComandi.NORD) {
                if (getCurrentRoom().getNorth() != null) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    out1.println(getCurrentRoom().getImages());
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
                        out1.println(getCurrentRoom().getImages());
                        //se sei in cucina e hai la chiave
                        move = true;
                    
                    }
                    else if(getCurrentRoom()==getRooms().get(3) && !getInventory().contains(p.getObject())){ 
                        // se sei in cucina e non hai la chiave 
                        out1.println("La porta sembrerebbe essere chiusa a chiave e tu non hai la chiave...");
                    }
                    else if(getCurrentRoom()!=getRooms().get(3)){
                        setCurrentRoom(getCurrentRoom().getSouth());
                        out1.println(getCurrentRoom().getImages());
                        move = true;
                        
                    }
                } else {
                    noroom = true;
                }
                // fine comando sud
            } else if (p.getCommand().getType() == TipoComandi.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    out1.println(getCurrentRoom().getImages());

                    move = true;
                    
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == TipoComandi.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    out1.println(getCurrentRoom().getImages());
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
                    if (p.getObject() != null && getCurrentRoom()==getRooms().get(5)) {
                        if (p.getObject().isPickupable()) {
                            String studio="studio ";
                            getCurrentRoom().setImages(studio);
                            out1.println( getCurrentRoom().getImages());
                            getCurrentRoom().getObjects().remove(p.getObject());
                            out1.println("Il quadro è stato spostato!!!");
                            
                            
                        }
                    }else{
                        out1.println("Non c'è niente da spostare qui");
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
                        out1.println("Non c'è niente di interessante qui");
                    }
                } else if (p.getCommand().getType() == TipoComandi.PICK_UP) {
                if (p.getObject() != null) {
                        if (p.getObject().isPickupable()) {
                            if(p.getObject() != null){
                                if(p.getObject().equals(quadro)){
                                    out1.println("Non puoi prendere il quadro");
                                }
                                else{
                                    getInventory().add(p.getObject());
                                    getCurrentRoom().getObjects().remove(p.getObject());
                                    out1.println("Hai raccolto: " + p.getObject().getName() +" "+ p.getObject().getDescription());
                                    
                                    if(getCurrentRoom()==getRooms().get(4)){
                                        getCurrentRoom().setLook("Chissà cosa apre quella chiave...");
                                         String stanzetta  = "stanzetta  ";
                                         getCurrentRoom().setImages(stanzetta );
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    if(getCurrentRoom()==getRooms().get(1)){
                                        getCurrentRoom().setLook("Non c'è più nulla di interessante qui, sarebbe meglio guardare altrove");
                                        String cameraDaLetto= "cameraDaLetto  ";
                                        getCurrentRoom().setImages(cameraDaLetto );
                                        out1.println(getCurrentRoom().getImages());
                                    }
                                    if(getCurrentRoom()==getRooms().get(7)){
                                         getCurrentRoom().setLook("Hai già raccolto abbastanza indizi in questa stanza, sarebbe meglio guardare altrove");
                                         String sgabuzzino   = "sgabuzzino  ";
                                         getCurrentRoom().setImages(sgabuzzino );
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    if(getCurrentRoom()==getRooms().get(6)){
                                         getCurrentRoom().setLook("Hai già scrutato la stanza, qui restano solo vestiti e polvere...");
                                         String cabinaArmadio   = "cabinaArmadio ";
                                         getCurrentRoom().setImages(cabinaArmadio );
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    // SETTAGGIO IMMAGINE CUCINA SENZA FOGLIETTO
                                    if(getCurrentRoom()==getRooms().get(3)){
                                         getCurrentRoom().setLook("La cucina stranamente è pulita, hai già trovato un indizio qui, l'unica cosa interessante è quella porta misteriosa...");
                                         String cucina   = "cucina ";
                                         getCurrentRoom().setImages(cucina );
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    
                                    Oggetti tesoro = new Oggetti(11, "tesoro", "Una pergamena.");
                                    if(getCurrentRoom()==getRooms().get(5) && !getCurrentRoom().getObjects().contains(tesoro)){
                                        out1.println("studio   ");
                                        new TrovaIlTesoroGioco(socket).end(out1);
                                        boolean flag=true;
                                        do{
                                            out1.println("digita #exit per terminare");
                                            String risposta=in.readLine();
                                            if(risposta.equals("#exit")){
                                                flag=false;
                                            }                                       
                                        }while(flag==true);
                                    }
                                }
                            }    
                            else{
                                    out1.println("Cosa vorresti prendere?");
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
                                    String combinazione=password;
                                    if(numero.equals(combinazione)){
                                        String studio="studio  ";
                                        getCurrentRoom().setImages(studio);
                                        out1.println( getCurrentRoom().getImages());
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
                                            out1.println("Combinazione errata");
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
                                    if(getCurrentRoom()==getRooms().get(4)){
                                         
                                         String stanzetta = "stanzetta ";
                                         getCurrentRoom().setImages(stanzetta); 
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    if(getCurrentRoom()==getRooms().get(1)){
                                       
                                         String cameraDaLetto = "cameraDaLetto ";
                                         getCurrentRoom().setImages(cameraDaLetto); 
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    if(getCurrentRoom()==getRooms().get(7)){
                                         String sgabuzzino1 ="sgabuzzino ";
                                         getCurrentRoom().setImages(sgabuzzino1); 
                                         out1.println(getCurrentRoom().getImages());
                                    }
                                    
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
                out1.println("Da quella parte non si può andare c'è un muro!");
            } else if (move) {
                //out1.println(getCurrentRoom().getName());
                out1.println("================================================");
                out1.println(getCurrentRoom().getDescription());
            }
        }
    
    }
    //DA MODIFICAREEE
    private void end(PrintWriter out1) throws IOException {
        out1.println("La pergamena scritta da tuo zio con scritto: L'amore per famiglia è più importante di qualsiasi tesoro.... Tuo zio te l'ha fatta un'altra volta!!!"+"\nFINE");

    }
}
