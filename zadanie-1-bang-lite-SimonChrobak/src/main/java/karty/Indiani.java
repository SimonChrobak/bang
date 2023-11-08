package karty;

import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Player;
public class Indiani extends Karty {
    private static final String CARD_NAME="Indiani!";
    public Indiani(Board board) {
        super(CARD_NAME, board);
    }

    @Override
    public boolean canPlay() {
        return true;
    }

    @Override
    public boolean canPlay(int index) {
        return true;
    }

    @Override
    public boolean playCard(Player player, Player[] players) {
        super.playCard(player, players);
        for(int i=0;i<players.length;i++) {
            boolean indiani=false;
            if(players[i]==player){
                System.out.println("Indiani neutocia na hraca ktory ich zahral");
            }
            else {
                for (int j = 0; j < players[i].getAllCards().size(); j++) {
                    Karty karta = players[i].getAllCards().get(j);
                    if (karta instanceof Bang) {
                        indiani=true;
                        players[i].getAllCards().remove(j);
                        System.out.println("Hrac " + players[i].getMeno() + " odrazil Indianov pomocou karty Bang");
                        break;
                    }
                }
                if(indiani==false){
                    players[i].removeLife();
                    System.out.println(player.getMeno()+" nema na ruke kartu Bang a prichadza o 1 zivot");
                }
            }
        }

        return false;
    }
}
