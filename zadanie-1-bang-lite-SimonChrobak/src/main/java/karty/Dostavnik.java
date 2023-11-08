package karty;
import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Player;
public class Dostavnik extends Karty {
    private static final String CARD_NAME="Dostavnik!";
    public Dostavnik(Board board) {
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
        return true;
    }
}