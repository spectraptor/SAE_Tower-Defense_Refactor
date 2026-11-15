package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * La classe Base constitue la base du jeu, c'est à dire la chose que doit défendre le joueur.
 * La base contient des points de vie. Elle peut en perdre si elle se fait attaquer, et mourir si ses points de vie deviennent nuls.
 */
public class Base {
    private IntegerProperty pvProperty;

    public Base () {
        this.pvProperty = new SimpleIntegerProperty(10);
    }

    public final void setPv(int pv) {this.pvProperty.setValue(pv);}

    public final int getPv() {return this.pvProperty.getValue();}

    public final IntegerProperty pvProperty() { return this.pvProperty;}

    public void subirDegats(int degats) {
        if (this.getPv()-degats < 0) {
            this.setPv(0);
        }
        else {
            this.setPv(this.getPv() - degats);
        }
    }

    public boolean estDetruite() {
        return getPv() <= 0;
    }
}