package org.example;

public class Melodia {
    private int id;
    private String nombre;
    private String compositor;
    private int anio;
    private String genero;
    private String cantante;
    private double precio;
    private String formato;

    public Melodia(String nombre, String compositor, int anio, String genero,
                   String cantante, double precio, String formato) {
        this.nombre = nombre;
        this.compositor = compositor;
        this.anio = anio;
        this.genero = genero;
        this.cantante = cantante;
        this.precio = precio;
        this.formato = formato;
        this.id = this.hashCode();
    }


    //Setters y getters y contructores
    public int getId(){
        return id;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCompositor() {
        return compositor;
    }

    public void setCompositor(String compositor){
        this.compositor = compositor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFormato() {
        return formato;
    }

    @Override
    public boolean equals(Object obj){
        Melodia mel = (Melodia)obj;

        return nombre.equals(mel.getNombre()) && (anio == mel.getAnio());
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + cantante + " | " + compositor + " | "
                + anio + " | " + genero + " | $" + precio + " | " + formato;
    }





}