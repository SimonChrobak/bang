package karty;

import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Player;

import java.util.Random;

public class Vazenie extends Karty {
    private Random random= new Random();
    private static final String CARD_NAME="Vazenie!";
    public Vazenie(Board board) {
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
        int sanca= random.nextInt(4);
        if(sanca==0){
            System.out.println("Vazenie bolo uspesne a "+player.getMeno()+" vynechava kolo");
            return false;
        }
        else {
            System.out.println("Vazenie nebolo uspesne");
            return true;
        }
    }
}
