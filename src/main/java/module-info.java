module universite_paris8.iut.mcheema.codesource {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;
    requires jdk.compiler;

    opens universite_paris8.iut.mcheema.codesource to javafx.fxml;
    exports universite_paris8.iut.mcheema.codesource;
    exports universite_paris8.iut.mcheema.codesource.controleur;
    opens universite_paris8.iut.mcheema.codesource.controleur to javafx.fxml;
    exports universite_paris8.iut.mcheema.codesource.modele;
    opens universite_paris8.iut.mcheema.codesource.modele to javafx.fxml;
}