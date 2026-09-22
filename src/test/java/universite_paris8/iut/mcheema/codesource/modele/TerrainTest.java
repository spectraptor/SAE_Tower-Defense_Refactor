package universite_paris8.iut.mcheema.codesource.modele;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class TerrainTest {

    private Terrain terrain1;
    private Terrain terrain2;
    private Terrain terrain3;

    @BeforeEach
    void faireTerrain() {
        terrain1 = new Terrain(1);
        terrain2 = new Terrain(2);
        terrain3 = new Terrain(3);
    }

    @Test
    void convertirCoordsTuile_valide() {
        // Coordonnées situées à l'intérieur d'une tuile
        int[] tabTuileT1 = terrain1.convertirCoordsTuile(68, 39);
        assertEquals(1, tabTuileT1[0], "cas ligne terrain 1");
        assertEquals(2, tabTuileT1[1], "cas colonne terrain 1");

        int[] tabTuileT2 = terrain2.convertirCoordsTuile(0, 145);
        assertEquals(4, tabTuileT2[0], "cas ligne terrain 2");
        assertEquals(0, tabTuileT2[1], "cas colonne terrain 2");


        int[] tabTuileT3 = terrain3.convertirCoordsTuile(204, 98);
        assertEquals(3, tabTuileT3[0], "cas ligne terrain 3");
        assertEquals(6, tabTuileT3[1], "cas colonne terrain 3");
    }

    @Test
    void convertirCoordsTuile_limites() {
        // Coordonnées placées exactement ou juste après une frontière de tuile
        // Étant donné que la première tuile va de 0 à 31, à partir de 32 on considère qu'on est à la deuxième.
        int[] tabTuileT1Limite = terrain1.convertirCoordsTuile(32, 64);
        assertEquals(2, tabTuileT1Limite[0], "cas ligne terrain 1");
        assertEquals(1, tabTuileT1Limite[1], "cas colonne terrain 1");

        int[] tabTuileT2Limite = terrain2.convertirCoordsTuile(65, 96);
        assertEquals(3, tabTuileT2Limite[0], "cas ligne terrain 2");
        assertEquals(2, tabTuileT2Limite[1], "cas colonne terrain 2");


        int[] tabTuileT3Limite = terrain3.convertirCoordsTuile(97, 128);
        assertEquals(4, tabTuileT3Limite[0], "cas ligne terrain 3");
        assertEquals(3, tabTuileT3Limite[1], "cas colonne terrain 3");

    }

    @Test
    void tuileEstAccessibleCoords_valide_chemin() {
        // Terrain 1 : case '1' ligne 3 colonne 12
        assertTrue(terrain1.tuileEstAccessibleCoords(384, 96));

        // Terrain 2 : case '1' ligne 2 colonne 12
        assertTrue(terrain2.tuileEstAccessibleCoords(384, 64));

        // Terrain 3 : case '1' ligne 7 colonne 4
        assertTrue(terrain3.tuileEstAccessibleCoords(128, 224));
    }

    @Test
    void tuileEstAccessibleCoords_valide_base() {
        // Terrain 1 : base ligne 12 colonne 0
        assertTrue(terrain1.tuileEstAccessibleCoords(0, 384));

        // Terrain 2 : base ligne 7 colonne 0
        assertTrue(terrain2.tuileEstAccessibleCoords(0, 224));

        // Terrain 3 : base ligne 7 colonne 9
        assertTrue(terrain3.tuileEstAccessibleCoords(288, 224));
    }

    @Test
    void tuileEstAccessibleCoords_invalide() {
        // Terrain 1 : case 'h' ligne 2 colonne 6
        assertFalse(terrain1.tuileEstAccessibleCoords(192, 64));

        // Terrain 2 : case 'h' ligne 1 colonne 0
        assertFalse(terrain2.tuileEstAccessibleCoords(0, 32));

        // Terrain 3 : case 'e' ligne 0 colonne 0
        assertFalse(terrain3.tuileEstAccessibleCoords(0, 0));
    }

    @Test
    void avoirCodeTuile() {
        // Vérifie que le code retourné correspond bien à la case ciblée

        assertEquals('1', terrain1.avoirCodeTuile(6, 5), "cas terrain 1 ligne 7 colonne 6");

        // Terrain 2 : case '2', ligne 7 colonne 6
        assertEquals('2', terrain2.avoirCodeTuile(6, 5), "cas terrain 2 ligne 7 colonne 6");

        // Terrain 3 : case 'h', ligne 7 colonne 6
        assertEquals('h', terrain3.avoirCodeTuile(6, 5), "cas terrain 3 ligne 7 colonne 6");
    }

    @Test
    void estDansTerrain_valide() {
        assertTrue(terrain1.estDansTerrain(7, 5));
        assertTrue(terrain2.estDansTerrain(4, 0));
        assertTrue(terrain3.estDansTerrain(0, 15));
    }

    @Test
    void estDansTerrain_limites() {
        // Vérifie que la dernière case du terrain est toujours considérée comme valide
        int hauteurTerrains = terrain1.obtenirHauteur();
        int largeurTerrains = terrain1.obtenirLargeur();

        assertTrue(terrain1.estDansTerrain(hauteurTerrains - 1,largeurTerrains - 1));
        assertTrue(terrain2.estDansTerrain(hauteurTerrains - 1, largeurTerrains - 1));
        assertTrue(terrain3.estDansTerrain(hauteurTerrains - 1, largeurTerrains - 1));
    }

    @Test
    void estDansTerrain_invalide() {
        // Coordonnées négatives ou hors terrain
        assertFalse(terrain1.estDansTerrain(19, -1), "cas uniquement ligne hors du terrain");
        assertFalse(terrain2.estDansTerrain(-6, 0), "cas uniquement colonne hors du terrain");
        assertFalse(terrain3.estDansTerrain(-9, -9), "cas ligne et colonne hors du terrain");
    }

    @Test
    void tuileEstPosable_invalideDecor() {
        // Les cases de décor (glitchs, 'e', 'c', 'd', 'f', 'g') ne doivent pas accepter de bâtiment
        assertFalse(terrain1.tuileTourPosable(0, 0), "cas case glitch terrain 1");
        assertFalse(terrain2.tuileTourPosable(32, 0), "cas case glitch terrain 2");
        assertFalse(terrain3.tuileTourPosable(0, 0), "cas case glitch terrain 3");
    }

    @Test
    void tuileEstPosable_invalideChemin() {
        // Les cases appartenant au chemin (chiffres) des ennemis ne doivent pas accepter de bâtiment
        assertFalse(terrain1.tuileTourPosable(384, 96), "cas case chemin terrain 1");
        assertFalse(terrain2.tuileTourPosable(160, 64), "cas case chemin terrain 2");
        assertFalse(terrain3.tuileTourPosable(128, 224), "cas case chemin terrain 3");
    }
}