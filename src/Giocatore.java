import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Giocatore extends Thread{

private int id;
private Arbitro arbitro;

List<Integer> carte = new ArrayList<Integer>(Globali.NUM_CARTE);

public Giocatore(int id, Arbitro arbitro) {
    this.id=id;
    this.arbitro=arbitro;
    for (int i = 1; i < Globali.NUM_CARTE; i++) {
        carte.add(i);
    }
    mescola();
}
public void mescola(){
    Collections.shuffle(carte);//rimescola le carte
}

@Override
public void run() {
    for (int  carta : carte) {
        arbitro.gioca(id, carta);   
    }
    if(arbitro.vincitore()==id){
        System.out.println("Il giocatore "+id+" ha vinto");
    }else{
        System.out.println("Il giocatore "+id+" ha perso");
    }
}
}