package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;

import java.util.ArrayList;
import java.util.HashMap;


public class ChangementCadenceTir {
    private HashMap<BatimentTir, Integer> cadancesAvantModification;

    public ChangementCadenceTir() {
        this.cadancesAvantModification = new HashMap<>();
    }

    public void appliquerNouvelleCadence(BatimentTir batiment, double multiplicateurCadence) {
        if (estModifiable(batiment,multiplicateurCadence)) {
            this.cadancesAvantModification.put(batiment, batiment.getCadenceTir());
            int nouvelleCadence = calculerNouvelleCadence(batiment.getCadenceTir(), multiplicateurCadence);
            batiment.setCadenceTir(nouvelleCadence);
        }
    }


    public void restaurerAncienneCadence(BatimentTir batimentTir, double multiplicateurCadence) {
        if (this.cadancesAvantModification.containsKey(batimentTir)) {
            this.cadancesAvantModification.remove(batimentTir);
            batimentTir.setCadenceTir((int) (batimentTir.getCadenceTir() * (1 / multiplicateurCadence)));
        }
    }

    public ArrayList<BatimentTir> getCleCadancesAvantModification() {
        return new ArrayList<>(this.cadancesAvantModification.keySet());
    }

    public void viderCadancesAvantModification() {
        this.cadancesAvantModification.clear();
    }

    public boolean estModifiable(BatimentTir batimentTir,double multiplicateurCadence) {
        int prochaineCadence = this.calculerNouvelleCadence(batimentTir.getCadenceTir(),multiplicateurCadence);
        return !this.cadancesAvantModification.containsKey(batimentTir)
                && BatimentTir.cadenceModifiable(prochaineCadence);
    }

    public int calculerNouvelleCadence(int ancienneCadence, double proportionNouvelleCadence) {
        return (int) (ancienneCadence * proportionNouvelleCadence);
    }

}
