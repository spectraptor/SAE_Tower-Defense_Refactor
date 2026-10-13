package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.*;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;

import java.util.ArrayList;

/**
 * La classe Ralentisseur est un type d'ennemi du jeu
 * Il ralentit la cadence d'attaque des tours autour de lui.
 * Il possède une portée d'attaque.
 */
public class Ralentisseur extends Ennemi {
    private double ralentissement;
    private int portee;
    private ChangementCadenceTir changementCadence;

    public Ralentisseur(Environnement env, ArrayList<Point> chemin) {
        super(50, 1, 250, env, chemin);
        this.ralentissement = 3;
        this.portee = 2 * Terrain.TAILLE_TUILLE;
        this.changementCadence = new ChangementCadenceTir();
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        for(Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir) {
                    if (this.estDansLaPortee(batiment,this.portee)) {
                        this.changementCadence.appliquerNouvelleCadence((BatimentTir)batiment,this.ralentissement);
                    }
            }
        }
        ArrayList<BatimentTir> listeBatimentCadenceModifier = new ArrayList<>(this.changementCadence.getCleCadancesAvantModification());
        for (BatimentTir batimentTir : listeBatimentCadenceModifier) {
            if (!this.estDansLaPortee(batimentTir,this.portee)) {
                this.changementCadence.restaurerAncienneCadence(batimentTir,this.ralentissement);
            }
        }
    }
}
