package modelo;

import java.time.LocalDate;

public class Pelicula implements Comparable<Pelicula> {
    private String nombre;
    private LocalDate fechaLanzamiento;
    private double notaCritica;
    private double presupuesto;
    public static final double FACTOR = 2.0;

    public Pelicula(String nombre, String fechaLanzamiento, double notaCritica, double presupuesto) {
        this.nombre = nombre;
        this.fechaLanzamiento = putFecha(fechaLanzamiento);
        this.notaCritica = notaCritica;
        this.presupuesto = presupuesto;
    }

    public Pelicula(String nombre, LocalDate fechaLanzamiento, double notaCritica, double presupuesto) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.notaCritica = notaCritica;
        this.presupuesto = presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public double getNotaCritica() {
        return notaCritica;
    }

    public void setNotaCritica(double notaCritica) {
        this.notaCritica = notaCritica;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public double getValorEspecifico() {
        return getPresupuesto() * FACTOR;
    }

    public double getTasaImpacto() {
        return (getNotaCritica() * FACTOR + getValorEspecifico()) * FACTOR;
    }

    private LocalDate putFecha(String fecha) {
        //TODO VALIDAR
        String[] fechaNum = fecha.split("-");
        return LocalDate.of(Integer.parseInt(fechaNum[0]), Integer.parseInt(fechaNum[1]), Integer.parseInt(fechaNum[2]));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "," + nombre +
                "," + fechaLanzamiento +
                "," + notaCritica +
                "," + presupuesto +
                "," + getTasaImpacto();
    }

    //TODO TEST
    @Override
    public boolean equals(Object obj) {
        boolean esIgual = false;
        if (this == obj) {
            esIgual = true;
        } else if (obj instanceof Pelicula) {
            esIgual = this.nombre.equals(((Pelicula) obj).getNombre());
        }
        return esIgual;
    }

    /*
     * TODO Cuando el objeto es NULL o el nombre es NULL
     *  HAY QUE HACER ALGO PA QUE NO SALTE LA EXCEPTION
     *   TEST
     */
    @Override
    public int compareTo(Pelicula obj) {
        //Comparame los nombre en sentido descendente (Z - A)
        return obj.getNombre().compareTo(this.getNombre());
    }


    /*
     * Lo que vamos a hacer es ordenar de la z(122) al a(97) y lo ordene, da el caso posibe en que una pelicula empiece
     * por la misma letra, asi que recorrermos el nombre de la pelicula hasta que encontremos una diferencia y ordenarla
     *
     */
//    public int compareTo(Pelicula obj) {
//        int salida = 0;
//        boolean isExit = false;
//        do {
//            int i = 0;
//            if (this.nombre.charAt(i) > obj.getNombre().charAt(i)) {
//                salida = -1;
//                isExit = true;
//            } else if (this.nombre.charAt(i) < obj.getNombre().charAt(i)) {
//                salida = 1;
//                isExit = true;
//            } else if (this.nombre.charAt(0) == obj.getNombre().charAt(0)) {
//                i++;
//            }
//        } while (isExit);
//        return salida;
//    }
}
