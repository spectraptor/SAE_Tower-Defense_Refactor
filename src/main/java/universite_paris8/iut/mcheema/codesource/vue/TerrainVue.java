package universite_paris8.iut.mcheema.codesource.vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import universite_paris8.iut.mcheema.codesource.modele.*;

/**
 * La classe TerrainVue à pour tâche de s'occuper de l'affichage du terrain et de la création de leur tuiles.
 * Elle va associer chaque numéro de tableau de tuile dans l'Environnement à une image (texture).
 */

public class TerrainVue {
    private Terrain terrainDeJeu;
    private TilePane tilePane;

    public TerrainVue(Terrain terrainDeJeu, TilePane tilePane) {
        this.terrainDeJeu = terrainDeJeu;
        this.tilePane = tilePane;
    }

    public void afficheTerrainJeu() {
        Image imgSol = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/posables/sol.png").toExternalForm());
        Image imgGlitch1 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/nonPosable/glitch1.png").toExternalForm());
        Image imgGlitch2 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/nonPosable/glitch2.png").toExternalForm());
        Image imgGlitch3 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/nonPosable/glitch3.png").toExternalForm());
        Image imgGlitch4 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/nonPosable/glitch4.png").toExternalForm());
        Image imgGlitch5 = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/nonPosable/glitch5.png").toExternalForm());
        Image ch_gauche_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin1.png").toExternalForm());
        Image ch_haut_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin2.png").toExternalForm());
        Image ch_droite_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin3.png").toExternalForm());
        Image ch_bas_gauche = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin4.png").toExternalForm());
        Image ch_gauche_haut = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin5.png").toExternalForm());
        Image ch_haut_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin6.png").toExternalForm());
        Image ch_haut_droite_bas = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin7.png").toExternalForm());
        Image ch_droite_bas_gauche = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin8.png").toExternalForm());
        Image ch_bas_gauche_haut = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin9.png").toExternalForm());
        Image ch_gauche_haut_droite = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/chemin10.png").toExternalForm());
        Image base = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/texture/tuiles/chemins/base.png").toExternalForm());
        for (int i = 0; i < this.terrainDeJeu.obtenirHauteur(); i++) {
            for (int j = 0; j < this.terrainDeJeu.obtenirLargeur(); j++) {
                switch (this.terrainDeJeu.avoirCodeTuile(i,j)) {
                    case '1':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_droite));
                        break;
                    case '2':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_bas));
                        break;
                    case '3':
                        this.tilePane.getChildren().add(new ImageView(ch_droite_bas));
                        break;
                    case '4':
                        this.tilePane.getChildren().add(new ImageView(ch_bas_gauche));
                        break;
                    case '5':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_haut));
                        break;
                    case '6':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_droite));
                        break;
                    case '7':
                        this.tilePane.getChildren().add(new ImageView(ch_haut_droite_bas));
                        break;
                    case '8':
                        this.tilePane.getChildren().add(new ImageView(ch_droite_bas_gauche));
                        break;
                    case '9':
                        this.tilePane.getChildren().add(new ImageView(ch_bas_gauche_haut));
                        break;
                    case '0':
                        this.tilePane.getChildren().add(new ImageView(ch_gauche_haut_droite));
                        break;
                    case 'b', 's':
                        this.tilePane.getChildren().add(new ImageView(base));
                        break;
                    case 'h':
                        this.tilePane.getChildren().add(new ImageView(imgSol));
                        break;
                    case 'e':
                        this.tilePane.getChildren().add(new ImageView(imgGlitch1));
                        break;
                    case 'c':
                        this.tilePane.getChildren().add(new ImageView(imgGlitch2));
                        break;
                    case 'd':
                        this.tilePane.getChildren().add(new ImageView(imgGlitch3));
                        break;
                    case 'f':
                        this.tilePane.getChildren().add(new ImageView(imgGlitch4));
                        break;
                    case 'g':
                        this.tilePane.getChildren().add(new ImageView(imgGlitch5));
                        break;
                }
            }
        }
    }

}
