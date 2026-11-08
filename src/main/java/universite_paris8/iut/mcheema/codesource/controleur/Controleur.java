package universite_paris8.iut.mcheema.codesource.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import universite_paris8.iut.mcheema.codesource.Main;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeBatiments;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeEnnemis;
import universite_paris8.iut.mcheema.codesource.controleur.listeners.ObservateurListeProjectiles;
import universite_paris8.iut.mcheema.codesource.modele.*;
import javafx.util.Duration;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;
import universite_paris8.iut.mcheema.codesource.vue.SonVue;
import universite_paris8.iut.mcheema.codesource.vue.TerrainVue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Le controleur fait la gestion entre le modèle et la vue.
 * Il s'occupe d'effectuer les différents bindings, et d'ajouter les listeners nécessaires, et possède la gameloop.
 * Il s'occupe également des interactions utilisateurs, tels que l'achat d'un bâtiment lorsqu'on appuie sur un bouton.
 */

public class Controleur implements Initializable {

    private Timeline gameLoop;

    private Environnement environnement;

    private boolean etatRapide = false;

    private boolean etatPause = true;

    private TypeBatiment typeBatimentSelectionne = null;

    private SonVue sonVue;

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane paneJeu;

    @FXML
    private Label labelVieBase;

    @FXML
    private  Label labelArgent;

    @FXML
    private Label labelVague;

    @FXML
    private Pane menuPause;

    @FXML
    private Button boutonLancerPause;

    @FXML
    private BorderPane borderPanePrincipal;

    @FXML
    private Pane paneFin;

    @FXML
    private Label labelResultat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initialiseBoutonStop();
        borderPanePrincipal.lookupAll(".button").forEach(node -> {
            node.setOnMouseEntered(e -> sonVue.jouerClique());
            node.setOnMouseClicked(e -> sonVue.jouerBoutonCliquer());
        });
        initAnimation();
    }

    public void chargerNiveau(int niveau) {

        this.environnement = new Environnement(niveau);

        this.labelVieBase.textProperty().bind(this.environnement.getBase().pvProperty().asString());
        this.labelArgent.textProperty().bind(this.environnement.argentProperty().asString());
        this.labelVague.textProperty().bind(this.environnement.getGestionVague().numVagueCouranteProperty().add(1).asString());

        // Listener sur l'Observable Liste d'ennemis
        this.environnement.getEnnemis().addListener(new ObservateurListeEnnemis(this.paneJeu, this.sonVue));
        this.environnement.getBatiments().addListener(new ObservateurListeBatiments(this.paneJeu, this.sonVue));
        this.environnement.getProjectiles().addListener(new ObservateurListeProjectiles(this.paneJeu, this.sonVue));

        this.tilePane.setPrefSize(this.environnement.getTerrainDeJeu().obtenirLargeur() * Terrain.TAILLE_TUILLE, this.environnement.getTerrainDeJeu().obtenirHauteur() * Terrain.TAILLE_TUILLE);
        TerrainVue terrainVue = new TerrainVue(this.environnement.getTerrainDeJeu(), this.tilePane);

        terrainVue.afficheTerrainJeu();
    }

    private void initAnimation() {

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017),
                ev -> {
                    environnement.unTour();
                    if (environnement.partieEstFinie()) {
                        gameLoop.stop();
                        if (environnement.getBase().getPv() > 0) {
                            labelResultat.setText("VICTOIRE");
                            labelResultat.setStyle("-fx-text-fill: lime;");
                        } else {
                            labelResultat.setText("DEFAITE");
                            labelResultat.setStyle("-fx-text-fill: red;");
                        }
                        paneFin.setVisible(true);
                        paneFin.toFront();
                    }
                }
        );
        gameLoop.getKeyFrames().add(kf);
    }

    @FXML
    public void ajouterBatiment(MouseEvent mouseEvent) {
        int coordsSourisX = (int) mouseEvent.getX();
        int coordsSourisY = (int) mouseEvent.getY();

        if (this.typeBatimentSelectionne != null) {
            this.environnement.poserBatiment(coordsSourisX, coordsSourisY, this.typeBatimentSelectionne);
            this.typeBatimentSelectionne = null;
        }
    }

    @FXML
    public void choisirBatiment(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getSource();
        typeBatimentSelectionne = TypeBatiment.valueOf(bouton.getId());
    }

    @FXML
    public void afficheReglage(ActionEvent actionEvent) {
        etatPause = true;
        gameLoop.pause();
        menuPause.setVisible(true);
        menuPause.toFront();
    }

    @FXML
    public void accelererOuRalentir(ActionEvent actionEvent) {
        if (etatRapide) {
            gameLoop.setRate(1);
            etatRapide = false;
            System.out.println("Jeu mis en x1");

        }
        else {
            gameLoop.setRate(2);
            etatRapide = true;
            System.out.println("Jeu mis en x2");
        }
    }

    @FXML
    public void lancer(ActionEvent actionEvent) {
        Image imgPause = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/pause.png").toExternalForm());
        Image imgLancer = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/lancer.png").toExternalForm());
        ImageView imgV;
        if (this.etatPause) {
            etatPause = false;
            this.gameLoop.play();
            menuPause.setVisible(false);
            imgV = new ImageView(imgPause);
        } else {
            this.etatPause = true;
            this.gameLoop.pause();
            imgV = new ImageView(imgLancer);
        }
        imgV.setFitHeight(50);
        imgV.setFitWidth(45);
        this.boutonLancerPause.setGraphic(imgV);
    }

    @FXML
    public void quitter(ActionEvent actionEvent) throws IOException {
        this.gameLoop.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("vueAccueil.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initialiseBoutonStop() {
        Image imgLancer = new Image(getClass().getResource("/universite_paris8/iut/mcheema/codesource/images/boutons/lancer.png").toExternalForm());
        ImageView imgVLancer = new ImageView(imgLancer);
        imgVLancer.setFitHeight(50);
        imgVLancer.setFitWidth(45);
        this.boutonLancerPause.setGraphic(imgVLancer);
    }

    public void setSonVue(SonVue sonVue) {
        this.sonVue = sonVue;
    }

}
