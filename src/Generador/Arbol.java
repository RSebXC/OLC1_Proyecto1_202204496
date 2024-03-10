/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rodri
 */
public class Arbol {
 public ArrayList<Arbol> hijos;
 public String lexema;
 public Object resultado;

    public Arbol(String lexema) {
        this.lexema = lexema;
        hijos = new ArrayList();
    }
    
    public void añadirHijo(Arbol hijo){
        hijos.add(hijo);
    }
    
    
    public void recorrer(Arbol raiz, ArrayList<Simbolo> Tabla){
        
        for(Arbol a: raiz.hijos){
            
            recorrer(a, Tabla);
        }
        if(raiz.lexema == "expresion" && raiz.hijos.size() == 1){
            System.out.println("aqui nomas");
            raiz.resultado = raiz.hijos.get(0).lexema;
        }else if (raiz.lexema == "expresion" && raiz.hijos.size() == 5){
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
               raiz.resultado = mul;
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("DIV(")){
               Double div = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) / Double.parseDouble(raiz.hijos.get(3).resultado.toString());
               raiz.resultado = div;
            }else if(raiz.hijos.get(0).lexema.equalsIgnoreCase("MOD(")){
               Double mod = Double.parseDouble(raiz.hijos.get(1).resultado.toString()) % Double.parseDouble(raiz.hijos.get(3).resultado.toString());
               raiz.resultado = mod;
            } else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Media(")){
                System.out.println("mediaaaa");
                System.out.println(raiz.hijos.get(1).resultado.toString());
            }
                /*Object media = calcularMedia(raiz.hijos.get(1).resultado.toString());
                System.out.println("La media es: " + media);
    
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Medina(")){
                double resultadoMediana = calcularMediana(raiz.hijos.get(1));
                System.out.println("La mediana es: " + resultadoMediana);
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Moda(")){
                double resultadoModa = calcularModa(raiz.hijos.get(1));
                System.out.println("La moda es: " + resultadoModa);
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Varianza(")){
                double resultadoVarianza = calcularVarianza(raiz.hijos.get(1));
                System.out.println("La varianza es: " + resultadoVarianza);
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Max(")){
                
            }else if (raiz.hijos.get(0).lexema.equalsIgnoreCase("Min(")){
            
            }*/
            
        else if (raiz.lexema == "list_cod" && raiz.hijos.size() == 1){
            raiz.resultado = raiz.hijos.get(0).resultado;
        }
        else if (raiz.lexema == "list_expresion"){
            raiz.resultado = raiz.hijos.get(0).resultado;
        }else if (raiz.lexema == "list"){
            raiz.resultado = "";
        }
        else if (raiz.lexema=="list_var"){
            Simbolo nuevoS = new Simbolo(raiz.hijos.get(5).lexema,raiz.hijos.get(2).lexema,raiz.hijos.get(8).lexema);
            Tabla.add(nuevoS);
        }else if (raiz.lexema=="list_arreglos" && raiz.hijos.size() == 1){
            ArrayList<Object> list = new ArrayList();
            list.add(raiz.hijos.get(0).resultado);
                System.out.println("Un hijo " + list);
                raiz.resultado = list;
        }else if(raiz.lexema.equals("list_arreglos")&& raiz.hijos.size()==3){
            ArrayList<Object> list;
            list = (ArrayList<Object>)raiz.hijos.get(0).resultado;
            
            list.add(raiz.hijos.get(2).resultado);
                System.out.println("Tres Hijos " + list);
            raiz.resultado = list;
        }
            }
        
        
    }
    /*
    public static double calcularMedia(ArrayList<Object> lista) {
    double suma = 0;

    for (Object elemento : lista) {
        suma += (Double)elemento;
    }

    return suma / lista.size();
}


    public static double calcularMediana(double[] arreglo) {
    Arrays.sort(arreglo);
    int n = arreglo.length;
    if (n % 2 == 0) {
        return (arreglo[n / 2 - 1] + arreglo[n / 2]) / 2.0;
    } else {
        return arreglo[n / 2];
    }
}

    public static double calcularModa(double[] arreglo) {
    Map<Double, Integer> frecuencias = new HashMap<>();
    for (double elemento : arreglo) {
        frecuencias.put(elemento, frecuencias.getOrDefault(elemento, 0) + 1);
    }
    double moda = 0;
    int maxFrecuencia = 0;
    for (Map.Entry<Double, Integer> entry : frecuencias.entrySet()) {
        if (entry.getValue() > maxFrecuencia) {
            maxFrecuencia = entry.getValue();
            moda = entry.getKey();
        }
    }
    return moda;
}
    public static double calcularVarianza(double[] arreglo) {
    double media = calcularMedia(arreglo);
    double sumaDiferenciasCuadrado = 0;
    for (double elemento : arreglo) {
        sumaDiferenciasCuadrado += Math.pow(elemento - media, 2);
    }
    return sumaDiferenciasCuadrado / arreglo.length;
}

    
 public static double encontrarMaximo(double[] arreglo) {
    double maximo = Double.NEGATIVE_INFINITY;
    for (double elemento : arreglo) {
        maximo = Math.max(maximo, elemento);
    }
    return maximo;
}

public static double encontrarMinimo(double[] arreglo) {
    double minimo = Double.POSITIVE_INFINITY;
    for (double elemento : arreglo) {
        minimo = Math.min(minimo, elemento);
    }
    return minimo;
}


*/



}
