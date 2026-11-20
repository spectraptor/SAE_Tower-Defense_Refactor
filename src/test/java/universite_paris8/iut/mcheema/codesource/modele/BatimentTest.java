package universite_paris8.iut.mcheema.codesource.modele;
import universite_paris8.iut.mcheema.codesource.modele.batiment.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.ChevalDeTroie;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.DroneEspion;
import universite_paris8.iut.mcheema.codesource.modele.ennemi.Ping;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BatimentTest {

    private ArrayList<Point> chemin;

    private BombeLogique bombeLogique;
    private Cloud cloud;
    private RAM ram;

    private Batiment[] batiments;

    private Environnement environnement;

    @BeforeEach()
    void initialiserBatiments() {
        // Chemin minimal nécessaire à la création des ennemis
        this.chemin = new ArrayList<>();
        this.chemin.add(new Point(1,1));

        this.environnement = Environnement.getInstanceEnvironnement(1);
        this.environnement.reinitialiserEnvironnement(1);

        this.bombeLogique = new BombeLogique(0, 0, this.environnement);
        this.cloud = new Cloud(0, 0, this.environnement);
        this.ram = new RAM(0, 0, this.environnement);
        this.batiments = new Batiment[]{
                this.bombeLogique,
                this.cloud,
                new Debugger(0, 0, this.environnement),
                this.ram
        };
    }

    @Test
    void incrementerNiveau() {
        // Vérifie que tous les bâtiments gagnent exactement un niveau

        for (Batiment b : this.batiments) {
            int ancienNiv = b.getNiveau();

            b.incrementerNiveau();

            assertEquals(ancienNiv + 1, b.getNiveau(), "cas incrémenter niveau");
        }
    }

    @Test
    void coutAmelioration() {
        // Le coût d'amélioration doit augmenter après chaque niveau

        for(Batiment b : batiments) {
            int ancienPrix = b.coutProchaineAmelioration();

            b.incrementerNiveau();

            assertTrue(b.coutProchaineAmelioration() > ancienPrix);
        }
    }

    @Test
    void peutAttaquer_Cloud() {
        // Cloud peut détecter les ennemis classiques mais pas les ennemis camouflés (pas ChevalDeTroie)

        Ping ping = new Ping(this.environnement, this.chemin);
        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);

        assertTrue(this.cloud.peutAttaquer(ping), "cas ennemi non camouflé");
        assertFalse(this.cloud.peutAttaquer(chevalDeTroie), "cas ennemi camouflé");
    }


    @Test
    void peutAttaquer_BombeLogique() {
        // BombeLogique attaque uniquement les ennemis terrestres non camouflés (pas ChevalDeTroie ni DroneEspion)

        Ping ping = new Ping(this.environnement, this.chemin);
        DroneEspion droneEspion = new DroneEspion(this.environnement, this.chemin);
        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);

        assertTrue(this.bombeLogique.peutAttaquer(ping), "cas ennemi non camouflé");
        assertFalse(this.bombeLogique.peutAttaquer(droneEspion), "cas ennemi volant");
        assertFalse(this.bombeLogique.peutAttaquer(chevalDeTroie), "cas ennemi camouflé");
    }


    @Test
    /*
     * Les tests suivants sont pour démontrer (ou à l'inverse refuter) le fait qu'un bâtiment (ici Cloud) peut attaquer des ennemis dans sa portée.
     * Si le bâtiment est capable d'attaquer l'ennemi, alors un projectile est crée. Sinon, aucun n'est produit.
     */
    void effectueActionCloud_toucheEnnemiDansPortee() {
        // Cloud tire à chaque tour pour simplifier le test

        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        Ping ping = new Ping(this.environnement, this.chemin);

        // Ennemi placé dans la portée du Cloud
        // sqrt(21² + 23²) = 31 < 50 (portée du Cloud) -> peut tirer sur l'ennemi
        ping.setX(21);
        ping.setY(23);

        this.environnement.ajouterEnnemi(ping);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles + 1, this.environnement.getProjectiles().size(), "cas" +
                "cloud attaque sur ennemi dans sa portée");

    }

    @Test
    void effectueActionCloud_touchePasEnnemiSpecalDansPortee() {
        // Ennemi camouflé : Cloud ne doit pas tirer

        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        ChevalDeTroie chevalDeTroie = new ChevalDeTroie(this.environnement, this.chemin);
        chevalDeTroie.setX(21);
        chevalDeTroie.setY(23);

        this.environnement.ajouterEnnemi(chevalDeTroie);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles, this.environnement.getProjectiles().size(), "cas" +
                "cloud n'attaque pas l'ennemi");

    }

    @Test
    void effectueActionCloud_neTouchePasEnnemi() {
        // Ennemi placé hors de portée

        this.environnement.setNbTours(0);
        this.cloud.setCadenceTir(1);

        int ancienNbreProjectiles = this.environnement.getProjectiles().size();

        Ping ping = new Ping(this.environnement, this.chemin);

        // sqrt(51² + 50²) = 71 > 50 (portée du Cloud) -> hors de portée
        ping.setX(51);
        ping.setY(50);

        this.environnement.ajouterEnnemi(ping);

        this.cloud.effectueAction();

        assertEquals(ancienNbreProjectiles, this.environnement.getProjectiles().size(), "cas" +
                "cloud n'attaque pas l'ennemi");
    }

    @Test
    void effectueActionCloud_unSeulProjectile() {
        // Deux ennemis dans la portée :
        // un seul projectile doit être créé (pour un seul ennemi)

        cloud.setCadenceTir(1);
        environnement.setNbTours(0);

        Ping proche = new Ping(environnement, chemin);
        proche.setX(21);
        proche.setY(23);

        Ping autre = new Ping(environnement, chemin);
        autre.setX(25);
        autre.setY(25);

        environnement.ajouterEnnemi(proche);
        environnement.ajouterEnnemi(autre);

        cloud.effectueAction();

        assertEquals(1,
                environnement.getProjectiles().size(),
                "un seul ennemi doit être ciblé");
    }

    @Test
    void effectueActionCloud_aucunEnnemi() {
        // Aucun ennemi présent sur le terrain

        cloud.setCadenceTir(1);
        environnement.setNbTours(0);

        int nbreProjectiles = environnement.getProjectiles().size();

        cloud.effectueAction();

        assertEquals(nbreProjectiles,
                environnement.getProjectiles().size(),
                "aucun ennemi = aucun tir");
    }

    @Test
    void effectueActionRAM() {
        // La RAM produit de l'argent lorsque son temps d'attente est atteint

        // Met le nombre de tours de l'environnement égal au temps d'attente de la RAM pour qu'il puisse générer de l'argent.
        this.environnement.setNbTours(400);

        int ancienArgent = this.environnement.getArgent();

        this.ram.effectueAction();

        assertEquals(ancienArgent + this.ram.getArgentDonne(), this.environnement.getArgent(), "cas argent génerée par bat ram");
    }
}