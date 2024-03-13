/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

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
    
    public static void generarHTMLTokens( String archivoSalida) {
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
    
    public static void generarHTMLErrores( String archivoSalida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            // Escribir la estructura básica del HTML
            writer.write("<html>\n<head>\n<title>Tabla de Errores</title>\n</head>\n<body>\n");

            // Escribir la tabla de errores
            escribirTabla(error, writer, "Errores");

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
    
    public static void generarGraficaDeBarras(Object labelsX, Object datosY, String tituloGrafica,
                                             String tituloEjeX, String tituloEjeY, String nombreArchivo) {
        System.out.println("entramos a graficar");
        ArrayList<String> arrayListEtiquetas = convertirAArrayList(labelsX);
        ArrayList<Double> arrayLis = convertir(datosY);

        // Imprime el ArrayList resultante
        System.out.println("ArrayList de Etiquetas: " + arrayListEtiquetas);

        // Convierte el ArrayList a un arreglo primitivo String[]
        String[] arregloEtiquetas = convertirAStringArray(arrayListEtiquetas);
        Double[] arreglonum = convertir(arrayLis);
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Agregar datos al conjunto de datos
        for (int i = 0; i < arregloEtiquetas.length; i++) {
            dataset.addValue(arreglonum[i], "Datos", arregloEtiquetas[i]);
        }
        

        // Crear la gráfica
        JFreeChart chart = ChartFactory.createBarChart(
                tituloGrafica,
                tituloEjeX,
                tituloEjeY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Guardar la gráfica como una imagen PNG
        try {
            ChartUtilities.saveChartAsPNG(new File(nombreArchivo + ".png"), chart, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static ArrayList<String> convertirAArrayList(Object objectLabel) {
        ArrayList<String> arrayListEtiquetas = new ArrayList<>();

        // Verifica que el objeto de etiqueta no sea nulo
        if (objectLabel != null) {
            // Convierte el objeto a String y agrégalo al ArrayList
            arrayListEtiquetas.add(objectLabel.toString());
        }

        return arrayListEtiquetas;
    }
    
    private static String[] convertirAStringArray(ArrayList<String> arrayList) {
        // Convierte el ArrayList a un arreglo primitivo String[]
        String[] stringArray = new String[arrayList.size()];
        return arrayList.toArray(stringArray);
    }
    
    private static ArrayList<Double> convertir(Object objectLabel) {
        ArrayList<Double> arrayListEtiquetas = new ArrayList<>();
            System.out.println(objectLabel);
        // Verifica que el objeto de etiqueta no sea nulo
        if (objectLabel != null) {
            // Convierte el objeto a String y agrégalo al ArrayList
            arrayListEtiquetas.add(Double.valueOf(objectLabel.toString()));
        }

        return arrayListEtiquetas;
    }
    
    private static Double[] convertir(ArrayList<Double> arrayList) {
        // Convierte el ArrayList a un arreglo primitivo String[]
        Double[] stringArray = new Double[arrayList.size()];
        return arrayList.toArray(stringArray);
    }
}

