package assignment2;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Deck deck = new Deck(5, 2);
        for (int i = 0; i < 12; i++) {
            System.out.println(deck.generateNextKeystreamValue());
        }
    }

}
