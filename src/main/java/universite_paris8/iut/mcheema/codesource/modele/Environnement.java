package universite_paris8.iut.mcheema.codesource.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ennemi;
import universite_paris8.iut.mcheema.codesource.modele.projectile.Projectile;

/**
* La classe Environnement gère l'ensemble du fonctionnement du jeu. Il effectue les actions à chaque tour,
* l'apparition de tous les élements du jeu (base, terrain, liste d'ennemis, liste de batiments).
 */
public class Environnement {
    private ObservableList<Ennemi> ennemis;
    private ObservableList<Batiment> batiments;
    private ObservableList<Projectile> projectiles;
    private GestionVague gestionVague;
    private Terrain terrainDeJeu;
    private int nbTours;
    private Base base;
    private SimpleIntegerProperty argentProperty;


    public Environnement(int niveau) {
        this.ennemis = FXCollections.observableArrayList();
        this.batiments = FXCollections.observableArrayList();
        this.projectiles = FXCollections.observableArrayList();
        this.terrainDeJeu = new Terrain(niveau);
        this.gestionVague = new GestionVague(niveau, this);
        this.nbTours = 0;
        this.base = new Base();
        this.argentProperty = new SimpleIntegerProperty(500);
    }

    public Terrain getTerrainDeJeu() {
        return this.terrainDeJeu;
    }

    public ObservableList<Ennemi> getEnnemis() {
        return this.ennemis;
    }

    public ObservableList<Batiment> getBatiments() {
        return this.batiments;
    }

    public ObservableList<Projectile> getProjectiles() {
        return this.projectiles;
    }

    public GestionVague getGestionVague() {
        return this.gestionVague;
    }

    public void ajouterEnnemi(Ennemi ennemi) {
        this.ennemis.add(ennemi);
    }

    public void ajouterBatiment(Batiment batiment) {
        this.batiments.add(batiment);
    }

    public void ajouterProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    public Base getBase() {
        return this.base;
    }

    public void setNbTours(int tour) {
        this.nbTours = tour;
    }

    public void unTour() {
        if(!this.partieEstFinie()) {
            this.gestionVague.mettreAJour();

            for (int i = this.getEnnemis().size() -1 ;i>=0;i--) {
                if (this.getNbTours() % 5 == 0) {
                    if(this.getEnnemis().get(i).estVivant()) {
                        this.getEnnemis().get(i).effectueAction();
                    }
                    else {
                        this.getEnnemis().remove(i);
                    }
                }
            }

            for(int i = this.getBatiments().size() -1 ;i>=0;i--) {
                this.getBatiments().get(i).effectueAction();
            }

            for(int i = this.getProjectiles().size() -1 ;i>=0;i--) {
                this.getProjectiles().get(i).effectueAction();
                if(this.getProjectiles().get(i).getEstArrive()) {
                    this.getProjectiles().remove(i);
                }
            }
            this.nbTours++;
        }
    }


    public boolean tuileTourPosable(int x, int y) {
        return this.getTerrainDeJeu().tuileTourPosable(x , y);
    }

    /**
     * Regarde si une tour est déja présente dans une tuile
     * @param x les coordonnées x de la tuile
     * @param y les coordonnées y de la tuile
     * @return true si une tuile est déjà présente, false autrement.
     */
    public boolean tuileContientUnBatiment(int x, int y) {
        boolean sortieBoucle = false;
        int i = 0;
        while (i < this.batiments.size() && !sortieBoucle) {
            if(x == this.batiments.get(i).getX() && y == this.batiments.get(i).getY()) {
                sortieBoucle = true;
            }
            i++;
        }
        return sortieBoucle;
    }

    /**
     * Permet de poser un batiment au sein du jeu.
     * @param x l'abscisse du batiment
     * @param y l'ordonnée du batiment
     * @param numBat le type de batiment à poser
     * @return le batiment à poser
     */
    public void poserBatiment(int x, int y, int numBat) {
        Batiment batimentAPoser = null;
        int[] tabLigneColonneBatCentre = this.terrainDeJeu.mettreCoordsSurCentreTuile(x, y);

        if (!this.partieEstFinie()) {
            if (this.tuileTourPosable(x, y) && !this.tuileContientUnBatiment(tabLigneColonneBatCentre[1],
                    tabLigneColonneBatCentre[0])) {
                switch (numBat) {
                    case 1:
                        batimentAPoser = new Compilateur(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                    case 2:
                        batimentAPoser = new Cloud(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                    case 3:
                        batimentAPoser = new Debugger(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                    case 4:
                        batimentAPoser = new BombeLogique(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                    case 5:
                        batimentAPoser = new Surcadence(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                    case 6:
                        batimentAPoser = new RAM(tabLigneColonneBatCentre[1], tabLigneColonneBatCentre[0], this);
                        break;
                }
                this.acheterBatiment(batimentAPoser);
            }
        }
    }


    public boolean partieEstFinie() {
        return this.getBase().estDetruite() || (this.gestionVague.getNumVagueCourante() ==
                this.gestionVague.getNbreVagues() - 1 && this.ennemis.isEmpty() && this.gestionVague.
                getVagues()[this.gestionVague.getNumVagueCourante()].getlisteEnnemisVague().isEmpty());
    }

    public int getNbTours() {
        return this.nbTours;
    }

    public final void setArgent(int argent) {this.argentProperty.setValue(argent);}

    public final int getArgent() {return this.argentProperty.getValue();}

    public final IntegerProperty argentProperty() { return this.argentProperty;}

    public void ajouterArgent(int argent) {
        this.argentProperty.setValue(getArgent() + argent);
    }

    public void retirerArgent(int argent) {
        this.argentProperty.setValue(getArgent() - argent);
    }

    public void acheterBatiment(Batiment batiment) {
        if(this.getArgent() >= batiment.getPrix()) {
            this.ajouterBatiment(batiment);
            this.setArgent(this.getArgent() - batiment.getPrix());
        }
    }

    public void vendreBatiment(Batiment batiment) {
        this.getBatiments().remove(batiment);
        this.setArgent(this.getArgent() + batiment.avoirPrixVente());
    }
}

