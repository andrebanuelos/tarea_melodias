package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CatalogoMelodias {
    private ArrayList<Melodia> canciones = new ArrayList<>();
    private int siguienteId = 1; // el catálogo le da un id a cada melodía nueva


    public boolean registrarMelodia(Melodia melodia) {
        boolean repetida = canciones.stream().anyMatch(m -> m.equals(melodia));
        if (repetida)
            return false;

        melodia.setId(siguienteId++);
        canciones.add(melodia);
        return true;
    }

    // Elimina por id. removeIf borra las que cumplan la condición de la lambda.
    public boolean removerMelodia(int id) {
        return canciones.removeIf(m -> m.getId() == id);
    }

    // Elimina por nombre (borra todas las versiones con ese nombre).
    public boolean removerMelodia(String nombreMelodia) {
        return canciones.removeIf(m -> m.getNombre().equalsIgnoreCase(nombreMelodia.trim()));
    }

    // Busca por nombre. Puede haber varias versiones con el mismo nombre.
    public ArrayList<Melodia> buscar(String nombreMelodia) {
        return canciones.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombreMelodia.trim()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // Busca por id. Regresa null si no la encuentra.
    public Melodia buscar(int id) {
        return canciones.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Corrige los datos de una melodía
    public boolean actualizarMelodia(int id, String nombre, String compositor, int anio,
                                     String genero, String cantante, double precio, String formato) {
        Melodia m = buscar(id);
        if (m == null)
            return false;

        m.setNombre(nombre);
        m.setCompositor(compositor);
        m.setAnio(anio);
        m.setGenero(genero);
        m.setCantante(cantante);
        m.setPrecio(precio);
        m.setFormato(formato);
        return true;
    }

    public double obtenerPrecioColeccion() {
        return canciones.stream()
                .mapToDouble(m -> m.getPrecio())
                .sum();
    }

    // De qué cantantes tienen melodías (sin repetir).
    public ArrayList<String> obtenerTodosLosCantantes() {
        return canciones.stream()
                .map(m -> m.getCantante())
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // De cuántos cantantes tienen melodías.
    public long contarCantantes() {
        return canciones.stream()
                .map(m -> m.getCantante().toLowerCase())
                .distinct()
                .count();
    }

    // Géneros musicales que abarca la colección
    public ArrayList<String> obtenerTodosLosGeneros() {
        return canciones.stream()
                .map(m -> m.getGenero())
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // Melodías que pertenecen a cada género.
    public Map<String, ArrayList<Melodia>> melodiasPorGenero() {
        return canciones.stream()
                .collect(Collectors.groupingBy(
                        m -> m.getGenero().toLowerCase(),
                        () -> new TreeMap<>(),
                        Collectors.toCollection(() -> new ArrayList<>())));
    }

    // Qué formatos tienen y cuántas melodías hay de cada formato.
    public Map<String, Long> contarPorFormato() {
        return canciones.stream()
                .collect(Collectors.groupingBy(
                        m -> m.getFormato().toLowerCase(),
                        () -> new TreeMap<>(),
                        Collectors.counting()));
    }

    // Melodías que compuso alguien en particular.
    public ArrayList<Melodia> melodiasPorCompositor(String compositor) {
        return canciones.stream()
                .filter(m -> m.getCompositor().equalsIgnoreCase(compositor.trim()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // Melodías de un cantante
    public ArrayList<Melodia> melodiasPorCantante(String cantante) {
        return canciones.stream()
                .filter(m -> m.getCantante().equalsIgnoreCase(cantante.trim()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // Melodías que se grabaron un año
    public ArrayList<Melodia> melodiasPorAnio(int anio) {
        return canciones.stream()
                .filter(m -> m.getAnio() == anio)
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }



    public ArrayList<Melodia> ordenarMelodiasPorPrecio() {
        return canciones.stream()
                .sorted((a, b) -> Double.compare(a.getPrecio(), b.getPrecio()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    public ArrayList<Melodia> ordenarMelodiasPorAnio() {
        return canciones.stream()
                .sorted((a, b) -> Integer.compare(a.getAnio(), b.getAnio()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    public ArrayList<Melodia> ordenarMelodiasPorCantante() {
        return canciones.stream()
                .sorted((a, b) -> a.getCantante().compareToIgnoreCase(b.getCantante()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    public ArrayList<Melodia> ordenarMelodiasPorNombre() {
        return canciones.stream()
                .sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    // Todas las melodías como texto
    public String mostrarTodasMelodias() {
        StringBuilder sb = new StringBuilder();
        canciones.forEach(m -> sb.append(m).append("\n"));
        return sb.toString();
    }

    public boolean estaVacio() {
        return canciones.isEmpty();
    }
}
