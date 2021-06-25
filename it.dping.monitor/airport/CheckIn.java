package airport;

import java.util.LinkedList;

// MONITOR ( GENERALIZZA ASSISTITO E SELF DROP )
public class CheckIn {
    private final String company;
    boolean checkInControll = false;

    LinkedList<Integer> waitingQueue = new LinkedList<>();

    //VARIABILE DI CONDIZIONAMENTO  // 0 LIBERO - 1 OCCUPATO
    int state = 0;

    String type ;
    String companies[] = {"AIRITALY", "RAYANAIR", "VOLOTEA", "MERIDIANA", "ALLITALIA"};


    // =============== INIZIALIZZAZIONE MONITOR =================//

    public CheckIn(int id, String type) {

        //INIZIALIZZO GLI OPERATORI E SELFDROPES CHE SONO LE RISORSE CONDIVISE
        super();
        this.type = type;
        this.company = companies[id];

    }


    // METTI IN CODA DI ACCESSO AL CHECK-IN

    public synchronized void addQueue (TypePassenger typePassenger, int idPassenger){

        //la tracchia del passenger è tenuta dall'id del tread
        //la priorità è solo per il tipo di passegero e non dal bagaglio

        System.out.println("PASSENGER #" + Thread.currentThread().getId() + " " +
                "========== >>  PROVA A METTERSI IN FILA");

        //GESTIONE DELLE PRIORITA' DI ATTRAVERSAMENTO DEL MONITOR "STARVATIONS"   ==   CASO GRUPPO PASSEGERI

        if (typePassenger == TypePassenger.GROUP_PASSENGERS) {
            System.err.println("GRUPPO DI PASSEGGERI #" + Thread.currentThread().getId() + " HA PRIORITA' E SALTA LA FILA");
            waitingQueue.addFirst((int) Thread.currentThread().getId());
            notifyAll();

        } else {

            System.out.println("PASSEGGERO SINGOLO #" + Thread.currentThread().getId() + " NO PRIORITY SI METTE IN FILA'");
            waitingQueue.add((int) Thread.currentThread().getId());
            notifyAll();

        }

        printQueque(waitingQueue);
    }


    // ELABORAZIONE CODA

    public synchronized boolean checkIn (TypePassenger typePassenger, int idPassenger, boolean baggageBigDimension) {

        //CASO GRUPPO PASSEGERI CON BAGAGLIO SPECIALE
        if (typePassenger == TypePassenger.GROUP_PASSENGERS || baggageBigDimension == true) {


            if (this.type.equals("ASSISTITO")) { // SE SIAMO NEL MONITOR ASSISTITO
                while (checkInControll = false && idPassenger != waitingQueue.getFirst()) {
                    try {
                        System.out.println("Passenger #" + Thread.currentThread().getId() + " non ci sono varchi liberi al check-in assistito, aspetta!");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                checkInControll = true;
                waitingQueue.removeFirst();
                System.out.println("\nPassenger #" + Thread.currentThread().getId() + " fa il CHECK-IN Assistito");
                return true;
            } else if (this.type.equals("SELF DROP")) { // CASO MONITOR SELF DROP
                while (checkInControll = false && idPassenger != waitingQueue.getFirst()) {
                    try {
                        System.out.println("Passenger #" + Thread.currentThread().getId() + " non ci sono varchi liberi al check-in assistito, aspetta!");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                waitingQueue.removeFirst();
                System.out.println("\nPassenger #" + Thread.currentThread().getId() + " esce da questo CHECK-IN e si rimette in coda");
                checkInControll = false;

                int id = waitingQueue.size() >0 ? waitingQueue.getFirst() :0;
                waitingQueue.removeFirst();
                //RITENTERA' IL CHECK-IN
                if (id != 0){
                waitingQueue.addFirst(id);
            }}
        }
              //CASO PASSEGERO SINGOLO CON BAGAGLIO SEMPLICE
        else {
            // SE SIAMO NEL MONITOR GENERICO
                while (checkInControll = false && idPassenger != waitingQueue.getFirst()) {
                    try {
                        System.out.println("Passenger #" + Thread.currentThread().getId() + " non ci sono varchi liberi al check-in "+this.type+", aspetta!");
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                checkInControll = true;
                waitingQueue.removeFirst();
                System.out.println("\nPassenger #" + Thread.currentThread().getId() + " fa il CHECK-IN "+this.type);
                return true;

                }
        return false;
    }



    private void printQueque(LinkedList<Integer> waitingQueue) {
        System.out.println("CODA ATTESA CHECK-IN ====>>\n");
        for (int i =0 ; i< waitingQueue.size(); i++) {


            System.out.println("Passenger #" + waitingQueue.get(i) + " ===> è in coda: POSIZIONE "+i);
        }
        System.out.println("=======================================================\n");
    }

    public boolean lascia() {
        return true;
    }

    void transito(){
        try{
            Thread.sleep((int)Math.round(Math.random()*1000));
        } catch(Exception e){
            e.printStackTrace();
        }

    }
}
