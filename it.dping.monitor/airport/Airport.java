package airport;
/*        In un aeroporto sono presenti N postazioni per il check-in, divise in egual numero tra quelle presso cui è
        presente personale delle diverse compagnie aeree (check-in assistito) e quelle automatiche (self-drop).
        I passeggeri si recano alle postazioni check-in, da soli o in gruppo, con un bagaglio personale che può essere
        di dimensione ordinaria o di grandi dimensioni.
        I bagagli di dimensione ordinaria potranno essere consegnati sia presso le postazioni di check-in assistito che
        presso quelle self-drop. I bagagli di grandi dimensioni (attrezzature sportive, strumenti musicali, etc) potranno
        essere consegnati esclusivamente presso le postazioni di check-in assistito.
        I passeggeri che raggiungono le postazioni di check-in in gruppo (ad esempio famiglie con bambini) non
        potranno usufruire del servizio self-drop e avranno priorità maggiore rispetto agli altri passeggeri.
        Si modelli lo scenario descritto mediante thread in linguaggio Java usando il costrutto monitor.
        Si descriva la sincronizzazione tra thread, discutendo anche se la soluzione proposta può presentare rinvio
        indefinito o deadlock. In tal caso, si propongano delle soluzioni implementative per evitare i due fenomeni.   */

import java.util.Random;

public class Airport {
    public static void main(String[] args) {


        //=============== PARAMETRI INIZZIALIZZAZIONE =================//
        TypePassenger typePassenger;

        //String companies[]={"AIRITALY","RAYANAIR","VOLOTEA","MERIDIANA","ALLITALIA"};

        int nOperatores = 5;
        int nSelfDropes = 5;

        int nPassengers = 100;


        //MONITOR
        CheckIn assistits[] = new CheckIn[nOperatores];
        CheckIn selfdropes[] = new CheckIn[nSelfDropes];

        for (int i = 0; i < nOperatores; i++) {

            assistits[i] = new CheckIn(i, "ASSISTITO");
            assistits[i].toString();
        }

        for (int i = 0; i < nSelfDropes; i++) {

            selfdropes[i] = new CheckIn(i, "SELF DROP");
            selfdropes[i].toString();
        }

        // =============== INIZIALIZZAZIONE THREADES =================//


        //INIZIALIZZO I PASSEGERI


        Passenger passengers[] = new Passenger[nPassengers];

        for (int i =0; i<nPassengers ; i++) {

            Random rd = new Random();
            if(rd.nextBoolean()){
                typePassenger = TypePassenger.SINGLE_PASSENGER;
            }
            else {
                typePassenger = TypePassenger.GROUP_PASSENGERS;
            }

            //ASSEGNAZIONE RANDOMIZZATA CHECK-IN MONITOR

            if(rd.nextBoolean()){
                Passenger passenger = new Passenger("Passenger #"+i , typePassenger, assistits[rd.nextInt(nOperatores)]);
                passengers[i] = passenger;

            }else{
                Passenger passenger = new Passenger("Passenger #"+i , typePassenger, selfdropes[rd.nextInt(nSelfDropes)]);
                passengers[i] = passenger;
            }

        }

    }

}
