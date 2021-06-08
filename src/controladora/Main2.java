package controladora;

import vista.Menu;

public class Main2 {
    public static void main(String[] args) {
        String eleccion;
        do {//En funcion de la eleccion del usuario, controla que se debe hacer
            eleccion = Menu.menuInicial();
            switch (eleccion) {
                case "1" -> Gestora.peliculasEstrenadasPorAnio();//Mostrar la pelicula estrenada dicho año
                case "2" -> Gestora.costeTotalPeliculaPago();//Se quiere añadir un profesor fijo
                case "3" -> Gestora.peliculaMenorTasa();//Muestra la pelicula con la menor tasa de impacto
                case "4" -> Gestora.aniadirPelicula();//Se quiere añadir pelicula
                case "5" -> Gestora.borrarPelicula();//Borrar pelicula
            }
        } while (!eleccion.equals("6"));//6 es la opcion para salir del programa
    }
}
