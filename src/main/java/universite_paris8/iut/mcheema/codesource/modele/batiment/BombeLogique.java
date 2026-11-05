package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCammoufle;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.*;

public class BombeLogique extends BatimentTir {
    public BombeLogique(double x, double y, Environnement env) {
        super("Bombe Logique", x, y, 100,3, 500,550, env);
    }


    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return (!(cible instanceof EnnemiVolant) && !(cible instanceof EnnemiCammoufle));
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile projectile =  new MissileZone(this.getX(),this.getY(),this.getDegat(),cible.getX(),cible.getY(),this.getEnvironnement());;
        switch(this.getNiveau()) {
            case 2:
                projectile.setVitesse((int) (projectile.getVitesse() * 1.3));
                break;
            case 3:
                projectile.setVitesse( (projectile.getVitesse() * 2));
                break;
            case 4:
                projectile.setVitesse(projectile.getVitesse() * 3);
                break;
        }
        return projectile;
    }

    @Override
    public void appliquerAmeliorations() {
        super.appliquerAmeliorations();
        if (this.getNiveau() + 1 == this.getNiveauMax()) // + 1 obligatoire car à ce stade la on n'a pas encore incrémenter le bâtiment.
            this.setDegat(this.getDegat() * 2);
    }
}
