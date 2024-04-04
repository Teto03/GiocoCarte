public class MainGioco {
    public static void main(String[] args) {

        Arbitro arbitro = new Arbitro();

        Giocatore[] giocatori = new Giocatore[Globali.NUM_GIOCATORI]; // [1

        for (int i = 0; i < Globali.NUM_GIOCATORI; i++) {
            giocatori[i] = new Giocatore(i, arbitro);
            giocatori[i].start();
        }

        for (int i = 0; i < Globali.NUM_GIOCATORI; i++) {
            try {
                giocatori[i].join();
            } catch (InterruptedException e) {
                System.out.println("il gioco è terminato con errore!");
                break;
            }
        }

        System.out.println("il gioco è finito!");
    }
}
