package vista;

import modelo.Pelicula;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static final String MENU_INICIAL = """
            [1]. Peliculas estrenadas por año.
            [2]. Total de costes de peliculas de pago.
            [3]. Pelicula con la menor tasa de impacto.
            [4]. Añadir pelicula.
            [5]. Borrar pelicula.
            [6]. EXIT.""";
    public static final String MENU_TIPO_PELICULA = """
            [1]. Gratis
            [2]. Pago""";
    public static final String ELIGE_OPCION = "Elige una opcion: ";
    public static final String MENU_NOMBRE = "Dime un nombre: ";
    public static final String MENU_NOTA_CRITICA = "Dime la nota de la pelicula: ";
    public static final String MENU_PRESUPUESTO = "Dime su presupuesto: ";
    public static final String MENSAJE_ERROR_FECHA = "ERROR! El formato de la fecha es dd-MM-yyyy.";
    public static final String MENSAJE_ERROR_NOMBRE = "ERROR! Ya existe una pelicula con ese nombre.";
    public static final String MENU_ANIO_PELICULA = "Dime una fecha: ";
    public static final String MENU_FECHA_CREACION = "Dime una fecha: ";
    public static final String MENU_COSTE_TOTAL = "El coste total asciende a: ";
    public static final String MENU_COSTE = "El coste de la pelicula es: ";
    public static final String MENU_PELICULA_MENOR_TASA = "La pelicula con la menor tasa es: ";

    public static final String RUTA_MASTER = ".//src//pelicula.txt";

    private static Scanner teclado = new Scanner(System.in);//TODO pasar el scaner por parametro para mejorar el acoplamiento

    public static String menuInicial() {
        System.out.println(MENU_INICIAL);//Mostrar informacion
        System.out.print(ELIGE_OPCION);
        return teclado.nextLine();//Obtener informacion
    }

    public static String menuTipoPelicula() {
        System.out.println(MENU_TIPO_PELICULA);//Mostrar informacion
        System.out.print(ELIGE_OPCION);
        return teclado.nextLine();//Obtener informacion
    }

    public static String obtenerNombre() {
        System.out.print(MENU_NOMBRE);
        return teclado.nextLine();//Obtener informacion
    }

    public static double obtenerNotaCritica() {
        System.out.print(MENU_NOTA_CRITICA);
        return Double.parseDouble(teclado.nextLine());//Obtener informacion
    }
    public static double obtenerPresupuesto() {
        System.out.print(MENU_PRESUPUESTO);
        return Double.parseDouble(teclado.nextLine());//Obtener informacion
    }

    public static double obtenerCoste() {
        System.out.print(MENU_COSTE);
        return Double.parseDouble(teclado.nextLine());//Obtener informacion
    }

    public static int obtenerAnioPelicula(){
        System.out.print(MENU_ANIO_PELICULA);
        return Integer.parseInt(teclado.nextLine());//Obtener informacion
    }

    public static void mensajeErrorNombre() {
        System.out.println(MENSAJE_ERROR_NOMBRE);
    }

    public static LocalDate obtenerFechaPelicula() {
        LocalDate fInicio;
        String fecha;
        do{
            System.out.print(MENU_FECHA_CREACION);
            fecha = teclado.nextLine();
            try {
                fInicio = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException tm){
                System.err.println(MENSAJE_ERROR_FECHA);
                fInicio = null;
            }
        }while (fInicio == null);
        return fInicio;
    }

    public static void obtenerCosteTotal(double coste){
        System.out.println(MENU_COSTE_TOTAL+coste);
    }

    public static void mostrarPeliculasAnio(List<Pelicula> yearPelicula) {
        for (Pelicula p : yearPelicula){
            System.out.println(p.toString());
        }
    }
    public static void mostrarPeliculas(List<Pelicula> listaPelicula) {
        for (Pelicula p : listaPelicula){
            System.out.println(p.toString());
        }
    }

    public static void mostrarPeliculaMenorTasa(Pelicula p){
        System.out.println(MENU_PELICULA_MENOR_TASA + p.getNombre());
    }
}
