package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.ennemi.*;
import java.util.ArrayList;

/**
 * La classe Vague s'occupe de la gestion individuelle des vagues.
 * Elle contient la liste des ennemis pour chaque vague.
 * Elle regarde uniquement si la vague est terminée, mais n'ajoute pas les ennemis.
 */
public class Vague {
    private ArrayList<Ennemi> listeEnnemisVague;
    private Environnement environnement;

    public Vague(Environnement env, ArrayList<Ennemi> ennemis) {
        this.environnement = env;
        this.listeEnnemisVague = ennemis;
    }

    public ArrayList<Ennemi> getlisteEnnemisVague() {
        return this.listeEnnemisVague;
    }

    public boolean vagueEstTerminee() {
        // une vague est terminée uniquement quand tous les ennemis prévus sont déployes, et qu'ils sont tous éliminés.
        return this.listeEnnemisVague.isEmpty() && this.environnement.getEnnemis().isEmpty();
    }

}
