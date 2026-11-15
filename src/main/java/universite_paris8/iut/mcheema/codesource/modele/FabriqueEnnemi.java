package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;

import java.util.ArrayList;

/**
 * La classe FabriqueEnnemi résulte du Design Pattern Simple Factory.
 * Son unique responsabilité est de créer des ennemis, avec la méthode creerEnnemi.
 */
public class FabriqueEnnemi {
    private Environnement environnement;
    
    public FabriqueEnnemi(Environnement env) {
        this.environnement = env;
    }
    
    public Ennemi creerEnnemi(TypeEnnemi ennemiType, ArrayList<Point> chemin) {
        return switch (ennemiType) {
            case BOGUE -> new Bogue(this.environnement, chemin);
            case CHEVAL_TROIE -> new ChevalDeTroie(this.environnement, chemin);
            case DRONE_ESPION -> new DroneEspion(this.environnement, chemin);
            case ERREUR_LOGIQUE -> new ErreurDeLogique(this.environnement, chemin);
            case ERREUR_SYNTAXE -> new ErreurDeSyntaxe(this.environnement, chemin);
            case ERREUR_EXECUTION -> new ErreurExecution(this.environnement, chemin);
            case GROS_BOGUE -> new GrosBogue(this.environnement, chemin);
            case PING -> new Ping(this.environnement, chemin);
            case RALENTISSEUR -> new Ralentisseur(this.environnement, chemin);
        };

    }
}
