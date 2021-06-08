package modelo;

import vista.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileDataAccess {

    public static File masterFile = new File(Menu.RUTA_MASTER);

    /**
     * <h1>addPersona()</h1>
     * <p>
     * Metodo publico que añade persona por persona a un fichero indicado<br>
     * <b>PreCondicion:</b> comprobar si el fichero existe <br>
     * <b>PostCondicion:</b> que el toString de persona sea legible para un String y se pueda pasar al fichero <br>
     * <b>Entrada:</b> Persona p, File file<br>
     * <b>Salida:</b> NONE <br>
     * </p>
     */
    public static void addPelicula(Pelicula pelicula, File file) {
        BufferedWriter bufferedWriter = null;
        FileWriter fileWriter = null;
        try {
            //Crea un buffer antes de escribir directamente en el archivo
            fileWriter = new FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            //Escribe el objeto en la primera linea
            bufferedWriter.write(pelicula.toString());
            //Le damos un salto de linea para que cuando se escriba el siguiente objeto no se escriba en la misma linea que el primero creado
            bufferedWriter.newLine();
            //bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Se usa para cerra el buffer y el fileWriter una vez abierto
            cerrarFlujos(bufferedWriter);
            cerrarFlujos(fileWriter);
        }
    }

    /**
     * <h1>readPelicula()</h1>
     * <p>
     * Metodo publico para obtener la lista de las personas del fichero <br>
     * <b>PreCondicion:</b> Tener un fichero existe <br>
     * <b>PostCondicion:</b>  Poder pasar el contenido del fichero a una lista <br>
     * <b>Entrada:</b> File <br>
     * <b>Salida:</b> ArrayList <br>
     * </p>
     */
    public static List<Pelicula> readPelicula(File file) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        List<Pelicula> listaPeliculas = new ArrayList<>();
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            String[] lineaArray;
            //Le damos una valor a la linea, este sera el que te de el bufferedReader
            while ((linea = bufferedReader.readLine()) != null) {
                //Luego este se hace un split y se pasa a un array
                lineaArray = linea.split(",");
                //Creamos una variable Pelicula null
                Pelicula p = null;
                //Si la primera posicion del array es "PeliculaPago"
                if (lineaArray[0].equals(PeliculaPago.class.getSimpleName())) {
                    //Aqui constrimos la pelicula de pago para meterla dentro de la Lista de peliculas
                    p = new PeliculaPago(lineaArray[1],
                            lineaArray[2],
                            Double.parseDouble(lineaArray[3]),
                            Double.parseDouble(lineaArray[4]),
                            Double.parseDouble(lineaArray[6]));
                } else {
                    //Aqui constrimos la pelicula para meterla dentro de la Lista de peliculas
                    p = new Pelicula(lineaArray[1],
                            lineaArray[2],
                            Double.parseDouble(lineaArray[3]),
                            Double.parseDouble(lineaArray[4]));
                }
                //La añadimos a una lista
                listaPeliculas.add(p);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            cerrarFlujos(bufferedReader);
            cerrarFlujos(fileReader);
        }
        return listaPeliculas;
    }

    /**
     * <h1>sortPeliculas()</h1>
     * <p>
     * <b>PreCondicion:</b> n/n <br>
     * <b>PostCondicion:</b> ordenar el fichero de peliculas segun el compareTo <br>
     * <b>Entrada:</b> n/n <br>
     * <b>Salida:</b> n/n <br>
     * </p>
     */
    public static void sortPelicula() {
        //Se llama al metodo readPelicula y se almacena el contenido en listaPelicula
        if (masterFile.exists()) {
            List<Pelicula> listaPelicula = readPelicula(masterFile);
            //Luego se borra el masterFile, para borrar el contenido actual y añadir el nuevo
            if (masterFile.delete()) {
                //Se crea el nuevo ya vacio
                masterFile = new File(Menu.RUTA_MASTER);
                //Se ordena de menor a mayor
                Collections.sort(listaPelicula);
                //Se añade al fichero con el metodo publico addPersona
                for (Pelicula p : listaPelicula) {
                    addPelicula(p, masterFile);
                }
            }
        }
    }

    /**
     * <h1>deletePelicula()</h1>
     */
    public static void deletePelicula(String nombre) {
        //Se llama al metodo readPelicula y se almacena el contenido en listaPelicula
        if (masterFile.exists()) {
            List<Pelicula> listaPelicula = readPelicula(masterFile);
            //Luego se borra el masterFile, para borrar el contenido actual y añadir el nuevo
            if (masterFile.delete()) {
                //Se crea el nuevo ya vacio
                masterFile = new File(Menu.RUTA_MASTER);
                //Se añade al fichero con el metodo publico addPersona
                for (Pelicula p : listaPelicula) {
                    if (!p.getNombre().equals(nombre)){
                        addPelicula(p, masterFile);
                    }
                }
            }
        }
    }

    /**
     * <h1>yearPelicula()</h1>
     *
     * @param anio
     * @return listaPeliculaAnio
     */
    public static List<Pelicula> yearPelicula(int anio) {
        List<Pelicula> listaPelicula = readPelicula(masterFile);
        List<Pelicula> listaPeliculaAnio = new ArrayList<>();
        for (Pelicula p : listaPelicula) {
            if (p.getFechaLanzamiento().getYear() == anio) {
                listaPeliculaAnio.add(p);
            }
        }
        return listaPeliculaAnio;
    }

    /**
     * <h1>totalCostPeliculaPago()</h1>
     *
     * @return double
     */
    public static double totalCostPeliculaPago() {
        double coste = 0.0;
        List<Pelicula> listaPelicula = readPelicula(masterFile);
        for (Pelicula p : listaPelicula) {
            if (p instanceof PeliculaPago) {
                coste += ((PeliculaPago) p).getCoste();
            }
        }
        return coste;
    }
