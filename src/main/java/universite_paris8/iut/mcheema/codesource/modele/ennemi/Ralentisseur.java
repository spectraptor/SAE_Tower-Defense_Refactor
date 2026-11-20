package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import javafx.collections.ObservableList;
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
        ObservableList<Batiment> batiments = this.getEnvironnement().getBatiments();
        this.changementCadence.actualiserCadencesDansPortee(batiments,this,this.portee,this.ralentissement);
    }
}
