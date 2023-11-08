package karty;
import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
public class Barrel extends Karty {
    private static final String CARD_NAME="Barrel!";
    public Barrel(Board board) {
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
}