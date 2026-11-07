package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileZone;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Cloud extends BatimentTir {
    public Cloud(double x, double y, Environnement env) {
        super("Cloud", x, y, 50, 1, 150, 100, env);
    }


    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiCammoufle));
    }

    @Override
    public Projectile avoirProjectileAdapte(Ennemi cible) {
        return new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
    }

    @Override
    public double choisirAmeliorationDegat() {
        double multiplicateurDegat = 1;
        switch (this.getNiveau()) {
            case 2:
                multiplicateurDegat = 1.2;
                break;
            case 3:
                multiplicateurDegat = 1.5;
                break;
            case 4:
                multiplicateurDegat = 2;
                break;
        }
        return multiplicateurDegat;
    }

}
