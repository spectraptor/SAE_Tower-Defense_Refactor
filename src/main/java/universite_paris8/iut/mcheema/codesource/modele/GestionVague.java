package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;

import java.util.ArrayList;

/**
 * La classe GestionVague se tâche de mettre en place et d'organiser
 * les différentes vagues, pour que les ennemis apparaissent et qu'elles se succèdent correctement.
 * C'est cette classe qui s'occupe d'insérer les ennemis à chaque vague dans l'environnement, avec un certain délai.
 * Elle définit également le nombre de vagues maximales.
 */
public class GestionVague {

    private static final int BONUS_SOMME_ENTRE_VAGUES = 100;
    private static final int BONUS_COEFFICIENT_ENTRE_VAGUES = 10;

    private static final int DELAI_ENTRE_VAGUES_FRAMES = 300;
    private static final int TEMPS_SPAWN_ENNEMIS_FRAMES = 100;
    private static final int MAX_VAGUES = 8;


    private IntegerProperty numVagueCouranteProperty;
    private Vague[] vagues;
    private Environnement environnement;
    private boolean pauseEntreVague;
    private int framesPauseVague;

    public GestionVague(int numNiv, Environnement env) {
        this.numVagueCouranteProperty = new SimpleIntegerProperty(0);
        this.vagues = new Vague[MAX_VAGUES];

        for (int i = 0; i < MAX_VAGUES; i++) {
            this.vagues[i] = new Vague(i + 1, numNiv, env);
        }

        this.environnement = env;
        this.pauseEntreVague = false;
        this.framesPauseVague = 0;
    }

    public Vague[] getVagues() {
        return this.vagues;
    }


    public int getNbreVagues() {
        return MAX_VAGUES;
    }

    public final IntegerProperty numVagueCouranteProperty() {
        return this.numVagueCouranteProperty;
    }

    public final int getNumVagueCourante() {
        return this.numVagueCouranteProperty.getValue();
    }

    public final void setNumVagueCourante(int val) {
        this.numVagueCouranteProperty.setValue(val);
    }

    public Vague obtenirVagueCourante() {
        return this.vagues[this.getNumVagueCourante()];
    }

    public ArrayList<Ennemi> avoirEnnemisVagueCourante() {
        return this.obtenirVagueCourante().getlisteEnnemisVague();
    }

    public void faireApparaitreEnnemis() {
        if (!this.avoirEnnemisVagueCourante().isEmpty() && this.environnement.getNbTours() % TEMPS_SPAWN_ENNEMIS_FRAMES == 0)
            this.environnement.ajouterEnnemi(this.avoirEnnemisVagueCourante().remove(0));
    }

    public boolean vagueCouranteEstTerminee() {
        return this.obtenirVagueCourante().vagueEstTerminee();
    }

    public void verifierFinVague() {
        if (this.vagueCouranteEstTerminee())
            this.pauseEntreVague = true;
    }

    public void gererPausesEntreVagues() {
        // Regarde si la vague est en "pause".
        if (this.pauseEntreVague)
            this.framesPauseVague++;

        if (this.framesPauseVague >= DELAI_ENTRE_VAGUES_FRAMES) {
            this.passerNouvelleVague();
            this.framesPauseVague = 0;
            this.pauseEntreVague = false;
        }
    }

    //  Méthode principale qui gère le fonctionnement de chaque vague (insertition des ennemis dans l'environnement avec un délai, pauses...)
    public void mettreAJour() {
        this.faireApparaitreEnnemis();
        this.verifierFinVague();
        this.gererPausesEntreVagues();
    }

    /**
     * Incrémente le numéro de vague pour passer à la vague suivante.
     */
    public void passerNouvelleVague() {
        // Ligne obligatoire : sinon, lorsque la derniere vague est finie,
        // on va à nouveau incrémenter et accéder à un indice qui n'existe pas.
        if (this.getNumVagueCourante() < this.getNbreVagues() - 1) {
            this.environnement.ajouterArgent(GestionVague.BONUS_SOMME_ENTRE_VAGUES + this.getNumVagueCourante() * GestionVague.BONUS_COEFFICIENT_ENTRE_VAGUES);
            this.setNumVagueCourante(this.getNumVagueCourante() + 1);
        }
    }
}
