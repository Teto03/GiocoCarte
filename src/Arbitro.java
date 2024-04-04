import java.util.Arrays;

public class Arbitro {
    int[] arraysGiocatoriMancanti= new int[Globali.NUM_GIOCATORI];
    int [][] giocate= new int[Globali.NUM_GIOCATORI][Globali.NUM_CARTE];

    //per ogni giocatore insrrisco che carta ha giocato nei vari turni 
    int turno =0;

    int[] numeroVittorie = new int[Globali.NUM_GIOCATORI]; // Array to track victories

    public Arbitro(){
        resetArrayGiocatoriMancanti();
        Arrays.fill(numeroVittorie, 0); // Initialize victories array
    }

    private void resetArrayGiocatoriMancanti() {
    Arrays.fill(arraysGiocatoriMancanti, 1);
    }    


    public synchronized void gioca(int id, int carta){
        //devo usare il wait per sincronizzare i thread sulla fine del turno
        try {
        while(arraysGiocatoriMancanti[id]==0){
                wait();
        }
        System.out.println("Il giocatore "+id+" ha giocato la carta "+carta);
        giocate[id][turno]=carta;//per ogni turno metto nella casella del giocatore la carta che ha giocato
        arraysGiocatoriMancanti[id]=0;
        if(verificaFineTurno()){
            notifyAll();
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    public boolean verificaFineTurno(){
        int tot=0;
        for (int id : arraysGiocatoriMancanti) {
            tot += id;
        }
        if(tot==0){
            turno++;
            resetArrayGiocatoriMancanti();
            System.out.println("Fine turno "+turno);
            aggiornaNumeroVittorie();
            return true;
        }
        return false;
    }

    private void aggiornaNumeroVittorie() {
        int vincitoreTurno=0;
        int cartaVincitore= giocate[vincitoreTurno][turno];
        boolean pareggio=false;
        for(int idGiocatore =1; idGiocatore<Globali.NUM_GIOCATORI; idGiocatore++){
            int cartaGiocatore=giocate[idGiocatore][turno];
            if(cartaGiocatore>cartaVincitore){
                vincitoreTurno=idGiocatore;
                pareggio=false;
        }
        if(cartaGiocatore==cartaVincitore){
            pareggio=true;
        }
    }
}     
    public synchronized int vincitore(){
        int vincitore=0;
        int numeroVittoria = numeroVittoriePerGiocatore[vincitore];
        boolean pareggio=false;
        for(int idGiocatore =1; idGiocatore<Globali.NUM_GIOCATORI; idGiocatore++){
            if(numeroVittoria<numeroVittoriePerGiocatore[idGiocatore]){
                vincitore=idGiocatore;
                pareggio=false;
            }
            if(numeroVittoria==numeroVittoriePerGiocatore[idGiocatore]){
                pareggio=true;
            }
        }
    }
}
