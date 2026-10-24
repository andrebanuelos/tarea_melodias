package org.example;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        CatalogoMelodias catalogo = new CatalogoMelodias();
       Melodia cancion1 = new Melodia( "entombed", "deftones", 2008,"Rock", "deftones", 100, "mp3");
        Melodia cancion2 = new Melodia("cherrywaves", "deftones", 2012, "rock", "deftones", 200, "mp3");
        Melodia cancion3 = new Melodia("billie jean", "michael jackson", 2009, "pop", "michael jackson", 300, "mp3");



        System.out.println("cancion 1 repetida: "+ catalogo.registrarMelodia(cancion1));
        System.out.println("cancion 2 repetida: "+ catalogo.registrarMelodia(cancion2));
        System.out.println("cancion 3 repetida: "+ catalogo.registrarMelodia(cancion3));

        System.out.println();
        System.out.println("=== Melodias registradas ===");
        System.out.println(catalogo.mostrarTodasMelodias());

        // Buscar canciones por cantante
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Escribe el nombre de un cantante (o \"salir\"): ");
            String cantante = sc.nextLine();
            if (cantante.equalsIgnoreCase("salir"))
                break;

            List<Melodia> resultado = catalogo.buscarPorCantante(cantante);
            if (resultado.isEmpty()) {
                System.out.println("No hay canciones de " + cantante);
            } else {
                System.out.println("Canciones de " + cantante + ":");
                resultado.forEach(melodia -> System.out.println("  " + melodia));
            }
            System.out.println();
        }
        sc.close();
        }
    }
