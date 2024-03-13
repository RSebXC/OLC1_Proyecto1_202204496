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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
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
    System.out.println("Entramos a graficar");
    System.out.println("Texto X: " + labelsX);
    System.out.println("NUmero Y: " + datosY);
    System.out.println(tituloGrafica + " " + tituloEjeX + " " + tituloEjeY);
    
    // Convertir etiquetas a ArrayList<String>
    ArrayList<String> arrayListEtiquetas = convertirAArrayList(labelsX);

    // Convertir datos a ArrayList<Double>
    ArrayList<Double> arrayLis = convertirADoubleArrayList(datosY);
        System.out.println(arrayLis);
    // Imprime el ArrayList resultante
    System.out.println("ArrayList de Etiquetas: " + arrayListEtiquetas);

    // Convierte el ArrayList a un arreglo primitivo String[]
    String[] arregloEtiquetas = arrayListEtiquetas.toArray(new String[0]);
    System.out.println("Arreglo de Etiquetas: " + Arrays.toString(arregloEtiquetas));

    // Convierte el ArrayList a un arreglo primitivo Double[]
    Double[] arreglonum = arrayLis.toArray(new Double[0]);
    System.out.println("Arreglo de Números: " + Arrays.toString(arreglonum));

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
int length = Math.min(arregloEtiquetas.length, arreglonum.length);

for (int i = 0; i < length; i++) {
    // Manejar cada etiqueta del arreglo por separado
    String etiqueta = arregloEtiquetas[i];

    // Asegurar que el índice sea válido antes de acceder al arreglo de números
    if (i < arreglonum.length) {
        double valor = arreglonum[i];
        dataset.addValue(valor, "Datos", etiqueta);
        System.out.println("Agregando al conjunto de datos: " + valor + " para " + etiqueta);
    }
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

    // Obtener el objeto del eje Y de la trama
    NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
    yAxis.setRange(0, 100); 

    // Guardar la gráfica como una imagen PNG
    try {
        ChartUtilities.saveChartAsPNG(new File(nombreArchivo + ".png"), chart, 600, 400);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private static ArrayList<String> convertirAArrayList(Object objectLabel) {
    ArrayList<String> arrayListEtiquetas = new ArrayList<>();

    // Verifica que el objeto de etiqueta no sea nulo y es un arreglo o una lista
    if (objectLabel != null) {
        if (objectLabel.getClass().isArray()) {
            int length = java.lang.reflect.Array.getLength(objectLabel);

            // Itera sobre los elementos del arreglo y conviértelos a String
            for (int i = 0; i < length; i++) {
                Object elemento = java.lang.reflect.Array.get(objectLabel, i);

                // Trata cada elemento interno como una etiqueta
                if (elemento != null && elemento instanceof List) {
                    List<?> innerList = (List<?>) elemento;

                    // Itera sobre los elementos internos y agrégales al ArrayList
                    for (Object innerElement : innerList) {
                        arrayListEtiquetas.add(innerElement.toString());
                    }
                } else {
                    // Si no es una lista, simplemente convierte el elemento a String y agrégalo al ArrayList
                    arrayListEtiquetas.add(elemento.toString());
                }
            }
        } else if (objectLabel instanceof List) {
            // Si el objeto es una lista, trata cada elemento interno como una etiqueta
            List<?> innerList = (List<?>) objectLabel;

            // Itera sobre los elementos internos y agrégales al ArrayList
            for (Object innerElement : innerList) {
                arrayListEtiquetas.add(innerElement.toString());
            }
        } else {
            // Si no es un arreglo ni una lista, simplemente convierte el objeto a String y agrégalo al ArrayList
            arrayListEtiquetas.add(objectLabel.toString());
        }
    }

    return arrayListEtiquetas;

}


    
    private static ArrayList<Double> convertirADoubleArrayList(Object datosY) {
    ArrayList<Double> arrayLis = new ArrayList<>();

    // Verifica que el objeto de datos no sea nulo y es una instancia de ArrayList
    if (datosY != null && datosY instanceof ArrayList) {
        try {
            // Extrae los números del ArrayList y los agrega al ArrayList<Double>
            ArrayList<Object> datos = (ArrayList<Object>) datosY;
            for (Object dato : datos) {
                if (dato instanceof Number) {
                    // Si el elemento es un número, agrégalo directamente
                    arrayLis.add(((Number) dato).doubleValue());
                } else if (dato instanceof String) {
                    // Intenta convertir el elemento como cadena a número
                    try {
                        arrayLis.add(Double.parseDouble((String) dato));
                    } catch (NumberFormatException e) {
                        System.out.println("Error: No se pudo convertir la cadena a número");
                    }
                } else {
                    System.out.println("Error: El elemento no es un número");
                }
            }
        } catch (ClassCastException e) {
            System.out.println("Error: Los datos no son un ArrayList");
        }
    }

    return arrayLis;
}



}
    
   
