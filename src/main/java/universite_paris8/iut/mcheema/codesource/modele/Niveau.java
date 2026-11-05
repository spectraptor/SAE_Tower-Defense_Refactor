package universite_paris8.iut.mcheema.codesource.modele;

import java.util.ArrayList;

/**
 * La classe Niveau correspond aux différents terrains / "niveaux" disponibles.
 * Chaque niveau possède une liste de points représentant les bases.
 * Chaque niveau possède également une liste de points représentant les entrées (là où les ennemis apparaissent).
 */
public class Niveau {

    private ArrayList<Point> bases;
    private ArrayList<Point> entrees;

    public Niveau(Terrain terrain) {
        this.bases = terrain.trouverBases();
        this.entrees = terrain.trouverEntrees();
    }

    public ArrayList<Point> getBases() {
        return bases;
    }

    public ArrayList<Point> getEntrees() {
        return entrees;
    }

}