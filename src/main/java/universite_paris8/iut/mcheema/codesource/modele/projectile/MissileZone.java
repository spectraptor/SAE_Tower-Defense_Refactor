package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;
import java.util.Objects;

public class MissileZone extends Projectile {
    private int portee;
    private double xCible;
    private double yCible;

    public MissileZone(double x, double y, int degat, double xCible, double yCible, Environnement env) {
        super(x, y, degat, 2,env);
        this.portee = 20;
        this.xCible = xCible;
        this.yCible = yCible;
    }

    @Override
    public void effectueAction() {
        double distX = xCible - this.getX();
        double distY = yCible - this.getY();
        double distance = Math.sqrt(distX * distX + distY * distY);
        if(distance <= this.getVitesse()) {
            ArrayList<Ennemi> listeEnnemis = this.ennemisDansPortee();
            for(Ennemi ennemi : listeEnnemis) {
                ennemi.subirDegats(this.getDegat());
            }
            this.setEstArrive(true);
        }
        else {
            this.deplaceMissile(distX,distY,distance);
        }
    }

    public ArrayList<Ennemi> ennemisDansPortee() {
        ArrayList<Ennemi> listeEnnemis = new ArrayList<>();
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            double distX = ennemi.getX() - this.getX();
            double distY = ennemi.getY() - this.getY();
            double distance = distX * distX + distY * distY;
            if(distance <= this.portee * this.portee) {
                listeEnnemis.add(ennemi);
            }
        }
        return listeEnnemis;
    }
}
