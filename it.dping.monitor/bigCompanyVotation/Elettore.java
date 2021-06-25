package bigCompanyVotation;

import java.util.Random;

public class Elettore extends Thread {

    boolean votationDone = false;
    Category category;
    int idElettore;
    Seggio seggio;

    public Elettore(int idElettore, Category category, Seggio seggio){
        super();
        this.idElettore= idElettore;
        this.category = category;
        this.seggio = seggio;

        start();
    }

    @Override
    public void run() {
        super.run();
    }
}
