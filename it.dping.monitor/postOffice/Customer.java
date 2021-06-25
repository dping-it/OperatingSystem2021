package postOffice;

public class Customer extends Thread{
    private int idCustomer;	//contatore cliente
    FrontOffice frontOffice; // monitor
    private boolean consulenza;	// tipo di servizio
    private boolean servito= false;	// servito?

    public Customer(int idCustomer, FrontOffice frontOffice, boolean consulenza){	//costruttore a cui passo il monitor
        super();
        this.idCustomer = idCustomer;
        this.frontOffice = frontOffice;
          //potrei generarla randomicamente qui
        this.consulenza= consulenza;
        start();

    }

    public boolean rinuncaConsulenza(){	// metodo di accesso al buffer condiviso
        this.consulenza= false;
        return false;
    }

    public void run() {		// comportamento
        frontOffice.addQueue(this.idCustomer, this.consulenza);
        while(servito= false){
       //
      //      System.out.println("Il cliente " +id + " ha acquistato " + bigliettiComprati + " di " + bigliettiDesiderati + "biglietti in rivendita " + rivendita.id);
            //System.out.println("R"+rivendita.id +" C"+id+ "-->"+bigliettiComprati+ " di " + bigliettiDesiderati);
        }
    }
}
