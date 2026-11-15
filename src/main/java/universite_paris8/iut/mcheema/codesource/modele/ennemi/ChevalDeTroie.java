package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import java.util.ArrayList;

/**
 * La classe ChevalDeTroie est un type d'ennemi qui peut attaquer la tour.
 * C'est un ennemi camouflé qui ne peut pas se faire attaquer par certains bâtiments (bombe logique, compilateur, cloud)
 */

public class ChevalDeTroie extends EnnemiCamoufle {

    public ChevalDeTroie(Environnement env, ArrayList<Point> chemin) {super(2, 1, 25, env, chemin);}


}
