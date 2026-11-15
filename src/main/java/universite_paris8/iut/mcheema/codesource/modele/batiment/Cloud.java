package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.EnnemiCamoufle;
import universite_paris8.iut.mcheema.codesource.modele.projectile.MissileTete;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

public class Cloud extends BatimentTir {
    public Cloud(double x, double y, Environnement env) {
        super("Cloud", x, y, 50, 1, 150, 100, env);
    }


    @Override
    public boolean peutAttaquer(Ennemi cible) {
        return !cible.estCamoufle();
    }

    @Override
    public Projectile choisirProjectile(Ennemi cible) {
        Projectile projectile =  new MissileTete(this.getX(),this.getY(),this.getDegat(),cible,this.getEnvironnement());
        switch(this.getNiveau()) {
            case 2:
                projectile.setVitesse((int) (projectile.getVitesse() * 1.2));
                break;
            case 3:
                projectile.setVitesse((int) (projectile.getVitesse() * 1.5));
                break;
            case 4 :
                projectile.setVitesse(projectile.getVitesse()*2);
                break;
        }
        return projectile;
    }

}
