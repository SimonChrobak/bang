package karty;

import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;

public class Vedla extends Karty {

    private final static String CARD_NAME="Vedla!";
    public Vedla(Board board) {
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
