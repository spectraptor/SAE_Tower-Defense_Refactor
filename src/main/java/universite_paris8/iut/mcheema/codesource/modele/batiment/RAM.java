package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

/**
 * La classe RAM est un type de bâtiment.
 * La RAM est capable de générer de l'argent, qu'on pourra utiliser.
 */
public class RAM extends Batiment {
    private int argentDonne;
    private int tempsAtt;

    public RAM(double x, double y, Environnement env) {
        super("RAM", x, y, 75, 3, env);
        this.argentDonne = 20;
        this.tempsAtt = 400;
    }

    public int getArgentDonne() {
        return this.argentDonne;
    }

    @Override
    public void effectueAction() {
        if(this.getEnvironnement().getNbTours() % this.tempsAtt == 0) {
            this.getEnvironnement().ajouterArgent(this.argentDonne);
        }
    }

    @Override
    public void appliquerAmeliorations() {
        this.argentDonne = (int)(this.argentDonne * (1 + this.tauxAmeliorationParNiveau()));
        this.tempsAtt = (int) (this.tempsAtt * (1 - this.tauxAmeliorationParNiveau()));
    }


    public String avoirDescription() {
        return super.avoirDescription() + "Argent donné : " + this.argentDonne + "\nTemps d'attente : " + this.tempsAtt;
    }

}
