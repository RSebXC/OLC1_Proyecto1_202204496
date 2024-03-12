### Manual de Usuario

#### Interfaz Gráfica

La interfaz gráfica del programa está diseñada para facilitar la interacción con el compilador Cup y Flex. Aquí se describen los elementos de la interfaz:

**Botones:**
- **Archivo:** Gestiona archivos, como abrir y guardar archivos de código fuente.
- **Pestaña:** Organiza el espacio de trabajo mediante la creación y gestión de pestañas para diferentes archivos de código.
- **Ejecutar:** Inicia la compilación y ejecución del código fuente proporcionado.
- **Reportes:** Facilita la visualización de informes y resultados generados durante el proceso de compilación.

**Áreas de Texto:**
- **Área de Entrada de Código:** Permite al usuario ingresar el código fuente escrito en el lenguaje especificado (Cup y Flex).
- **Área de Consola:** Proporciona una salida interactiva para mensajes de estado, errores y resultados generados durante la compilación y ejecución del código.

**Label para Gráficas:**
- Muestra gráficas e informes visuales generados durante el proceso de compilación.

#### Clase Principal (Main)

La clase principal, `OC1_Proyecto1`, contiene la función `main`, siendo el punto de entrada del programa. A continuación, se describen los elementos clave de esta clase:

- **Importaciones:** Se importan las clases y paquetes necesarios, incluyendo `Arbol`, `BufferedReader`, `StringReader`, `Scanner`, `Parser`, `Simbolo`, y `ArrayList`.
- **Lista de Símbolos (Tabla):** Se declara una lista (`ArrayList`) llamada `Tabla` para almacenar los símbolos.
- **Bloque try:** Se manejan posibles excepciones durante la ejecución del código.
- **Definición del Programa de Ejemplo (text):** Se define un programa de ejemplo en un lenguaje específico.
- **Creación del Scanner y del Parser:** Se crean objetos `Scanner` y `Parser` para el análisis del texto proporcionado.
- **Obtención de la Raíz del Árbol Sintáctico Abstracto (AST):** Se obtiene la raíz del AST al llamar al método `parse` del `Parser`.
- **Impresión de la Raíz del AST:** Se imprime la representación en cadena de la raíz del AST en la consola.
- **Llamada a la Función `recorrer`:** Se invoca la función `recorrer` en la raíz del árbol para la generación de la tabla de símbolos `Tabla`.
- **Manejo de Excepciones (`catch`):** En caso de excepciones, se imprime la traza de la excepción.
- **Impresión de la Tabla de Símbolos (`Tabla`):** Se imprime en la consola la información almacenada en la tabla de símbolos.

#### Flex (Lexer)

El código proporcionado es un archivo Flex (Lexer) que analiza el texto de entrada y genera tokens. A continuación, se destacan aspectos importantes:

- **%class Scanner:** Indica que la clase generada por JFlex se llamará `Scanner`.
- **%public:** Permite el acceso a la clase desde otros paquetes.
- **%line, %char:** Habilitan el seguimiento de líneas y caracteres de entrada.
- **%cup, %unicode, %ignorecase:** Configuraciones para integración con Cup, reconocimiento de caracteres Unicode, e ignorar mayúsculas/minúsculas.

- **Reglas de Expresiones Regulares:** Definen reglas para reconocer y generar tokens para palabras clave, enteros, y tipo de datos `char[]`.

- **Manejo de Errores Léxicos:** Captura caracteres no reconocidos y lanza excepciones de error léxico.

#### Cup (Parser)

- **Terminales y No Terminales:** Definen símbolos terminales (tokens) y no terminales.
- **Precedencias y Asociatividades:** Especifican las precedencias y asociatividades para operadores aritméticos.
- **Producciones:** Definen reglas de producción para la gramática del lenguaje.

- **Producciones para las Sentencias Gráficas:** Especifican cómo deben combinarse no terminales y terminales para formar estructuras más grandes.

#### Clase Árbol

La clase `Arbol` representa un árbol y contiene funciones de análisis. Algunos aspectos clave:

- **Atributos:**
  - `hijos:` ArrayList para almacenar nodos hijos del árbol.
  - `lexema:` Cadena que representa el valor o etiqueta del nodo.
  - `resultado:` Objeto que almacena el resultado calculado durante el análisis semántico.

- **Constructores y Métodos:**
  - `public Arbol(String lexema):` Inicializa el árbol con un valor de lexema y crea un ArrayList para los hijos.
  - `public void añadirHijo(Arbol hijo):` Agrega un nodo hijo al árbol.
  - `public void recorrer(Arbol raiz, ArrayList<Simbolo> Tabla):` Realiza un recorrido recursivo del árbol, calculando resultados específicos y almacenándolos en el atributo `resultado`.

- **Lógica de Recorrido y Cálculos:** En el método `recorrer`, se verifica el tipo de expresión y se realizan cálculos específicos. Se utiliza una clase llamada `Globales` para almacenar variables globales y operar con ellas.

- **Gestión de Tabla de Símbolos:** Se utiliza un `ArrayList` llamado `Tabla` para almacenar símbolos (objetos de tipo `Simbolo`). Durante el análisis semántico, se agregan nuevos símbolos a esta tabla.

#### Clase Globales

- **Atributos estáticos:**
  - `Variables:` HashMap que almacena variables globales (clave: nombre, valor: contenido).
  - `tokens:` ArrayList que almacena tokens reconocidos durante el análisis léxico.
  - `error:` ArrayList que almacena tokens que representan errores léxicos.

- **Métodos estáticos:**
  - `imprimirHashMap():` Muestra en la consola el contenido del HashMap de variables.
  - `guardarVariables(String clave, Object valor):` Agrega una variable al HashMap de variables globales.
  - `generarHTMLTokens(ArrayList<Token> tokens, String archivoSalida):` Genera un archivo HTML que contiene una tabla de tokens.
  - `generarHTMLErrores(ArrayList<Token> errores, String archivoSalida):` Genera un archivo HTML que contiene una tabla de errores.

- **Método privado:**
  - `

escribirTabla(ArrayList<Token> lista, BufferedWriter writer, String titulo):` Escribe una tabla HTML (lexema, token, fila, columna) basada en una lista de tokens o errores. Este método es utilizado por los métodos de generación de HTML.

- **Uso de la Clase Token:** La clase `Token` se utiliza para representar información sobre tokens, incluyendo lexema, token, fila y columna.

- **Generación de HTML:** Los métodos `generarHTMLTokens` y `generarHTMLErrores` se encargan de escribir archivos HTML que contienen tablas de tokens y errores, respectivamente.

- **Estructura básica del HTML:** En los métodos de generación de HTML, se escribe una estructura básica de HTML que incluye las etiquetas `<html>`, `<head>`, `<title>`, `<body>`, etc.