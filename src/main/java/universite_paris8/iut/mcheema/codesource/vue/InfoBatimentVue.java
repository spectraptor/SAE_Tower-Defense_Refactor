package universite_paris8.iut.mcheema.codesource.vue;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import universite_paris8.iut.mcheema.codesource.modele.Terrain;
import universite_paris8.iut.mcheema.codesource.modele.batiment.Batiment;
import universite_paris8.iut.mcheema.codesource.modele.batiment.BatimentAvecPortee;

/**
 * La classe InfoBatimentVue s'occupe d'afficher les informations liées à une tour déjà posée.
 * Elle s'occupe d'afficher un encadré lorsqu'on appuie sur la tour posé.
 * Elle se charge également du déplacement d'un bâtiment déjà acheté, de son amélioration ou encore sa supression.
 */
public class InfoBatimentVue {
    private final static int LARGEUR_PANE = 200;
    private final static int HAUTEUR_PANE = 200;
    private Batiment batiment;
    private Pane paneJeu;
    private SonVue sonVue;

    public InfoBatimentVue(Batiment batiment, Pane paneJeu, SonVue sonVue) {
        this.batiment = batiment;
        this.paneJeu = paneJeu;
        this.sonVue = sonVue;
    }

    public void afficheInfoBatiment() {
        if(this.paneJeu.lookup("#"+this.batiment.getId() + "I") == null) {

            //Pane dans lequel il y'a les informations, boutons (achat,vente,deplacement)
            Pane paneInfo = this.creePaneInfo();

            //Bouton qui permet de fermer le paneInfo
            this.creeBoutonQuitter(paneInfo);

            //VBox qui contient les 3 boutons ( vente,déplacement et amélioration)
            VBox boiteBoutonAction = this.creeBoiteBoutonAction(paneInfo);

            //HBox qui contient les boutons vente et déplacement
            HBox boiteVenDep = this.creeBoiteVenDep(boiteBoutonAction);

            //Bouton situé tout en bas du PaneInfo pour l'amelioration du batiment
            this.creeBoutonAmeliorer(paneInfo,boiteBoutonAction,boiteVenDep);

            //Bouton vendre
            this.creeBoutonVendre(paneInfo,boiteVenDep);

            //Initialise le bouton Deplacement avec ses fonctionnalités
            this.initialiseBoutonDeplacer(paneInfo,boiteVenDep);

            //Permet de faire en sorte que le PaneInfo ne sorte pas de la fenetre de jeu, s'adapte à la ou le batiment se situe
            this.bonPositionnementPaneInfo(paneInfo);
        }
    }

    public Pane creePaneInfo() {
        Pane paneInfo = new Pane();
        paneInfo.setId(this.batiment.getId() + "I");
        paneInfo.setPrefWidth(LARGEUR_PANE);
        paneInfo.setPrefHeight(HAUTEUR_PANE);
        this.paneJeu.getChildren().add(paneInfo);

        //Label qui affiche les informations
        Label textInfo = new Label();
        textInfo.setPrefWidth(LARGEUR_PANE);
        textInfo.setPrefHeight(HAUTEUR_PANE - HAUTEUR_PANE /4);
        textInfo.setId("textInfoBatiment");
        textInfo.setText(this.batiment.avoirDescription());
        textInfo.setPadding(new Insets(5, 0, 5, 15));
        paneInfo.getChildren().add(textInfo);

        return paneInfo;
    }

    public void creeBoutonQuitter(Pane paneInfo) {
        Button boutonQuitter = new Button("X");
        boutonQuitter.setPrefWidth(35);
        boutonQuitter.setPrefHeight(35);
        boutonQuitter.setTranslateX(LARGEUR_PANE - boutonQuitter.getPrefWidth());
        boutonQuitter.setTranslateY(0);
        paneInfo.getChildren().add(boutonQuitter);
        boutonQuitter.setId("boutonQuitterInfoBat");

        boutonQuitter.setOnAction(event -> {
            this.sonVue.jouerRetirer();
            this.paneJeu.getChildren().remove(paneInfo);
        });
    }

    public VBox creeBoiteBoutonAction(Pane paneInfo) {
        VBox boiteBoutonAction = new VBox();
        boiteBoutonAction.setPrefWidth(LARGEUR_PANE);
        boiteBoutonAction.setPrefHeight(HAUTEUR_PANE/4);
        boiteBoutonAction.setTranslateX(0);
        boiteBoutonAction.setTranslateY(HAUTEUR_PANE-HAUTEUR_PANE/4);
        paneInfo.getChildren().add(boiteBoutonAction);
        return boiteBoutonAction;
    }

