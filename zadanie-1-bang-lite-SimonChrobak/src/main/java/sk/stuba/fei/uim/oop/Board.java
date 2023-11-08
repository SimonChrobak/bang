package sk.stuba.fei.uim.oop;

import karty.*;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    public ArrayList<Karty> cards;

    public Board(Player[] players) {
        this.cards = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cards.add(new Bang(this));
        }
        for (int i = 0; i < 15; i++) {
            cards.add(new Vedla(this));
        }
        for (int i = 0; i < 8; i++) {
            cards.add(new Pivo(this));
        }
        for (int i = 0; i < 4; i++) {
            cards.add(new Dostavnik(this));
        }
        for (int i = 0; i < 6; i++) {
            cards.add(new CatBalou(this));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Indiani(this));
        }
        for (int i = 0; i < 2; i++) {
            cards.add(new Barrel(this));
        }
        for (int i = 0; i < 3; i++) {
            cards.add(new Vazenie(this));
        }
        cards.add(new Dynamit(this));
        Collections.shuffle(this.cards);
    }

    public Karty getCard() {
        return this.cards.remove(0);
    }

    public void addCard(Karty card) {
        this.cards.add(card);
    }
}
