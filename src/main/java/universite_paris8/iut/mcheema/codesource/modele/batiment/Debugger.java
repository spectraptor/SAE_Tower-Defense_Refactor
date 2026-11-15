package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiVolant;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileFragmentation;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

/**
 * La classe Debugger représente un type de bâtiment qui peut tirer.
 * Le Debugger par défaut est incapable d'attaquer les ennemis volant, sauf quand il atteint le niveau 4 (niveau maximal).
 */
public class Debugger extends BatimentTir {

    public Debugger(double x, double y, Environnement env) {
        super("Debugger",x, y, 200,2, 150,500, env);
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile projectile = new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
        switch (this.getNiveau()) {
            case 2:
                projectile.setVitesse((int) (projectile.getVitesse()*1.4));
                break;
            case 3:
                projectile.setVitesse((projectile.getVitesse()*2));
                break;
            case 4:
                projectile = new MissileFragmentation(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
                break;
        }
        return projectile;
    }
    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return !cible.estVolant();
    }

}
