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
        switch (ennemiType) {
            case BOGUE:
                return new Bogue(this.environnement, chemin);
            case CHEVAL_TROIE:
                return new  ChevalDeTroie(this.environnement, chemin);
            case DRONE_ESPION:
                return new DroneEspion(this.environnement, chemin);
            case ERREUR_LOGIQUE:
                return new ErreurDeLogique(this.environnement, chemin);
            case ERREUR_SYNTAXE:
                return new ErreurDeSyntaxe(this.environnement, chemin);
            case ERREUR_EXECUTION:
                return new ErreurExecution(this.environnement, chemin);
            case GROS_BOGUE:
                return new GrosBogue(this.environnement, chemin);
            case PING:
                return new Ping(this.environnement, chemin);
            case RALENTISSEUR:
                return new Ralentisseur(this.environnement, chemin);
        }

        return null;
    }
}
