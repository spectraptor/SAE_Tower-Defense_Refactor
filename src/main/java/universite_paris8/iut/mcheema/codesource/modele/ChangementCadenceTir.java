package universite_paris8.iut.mcheema.codesource.modele;

import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentTir;
import java.util.ArrayList;


public class ChangementCadenceTir {
    private ArrayList<BatimentTir> batimentsAffectes;

    public ChangementCadenceTir() {
        this.batimentsAffectes = new ArrayList<>();
    }

    public double calculerCadence(double ancienneCadence, double proportionNouvelleCadence) {
        return (ancienneCadence * proportionNouvelleCadence);
    }

    public void appliquerNouvelleCadenceAuBatiment(BatimentTir batiment, double multiplicateurCadence) {
        if (this.estModifiable(batiment,multiplicateurCadence)) {
            this.batimentsAffectes.add(batiment);
            double nouvelleCadence = this.calculerCadence(batiment.getCadenceTir(), multiplicateurCadence);
            batiment.setCadenceTir(nouvelleCadence);
        }
    }

    public void appliquerNouvelleCadenceDansPortee(ObservableList<Batiment> batiments, Entite entite, int portee, double multiplicateurCadence) {
        for (Batiment batiment : batiments) {
            if (batiment.possedeCadenceTir()) {
                if (entite.estDansLaPortee(batiment,portee)) {
                    this.appliquerNouvelleCadenceAuBatiment((BatimentTir) batiment,multiplicateurCadence);
                }
            }
        }
    }

    public void restaurerAncienneCadenceAuBatiment(BatimentTir batimentTir, double multiplicateurCadence) {
        if (this.batimentsAffectes.contains(batimentTir)) {
            this.batimentsAffectes.remove(batimentTir);
            batimentTir.setCadenceTir(this.calculerCadence(batimentTir.getCadenceTir(),1/multiplicateurCadence));
        }
    }

    public void restaurerCadenceHorsPortee(Entite entite,int portee,double multiplicateurCadence) {
        for (int i = this.batimentsAffectes.size() - 1 ;i>=0;i--) {
            BatimentTir batimentTir = this.batimentsAffectes.get(i);
          if (!entite.estDansLaPortee(batimentTir, portee)) {
                this.restaurerAncienneCadenceAuBatiment(batimentTir, multiplicateurCadence);
          }
        }
    }

    public boolean estModifiable(BatimentTir batimentTir,double multiplicateurCadence) {
        double prochaineCadence = this.calculerCadence(batimentTir.getCadenceTir(),multiplicateurCadence);
        return !this.batimentsAffectes.contains(batimentTir)
                && BatimentTir.cadenceEstModifiable(prochaineCadence);
    }

    public void viderCadancesAvantModification(double multiplicateurCadence) {
        for (BatimentTir batimentTir : this.batimentsAffectes) {
            batimentTir.setCadenceTir(this.calculerCadence(batimentTir.getCadenceTir(),1/multiplicateurCadence));
        }
        this.batimentsAffectes.clear();
    }

    public void actualiserCadencesDansPortee(ObservableList<Batiment> batiments, Entite entite,int portee, double multiplicateurCadence) {
        this.appliquerNouvelleCadenceDansPortee(batiments,entite,portee,multiplicateurCadence);
        this.restaurerCadenceHorsPortee(entite,portee,multiplicateurCadence);
    }

}
