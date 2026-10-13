package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.BFS;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 * La classe GrosBogue est un type d'ennemi qui représente un des boss du jeux.
 * Il crée 2 bogues lorsque ses pvs sont faibles (<= 5) : un qui apparaît à l'endroit où il meurt, l'autre au début du chemin du GrosBogue.
 */
public class GrosBogue extends Ennemi {
  
    public GrosBogue(Environnement env, ArrayList<Point> chemin) {super(25, 1, 100, env, chemin);}

    @Override
    public boolean estUnBoss() {
        return true;
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        if(this.getPv() <= 5 && !this.aAtteintDestination()) {
            Point basePoint = this.getChemin().get(this.getChemin().size() - 1);
            Point entree = new Point((int)this.getX() / Terrain.TAILLE_TUILLE, (int)this.getY() / Terrain.TAILLE_TUILLE);
            BFS bfs = new BFS(this.getEnvironnement().getTerrainDeJeu(), basePoint);
            ArrayList<Point> chemin = bfs.cheminDepuisSource(entree);
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getEnvironnement(), this.getChemin()));
            this.getEnvironnement().getEnnemis().add(new Bogue(this.getEnvironnement(), chemin));
            this.meurt();
        }
    }
}
