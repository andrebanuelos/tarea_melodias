package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogoMelodias {
    private ArrayList<Melodia> canciones = new ArrayList<>();

    public boolean registrarMelodia(Melodia obj) {
        boolean ban = canciones.stream().anyMatch(melodia -> melodia.equals(obj));


        if (!ban)
            canciones.add(obj);
        return ban;
    }

    public String mostrarTodasMelodias() {
        StringBuffer sb = new StringBuffer();
        // forEach recibe una lambda que se ejecuta por cada melodia
        canciones.forEach(melodia -> sb.append(melodia).append("\n"));
        return sb.toString();
    }

    // Regresa solo las canciones del cantante que se escriba
    public List<Melodia> buscarPorCantante(String cantante) {
        return canciones.stream()
                .filter(melodia -> melodia.getCantante().equalsIgnoreCase(cantante.trim()))
                .collect(Collectors.toList());
    }

    public ArrayList<String> obtenertodoslosgeneros() {
        ArrayList<String> generos = (ArrayList<String>) canciones.stream()
                .map(melodia -> melodia.getGenero())
                .distinct()
                .collect(Collectors.toList());
        return generos;
    }

    public HashSet<String> obtenerTodosLosGenerosIterativo() {
        HashSet<String> generos = new HashSet<>();
        for (Melodia cancion : canciones) {
            generos.add(cancion.getGenero());
        }
        return generos;
    }

    /*
    public HashSet<String>obtenerNombre() {
        HashSet<String> nombre = new HashSet();
        for (Melodia nombre : canciones) {
            generos.add(cancion.getNombre());
        }
        */

}
