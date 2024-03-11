/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rodri
 */
public class Globales {
    
    public static HashMap<String,Object> Variables = new HashMap<>();
    public static ArrayList<Token> tokens = new ArrayList<>();
    public static ArrayList<Token> error = new ArrayList<>();
      public static void imprimirHashMap() {
        for (Map.Entry<String, Object> entry : Variables.entrySet()) {
            String clave = entry.getKey();
            Object valor = entry.getValue();
            System.out.println("Clave: " + clave + ", Valor: " + valor);
        }
    }
      
    public static void guardarVariables(String clave, Object valor) {
        Variables.put(clave, valor);
    }
    
    public static void generarHTMLTokens(ArrayList<Token> tokens, String archivoSalida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            // Escribir la estructura básica del HTML
            writer.write("<html>\n<head>\n<title>Tabla de Tokens</title>\n</head>\n<body>\n");

            // Escribir la tabla de tokens
            escribirTabla(tokens, writer, "Tokens");

            // Cerrar la etiqueta body y html
            writer.write("</body>\n</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void generarHTMLErrores(ArrayList<Token> errores, String archivoSalida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            // Escribir la estructura básica del HTML
            writer.write("<html>\n<head>\n<title>Tabla de Errores</title>\n</head>\n<body>\n");

            // Escribir la tabla de errores
            escribirTabla(errores, writer, "Errores");

            // Cerrar la etiqueta body y html
            writer.write("</body>\n</html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void escribirTabla(ArrayList<Token> lista, BufferedWriter writer, String titulo) throws IOException {
        // Escribir el título de la tabla
        writer.write("<h2>" + titulo + "</h2>\n");

        // Comprobar si la lista no está vacía
        if (!lista.isEmpty()) {
            // Iniciar la tabla
            writer.write("<table border=\"1\">\n<tr><th>Lexema</th><th>Token</th><th>Fila</th><th>Columna</th></tr>\n");

            // Escribir cada elemento de la lista como una fila de la tabla
            for (Token token : lista) {
                writer.write("<tr><td>" + token.getLexema() + "</td><td>" + token.getToken() + "</td><td>"
                        + token.getFila() + "</td><td>" + token.getColumna() + "</td></tr>\n");
            }

            // Cerrar la etiqueta de la tabla
            writer.write("</table>\n");
        } else {
            // Si la lista está vacía, escribir un mensaje indicando que no hay elementos
            writer.write("<p>No hay " + titulo.toLowerCase() + " para mostrar.</p>\n");
        }
    }
    
}

