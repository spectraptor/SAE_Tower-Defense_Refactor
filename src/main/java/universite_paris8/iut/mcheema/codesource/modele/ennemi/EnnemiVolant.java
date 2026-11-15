package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;

import java.util.ArrayList;

public abstract class EnnemiVolant extends Ennemi {
    public EnnemiVolant(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        super(pv, vitesse, argentDonne, env, chemin);
    }

    @Override
    public boolean estVolant() {
        return true;
    }

}
