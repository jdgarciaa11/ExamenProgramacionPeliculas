package controladora;

import modelo.FileDataAccess;
import modelo.Pelicula;
import modelo.PeliculaPago;
import vista.Menu;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Gestora {

    public static final File FICHERO_MASTER = new File(Menu.RUTA_MASTER);

    public static void peliculasEstrenadasPorAnio() {
        int anio = Menu.obtenerAnioPelicula();
        Menu.mostrarPeliculasAnio(FileDataAccess.yearPelicula(anio));
    }

    public static void costeTotalPeliculaPago() {
        double coste = FileDataAccess.totalCostPeliculaPago();
        Menu.obtenerCosteTotal(coste);
    }

    public static void peliculaMenorTasa() {
        Menu.mostrarPeliculaMenorTasa(FileDataAccess.lessTasaImpacto());
    }

    public static void aniadirPelicula() {
        boolean salir = false;
        String eleccion;
        do {//En funcion de la eleccion del usuario, controla que se debe hacer
            eleccion = Menu.menuTipoPelicula();
            switch (eleccion) {
                case "1" -> salir = pelicula();//Menu para añadir pelicula gratis
                case "2" -> salir = peliculaPago();//Menu para añadir pelicula gratis
            }
        } while (salir);//salir = true para del programa
    }

    private static boolean pelicula() {
        String nombre = Menu.obtenerNombre();
        LocalDate fechaLanzamiento = Menu.obtenerFechaPelicula();
        double notaCritica = Menu.obtenerNotaCritica();
        double presupuesto = Menu.obtenerPresupuesto();
        //Creo el objeto Pelicula
        Pelicula pelicula = new Pelicula(nombre, fechaLanzamiento, notaCritica, presupuesto);
        //Aqui compruebo si la pelicula es igual a otra ya creada, el criterio de igualdad es por el nombre
        if (!yaExiste(pelicula)) {
            FileDataAccess.addPelicula(pelicula, FICHERO_MASTER);
        } else {
            //Si la pelicula existe, nos salta un mensaje de error y llama de manera recursiva al metodo de nuevo
            Menu.mensajeErrorNombre();
            pelicula();
        }
        return true;
    }

    private static boolean peliculaPago() {
        String nombre = Menu.obtenerNombre();
        LocalDate fechaLanzamiento = Menu.obtenerFechaPelicula();
        double notaCritica = Menu.obtenerNotaCritica();
        double presupuesto = Menu.obtenerPresupuesto();
        double coste = Menu.obtenerCoste();
        //Creo el objeto Pelicula
        PeliculaPago peliculaPago = new PeliculaPago(nombre, fechaLanzamiento, notaCritica, presupuesto, coste);
        //Aqui compruebo si la pelicula es igual a otra ya creada, el criterio de igualdad es por el nombre
        if (!yaExiste(peliculaPago)) {
            FileDataAccess.addPelicula(peliculaPago, FICHERO_MASTER);
        } else {
            //Si la pelicula existe, nos salta un mensaje de error y llama de manera recursiva al metodo de nuevo
            Menu.mensajeErrorNombre();
            peliculaPago();
        }
        return true;
    }

    public static void borrarPelicula(){
        Menu.mostrarPeliculas(FileDataAccess.readPelicula(FICHERO_MASTER));
        String nombre = Menu.obtenerNombre();
        FileDataAccess.deletePelicula(nombre);
        //TODO validar si el nombre existe en la lista
    }

    private static boolean yaExiste(Object obj) {
        boolean existe = false;
        //Compruebo si el fichero master tiene contenido, si es asi entro
        if (FICHERO_MASTER.length() > 0) {
            //Vuelco el contenido del fichero master a lista pelicula
            List<Pelicula> listaPeliculas = FileDataAccess.readPelicula(FICHERO_MASTER);
            //Recorro lista pelicula hasta que existe sea true
            for (Pelicula p : listaPeliculas) {
                if (p.equals(obj)) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }
}
