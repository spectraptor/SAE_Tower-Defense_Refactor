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
    private Niveau niveau;
    private Environnement environnement;
    private ConfigurationEnnemi configurationEnnemi;
    private boolean pauseEntreVague;
    private int framesPauseVague;

    public GestionVague(Niveau niveau, Environnement env, ConfigurationEnnemi configEnnemi) {
        this.numVagueCouranteProperty = new SimpleIntegerProperty(0);
        this.niveau = niveau;
        this.environnement = env;
        this.configurationEnnemi = configEnnemi;
        this.pauseEntreVague = false;
        this.framesPauseVague = 0;
        this.initialiserVagues();
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

    /**
     * Crée une vague, avec une liste d'ennemis.
     *
     * @param ennemis les ennemis qui seront dans la vague.
     *
     * @return une nouvelle instance de vague, avec une liste d'ennemis.
     */
    public Vague creerVague(ArrayList<Ennemi> ennemis) {
        return new Vague(this.environnement, ennemis);
    }

    /**
     * Ajoute des ennemis à une liste d'ennemis vide déjà créée.
     *
     * @param ennemis la liste d'ennemis, dans lequel on souhaite rajouter l'ennemi.
     * @param typeEnnemi le type d'ennemi à ajouter (enum)
     * @param quantite le nombre d ennemis de ce type qu'on souhaite ajouter.
     */
    public void ajouterEnnemis(ArrayList<Ennemi> ennemis, TypeEnnemi typeEnnemi, int quantite) {
        ennemis.addAll(this.configurationEnnemi.creerEnnemis(typeEnnemi, quantite, this.niveau.avoirTousLesChemins()));
    }

    public void initialiserVagues() {
        this.vagues = new Vague[MAX_VAGUES];

        ArrayList<Ennemi> ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.BOGUE, 3);
        vagues[0] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.PING, 7);
        vagues[1] = creerVague(ennemis);


        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.PING, 3);
        this.ajouterEnnemis(ennemis, TypeEnnemi.BOGUE, 3);
        vagues[2] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.BOGUE, 3);
        this.ajouterEnnemis(ennemis, TypeEnnemi.CHEVAL_TROIE, 1);
        vagues[3] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.CHEVAL_TROIE, 3);
        this.ajouterEnnemis(ennemis, TypeEnnemi.DRONE_ESPION, 1);
        vagues[4] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.DRONE_ESPION, 5);
        this.ajouterEnnemis(ennemis, TypeEnnemi.CHEVAL_TROIE, 5);
        vagues[5] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.BOGUE, 3);
        this.ajouterEnnemis(ennemis, TypeEnnemi.GROS_BOGUE, 3);
        this.ajouterEnnemis(ennemis, TypeEnnemi.RALENTISSEUR, 2);
        vagues[6] = creerVague(ennemis);

        ennemis = new ArrayList<>();
        this.ajouterEnnemis(ennemis, TypeEnnemi.ERREUR_EXECUTION, 1);
        this.ajouterEnnemis(ennemis, TypeEnnemi.ERREUR_LOGIQUE, 1);
        this.ajouterEnnemis(ennemis, TypeEnnemi.ERREUR_SYNTAXE, 1);
        vagues[7] = creerVague(ennemis);
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

    public boolean derniereVagueEstTerminee() {
        return this.getNumVagueCourante() == this.getNbreVagues() - 1
                && this.environnement.getEnnemis().isEmpty()
                && this.avoirEnnemisVagueCourante().isEmpty();
    }
}
