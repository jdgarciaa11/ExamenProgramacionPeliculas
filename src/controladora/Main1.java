package controladora;

import modelo.FileDataAccess;
import modelo.Pelicula;
import modelo.PeliculaPago;
import vista.Menu;

import java.io.File;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        Pelicula p1 = new Pelicula("Por el amor de dios", "2020-07-11",4.5,45676);
        PeliculaPago p2 = new PeliculaPago("Por ti", "2008-09-22",8.5,89.9,6.9);
        Pelicula p3 = new Pelicula("Ayyy Paquita", "2009-09-11",9.5,456076);
        PeliculaPago p4 = new PeliculaPago("Zorros sin cabeza", "2000-09-22",3.5,89111.9,0.9);
        File f = new File(Menu.RUTA_MASTER);
        FileDataAccess.addPelicula(p1,f);
        FileDataAccess.addPelicula(p2,f);
        FileDataAccess.addPelicula(p3,f);
        FileDataAccess.addPelicula(p4,f);

        List<Pelicula> listaPeliculas = FileDataAccess.readPelicula(f);

        System.out.println("Lista Desordenada");
        for (Pelicula p : listaPeliculas){
            System.out.println(p);
        }

        FileDataAccess.sortPelicula();

        listaPeliculas = FileDataAccess.readPelicula(f);
        System.out.println();
        System.out.println("Lista Ordenada");
        for (Pelicula p : listaPeliculas){
            System.out.println(p);
        }
    }



}
