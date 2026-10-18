
package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

/**
 * La classe Surcadence est un type de bâtiment.
 * Il possède une portée.
 * Il  permet d'augmenter la vitesse d'attaque des tours à proximité.
 */
public class Surcadence extends BatimentAvecPortee{
    private ArrayList<BatimentTir> batiments;
    private double diviseCadence;
    private final static int CADENCE_LIMITE = 15;

    public Surcadence(double x, double y, Environnement env) {
        super("Surcadence", x, y, Terrain.TAILLE_TUILLE,300, 2, env);
        batiments = new ArrayList<>();
        this.diviseCadence = 1.25;
    }

    @Override
    public void effectueAction() {
        for (Batiment batiment : this.getEnvironnement().getBatiments()) {
            if(batiment instanceof BatimentTir) {
                if (!batiments.contains(batiment)) {
                    if (this.calculDistance(batiment) <= this.getPortee()) {
                        if(((BatimentTir) batiment).getCadenceTir() > CADENCE_LIMITE) {
                            ((BatimentTir) batiment).setCadenceTir((int) (((BatimentTir) batiment).getCadenceTir() / this.diviseCadence)); // la tour ne doit pas dépasser la cadence de de 6
                            batiments.add((BatimentTir)batiment);
                        }
                    }
                }
            }
        }
        // boucle utilisée lorsqu'on retire les ennemis de la portée de la surcadence.
        // on remet le bâtiment à sa portée de base
        for(int i = this.batiments.size() -1;i>=0;i--) {
            if(this.calculDistance(this.batiments.get(i)) > this.getPortee()) {
                this.batiments.get(i).setCadenceTir(this.batiments.get(i).getCadenceTirBase());
                this.batiments.remove(i);
            }
        }
    }

    @Override
    public void appliquerAmeliorations() {
        this.diviseCadence *= (1 + this.tauxAmeliorationParNiveau());
        this.batiments.clear(); // enlever la liste des bâtiments déjà présents pour pouvoir changer leur cadence
    }

    public String avoirDescription() {
        return super.avoirDescription() + "\nDivise cadence par : " + this.diviseCadence;
    }
}
