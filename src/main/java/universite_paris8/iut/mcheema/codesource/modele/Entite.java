package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * La classe Entite est la classe mère de tous les "espèces" dans le jeu : projectiles, ennemis, et bâtiments.
 * Elle définit les caractéristiques et méthodes communes à chacunes.
 */
public abstract class Entite {
    private String id;
    private DoubleProperty xProperty;
    private DoubleProperty yProperty;
    private Environnement environnement;

    public Entite(String id, double x, double y, Environnement env) {
        this.id = id;
        this.xProperty = new SimpleDoubleProperty(x);
        this.yProperty = new SimpleDoubleProperty(y);
        this.environnement = env;
    }

    public String getId() {
        return this.id;
    }

    public final DoubleProperty xProperty() {
        return this.xProperty;
    }

    public final DoubleProperty yProperty() {
        return this.yProperty;
    }

    public final double getX() {
        return this.xProperty.getValue();
    }

    public final double getY() {
        return this.yProperty.getValue();
    }

    public final void setX(double x) {
        this.xProperty.set(x);
    }

    public final void setY(double y) {
        this.yProperty.set(y);
    }


    public Environnement getEnvironnement() {
        return this.environnement;
    }

    /**
     * Calcul la distance euclidienne entre deux entités.
     * @param ent l'entite dont on voudrait savoir la distance
     * @return la distance euclidienne entre les deux entites
     */
    public double calculDistance(Entite ent) {
        return  (Math.sqrt((this.getX() - ent.getX()) * (this.getX() - ent.getX()) +
                (this.getY() - ent.getY()) * (this.getY() - ent.getY())));
    }

    /**
     * Vérifie si la distance euclidienne entre deux entités est dans la portée
     * @param ent l'entité avec laquelle on souhaite vérifier
     * @param portee la portee qui doit etre verifier
     * @return vrai si la portee est superieur ou egal a la distance euclidienne sinon faux
     */
    public boolean estDansLaPortee(Entite ent, int portee) {
        double calculDistance = this.calculDistance(ent);
        return calculDistance <= portee;
    }

    public abstract void effectueAction();
}
