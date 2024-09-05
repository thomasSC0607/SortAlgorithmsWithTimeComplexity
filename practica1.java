import java.io.*;
import java.util.*;

public class practica1 {

    // Método para leer las primeras 'n' palabras del archivo palabras.es
    public static List<String> leerArchivo(String archivoEntrada, int cantidadPalabras) throws IOException {
        List<String> palabras = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
        String linea;
        int contador = 0;

        // Leer hasta la cantidad deseada de palabras
        while ((linea = br.readLine()) != null && contador < cantidadPalabras) {
            palabras.add(linea.trim()); // Añadimos la palabra a la lista
            contador++;
        }
        br.close();
        return palabras;
    }

    // Método para escribir las palabras ordenadas en un nuevo archivo .txt
    public static void escribirArchivo(String archivoSalida, List<String> palabras) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida));
        for (String palabra : palabras) {
            bw.write(palabra);
            bw.newLine(); // Nueva línea para cada palabra
        }
        bw.close();
    }

    // Implementación de BubbleSort
    public static void bubbleSort(List<String> palabras) {
        int n = palabras.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (palabras.get(j).compareTo(palabras.get(j + 1)) > 0) {
                    // Intercambiar palabras[j] y palabras[j + 1]
                    Collections.swap(palabras, j, j + 1);
                }
            }
        }
    }

    // Implementación de BucketSort
    public static void bucketSort(List<String> palabras) {
        int n = palabras.size();
        if (n == 0) return;

        // Crear una lista de buckets (26 letras del alfabeto + 1 bucket especial)
        List<List<String>> buckets = new ArrayList<>(27); // 26 letras + 1 bucket extra
        for (int i = 0; i < 27; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribuir palabras en los buckets según su letra inicial
        for (String palabra : palabras) {
            char letraInicial = palabra.toLowerCase().charAt(0); // Convertir a minúsculas

            // Verificar si la letra inicial está entre 'a' y 'z'
            if (letraInicial >= 'a' && letraInicial <= 'z') {
                int indice = letraInicial - 'a'; // Índice para letras alfabéticas
                buckets.get(indice).add(palabra);
            } else {
                // Palabras que no empiezan con letras alfabéticas se agregan al último bucket
                buckets.get(26).add(palabra);
            }
        }

        // Ordenar cada bucket y recolectar resultados
        palabras.clear(); // Limpiar lista original
        for (List<String> bucket : buckets) {
            Collections.sort(bucket); // Ordenar cada bucket
            palabras.addAll(bucket);  // Añadir las palabras ordenadas al resultado final
        }
    }

    public static void main(String[] args) {
        int cantidadPalabras = 10000; // Cantidad de palabras que deseas organizar

        while(cantidadPalabras <= 247047 ){
            String archivoEntrada = "C:\\Users\\thoma\\Documents\\DatosAlgoritmos2\\src\\palabras.txt";
            String archivoSalidaBubble = "salida_bubble"+cantidadPalabras+"mil.txt";
            String archivoSalidaBucket = "salida_bucket"+cantidadPalabras+"mil.txt";

            try {
                // Leer las primeras 'n' palabras del archivo
                List<String> palabras = leerArchivo(archivoEntrada, cantidadPalabras);

                // Medir tiempo de ejecución para BubbleSort
                List<String> palabrasBubble = new ArrayList<>(palabras);
                long startTimeBubble = System.currentTimeMillis(); // Tiempo de inicio
                bubbleSort(palabrasBubble);
                long endTimeBubble = System.currentTimeMillis();   // Tiempo de fin
                long durationBubble = endTimeBubble - startTimeBubble; // Duración en milisegundos
                escribirArchivo(archivoSalidaBubble, palabrasBubble);

                // Medir tiempo de ejecución para BucketSort
//                List<String> palabrasBucket = new ArrayList<>(palabras);
//                long startTimeBucket = System.currentTimeMillis(); // Tiempo de inicio
//                bucketSort(palabrasBucket);
//                long endTimeBucket = System.currentTimeMillis();   // Tiempo de fin
//                long durationBucket = endTimeBucket - startTimeBucket; // Duración en milisegundos
//                escribirArchivo(archivoSalidaBucket, palabrasBucket);

                // Mostrar el tiempo de ejecución en la consola
                System.out.println("Tiempo de ejecución de BubbleSort: " + durationBubble + " ms, con cantidad de palabras: "+cantidadPalabras);
//                System.out.println("Tiempo de ejecución de BucketSort: " + durationBucket + " ms, con cantidad de palabras: "+cantidadPalabras);

                System.out.println("Archivos ordenados creados correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            cantidadPalabras += 10000;
        }

    }
}
