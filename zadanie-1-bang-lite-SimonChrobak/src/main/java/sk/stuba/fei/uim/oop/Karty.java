package sk.stuba.fei.uim.oop;

public abstract class Karty {
    protected String name;
    protected Board board;


    public Karty(String name, sk.stuba.fei.uim.oop.Board board) {
        this.name=name;
        this.board=board;
    }

    public abstract boolean canPlay();

    public abstract boolean canPlay(int index);

    public boolean playCard(Player player, Player[] players) {
        System.out.println("--- " + player.getMeno() + " choose " + this.name + " card to play. ---");
        return true;
    }

    public String getName() {
        return name;
    }
}
