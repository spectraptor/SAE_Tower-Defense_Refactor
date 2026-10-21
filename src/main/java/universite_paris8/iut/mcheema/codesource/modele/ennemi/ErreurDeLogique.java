package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * La classe ErreurDeLogique est un type d'ennemi qui représente un des boss du jeux.
 */
public class ErreurDeLogique extends EnnemiArmure {
    public ErreurDeLogique(Environnement env, ArrayList<Point> chemin) {super(50, 1, 250, env, chemin,3);}

    @Override
    public boolean estUnBoss() {
        return true;
    }
}
