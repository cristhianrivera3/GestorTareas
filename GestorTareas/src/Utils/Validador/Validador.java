package Utils.Validador;

public class Validador {

    public static boolean esEnteroPositivo(int numero) {
        return numero >= 0;
    }

    public static boolean precioValido(double precio) {
        return precio >= 0;
    }

    public static boolean textoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean emailValido(String email) {
        return email != null && email.contains("@") && email.indexOf('.') > email.indexOf('@') + 1;
    }
}