    public HBox creeBoiteVenDep(VBox boiteBoutonAction) {
        HBox boiteVenDep = new HBox();
        boiteVenDep.setPrefWidth(boiteBoutonAction.getPrefWidth());
        boiteVenDep.setPrefHeight(boiteBoutonAction.getPrefHeight());
        boiteBoutonAction.getChildren().add(boiteVenDep);
        return boiteVenDep;
    }

    public void creeBoutonAmeliorer(Pane paneInfo, VBox boiteBoutonAction, HBox boiteVenDep) {
        Button boutonAmeliorer = new Button("Ameliorer");
        boutonAmeliorer.setId("boutonAmeliorer");
        boutonAmeliorer.setPrefWidth(LARGEUR_PANE);
        boutonAmeliorer.setPrefHeight(boiteBoutonAction.getPrefHeight() - boiteVenDep.getPrefHeight());
        boiteBoutonAction.getChildren().add(boutonAmeliorer);


        if(this.batiment.getNiveau() == this.batiment.getNiveauMax()) {
            boutonAmeliorer.setDisable(true);
        }

        boutonAmeliorer.setOnMouseEntered(e-> {
            boutonAmeliorer.setText(Integer.toString(this.batiment.coutProchaineAmelioration()) + " OCTET");
        });
        boutonAmeliorer.setOnMouseExited(e-> {
            boutonAmeliorer.setText("Ameliorer");
        });

        boutonAmeliorer.setOnAction(e -> {
            this.sonVue.jouerAmeliorer();
            this.batiment.ameliorerBatiment();
            this.paneJeu.getChildren().remove(paneInfo);
        });
    }

    public void creeBoutonVendre(Pane paneInfo,HBox boiteVenDep) {
        Button boutonVendre = new Button("Vendre");
        boutonVendre.setPrefWidth(LARGEUR_PANE / 2);
        boutonVendre.setPrefHeight(HAUTEUR_PANE / 4);
        boiteVenDep.getChildren().add(boutonVendre);
        boutonVendre.setId("boutonVendre");

        boutonVendre.setOnMouseEntered(e-> {
            boutonVendre.setText("+"+Integer.toString(this.batiment.avoirPrixVente()));
        });
        boutonVendre.setOnMouseExited(e-> {
            boutonVendre.setText("Vendre");
        });
        boutonVendre.setOnAction(event -> {
            this.sonVue.jouerRetirer();
            this.batiment.getEnvironnement().vendreBatiment(batiment);
            this.paneJeu.getChildren().remove(paneInfo);
        });
    }


