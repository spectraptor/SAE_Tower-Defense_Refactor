package universite_paris8.iut.mcheema.codesource.modele.projectile;

import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

public class MissileFragmentation extends MissileTeleguide {
    // Va a ces coord et va ensuite se fragmenté en plusieurs missiles a tete chercheuse
    private final static int X_SUIVRE = 320;
    private final static int Y_SUIVRE = 6;
    private final static int DISTANCE_MISSILE = 8;
    public MissileFragmentation(double x, double y,int degat, Ennemi cible, Environnement env) {
        super(x, y,degat, 2,cible, env);
    }

    @Override
    public double[] calculDifference() {
        double[] difference = new double[2];
        difference[0] = X_SUIVRE - this.getX();
        difference[1] = Y_SUIVRE - this.getY();
        return difference;
    }

    @Override
    public void gererImpact() {
        ArrayList<Ennemi> autreCibles = this.selectionnerCiblesSecondaires();
        int nombreEnnemis = autreCibles.size();
        Ennemi c1 = this.getCible();
        Ennemi c2 = this.getCible();
        Ennemi c3 = this.getCible();
        if (!autreCibles.isEmpty()) {
            c2 = autreCibles.get(0);
            if (nombreEnnemis >= 2)
                c3 = autreCibles.get(1);
        }
        this.creeProjectiles(c1,c2,c3);
    }

    public void creeProjectiles(Ennemi c1,Ennemi c2,Ennemi c3) {
        this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX(), this.getY(), this.getDegat(), c1, this.getEnvironnement()));
        this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() + DISTANCE_MISSILE, this.getY(), this.getDegat(), c2, this.getEnvironnement()));
        this.getEnvironnement().getProjectiles().add(new MissileTete(this.getX() - DISTANCE_MISSILE, this.getY(), this.getDegat(), c3, this.getEnvironnement()));
    }

    public ArrayList<Ennemi> selectionnerCiblesSecondaires() {
        ArrayList<Ennemi> cibles = new ArrayList<>();
        for (Ennemi ennemi : this.getEnvironnement().getEnnemis()) {
            if (ennemi != this.getCible()) {
                cibles.add(ennemi);
            }
        }
        return cibles;
    }



}
