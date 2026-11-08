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



    public ArrayList<Ennemi> ennemisDansPortee() {
        ArrayList<Ennemi> listeEnnemis = new ArrayList<>();
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            double distX = ennemi.getX() - this.xCible;
            double distY = ennemi.getY() - this.yCible;
            double distance = this.calculDistance(distX,distY);
            if(distance <= this.portee) {
                listeEnnemis.add(ennemi);
            }
        }
        return listeEnnemis;
    }

    @Override
    public void gererImpact() {
        ArrayList<Ennemi> listeEnnemis = this.ennemisDansPortee();
        for(Ennemi ennemi : listeEnnemis) {
            ennemi.subirDegats(this.getDegat());
        }
    }

    @Override
    public double[] calculDifference() {
        double[] difference = new double[2];
        difference[0] = this.xCible - this.getX();
        difference[1] = this.yCible - this.getY();
        return difference;
    }
}
