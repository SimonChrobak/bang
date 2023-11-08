package sk.stuba.fei.uim.oop;

import karty.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Hra extends ZKlavesnice {
    private final Player[] players;
    private int currentPlayer;
    private final Board board;
    private final ArrayList<Karty> odhadzovaciBalicek;
    public final ArrayList<Karty> kartyNaStole;
    private int kolo;
    private boolean koniecKola;
    private int index=0;

    public Hra() {
        int pocetHracov = 0;
        kolo=0;
        this.odhadzovaciBalicek=new ArrayList<>();
        this.kartyNaStole=new ArrayList<>();
        while (pocetHracov < 2 || pocetHracov > 4) {
            pocetHracov = ZKlavesnice.readInt("zadaj pocet hracov (2-4)");
            if (pocetHracov < 2 || pocetHracov > 4) {
                System.out.println("musite zadat pocet hracov v rozmedzi 2-4");
            }
        }
        this.players = new Player[pocetHracov];
        for (int i = 0; i < pocetHracov; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("zadaj meno hraca"));
        }
        this.board = new Board(this.players);
        this.startHry();
    }

    private void startHry() {
        while (this.pocetAktivnychHracov() > 1) {
            kolo++;
            Player aktivnyPlayer = this.players[this.currentPlayer];

            if (!aktivnyPlayer.isActive()) {
                ArrayList<Karty> kartyDoBalika = aktivnyPlayer.odhodKartuZRuky();
                for (Karty card : kartyDoBalika) {
                    this.board.addCard(card);
                }
                this.incrementCounter();
            }
            ArrayList<Karty> hratelneKarty=new ArrayList<>();
            if(kolo==1) {
                for (int i = players.length - 1; i >= 0; i--) {
                    hratelneKarty = getPlayableCards(players[i]);
                    players[i].setCards(hratelneKarty);
                }
            }
            this.makeTurn(aktivnyPlayer,hratelneKarty);
            this.incrementCounter();
        }
        System.out.println("Hra skoncila a vitaz je "+getWinner().getMeno());
    }
    private void makeTurn(Player activePlayer,ArrayList<Karty> hratelneKarty){
        koniecKola=false;
        kartyNaStole.clear();
        for (int i=0;i<players.length;i++){
            for(int j=0;j<players[i].getKartyNaStole().size();j++){
                kartyNaStole.add(players[i].getKartyNaStole().get(j));
            }
        }
        if(!kartyNaStole.isEmpty()){
            for (int i=0;i<kartyNaStole.size();i++){
                Karty karta=kartyNaStole.get(i);
                if (karta instanceof Dynamit){
                    boolean dynamit=karta.playCard(activePlayer,players);
                    if(dynamit==false){
                        kartyNaStole.remove(karta);
                    }
                }
                if(karta instanceof Vazenie && players[index]==activePlayer){
                    boolean vazenie=karta.playCard(activePlayer,players);
                    kartyNaStole.remove(i);
                    for(int j=0;j<activePlayer.getKartyNaStole().size();j++){
                        Karty cards=activePlayer.getKartyNaStole().get(j);
                        if(cards instanceof Vazenie){
                            activePlayer.getKartyNaStole().remove(j);
                        }
                    }
                    if(vazenie==true){
                        koniecKola=true;
                    }
                }
                if(karta instanceof Barrel){
                    boolean barrel=karta.playCard(activePlayer,players);
                }
            }
        }
        if(kolo>=2){
            hratelneKarty=activePlayer.getAllCards();
        }
        if(2>board.cards.size()){
            board.cards.addAll(odhadzovaciBalicek);
            odhadzovaciBalicek.clear();
        }
        if(koniecKola==false) {
            for (int i = 0; i < 2; i++) {
                hratelneKarty.add(board.cards.get(i));
                board.cards.remove(i);
            }
        }
        System.out.println("Tah "+activePlayer.getMeno());
        while(koniecKola==false && hratelneKarty.size()!=0){
            this.hrajKartu(hratelneKarty,activePlayer);
        }
        if(hratelneKarty.size()>activePlayer.getZivoty() && koniecKola==false) {
            this.removeCard(activePlayer,hratelneKarty);
        }

    }
    private void removeCard(Player activePlayer,ArrayList<Karty>karty){
        while(karty.size()>activePlayer.getZivoty()) {
            System.out.println("musis odhodit nejake karty");
            int cisloKarty = vyberKarty(karty, "na odohodenie");
            if (cisloKarty < 0 || cisloKarty > karty.size() - 1) {
                System.out.println("zle cislo karty");
            }
            else {
                System.out.println(activePlayer.getMeno() + " odhodil " + karty.get(cisloKarty).name);
                odhadzovaciBalicek.add(karty.get(cisloKarty));
                karty.remove(cisloKarty);
            }
        }
        koniecKola=true;
        activePlayer.setCards(karty);
    }
    private void hrajKartu(ArrayList<Karty> hratelneKarty, Player activePlayer){
        System.out.println("Zadaj 0 ak nechces zahrat ziadnu kartu");
        int cisloKarty=vyberKarty(hratelneKarty,"na zahranie");
        if(cisloKarty==-1){
            removeCard(activePlayer,hratelneKarty);
        }
        else if(hratelneKarty.get(cisloKarty) instanceof Dynamit){
            System.out.println(activePlayer.getMeno()+" zahral kartu dynamit");
            kartyNaStole.add(hratelneKarty.get(cisloKarty));
            activePlayer.setKartyNaStole(hratelneKarty.get(cisloKarty));
            hratelneKarty.remove(cisloKarty);
        }
        else if(hratelneKarty.get(cisloKarty) instanceof Vazenie){
            System.out.println(activePlayer.getMeno()+" zahral kartu vazenie");
            for (int i=0;i<players.length;i++){
                System.out.println(i+1+" : "+players[i].getMeno());
            }
            index=ZKlavesnice.readInt("zadaj index hraca na ktoreho chces pouzit vazenie")-1;
            while(true){
                while(index<0 || index>players.length){
                    System.out.println("neplatny index hraca");
                    index=ZKlavesnice.readInt("zadaj index hraca na ktoreho chces pouzit vazenie")-1;
                }
                if(players[index]==activePlayer){
                    System.out.println("nemozes pouzit vazenie na seba");
                }
                else{
                    break;
                }
                index=ZKlavesnice.readInt("zadaj index hraca na ktoreho chces pouzit vazenie")-1;
            }
            kartyNaStole.add(hratelneKarty.get(cisloKarty));
            players[index].setKartyNaStole(hratelneKarty.get(cisloKarty));
            hratelneKarty.remove(cisloKarty);
        }
        else if(hratelneKarty.get(cisloKarty) instanceof Barrel){
            System.out.println(activePlayer.getMeno()+" zahral kartu barrel");
            kartyNaStole.add(hratelneKarty.get(cisloKarty));
            activePlayer.setKartyNaStole(hratelneKarty.get(cisloKarty));
            hratelneKarty.remove(cisloKarty);
        }
        else if(hratelneKarty.get(cisloKarty) instanceof Vedla){
            System.out.println("Karta vedla sa neda zahrat");
        }
        else {
            hratelneKarty.get(cisloKarty).playCard(activePlayer,players);
            if(hratelneKarty.get(cisloKarty) instanceof Dostavnik){
                System.out.println(activePlayer.getMeno()+" zahral kartu dostavnik a potiahol si 2 karty");
                for(int i=0;i<2;i++){
                    hratelneKarty.add(board.cards.get(i));
                    board.cards.remove(i);
                }
            }
            odhadzovaciBalicek.add(hratelneKarty.get(cisloKarty));
            hratelneKarty.remove(cisloKarty);
        }
    }
    private int vyberKarty(ArrayList<Karty> karty,String verb){
        for (int i = 0; i < karty.size();i++){
            System.out.println("Karta "+(i+1)+":"+karty.get(i).getName());
        }
        int cisloKarty=0;
        while(true){
            cisloKarty=ZKlavesnice.readInt("Vyber cislo karty "+verb)-1;
            if(cisloKarty<-1 || cisloKarty>karty.size()-1){
                System.out.println("Zle cislo karty");
            }
            else{
                break;
            }
        }
        return cisloKarty;
    }

    private int pocetAktivnychHracov() {
        int pocet = 0;
        for (Player player : this.players) {
            if (player.isActive()) {
                pocet++;
            }
        }
        return pocet;
    }

    private void incrementCounter() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
    }
    public ArrayList<Karty> getPlayableCards(Player activePlayer){
        ArrayList<Karty> karty=new ArrayList<Karty>();
        for(int i=0;i<4;i++){
            karty.add(board.cards.get(i));
            board.cards.remove(i);
        }
        return karty;
    }
    private Player getWinner() {
        for (Player player : this.players) {
            if (player.isActive()) {
                return player;
            }
        }
        return null;
    }
}
