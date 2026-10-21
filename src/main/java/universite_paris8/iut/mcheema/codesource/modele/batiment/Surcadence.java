
package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.ChangementCadenceTir;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

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
        for (Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir) {
                if (this.estDansLaPortee(batiment,this.getPortee())) {
                    this.changementCadence.appliquerNouvelleCadence((BatimentTir)batiment,this.diviseCadence);
                }
            }
        }
        // boucle utilisée lorsqu'on retire les ennemis de la portée de la surcadence.
        // on remet le bâtiment à sa portée de base
        ArrayList<BatimentTir> listeBatimentCadenceModifier = this.changementCadence.getCleCadancesAvantModification();
        for (BatimentTir batimentTir : listeBatimentCadenceModifier) {
            if(!this.estDansLaPortee(batimentTir,this.getPortee())) {
                this.changementCadence.restaurerAncienneCadence(batimentTir,this.diviseCadence);
            }
        }
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
