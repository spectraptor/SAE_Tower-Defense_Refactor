package universite_paris8.iut.mcheema.codesource.modele;

/**
 * La classe Point représente une tuile du terrain de jeu.
 * Elle est utilisée pour l'algorithme BFS du chemin.
 * Elle connait sa ligne et sa colonne.
 */
public class Point {
    private int ligne;
    private int colonne;

    public Point(int colonne, int ligne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public int getLigne() { return this.ligne; }
    public int getColonne() { return this.colonne;}

    public int getCentreX() {
        return this.colonne * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
    }
    public int getCentreY() {
        return this.ligne * Terrain.TAILLE_TUILLE + Terrain.TAILLE_TUILLE / 2;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        return this.ligne == other.ligne && this.colonne == other.colonne;
    }

    public int hashCode() {
        return 31 * this.ligne + this.colonne;
    }

    public String toString() {
        return "Tuile[" + this.ligne + ", " + this.colonne + "]";
    }
}