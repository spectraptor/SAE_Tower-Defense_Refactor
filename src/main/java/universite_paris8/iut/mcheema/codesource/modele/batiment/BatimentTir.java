package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;
import java.util.ArrayList;

public abstract class BatimentTir extends BatimentAvecPortee{
    public final static int MIN_CADENCE_LIMITE = 15;
    public final static int MAX_CADENCE_LIMITE = 3000;
    private double cadenceTir;
    private int degat;

    public BatimentTir(String nom , double x, double y, int portee, int degat, int prix, double cTir, Environnement env) {
        super(nom,x, y, portee, prix, 4, env);
        this.cadenceTir = cTir;
        this.degat = degat;
    }

    public void setCadenceTir(double cadenceTir) {
        this.cadenceTir = cadenceTir;
    }

    public void effectueAction() {
        if (this.estCapableDeTirer()) {
            ArrayList<Ennemi> ennemis = new ArrayList<>(this.getEnvironnement().getEnnemis());
            int i = 0;
            boolean ennemiTrouve = false;
            while (i < ennemis.size() && !ennemiTrouve) {
                Ennemi ennemi = ennemis.get(i);
                if (ennemi.estDansLaPortee(this,this.getPortee()) && this.peutAttaquer(ennemi)) {
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
  
    public abstract boolean peutAttaquer(Ennemi cible);

    public boolean estCapableDeTirer() {
        return this.getEnvironnement().getNbTours() % (int) this.getCadenceTir() == 0;
    }


    public Projectile choisirProjectile(Ennemi cible) {
        Projectile projectile =  this.avoirProjectileAdapte(cible);
        double multiplicateur = this.choisirAmeliorationVitesse();
        projectile.setVitesse((int) (projectile.getVitesse() * multiplicateur));
        return projectile;
    }


    public static boolean cadenceEstModifiable(double nouvelleCadence) {
        return nouvelleCadence >= MIN_CADENCE_LIMITE && nouvelleCadence <= MAX_CADENCE_LIMITE;
    }

    public String avoirDescription() {
        return  super.avoirDescription() + "\nCadence (s.) : " +  (int) this.cadenceTir/60 +
                "\nDegat : " + this.getDegat();
    }

    @Override
    public boolean possedeCadenceTir() {
        return true;
    }
    
    

    public abstract double choisirAmeliorationVitesse();
    
    public abstract Projectile avoirProjectileAdapte(Ennemi cible);
}