//    public static double totalCostPeliculaPago() {
//        double coste = 0.0;
//        FileReader fileReader = null;
//        BufferedReader bufferedReader = null;
//        try {
//            fileReader = new FileReader(masterFile);
//            bufferedReader = new BufferedReader(fileReader);
//            String linea;
//            String[] lineaArray;
//            while ((linea = bufferedReader.readLine()) != null) {
//                //Luego este se hace un split y se pasa a un array
//                lineaArray = linea.split(",");
//                if (lineaArray[0].equals(PeliculaPago.class.getSimpleName())) {
//                    coste += Double.parseDouble(lineaArray[5]);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            cerrarFlujos(bufferedReader);
//            cerrarFlujos(fileReader);
//        }
//
//        return coste;
//    }

    /**
     * <h1>lessTasaImpacto()</h1>
     *
     * @return pelicula
     */
    public static Pelicula lessTasaImpacto(){
        List<Pelicula> listaPeliculas = readPelicula(masterFile);
        double tasa = listaPeliculas.get(0).getTasaImpacto();
        Pelicula pelicula = listaPeliculas.get(0);
        for (Pelicula p : listaPeliculas){
            if (tasa > p.getTasaImpacto()){
                tasa = p.getTasaImpacto();
            }
        }
        return pelicula;
    }

    /**
     * <h1>cerrarFlujo()</h1>
     * <p>
     * Metodo privado para cerrar t0d0 tipo de flujos para ahorrarnos escribir un <br>
     * try..catch en todos los finally. Primero comprueba si es distinto de null, si es asi <br>
     * cierra el flujo <br>
     * <b>PreCondicion:</b> n/n <br>
     * <b>PostCondicion:</b> se va a cerrar el flujo si no es null <br>
     * <b>Entrada:</b> AutoCloseable flujo <br>
     * <b>Salida:</b> n/n <br>
     * </p>
     */
    private static void cerrarFlujos(AutoCloseable flujo) {
        if (flujo != null) {
            try {
                flujo.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
