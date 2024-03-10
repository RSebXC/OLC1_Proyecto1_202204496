/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

/**
 *
 * @author Carlos Perez
 */
public class Generador {
    public static void main(String[] args) {
        try {
            String ruta = "./src/Generador/"; 
            String[] opJFlex = {ruta+"lex.jflex","-d",ruta};
            jflex.Main.generate(opJFlex);
            
           String[] opCup = {"-destdir",ruta,"-parser","Parser",ruta+"parser.cup"};
           java_cup.Main.main(opCup);
          
        } catch (Exception e) {
        }
    }
}

