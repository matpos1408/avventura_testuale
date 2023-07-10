/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Vito e Mattia
 */
public class JDBC {
        /**
     *
     */
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Connessione al DB
            Properties dbprops = new Properties();
            dbprops.setProperty("user", "vito");
            dbprops.setProperty("password", "");
            Connection conn = DriverManager.getConnection("jdbc:h2:./resources/db/indovinelli", dbprops);
            Statement stm = conn.createStatement();
            stm = conn.createStatement();
            //creazione statement
            // Dichiarazione e inializzazione dell'array di oggetti
            Object[] domandeERisultati = new Object[4];
            int index =0;
            // Generazione di 4 numeri casuali 
            Random rand = new Random();
            Set<Integer> numeriCasuali = new HashSet<>();
            while (numeriCasuali.size() < 4) {
            // Cambiare il numero in base a quanti indovinelli ci sono nel db
            int numeroCasuale = rand.nextInt(16) + 1;
            numeriCasuali.add(numeroCasuale);
            }
            // Esecuzione del codice per ogni numero casuale generato
            for (int numero : numeriCasuali) {
            // PPROVA STAMPA QUERY PER NUMERI CASUALI
            PreparedStatement pstm = conn.prepareStatement("SELECT Id, DOMANDA, RISPOSTA FROM indovinelli WHERE id=?");
            pstm.setInt(1, numero);
            ResultSet rs = pstm.executeQuery(); 
            while (rs.next()) {
                /* Stampa delle query selezionate
                System.out.println(rs.getInt(1) + ":     " + rs.getString(2) + " = " + rs.getString(3));
                */
                // inserimento dati nell'array
                domandeERisultati[index] = new Domanda(rs.getString(2), rs.getString(3));
                index++;
            }
            rs.close();
            stm.close();
            }
            // stampa dell'array di oggetti
               for (Object item : domandeERisultati) {
            if (item instanceof Domanda) {
                Domanda domanda = (Domanda) item;
                System.out.println("Domanda: " + domanda.getDomanda());
                System.out.println("Risultato: " + domanda.getRisultato());
                System.out.println();
            }
        }
            // -----------------------------------------------------------------------
            
            /*
            stm = conn.createStatement();
            System.out.println("SQL Query");
            System.out.println("=======================");
            ResultSet rs = stm.executeQuery("SELECT ID, DOMANDA, RISPOSTA FROM indovinelli");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ":     " + rs.getString(2) + " = " + rs.getString(3));
            }
            rs.close();
            stm.close();*/
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }
    }
}


   class Domanda {
        private String domanda;
        private String risultato;
    
        public Domanda(String domanda, String risultato) {
            this.domanda = domanda;
            this.risultato = risultato;
        }
    
        public String getDomanda() {
            return domanda;
        }
    
        public String getRisultato() {
            return risultato;
        }
        
    }

