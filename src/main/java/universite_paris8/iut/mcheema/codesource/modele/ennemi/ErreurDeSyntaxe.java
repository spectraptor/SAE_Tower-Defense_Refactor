package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;


/**
 * La classe ErreurDeLogique est un type d'ennemi qui représente un des boss du jeux.
 */
public class ErreurDeSyntaxe extends Ennemi {
    public ErreurDeSyntaxe(Environnement env, ArrayList<Point> chemin) {super(200,1 ,1000 , env, chemin);}

    @Override
    public boolean estUnBoss() {
        return true;
    }
}
