package universite_paris8.iut.mcheema.codesource.modele.ennemi;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Point;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;

import java.util.ArrayList;

public abstract class EnnemiVolant extends Ennemi {
    public EnnemiVolant(int pv, int vitesse, int argentDonne, Environnement env, ArrayList<Point> chemin) {
        super(pv, vitesse, argentDonne, env, chemin);
    }

    @Override
    public void seDeplace() {
        Point base = this.getChemin().get(this.getChemin().size() -1);
        double xBase = base.avoirXPixels();
        double yBase = base.avoirYPixels();
        double distX = xBase - this.getX();
        double distY = yBase - this.getY();
        this.verifieProchainMouvement(distX,distY);
    }


    public void verifieProchainMouvement(double distX,double distY) {
        double distance = Math.sqrt(distX * distX + distY * distY);
        if(distance <= this.getVitesse()) {
            this.setIndiceChemin(this.getChemin().size()-1);
        }
        else {
            double vUnitX = distX / distance;
            double vUnitY = distY / distance;
            this.setX(this.getX() + vUnitX * this.getVitesse());
            this.setY(this.getY() + vUnitY * this.getVitesse());
        }
    }

}
