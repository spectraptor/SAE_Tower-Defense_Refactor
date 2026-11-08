package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

/**
 * La classe ConfigurationEnnemi a pour but de créer des ennemis par quantité donnée.
 * Elle est principalement utilisée pour la création d'ennemis au sein des différentes vagues.
 */
public class ConfigurationEnnemi {
    private FabriqueEnnemi fabriqueEnnemi;

    public ConfigurationEnnemi(FabriqueEnnemi fabrique) {
        this.fabriqueEnnemi = fabrique;
    }

    public ArrayList<Ennemi> creerEnnemis(TypeEnnemi typeEnnemi, int quantiteEnnemis, ArrayList<ArrayList<Point>> chemins) {
        ArrayList<Ennemi> ennemis = new ArrayList<>();

        for (int i = 0; i < quantiteEnnemis; i++) {
            ennemis.add(this.fabriqueEnnemi.creerEnnemi(typeEnnemi, GestionVague.choisirAleatoirementChemin(chemins)));
        }

        return ennemis;
    }

}
