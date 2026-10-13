package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;

import java.util.ArrayList;

/**
 * La classe Ralentisseur est un type d'ennemi du jeu
 * Il ralentit la cadence d'attaque des tours autour de lui.
 * Il possède une portée d'attaque.
 */
public class Ralentisseur extends Ennemi {
    private int ralentissement;
    private int portee;
    private ArrayList<BatimentTir> batimentRalenti;
    public Ralentisseur(Environnement env, ArrayList<Point> chemin) {
        super(50, 1, 250, env, chemin);
        this.ralentissement = 3;
        this.portee = 2 * Terrain.TAILLE_TUILLE;
        this.batimentRalenti = new ArrayList<>();
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        this.ralentirBatiment();
        this.retablirBatiment();
    }

    public void ralentirBatiment() {
        for (Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir batimentTir && !batimentRalenti.contains(batiment)
                    && this.calculDistance(batiment) <= this.portee) {
                batimentTir.setCadenceTir((int) (batimentTir.getCadenceTir() * this.ralentissement));
                this.batimentRalenti.add(batimentTir);
            }
        }
    }

    public void retablirBatiment() {
        for(int i = this.batimentRalenti.size() - 1; i >= 0; i--) {
            BatimentTir batimentTir = this.batimentRalenti.get(i);
            if (this.calculDistance(batimentTir) > this.portee) {
                batimentTir.setCadenceTir((int) (batimentTir.getCadenceTir() / this.ralentissement));
                this.batimentRalenti.remove(i);
            }
        }
    }
}
