package universite_paris8.iut.mcheema.codesource.modele.batiment;


import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
import java.util.ArrayList;

public abstract class BatimentTir extends BatimentAvecPortee{
    private final int cadenceTirBase;
    private int cadenceTir;
    private int degat;

    public BatimentTir(String nom ,double x, double y, int portee, int degat, int prix, int cTir, Environnement env) {
        super(nom,x, y, portee, prix, 4, env);
        this.cadenceTir = cTir;
        this.cadenceTirBase = cTir;
        this.degat = degat;
    }

    public void setCadenceTir(int cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public void effectueAction() {
        if (this.estCapableDeTirer()) {
            ArrayList<Ennemi> ennemis = new ArrayList<>(this.getEnvironnement().getEnnemis());
            int i = 0;
            boolean ennemiTrouve = false;
            while (i < ennemis.size() && !ennemiTrouve) {
                Ennemi ennemi = ennemis.get(i);
                if (this.calculDistance(ennemi) <= this.getPortee() && this.peutAttaquer(ennemi)) {
                    this.getEnvironnement().ajouterProjectile(this.choisirProjectile(ennemis.get(i)));
                    ennemiTrouve = true;
                }
                i++;
            }
        }
    }

    public int getDegat() {
        return this.degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public double getCadenceTir() {
        return this.cadenceTir;
    }

    @Override
    public void appliquerAmeliorations() {
        super.appliquerAmeliorations();
        this.cadenceTir = (int)(this.cadenceTir * (1 - this.tauxAmeliorationParNiveau()));
    }


    public int getCadenceTirBase() {
        return this.cadenceTirBase;
    }

    public abstract boolean peutAttaquer(Ennemi cible);

    public boolean estCapableDeTirer() {
        return this.getEnvironnement().getNbTours() % this.getCadenceTir() == 0;
    }

    public abstract Projectile choisirProjectile(Ennemi cible);

    public String avoirDescription() {
        return  super.avoirDescription() + "\nCadence (s.) : " +  this.cadenceTir/60 +
                "\nDegat : " + this.getDegat();
    }
}
