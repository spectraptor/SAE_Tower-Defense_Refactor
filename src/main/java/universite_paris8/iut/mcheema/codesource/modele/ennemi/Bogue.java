package universite_paris8.iut.mcheema.codesource.modele.ennemi;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * Bogue qui étend la classe Ennemi, contient les mêmes attributs que Ennemi.
 */
public class Bogue extends Ennemi {
    public Bogue(Environnement env, ArrayList<Point> chemin) {super(3, 2, 15, env, chemin);}
}
