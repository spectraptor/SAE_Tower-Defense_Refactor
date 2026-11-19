package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCamoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * La classe Compilateur constitue le bâtiment de base du jeu
 * Il n'est pas capable d'attaquer les ennemis camouflés et volants.
 */
public class Compilateur extends BatimentTir {
    public Compilateur(double x, double y, Environnement env) {
        super("Compilateur",x, y, 100,1, 100,200, env, new CiblageCombine().ajouter(new SansVolant()).ajouter(new SansCamoufle()));
    }

    @Override
    public Projectile avoirProjectileAdapte(Ennemi cible) {
        return new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
    }

    @Override
    public double choisirAmeliorationVitesse() {
        double multiplicateurVitesse = 1;
        switch (this.getNiveau()) {
            case 2:
                multiplicateurVitesse = 1.5;
                break;
            case 3:
                multiplicateurVitesse = 1.8;
                break;
            case 4:
                multiplicateurVitesse = 2.5;
                break;
        }
        return multiplicateurVitesse;
    }

}

