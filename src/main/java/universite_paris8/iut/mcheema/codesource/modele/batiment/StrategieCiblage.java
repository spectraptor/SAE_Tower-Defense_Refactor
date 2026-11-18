package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

/**
 * Décide si un bâtiment de tir peut attaquer un ennemi.
 */
public interface StrategieCiblage {
    boolean peutAttaquer(Ennemi cible);
}