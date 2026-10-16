
package universite_paris8.iut.mcheema.codesource.modele.batiment;

import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.ChangementCadenceTir;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

/**
 * La classe Surcadence est un type de bâtiment.
 * Il possède une portée.
 * Il  permet d'augmenter la vitesse d'attaque des tours à proximité.
 */
public class Surcadence extends BatimentAvecPortee{
    public double diviseCadence;
    private ChangementCadenceTir changementCadence;

    public Surcadence(double x, double y, Environnement env) {
        super("Surcadence", x, y, Terrain.TAILLE_TUILLE,300, 2, env);
        this.diviseCadence = 1/1.25;
        this.changementCadence = new ChangementCadenceTir();
    }


    @Override
    public void effectueAction() {
        ObservableList<Batiment> batiments = this.getEnvironnement().getBatiments();
        this.changementCadence.actualiserCadencesDansPortee(batiments,this,this.getPortee(),this.diviseCadence);
   }

    @Override
    public void ameliorerBatiment() {
        if(this.getEnvironnement().getArgent() >= this.coutProchaineAmelioration()) {
            if (this.getNiveau() < this.getNiveauMax()) {
                this.diviseCadence *= (1 + this.tauxAmeliorationParNiveau());
                this.getEnvironnement().retirerArgent(this.coutProchaineAmelioration());
                this.incrementerNiveau();
                this.changementCadence.viderCadancesAvantModification(); // enlever la liste des bâtiments déjà présents pour pouvoir changer leur cadence
            }
        }
    }

    public String avoirDescription() {
        return super.avoirDescription() + "\nDivise cadence par : " + this.diviseCadence;
    }
}
