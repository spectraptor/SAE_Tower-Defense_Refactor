package universite_paris8.iut.mcheema.codesource.modele.batiment;

import universite_paris8.iut.mcheema.codesource.modele.Entite;
import universite_paris8.iut.mcheema.codesource.modele.Environnement;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
/**
 * La classe Batiment est la classe mère des bâtiments. Elle définit les méthodes communes à tous les bâtiments.
 * Par défaut, chaque bâtiment à un nom, un prix, et un niveau d'amélioration max.
 * Elle se charge également de la gestion des bâtiments (achat, vente, déplacement).
 */

public abstract class Batiment extends Entite {
    private static int idCpt = 0;
    private String nom;
    private int prix;
    private int niveau;
    private int niveauMax;

    // Réduction de 10 %
    public static final double TAUX_AMELIORATION_PAR_NIVEAU = 0.10;

    public Batiment(String nom, double x, double y, int prix, int nivMax, Environnement env) {
        super("B" + idCpt++, x, y, env);
        this.nom = nom;
        this.prix = prix;
        this.niveau = 1;
        this.niveauMax = nivMax;
    }

    public int getPrix() {
        return this.prix;
    }

    public int getNiveau() {
        return this.niveau;
    }

    public String getNom() {
        return this.nom;
    }

    public int getNiveauMax() {
        return this.niveauMax;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
  
    public int avoirPrixVente() {
        return this.niveau * this.prix / 4;
    }

    public void vendreBatiment() {
        this.getEnvironnement().getBatiments().remove(this);
        this.getEnvironnement().setArgent(this.getEnvironnement().getArgent() + this.avoirPrixVente());
    }

    /**
     * Déplace le bâtiment vers une nouvelle position sur le terrain.
     * Le déplacement est soumis à plusieurs conditions :
     * - la tuile cible doit être valide et libre
     * - le joueur doit disposer de suffisamment d'argent pour payer le coût du déplacement
     *
     * Le coût du déplacement est calculé en fonction de la distance (en tuiles) entre
     * la position actuelle et la nouvelle position, multiplié par 1/10 du prix du batiment.
     *
     * @param nouvX la nouvelle coordonnée X du batiment
     * @param nouvY la nouvelle coordonnée Y du batiment
     */
    public void deplacerBatiment(int nouvX,int nouvY) {
        int[] tabLigneColCentreBat = this.getEnvironnement().getTerrainDeJeu().mettreCoordsSurCentreTuile(nouvX, nouvY);
        double distanceNbTuile = (Math.abs(this.getX() - tabLigneColCentreBat[1]) + Math.abs(this.getY() - tabLigneColCentreBat[0]))/Terrain.TAILLE_TUILLE;
        int prixDepBatiment = (int) ((this.getPrix() /10) * distanceNbTuile);
        if(this.getEnvironnement().getArgent() >= prixDepBatiment) {
            if(!this.getEnvironnement().partieEstFinie()) {
                if(this.getEnvironnement().tuileTourPosable(nouvX,nouvY) && !this.getEnvironnement().tuileContientUnBatiment(tabLigneColCentreBat[1],tabLigneColCentreBat[0])) {
                    this.setX(tabLigneColCentreBat[1]);
                    this.setY(tabLigneColCentreBat[0]);
                    this.getEnvironnement().setArgent((this.getEnvironnement().getArgent() - prixDepBatiment));
                }
            }
        }
    }

    public void incrementerNiveau() {
        this.setNiveau(this.getNiveau() + 1);
    }

    public abstract void ameliorerBatiment();

    public int coutProchaineAmelioration() {
        return (this.getNiveau()+1) *(this.getPrix() / 4);
    }


    /**
     * Calcule le coefficient d'amélioration lié au niveau du bâtiment.
     * Ce coefficient augmente proportionnellement au niveau du bâtiment,
     * en utilisant une base fixe (REDUCTION_NIVEAU).
     * Il est utilisé pour :
     * - augmenter certaines caractéristiques (ex : portee)
     * - réduire d'autres (ex : temps de recharge)
     * Exemple :
     * Niveau 1 → 10% (0.1)
     * Niveau 2 → 20% (0.2)
     *
     * @return un coefficient d'amélioration dépendant du niveau actuel du bâtiment
     *
     */
    public double tauxAmeliorationParNiveau() {
        return this.getNiveau() * TAUX_AMELIORATION_PAR_NIVEAU;
    }


    public String avoirDescription() {
        return   this.getNom() +  "      \n\n" + "Niveau actuel : " + this.getNiveau() + "/" + this.getNiveauMax() + "\n";
    }


}
