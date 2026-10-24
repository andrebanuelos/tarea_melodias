package org.example;

public class Main {
    static void main() {
        CatalogoMelodias catalogo = new CatalogoMelodias();

        // Algunas melodías de ejemplo para no empezar vacío
        catalogo.registrarMelodia(new Melodia("entombed", "deftones", 2008, "rock", "deftones", 100, "mp3"));
        catalogo.registrarMelodia(new Melodia("cherry waves", "deftones", 2012, "rock", "deftones", 200, "flac"));
        catalogo.registrarMelodia(new Melodia("billie jean", "michael jackson", 1982, "pop", "michael jackson", 300, "mp3"));
        catalogo.registrarMelodia(new Melodia("thriller", "rod temperton", 1982, "pop", "michael jackson", 250, "wav"));
        catalogo.registrarMelodia(new Melodia("change", "deftones", 2000, "rock", "deftones", 150, "ogg"));
        catalogo.registrarMelodia(new Melodia("el rey", "jose alfredo jimenez", 1971, "ranchera", "vicente fernandez", 120, "mp3"));
        catalogo.registrarMelodia(new Melodia("volver volver", "fernando z. maldonado", 1972, "ranchera", "vicente fernandez", 130, "flac"));
        catalogo.registrarMelodia(new Melodia("bohemian rhapsody", "freddie mercury", 1975, "rock", "queen", 280, "flac"));
        catalogo.registrarMelodia(new Melodia("rolling in the deep", "adele", 2010, "pop", "adele", 180, "mp3"));
        catalogo.registrarMelodia(new Melodia("la chona", "pancho ramirez", 1994, "nortenia", "los tucanes de tijuana", 90, "ogg"));

        CatalogoView vista = new CatalogoView(catalogo);
        vista.iniciar();
    }
}
