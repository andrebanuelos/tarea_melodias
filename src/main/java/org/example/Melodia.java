package org.example;

import java.util.Objects;

public class Melodia {
    private int id;
    private String nombre;
    private String compositor;
    private int anio;
    private String genero;
    private String cantante;
    private double precio;
    private String formato;


    public Melodia() {
    }

    public Melodia(String nombre, String compositor, int anio, String genero,
                   String cantante, double precio, String formato) {
        this.nombre = nombre;
        this.compositor = compositor;
        this.anio = anio;
        this.genero = genero;
        this.cantante = cantante;
        this.precio = precio;
        this.formato = formato;
    }

    // ===== Setters y getters =====
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor) {
        this.compositor = compositor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCantante() {
        return cantante;
    }

    public void setCantante(String cantante) {
        this.cantante = cantante;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Melodia)) return false;
        Melodia mel = (Melodia) obj;
        return nombre.equalsIgnoreCase(mel.getNombre())
                && cantante.equalsIgnoreCase(mel.getCantante())
                && anio == mel.getAnio();
    }

    // hashCode debe usar los mismos datos que equals
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), cantante.toLowerCase(), anio);
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + cantante + " | " + compositor + " | "
                + anio + " | " + genero + " | $" + precio + " | " + formato;
    }
}
