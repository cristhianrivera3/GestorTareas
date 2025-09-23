import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    // Método para imprimir títulos con formato
    public static void printHeader(String titulo) {
        System.out.println("\n==============================");
        System.out.println(" " + titulo.toUpperCase());
        System.out.println("==============================\n");
    }

    // Método para obtener la fecha actual en formato legible
    public static String getFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdf.format(new Date());
    }

    // Método para truncar un texto largo
    public static String truncarTexto(String texto, int maxLength) {
        if (texto.length() <= maxLength) {
            return texto;
        }
        return texto.substring(0, maxLength - 3) + "...";
    }

    // Método para limpiar la consola (truco básico)
    public static void limpiarConsola() throws InterruptedException {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException e) {
            System.out.println("\n[!] No se pudo limpiar la consola.");
        }
    }
}
