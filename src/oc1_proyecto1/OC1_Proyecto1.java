/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package OC1_Proyecto1;

import Generador.Arbol;
import java.io.BufferedReader;
import java.io.StringReader;
import Generador.Scanner;
import Generador.Parser;
import Generador.Simbolo;
import java.util.ArrayList;

/**
 *
 * @author Carlos Perez
 */
public class OC1_Proyecto1 {

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Simbolo> Tabla = new ArrayList();
        try {
            String text = "PROGRAM SUM(2,3) RES(2,3) MUL(2,3) DIV(2,3)  MOD(2,3) arr: double :: @carray <- [12, 2, 3] end; var:double:: numero <- 2.5 end; Media([1,2,3]) Mediana([2,3,4,4,5,3,4,3,6,4]) Moda([2,3,4,4,5,3,4,3,6,4]) Min([2,3,4,4,5,3,4,3,6,1]) console :: print = \"hola\", numero, 15, \"adios\" end; END PROGRAM";

            Scanner scanner = new Scanner(new BufferedReader(new StringReader(text)));
            Parser parser = new Parser(scanner);
            Arbol raiz = (Arbol) parser.parse().value;
            
            System.out.println(raiz);
            raiz.recorrer(raiz, Tabla);
            
            
            
        } catch (Exception e) {
          //  System.out.println(e);
          e.printStackTrace();
        }for(Simbolo s : Tabla){
                System.out.println("id: "+s.id + " Valor: "+s.contenido);
            }
    }
    
}
