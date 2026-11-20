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
    private Terrain terrain;

    public Niveau(Terrain terrain) {
        this.bases = terrain.trouverBases();
        this.entrees = terrain.trouverEntrees();
        this.terrain = terrain;
    }

    public ArrayList<ArrayList<Point>> avoirTousLesChemins() {
        ArrayList<ArrayList<Point>> chemins = new ArrayList<>();
        for (int i = 0; i < this.bases.size(); i++) {
            Point base = this.bases.get(i);
            Point entree = this.entrees.get(i);
            BFS bfs = new BFS(this.terrain, base);
            chemins.add(bfs.cheminDepuisSource(entree));
        }

        return chemins;
    }

    public ArrayList<Point> getBases() {
        return bases;
    }

    public ArrayList<Point> getEntrees() {
        return entrees;
    }

}