import java.util.Arrays;

public class Arbitro {
    int[] arraysGiocatoriMancanti= new int[Globali.NUM_GIOCATORI];
    int [][] giocate= new int[Globali.NUM_GIOCATORI][Globali.NUM_CARTE];
    //per ogni giocatore insrrisco che carta ha giocato nei vari turni 
    int turno =0;

    public Arbitro(){
        resetArrayGiocatoriMancanti();
    }

    private void resetArrayGiocatoriMancanti() {
    Arrays.fill(arraysGiocatoriMancanti, 1);
    }    


    public synchronized void gioca(int id, int carta){
        System.out.println("Il giocatore "+id+" ha giocato la carta "+carta);
        giocate[id][turno]=carta;//per ogni turno metto nella casella del giocatore la carta che ha giocato
        arraysGiocatoriMancanti[id]=0;
        verificaFineTurno();
    }

public boolean verificaFineTurno(){
    int tot=0;
    for (int id : arraysGiocatoriMancanti) {
        tot += id;
        if(tot==0){
            turno++;
            resetArrayGiocatoriMancanti();
            System.out.println("Fine turno "+turno);
            return true;
        }
    }
    return false;
}

    public int vincitore(){
        return 0;
    }
}
