package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public class SansCamoufle implements StrategieCiblage {
    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return !cible.estCamoufle();
    }
}
