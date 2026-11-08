package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.Objects;

public class MissileTete extends MissileTeleguide {
    public MissileTete(double x, double y,int degat, Ennemi cible,Environnement env) {
        super(x, y, degat, 3,cible,env);
    }

    @Override
    public void effectueAction() {
        super.effectueAction();
        if(!this.getCible().estVivant()) {
            if(arriveEmplacementCibleMorte()) {
                this.setEstArrive(true);
            }
        }
    }

    public boolean arriveEmplacementCibleMorte() {
        return this.getX() == this.getCible().getX() && this.getY() == this.getCible().getY();
    }
}
