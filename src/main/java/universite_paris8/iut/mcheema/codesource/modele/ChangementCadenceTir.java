package universite_paris8.iut.mcheema.codesource.modele;

import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;
import java.util.HashMap;


public class ChangementCadenceTir {
    private HashMap<BatimentTir, Integer> cadancesAvantModification;

    public ChangementCadenceTir() {
        this.cadancesAvantModification = new HashMap<>();
    }

    public void actualiserCadencesDansPortee(ObservableList<Batiment> batiments, Entite entite,int portee, double multiplicateurCadence) {
        this.appliquerNouvelleCadenceDansPortee(batiments,entite,portee,multiplicateurCadence);
        this.restaurerCadenceHorsPortee(entite,portee,multiplicateurCadence);
    }

    public void appliquerNouvelleCadenceAuBatiment(BatimentTir batiment, double multiplicateurCadence) {
        if (this.estModifiable(batiment,multiplicateurCadence)) {
            this.cadancesAvantModification.put(batiment, batiment.getCadenceTir());
            int nouvelleCadence = calculerNouvelleCadence(batiment.getCadenceTir(), multiplicateurCadence);
            batiment.setCadenceTir(nouvelleCadence);
        }
    }

    public void appliquerNouvelleCadenceDansPortee(ObservableList<Batiment> batiments, Entite entite, int portee, double multiplicateurCadence) {
        for (Batiment batiment : batiments) {
            if (batiment.possedeCadenceTir()) {
                if (Entite.estDansLaPortee(batiment,entite,portee)) {
                    this.appliquerNouvelleCadenceAuBatiment((BatimentTir) batiment,multiplicateurCadence);
                }
            }
        }
    }


    public void restaurerAncienneCadenceAuBatiment(BatimentTir batimentTir, double multiplicateurCadence) {
        if (this.cadancesAvantModification.containsKey(batimentTir)) {
            this.cadancesAvantModification.remove(batimentTir);
            batimentTir.setCadenceTir((int) (batimentTir.getCadenceTir() * (1 / multiplicateurCadence)));
        }
    }

    public void restaurerCadenceHorsPortee(Entite entite,int portee,double multiplicateurCadence) {
        for (BatimentTir batimentTir : this.cadancesAvantModification.keySet()) {
            if (!Entite.estDansLaPortee(entite, batimentTir, portee)) {
                this.restaurerAncienneCadenceAuBatiment(batimentTir, multiplicateurCadence);
            }
        }
    }

    public void viderCadancesAvantModification() {
        this.cadancesAvantModification.clear();
    }

    public boolean estModifiable(BatimentTir batimentTir,double multiplicateurCadence) {
        int prochaineCadence = this.calculerNouvelleCadence(batimentTir.getCadenceTir(),multiplicateurCadence);
        return !this.cadancesAvantModification.containsKey(batimentTir)
                && BatimentTir.cadenceEstModifiable(prochaineCadence);
    }

    public int calculerNouvelleCadence(int ancienneCadence, double proportionNouvelleCadence) {
        return (int) (ancienneCadence * proportionNouvelleCadence);
    }

}
