/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AvventuraTestuale.Gioco;

/**
 *
 * @author Vito e Mattia
 */
public class Indovinelli {
    private String domanda;
         private String risultato;
    
        public Indovinelli(String domanda, String risultato) {
            this.domanda = domanda;
            this.risultato = risultato;
        }
    
        public String getIndovinello() {
            return domanda;
        }
    
        public String getRisposta() {
            return risultato;
        }
}
