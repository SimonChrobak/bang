package karty;

import java.util.Random;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Player;

public class Dynamit extends Karty {
    private Random random=new Random();
    private static final String CARD_NAME="Dynamit!";
    public Dynamit(Board board) {
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
        int index = 0;
        int sanca= random.nextInt(8);
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < players[i].getKartyNaStole().size(); j++) {
                Karty karta = players[i].getKartyNaStole().get(j);
                if (karta instanceof Dynamit) {
                    index=i;
                    if (sanca == 0) {
                        for (int k = 0; k < 3; k++) {
                            players[i].removeLife();
                        }
                        players[i].getKartyNaStole().remove(j);
                        System.out.println(players[i].getMeno() + " vybuchol dynamit a prisiel o 3 zivoty");
                        return false;
                    }
                    else {
                        if (index == 0) {
                            index= players.length;
                            players[index - 1].getKartyNaStole().add(players[0].getKartyNaStole().get(j));
                            players[0].getKartyNaStole().remove(j);
                            return true;
                        }
                        players[index-1].getKartyNaStole().add(players[index].getKartyNaStole().get(j));
                        players[index].getKartyNaStole().remove(j);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}

