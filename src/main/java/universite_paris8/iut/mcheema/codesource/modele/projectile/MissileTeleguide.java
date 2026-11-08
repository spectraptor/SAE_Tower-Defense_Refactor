package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

public abstract class MissileTeleguide extends Projectile {
    private Ennemi cible;

    public MissileTeleguide(double x, double y, int degat, int vitesse, Ennemi cible, Environnement env) {
        super(x, y, degat, vitesse,env);
        this.cible = cible;
    }

    public Ennemi getCible() {
        return this.cible;
    }

    @Override
    public void gererImpact() {
        this.getCible().subirDegats(this.getDegat());
    }

    @Override
    public double[] calculDifference() {
        double[] difference = new double[2];
        difference[0] = this.getCible().getX() - this.getX();
        difference[1] = this.getCible().getY() - this.getY();
        return difference;
    }

}
