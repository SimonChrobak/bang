package karty;
import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
public class Bang extends Karty {
    private static final String CARD_NAME="Bang!";
    public Bang(Board board) {
        super(CARD_NAME,board);

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
        super.playCard(player,players);
        for (int i=0;i<players.length;i++){
            System.out.println(i+1+" : "+players[i].getMeno());
        }
        int index=ZKlavesnice.readInt("vyber index hraca na ktoreho chces pouzit bang")-1;
        boolean zasah=false;
        while(zasah==false) {
            while(index<0 || index>players.length){
                System.out.println("neplatny index hraca");
                index=ZKlavesnice.readInt("vyber index hraca na ktoreho chces pouzit bang")-1;
            }
            if (players[index] == player) {
                System.out.println("bang na seba pouzit nemozes");
                index=ZKlavesnice.readInt("vyber index hraca na ktoreho chces pouzit bang")-1;
            }
            else {
                for(int i=0;i<players[index].getAllCards().size();i++) {
                    Karty karta = players[index].getAllCards().get(i);
                    if(players[index].getKartyNaStole().size()>0) {
                        for(int j=0;j<players[index].getKartyNaStole().size();j++) {
                            Karty cards = players[index].getKartyNaStole().get(j);
                            if (cards instanceof Barrel) {
                                zasah = true;
                                System.out.println("Hrac " + players[index].getMeno() + " odrazil Bang pomocou Barrelu na stole");
                                break;
                            }
                        }
                    }
                    if(zasah==true){
                        break;
                    }
                    if(karta instanceof Vedla) {
                        zasah=true;
                        players[index].getAllCards().remove(i);
                        System.out.println("Hrac "+players[index].getMeno()+" odrazil Bang pomocou karty Vedla");
                    }

                }
                if(zasah==false) {
                    System.out.println("Bang bol uspesny a hrac " + players[index].getMeno() + " prisiel o zivot");
                    players[index].removeLife();
                    break;
                }

            }
        }
        return zasah;
    }
}

