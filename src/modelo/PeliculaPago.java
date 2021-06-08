package modelo;

import java.time.LocalDate;

public class PeliculaPago extends Pelicula {
    private double coste;
    public static final double FACTOR = 3.0;

    public PeliculaPago(String nombre, String fechaLanzamiento, double notaCritica, double presupuesto, double coste) {
        super(nombre, fechaLanzamiento, notaCritica, presupuesto);
        this.coste = coste;
    }

    public PeliculaPago(String nombre, LocalDate fechaLanzamiento, double notaCritica, double presupuesto, double coste) {
        super(nombre, fechaLanzamiento, notaCritica, presupuesto);
        this.coste = coste;
    }

    public double getCoste() {
        return coste;
    }

    @Override
    public double getValorEspecifico(){
        return getCoste() * FACTOR;
    }

    @Override
    public String toString() {
        return super.toString() +
                "," + coste;
    }
    //Contarlo por la longitud, si el tamaño es 6 es de pago, si es 5 es gratis
}