    public void initialiseBoutonDeplacer(Pane paneInfo, HBox boiteVenDep) {
        Button boutonDeplacer = new Button("Deplacer");
        boutonDeplacer.setPrefWidth(LARGEUR_PANE /2);
        boutonDeplacer.setPrefHeight(HAUTEUR_PANE / 4);
        boiteVenDep.getChildren().add(boutonDeplacer);
        boutonDeplacer.setId("boutonDeplacer");

        boutonDeplacer.setOnAction(event -> {
            this.sonVue.jouerDeplacer();
            // Enleve le PaneInfo lorsqu'on clique sur le bouton déplacer
            this.paneJeu.getChildren().remove(paneInfo);
            // Crée un Pane temporaire pour le clique du déplacement du batiment ce Pane a exactement les memes dimension que le Pane de Jeu
            Pane paneTemp = new Pane();
            paneTemp.setPrefWidth(this.paneJeu.getPrefWidth());
            paneTemp.setPrefHeight(this.paneJeu.getPrefHeight());
            paneTemp.setId(this.batiment.getId()+"D");
            this.paneJeu.getChildren().add(paneTemp);

            // Label qui affiche le cout du déplacement du batiment le cout est un dixieme du prix du batiment multiplié par le nombre de tuile déplacé
            Label labelCoutDep = new Label();
            labelCoutDep.setPrefWidth(220);
            labelCoutDep.setPrefHeight(50);
            labelCoutDep.setDisable(true);
            labelCoutDep.setTranslateX(this.paneJeu.getPrefWidth() - labelCoutDep.getPrefWidth());
            labelCoutDep.setTranslateY(this.paneJeu.getPrefHeight() - labelCoutDep.getPrefHeight());
            labelCoutDep.setId("labelCoutDepBat");
            this.paneJeu.getChildren().add(labelCoutDep);

            // Crée une ImageView temporaire pour avoir un apercu du nouvelle emplacement du batiment
            ImageView imgVBat = (ImageView) this.paneJeu.lookup("#" + this.batiment.getId());
            ImageView nouvImgVBat = new ImageView(imgVBat.getImage());
            nouvImgVBat.setDisable(true);
            nouvImgVBat.setOpacity(0.5);
            nouvImgVBat.setId(this.batiment.getId() + "DEP");
            nouvImgVBat.setVisible(false);
            this.paneJeu.getChildren().add(nouvImgVBat);

            // Portee visible lors du déplacement du batiment pour les batiments avec une portée
            Circle rayonB;
            if(this.batiment instanceof BatimentAvecPortee) {
                rayonB = new Circle();
                BatimentAvecPortee bat = (BatimentAvecPortee) this.batiment;
                rayonB.setRadius(bat.getPortee()); // permet de visualiser le rayon de la tour

                /* Style visuel */
                rayonB.setFill(Color.TRANSPARENT);
                rayonB.setStroke(Color.BLACK);
                rayonB.setStrokeWidth(2.5);
                rayonB.setDisable(true);
                rayonB.setOpacity(0.5);
                rayonB.setVisible(false);
                this.paneJeu.getChildren().add(rayonB);
            } else {
                rayonB = null;
            }

            // Désactive la vBox car si il y'a un rayon qui sort du pane et va dans la vBox cela peut faire crash
            VBox vbox = (VBox) paneJeu.getScene().lookup("#vBoxBoutons");
            vbox.setDisable(true);

            // Permet d'avoir des coordonnées sans passer par le pane de jeu sinon le joueur pourra déplacer a l'infini le batiment
            paneTemp.setOnMouseClicked(e -> {
                this.sonVue.jouerPoserConfirmer();
                this.batiment.deplacerBatiment((int)e.getX(),(int)e.getY());
                this.paneJeu.getChildren().remove(paneTemp);
                this.paneJeu.getChildren().remove(nouvImgVBat);
                this.paneJeu.getChildren().remove(labelCoutDep);
                this.paneJeu.getChildren().remove(rayonB);
                vbox.setDisable(false);
            });

            // Permet d'afficher la nouvelle position semi-transparente du déplacement du batiment et également le cout du déplacement
            paneTemp.setOnMouseMoved(e-> {
                // Enleve l'image si la tuile actuelle n'est pas utilisable par le batiment
                if(this.batiment.getEnvironnement().tuileContientUnBatiment((int)e.getX(),(int)e.getY()) || !this.batiment.getEnvironnement().tuileTourPosable((int)e.getX(),(int)e.getY())) {
                    nouvImgVBat.setVisible(false);
                    if(rayonB != null) {
                        rayonB.setVisible(false);
                    }
                }

                else {
                    // Affiche l'image si la tuile est une tuile posable pour le batiment
                    nouvImgVBat.setVisible(true);
                    if(rayonB != null) {
                        rayonB.setVisible(true);
                    }
                }

                // Force le positionnement du batiment au centre de la tuile.
                int[] tabLigneColBat = this.batiment.getEnvironnement().getTerrainDeJeu().mettreCoordsSurCentreTuile((int) e.getX(), (int) e.getY());

                int distance = (int)(Math.abs(tabLigneColBat[1] - this.batiment.getX()) + Math.abs(tabLigneColBat[0] - this.batiment.getY())) / Terrain.TAILLE_TUILLE;
                labelCoutDep.setText("  Prix du déplacement : " + Integer.toString(this.batiment.getPrix()/10 * distance));

                // Force l'affichage de l'image au centre de la tuile pour avoir un apercu conforme au placement du batiment
                nouvImgVBat.setTranslateX((tabLigneColBat[1] - Terrain.TAILLE_TUILLE/2));
                nouvImgVBat.setTranslateY((tabLigneColBat[0] - Terrain.TAILLE_TUILLE/2));

                // Le rayon suit la souris
                if(rayonB != null) {
                    rayonB.setCenterX(tabLigneColBat[1]);
                    rayonB.setCenterY(tabLigneColBat[0]);
                }
            });
        });
    }

    public void bonPositionnementPaneInfo(Pane paneInfo) {
        if (this.batiment.getX() <= this.paneJeu.getPrefWidth() / 2) {
            paneInfo.setTranslateX(batiment.getX());
        } else {
            paneInfo.setTranslateX(batiment.getX() - paneInfo.getPrefWidth());
        }
        if (this.batiment.getY() <= this.paneJeu.getPrefHeight() / 2) {
            paneInfo.setTranslateY(batiment.getY());
        } else {
            paneInfo.setTranslateY(batiment.getY() - paneInfo.getPrefHeight());
        }
    }

}
