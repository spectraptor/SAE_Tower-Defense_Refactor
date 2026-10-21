package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * La classe ErreurExecution est un type d'ennemi qui représente un des boss du jeux.
 */
public class ErreurExecution extends Ennemi {
    public ErreurExecution(Environnement env, ArrayList<Point> chemin) {super(100, 2, 1500, env, chemin);}

    @Override
    public boolean estUnBoss() {
        return true;
    }
}
