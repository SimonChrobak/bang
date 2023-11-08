package karty;
import sk.stuba.fei.uim.oop.Board;
import sk.stuba.fei.uim.oop.Karty;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Random;

public class CatBalou extends Karty {
    private static final String CARD_NAME="Cat Balou!";
    private Random random=new Random();
    public CatBalou(Board board) {
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
        super.playCard(player, players);
        int karta=0;
        for (int i=0;i<players.length;i++){
            System.out.println(i+1+" : "+players[i].getMeno());
        }
        while(true) {

            int index= ZKlavesnice.readInt("Zadaj index hraca ktoremu chces odhodit kartu")-1;
            while(true){
                while(index<0 || index>players.length){
                    System.out.println("neplatny index hraca");
                    index=ZKlavesnice.readInt("vyber index hraca na ktoremu chces odhodit kartu")-1;
                }
                if(players[index]==player){
                    System.out.println("nemozes pouzit cat balou na seba");
                }
                else{
                    break;
                }
                index= ZKlavesnice.readInt("Zadaj index hraca ktoremu chces odhodit kartu")-1;
            }
            int stolAleboRuka=ZKlavesnice.readInt("Zadaj ci chces odhodit kartu z ruky alebo zo stola (0 pre stol 1 pre ruku)");
            if (players[index].getAllCards().size() == 0 && players[index].getKartyNaStole().size() == 0) {
                System.out.println("na tohoto hraca nemozes pouzit kartu Cat balou");
            }
            else if (stolAleboRuka==0 && players[index].getKartyNaStole().size()!=0){
                karta=random.nextInt(players[index].getKartyNaStole().size());
                players[index].getKartyNaStole().remove(karta);
                break;
            }
            else if (stolAleboRuka==1 && players[index].getAllCards().size() !=0) {
                karta=random.nextInt(players[index].getAllCards().size());
                break;
            }
        }
        return true;
    }
}
