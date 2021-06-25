package airport;

import java.util.Objects;
import java.util.Random;

public class Passenger extends Thread{

    int idPassenger;
    private String pass;
    private CheckIn checkIn;
    private TypePassenger typePassenger;    // GRUPPO O SINGOLO
    private boolean checkInDone = false;    // SE HA COMPLETATO IL CHECK-IN


    boolean baggageBigDimension = false;
    private boolean checkInState= false;

    public Passenger(String name, TypePassenger typePassenger, CheckIn checkIn){
        super(name);
        this.checkIn = checkIn;
        this.typePassenger = typePassenger;

        Random rd = new Random();
        //GENERA RANDOM UN TIPO DI BAGAGLIO
        this.baggageBigDimension = rd.nextBoolean();

        this.start(); // avvio in fase di costruzione
    }


    @Override
    public void run() {

        while (this.checkInDone == false) {
            try {
                System.out.println("Passenger #" + getName() + " arriva alla ingresso. ");

                sleep((int) (Math.random() * 2000)); // tempo di arrivo alla coda

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkIn.addQueue(this.typePassenger, this.idPassenger);

//            ==================================================
            System.out.println("Passenger #" + getName() + " prova a fare il CHECK-IN");
            this.checkInState = checkIn.checkIn(this.typePassenger, this.idPassenger, this.baggageBigDimension);
            if (checkInDone) {
                System.out.println("Passenger #" + getName() + " effettua il check-in. ");
            }


            try {
                sleep((int) (Math.random() * 10000)); // tempo di check-in

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Passenger #" + getName() + " prova a fare il check-in ");

            if (checkInDone) {

                this.checkInDone = checkIn.lascia();
                System.err.println("Passenger #" + getId() + " CHECK-IN EFFETTUATO CON SUCCESSO! ");
            }


        }
    }
}
