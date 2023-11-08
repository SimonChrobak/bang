package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final String meno;
    private int zivoty;
    private ArrayList<Karty> karty;
    private ArrayList<Karty> kartyNaStole;

    public Player(String meno) {
        this.karty = new ArrayList<Karty>();
        this.meno = meno;
        this.zivoty = 4;
        this.kartyNaStole=new ArrayList<Karty>();
    }

    public String getMeno() {
        return meno;
    }

    public int getZivoty() {
        return zivoty;
    }

    public ArrayList<Karty> getAllCards() {
        return this.karty;
    }
    public ArrayList<Karty> getKartyNaStole(){return this.kartyNaStole;}
    public boolean isActive(){return this.zivoty>0;}
    public void removeLife() {
        this.zivoty--;
    }
    public void addLife(){this.zivoty++;}

    public ArrayList<Karty> odhodKartuZRuky(){
        ArrayList<Karty> odhodeneKarty= new ArrayList<>(this.karty);
        return odhodeneKarty;
    }

    public void removeAndTakeNewCard(Karty oldCard, Karty newCard) {
        for (int i = 0; i < this.karty.size(); i++) {
            if (Objects.equals(this.karty.get(i), oldCard)) {
                this.karty.remove(i);
                this.karty.add(i, newCard);
                break;
            }
        }
    }
    public void setCards(ArrayList<Karty> cards) {
        this.karty = cards;
    }
    public void setKartyNaStole(Karty karty){
        kartyNaStole.add(karty);
    }
}

