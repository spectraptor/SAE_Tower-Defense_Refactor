package universite_paris8.iut.mcheema.codesource.modele;

import universite_paris8.iut.mcheema.codesource.modele.batiment.*;

public class FabriqueBatiment {
    private Environnement environnement;

    public FabriqueBatiment(Environnement env) {
        this.environnement = env;
    }
    public Batiment creerBatiment(int x, int y, TypeBatiment typeBatiment) {
        return switch (typeBatiment) {
            case BOMBE_LOGIQUE -> new BombeLogique(x, y, environnement);
            case CLOUD -> new Cloud(x, y, environnement);
            case COMPILATEUR -> new Compilateur(x, y, environnement);
            case DEBUGGER -> new Debugger(x, y, environnement);
            case RAM -> new RAM(x, y, environnement);
            case SURCADENCE -> new Surcadence(x, y, environnement);
        };
    }
}
