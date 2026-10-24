package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// Vista: se encarga de pedir datos al usuario y mostrar resultados.
public class CatalogoView {

    private Scanner leerCadenas = new Scanner(System.in);
    private Scanner leerNumeros = new Scanner(System.in);
    private CatalogoMelodias catalogo;

    public CatalogoView(CatalogoMelodias catalogo) {
        this.catalogo = catalogo;
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> agregarMelodia();
                case 2 -> eliminarMelodia();
                case 3 -> consultarMelodia();
                case 4 -> corregirMelodia();
                case 5 -> System.out.printf("Valor de la colección: $%.2f%n",
                        catalogo.obtenerPrecioColeccion());
                case 6 -> mostrarCantantes();
                case 7 -> mostrarPorGenero();
                case 8 -> mostrarGeneros();
                case 9 -> mostrarFormatos();
                case 10 -> mostrarLista(catalogo.melodiasPorCompositor(
                        leerTexto("Compositor: ")));
                case 11 -> mostrarLista(catalogo.melodiasPorCantante(
                        leerTexto("Cantante/artista: ")));
                case 12 -> mostrarLista(catalogo.melodiasPorAnio(
                        leerEntero("Año: ")));
                case 13 -> consultaGeneral();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("===== CATÁLOGO DE MELODÍAS =====");
        System.out.println("1. Agregar melodía");
        System.out.println("2. Eliminar melodía (por nombre)");
        System.out.println("3. Consultar melodía (por nombre)");
        System.out.println("4. Corregir datos de una melodía");
        System.out.println("5. Valor monetario de la colección");
        System.out.println("6. Cantantes que tienen melodías");
        System.out.println("7. Melodías por género");
        System.out.println("8. Géneros de la colección");
        System.out.println("9. Formatos y cuántas melodías de cada uno");
        System.out.println("10. Melodías de un compositor");
        System.out.println("11. Melodías de un cantante/artista");
        System.out.println("12. Melodías de un año");
        System.out.println("13. Consulta general ordenada");
        System.out.println("0. Salir");
    }


    private void agregarMelodia() {
        Melodia m = pedirDatosMelodia();
        if (catalogo.registrarMelodia(m))
            System.out.println("Melodía agregada con id " + m.getId());
        else
            System.out.println("Ya tienen esa versión (mismo nombre, cantante y año). No se agregó.");
    }

    private void eliminarMelodia() {
        String nombre = leerTexto("Nombre de la melodía a eliminar: ");
        if (catalogo.removerMelodia(nombre))
            System.out.println("Melodía eliminada.");
        else
            System.out.println("No existe una melodía con ese nombre.");
    }

    private void consultarMelodia() {
        String nombre = leerTexto("Nombre de la melodía: ");
        mostrarLista(catalogo.buscar(nombre));
    }

    private void corregirMelodia() {
        System.out.print(catalogo.mostrarTodasMelodias());
        int id = leerEntero("Id de la melodía a corregir: ");
        if (catalogo.buscar(id) == null) {
            System.out.println("No existe una melodía con ese id.");
            return;
        }
        System.out.println("Escribe los datos correctos:");
        Melodia nueva = pedirDatosMelodia();
        catalogo.actualizarMelodia(id, nueva.getNombre(), nueva.getCompositor(), nueva.getAnio(),
                nueva.getGenero(), nueva.getCantante(), nueva.getPrecio(), nueva.getFormato());
        System.out.println("Melodía actualizada: " + catalogo.buscar(id));
    }



    private void mostrarCantantes() {
        System.out.println("Tienen melodías de " + catalogo.contarCantantes() + " cantantes:");
        catalogo.obtenerTodosLosCantantes().forEach(c -> System.out.println("  - " + c));
    }

    private void mostrarGeneros() {
        System.out.println("Géneros de la colección:");
        catalogo.obtenerTodosLosGeneros().forEach(g -> System.out.println("  - " + g));
    }

    private void mostrarPorGenero() {
        // forEach de un Map recibe una lambda con dos datos: la llave y el valor
        catalogo.melodiasPorGenero().forEach((genero, lista) -> {
            System.out.println("== " + genero + " ==");
            lista.forEach(m -> System.out.println("  " + m));
        });
    }

    private void mostrarFormatos() {
        catalogo.contarPorFormato().forEach((formato, cantidad) ->
                System.out.println("  " + formato + ": " + cantidad + " melodía(s)"));
    }

    private void consultaGeneral() {
        System.out.println("Ordenar por: 1) Nombre  2) Precio  3) Año  4) Cantante");
        int llave = leerEntero("Opción: ");
        ArrayList<Melodia> lista = switch (llave) {
            case 2 -> catalogo.ordenarMelodiasPorPrecio();
            case 3 -> catalogo.ordenarMelodiasPorAnio();
            case 4 -> catalogo.ordenarMelodiasPorCantante();
            default -> catalogo.ordenarMelodiasPorNombre();
        };
        mostrarLista(lista);
    }



    private Melodia pedirDatosMelodia() {
        String nombre = leerTexto("Nombre: ");
        String compositor = leerTexto("Compositor: ");
        int anio = leerEntero("Año de grabación: ");
        String genero = leerTexto("Género: ");
        String cantante = leerTexto("Cantante: ");
        double precio = leerDecimal("Precio: ");
        String formato = leerTexto("Formato (mp3, ogg, wav, flac...): ");
        return new Melodia(nombre, compositor, anio, genero, cantante, precio, formato);
    }

    private void mostrarLista(ArrayList<Melodia> lista) {
        if (lista.isEmpty())
            System.out.println("No se encontraron melodías.");
        else
            lista.forEach(m -> System.out.println("  " + m));
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return leerCadenas.nextLine().trim();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!leerNumeros.hasNextInt()) {
            leerNumeros.next(); // descarta lo que no es número
            System.out.print("Escribe un número entero: ");
        }
        return leerNumeros.nextInt();
    }

    private double leerDecimal(String mensaje) {
        System.out.print(mensaje);
        while (!leerNumeros.hasNextDouble()) {
            leerNumeros.next();
            System.out.print("Escribe un número: ");
        }
        return leerNumeros.nextDouble();
    }
}
