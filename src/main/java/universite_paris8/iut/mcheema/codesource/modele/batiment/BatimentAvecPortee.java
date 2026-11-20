package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;

public abstract class BatimentAvecPortee extends Batiment {
    private int portee;

    public BatimentAvecPortee(String nom, double x, double y, int portee, int prix, int nivMax, Environnement env) {
        super(nom, x, y, prix, nivMax, env);
        this.portee = portee;
    }

    public int getPortee() {
        return this.portee;
    }

    public String avoirDescription() {
        return  super.avoirDescription() +
                "Portee : " + this.getPortee();
    }

    public void appliquerAmeliorations() {
        this.portee = (int)(this.portee * (1 + this.tauxAmeliorationParNiveau()));
    }

}
