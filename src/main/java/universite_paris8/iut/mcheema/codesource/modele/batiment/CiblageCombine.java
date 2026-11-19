package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class CiblageCombine implements StrategieCiblage {

    private ArrayList<StrategieCiblage> strategies;

    public CiblageCombine(ArrayList<StrategieCiblage> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean peutAttaquer(Ennemi cible) {
        for (StrategieCiblage strategie : this.strategies) {
            if (!strategie.peutAttaquer(cible)) {
                return false;
            }
        }
        return true;
    }
}
