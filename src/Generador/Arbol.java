/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rodri
 */
public class Arbol {
 public ArrayList<Arbol> hijos;
 public String lexema;
 public Object resultado;

    public ArrayList<Arbol> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Arbol> hijos) {
        this.hijos = hijos;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
 
 
    public Arbol(String lexema) {
        this.lexema = lexema;
        hijos = new ArrayList();
    }
    
    public void añadirHijo(Arbol hijo){
        hijos.add(hijo);
    }
    
    
    public void recorrer(Arbol raiz, ArrayList<Simbolo> Tabla){
        System.out.println(raiz.lexema + "raiz");
        for(Arbol a: raiz.hijos){
           //System.out.println("For: "+raiz.hijos.get(0).lexema + "D: " + raiz.lexema);
            recorrer(a, Tabla);
        }
        //System.out.println(raiz.hijos.get(0).lexema);
        //System.out.println("Entrando al if");
        if("expresion".equals(raiz.lexema) && raiz.hijos.size() == 1){
            
            raiz.resultado = raiz.hijos.get(0).lexema;
        }else if (raiz.lexema == "expresion" ){
            if(raiz.hijos.get(0).lexema.equalsIgnoreCase("SUM(")){
                System.out.println("LLegamos");
               Double res = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) + Double.parseDouble(raiz.hijos.get(3).resultado.toString());
                System.out.println(res);
               raiz.resultado = res;
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("RES(")){
                System.out.println("LLegamos2");
               Double res = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) - Double.parseDouble(raiz.hijos.get(3).resultado.toString());
                System.out.println(res);
               raiz.resultado = res;
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("MUL(")){
               Double mul = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) * Double.parseDouble(raiz.hijos.get(3).resultado.toString());
               System.out.println(mul);
               raiz.resultado = mul;
               
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("DIV(")){
               Double div = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) / Double.parseDouble(raiz.hijos.get(3).resultado.toString());
               System.out.println(div);
               raiz.resultado = div;
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("MOD(")){
               Double mod = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) % Double.parseDouble(raiz.hijos.get(3).resultado.toString());
               System.out.println(mod);
               raiz.resultado = mod;
            } else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Media(") ) {
                System.out.println("Calculando Media");
                System.out.println("HOLA" + raiz.hijos.get(1).resultado);
                Arbol listaValoresArbol = raiz.hijos.get(1);
                System.out.println(listaValoresArbol.resultado);
                
                ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;
                    
                double suma = 0;

                for (Object elemento : listaValores) {
                        suma += Double.parseDouble(elemento.toString());
                    }

                    double media = suma / listaValores.size();
                    System.out.println("La media es: " + media);

                    raiz.resultado = media;
                
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Mediana(")) {
    System.out.println("Calculando Mediana");
    Arbol listaValoresArbol = raiz.hijos.get(1);
    System.out.println(listaValoresArbol.resultado);

    ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;

    // Implementar algoritmo de ordenación (puede ser burbuja u otro)
    for (int i = 0; i < listaValores.size() - 1; i++) {
        for (int j = 0; j < listaValores.size() - 1 - i; j++) {
            double valorActual = Double.parseDouble(listaValores.get(j).toString());
            double siguienteValor = Double.parseDouble(listaValores.get(j + 1).toString());

            if (valorActual > siguienteValor) {
                // Intercambiar los elementos si están en el orden incorrecto
                Object temp = listaValores.get(j);
                listaValores.set(j, listaValores.get(j + 1));
                listaValores.set(j + 1, temp);
            }
        }
    }

    double mediana;
    int n = listaValores.size();

    // Calcular la mediana
    if (n % 2 == 0) {
        // Si el tamaño de la lista es par
        double valor1 = Double.parseDouble(listaValores.get(n / 2 - 1).toString());
        double valor2 = Double.parseDouble(listaValores.get(n / 2).toString());
        mediana = (valor1 + valor2) / 2;
    } else {
        // Si el tamaño de la lista es impar
        mediana = Double.parseDouble(listaValores.get(n / 2).toString());
    }

    System.out.println("La mediana es: " + mediana);

    raiz.resultado = mediana;
}else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Moda(")) {
    System.out.println("Calculando Moda");
    Arbol listaValoresArbol = raiz.hijos.get(1);
    System.out.println(listaValoresArbol.resultado);

    ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;

    // Contador de frecuencias para cada valor
    Map<Object, Integer> frecuencias = new HashMap<>();

    // Calcular frecuencias
    for (Object elemento : listaValores) {
        int frecuencia = frecuencias.getOrDefault(elemento, 0);
        frecuencias.put(elemento, frecuencia + 1);
    }

    // Encontrar el valor (o valores) con la mayor frecuencia
    List<Object> moda = new ArrayList<>();
    int maxFrecuencia = 0;

    for (Map.Entry<Object, Integer> entry : frecuencias.entrySet()) {
        int frecuenciaActual = entry.getValue();

        if (frecuenciaActual > maxFrecuencia) {
            moda.clear();
            moda.add(entry.getKey());
            maxFrecuencia = frecuenciaActual;
        } else if (frecuenciaActual == maxFrecuencia) {
            moda.add(entry.getKey());
        }
    }

    if (!moda.isEmpty()) {
        System.out.println("La moda es: " + moda);
        raiz.resultado = moda;
    } else {
        System.out.println("No hay moda");
    }
}else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Varianza(")) {
    System.out.println("Calculando Varianza");
    Arbol listaValoresArbol = raiz.hijos.get(1);
    System.out.println(listaValoresArbol.resultado);

    ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;

    // Calcular la media
    double suma = 0;

    for (Object elemento : listaValores) {
        suma += Double.parseDouble(elemento.toString());
    }

    double media = suma / listaValores.size();

    // Calcular la varianza
    double sumaDiferenciasCuadradas = 0;

    for (Object elemento : listaValores) {
        double diferencia = Double.parseDouble(elemento.toString()) - media;
        sumaDiferenciasCuadradas += Math.pow(diferencia, 2);
    }

    double varianza = sumaDiferenciasCuadradas / (listaValores.size() - 1);

    System.out.println("La varianza es: " + varianza);

    raiz.resultado = varianza;
}else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Max(")) {
    System.out.println("Calculando Max");
    Arbol listaValoresArbol = raiz.hijos.get(1);
    System.out.println(listaValoresArbol.resultado);

    ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;

    if (!listaValores.isEmpty()) {
        // Encontrar el máximo
        Object maximo = listaValores.get(0);

        for (Object elemento : listaValores) {
            if (Double.parseDouble(elemento.toString()) > Double.parseDouble(maximo.toString())) {
                maximo = elemento;
            }
        }

        System.out.println("El máximo es: " + maximo);
        raiz.resultado = maximo;
    } else {
        System.out.println("La lista está vacía, no hay máximo");
    }
}else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Min(")) {
    System.out.println("Calculando Min");
    Arbol listaValoresArbol = raiz.hijos.get(1);
    System.out.println(listaValoresArbol.resultado);

    ArrayList<Object> listaValores = (ArrayList<Object>) listaValoresArbol.hijos.get(1).resultado;

    if (!listaValores.isEmpty()) {
        // Encontrar el mínimo
        Object minimo = listaValores.get(0);

        for (Object elemento : listaValores) {
            if (Double.parseDouble(elemento.toString()) < Double.parseDouble(minimo.toString())) {
                minimo = elemento;
            }
        }

        System.out.println("El mínimo es: " + minimo);
        raiz.resultado = minimo;
    } else {
        System.out.println("La lista está vacía, no hay mínimo");
    }
}
            
        
        }
        else if ("grafica".equalsIgnoreCase(raiz.lexema)){
                System.out.println("Estamos en if grafica");
            ArrayList<Object> d = (ArrayList<Object>) raiz.hijos.get(2).resultado;
            Grafica sentens;
            String titulo = "Llanta";
            String X = "";
            String Y = "";
            Object ejex = 0;
            Object ejey = 0;
            
            for(Object o:d){
                System.out.println("estamos for grafica");
                sentens = (Grafica)o;
                System.out.println(sentens.nombre);
                System.out.println(sentens.contenido);
                if(sentens.nombre.equalsIgnoreCase("titulo")){
                    
                    System.out.println(sentens.contenido);
                    System.out.println("si hay titulo");
                    titulo = (String) (sentens.contenido);
                }else if (sentens.nombre.equalsIgnoreCase("tituloX")){
                    System.out.println("si hat tX");
                    X = (String) (sentens.contenido);
                }else if (sentens.nombre.equalsIgnoreCase("tituloY")){
                    System.out.println("si hay ty");
                    Y = (String) (sentens.contenido);
                }else if (sentens.nombre.equalsIgnoreCase("ejeX")){
                    System.out.println("tenemos ejex");
                    System.out.println("ejeX" + sentens.contenido);
                    ejex = sentens.contenido;
                }else if (sentens.nombre.equalsIgnoreCase("ejeY")){
                    System.out.println("tenemos ejey");
                    ejey = sentens.contenido;
                    System.out.println("ejeY" + sentens.contenido);
                }
            }
            System.out.println("Titulo: " + titulo);
            System.out.println("X: " + X);
            System.out.println("Y: " + Y);
            System.out.println("Eje X: " + ejex);
            System.out.println("Eje Y: " + ejey);
            String tit = "Grafica de Barras.png";
            Globales.generarGraficaDeBarras(ejex, ejey, titulo, X, Y, tit);
            
            
        }
            
        
        else if (raiz.lexema == "tipo_sentencia" && raiz.hijos.size() == 2){
            System.out.println("tipo sentensiaaaaaa");
            ArrayList<Object> d = (ArrayList<Object>) raiz.hijos.get(0).resultado;
            System.out.println(d);
            d.add(raiz.hijos.get(1).resultado);
            raiz.resultado = d;
        }
        else if (raiz.lexema == "tipo_sentencia" && raiz.hijos.size() == 1){
            System.out.println("tipo sentenciaaa");
            ArrayList<Object> d = new ArrayList();
            System.out.println(d);
            d.add(raiz.hijos.get(0).resultado);
            raiz.resultado = d;
        }
        else if (raiz.lexema == "sentencia1" && raiz.hijos.size() == 8){
            System.out.println("estamos antes del metodo de grafica");
            System.out.println("hijo 0 " + raiz.hijos.get(0).lexema + " hijo 1 " + raiz.hijos.get(3).lexema + " hijo 2 " + raiz.hijos.get(5).resultado);
            Grafica sentens = new Grafica(raiz.hijos.get(0).lexema, raiz.hijos.get(3).lexema, raiz.hijos.get(5).lexema);
            raiz.resultado = sentens;
        }
        else if (raiz.lexema == "sentencia2" && raiz.hijos.size() == 8){
            System.out.println("estamos antes del metodo de grafica");
            System.out.println("hijo 0 " + raiz.hijos.get(0).lexema + " hijo 1 " + raiz.hijos.get(3).lexema + " hijo 2 " + raiz.hijos.get(5).resultado);
            Grafica sentens = new Grafica(raiz.hijos.get(0).lexema, raiz.hijos.get(3).lexema, raiz.hijos.get(5).resultado);
            raiz.resultado = sentens;
        }
        else if ("list_cod".equals(raiz.lexema) && raiz.hijos.size() == 1){
            raiz.resultado = raiz.hijos.get(0).resultado;
        }
        else if ("list_expresion".equals(raiz.lexema)){
            raiz.resultado = raiz.hijos.get(0).resultado;
        }else if ("list".equals(raiz.lexema)){
            raiz.resultado = "";
        }
        else if ("list_var".equals(raiz.lexema)){
            
            Simbolo nuevoS = new Simbolo(raiz.hijos.get(5).lexema,raiz.hijos.get(2).lexema,raiz.hijos.get(8).resultado);
            Globales.Variables.put(raiz.hijos.get(5).lexema, raiz.hijos.get(8).resultado);
            Tabla.add(nuevoS);
            
        
        }else if(raiz.lexema.equals("list_arreglos")){
            
        }else if ("Tipo_arreglo".equals(raiz.lexema) && raiz.hijos.size()== 3){
            System.out.println("HHH" + raiz.hijos.get(1).resultado);
            raiz.resultado = raiz.hijos.get(1).resultado;
                
        }else if ("Tipo_arreglo".equals(raiz.lexema)){
            raiz.resultado = raiz.hijos.get(1).resultado;
        }else if ("list_varA".equals(raiz.lexema) && raiz.hijos.size()==1){
            ArrayList<Object> list = new ArrayList();
            list.add(raiz.hijos.get(0).resultado);
            raiz.resultado = list;
        }else if ("list_varA".equals(raiz.lexema) && raiz.hijos.size()==3){
            ArrayList<Object> list;
            list = (ArrayList<Object>)raiz.hijos.get(0).resultado;
            System.out.println(raiz.hijos.size() + " hijos");
            list.add(raiz.hijos.get(2).resultado);
                System.out.println("Tres Hijos " + list);
            raiz.resultado = list;
        }
        
        
        
        
        
        
    }
    
    

}
