package assignment2;

import java.util.Random;

public class Deck {
    public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
    public static Random gen = new Random(10);

    public int numOfCards; // contains the total number of cards in the deck
    public Card head; // contains a pointer to the card on the top of the deck

    /*
     * TODO: Initializes a Deck object using the inputs provided
     */
    public Deck(int numOfCardsPerSuit, int numOfSuits) {
        /**** ADD CODE HERE ****/
        if (numOfCardsPerSuit > 13 || numOfCardsPerSuit < 1 || numOfSuits < 1 || numOfSuits > suitsInOrder.length) {
            throw new IllegalArgumentException("wrong numOfCardsPerSuit or numOfSuits");
        }
        Card dummyNode = new PlayingCard("clubs", 1);
        Card current = dummyNode;

        // add all card
        for (int i = 0; i < numOfSuits; i++) {
            for (int j = 1; j <= numOfCardsPerSuit; j++) {
                current.next = new PlayingCard(suitsInOrder[i], j);
                current = current.next;
            }
        }

        // set head and num
        head = dummyNode.next;
        numOfCards = numOfCardsPerSuit * numOfSuits + 2;

        // add joker
        current.next = new Joker("red");
        current = current.next;
        current.next = new Joker("black");
        current = current.next;
        current.next = head;

        // set prev point
        head.prev = current;
        Card tail = head.prev;
        current = head;
        dummyNode.next = current;
        while (current != tail) {
            current = current.next;
            current.prev = dummyNode.next;
            dummyNode.next = current;
        }
        tail.prev = dummyNode.next.prev;
    }

    /*
     * TODO: Implements a copy constructor for Deck using Card.getCopy().
     * This method runs in O(n), where n is the number of cards in d.
     */
    public Deck(Deck d) {
        /**** ADD CODE HERE ****/
       Card p = d.head;

       Deck deck = new Deck();
       while(p.next != d.head){
           deck.addCard(p.getCopy());
           p = p.next;
       }
       head = deck.head;
    }

    /*
     * For testing purposes we need a default constructor.
     */
    public Deck() {}

    /*
     * TODO: Adds the specified card at the bottom of the deck. This
     * method runs in $O(1)$.
     */
    public void addCard(Card c) {
        /**** ADD CODE HERE ****/
        if(numOfCards == 0){
            head = c;
            head.next = c;
            head.prev = c;
        }else if(numOfCards == 1){
            head.next = c;
            head.prev = c;
            c.next = head;
            c.prev = head;
        }else{
            Card tail = head.prev;
            tail.next = c;
            c.next = head;
            c.prev = tail;
            head.prev = c;
        }
        numOfCards += 1;

    }

    /*
     * TODO: Shuffles the deck using the algorithm described in the pdf.
     * This method runs in O(n) and uses O(n) space, where n is the total
     * number of cards in the deck.
     */
    public void shuffle() {
        /**** ADD CODE HERE ****/
        // copy to array
        Card[] cardArray = new Card[numOfCards];
        Card current = head;
        Card tail = head.prev;
        int cnt = 0;
        while (current != tail) {
            cardArray[cnt++] = current;
            current = current.next;
        }
        cardArray[cnt] = tail;

        // shuffle
        for (int i = numOfCards - 1; i >= 1; i--) {
            int j = gen.nextInt(i+1);
            Card temp = cardArray[i];
            cardArray[i] = cardArray[j];
            cardArray[j] = temp;
        }

        // rebuild
        head = cardArray[0];
        tail = cardArray[numOfCards - 1];
        for (int i = 0; i < numOfCards - 1; i++) {
            cardArray[i].next = cardArray[i+1];
        }
        tail.next = head;
        for (int i = 1; i < numOfCards; i++) {
            cardArray[i].prev = cardArray[i - 1];
        }
        head.prev = tail;
    }

    /*
     * TODO: Returns a reference to the joker with the specified color in
     * the deck. This method runs in O(n), where n is the total number of
     * cards in the deck.
     */
    public Joker locateJoker(String color) {
        /**** ADD CODE HERE ****/
        Joker res = null;
        Card current = head;
        int cnt = numOfCards;
        while (cnt >= 0) {
            if (current instanceof Joker) {
                if (((Joker) current).getColor().equals(color)) {
                    res = (Joker) current;
                    break;
                }
            }
            current = current.next;
            cnt -= 1;
        }
        return res;
    }

    /*
     * TODO: Moved the specified Card, p positions down the deck. You can
     * assume that the input Card does belong to the deck (hence the deck is
     * not empty). This method runs in O(p).
     */
    public void moveCard(Card c, int p) {
        /**** ADD CODE HERE ****/
        Card pre = c.prev;
        pre.next = c.next;
        c.next.prev = pre;
        Card current = c;
        for (int i = 0; i < p; i++) {
            current = current.next;
        }
        c.next = current.next;
        current.next.prev = c;
        c.prev = current;
        current.next = c;
    }

    /*
     * TODO: Performs a triple cut on the deck using the two input cards. You
     * can assume that the input cards belong to the deck and the first one is
     * nearest to the top of the deck. This method runs in O(1)
     */
    public void tripleCut(Card firstCard, Card secondCard) {
        /**** ADD CODE HERE ****/
        /**
         * think about zero
         */

        // refer to the first section
        Card firstHead = head;
        Card firstTail = firstCard.prev;
        // refer to the second section
        Card secondHead = secondCard.next;
        Card secondTail = head.prev;

        print("first section", firstHead, firstTail);
        print("second section", secondHead, secondTail);

        if(firstCard != head && secondCard != head.prev){  // first card not top and second card not bottom

            // adjust the first section head and tail
            firstHead.prev = secondCard;
            firstTail.next = secondHead;


            // adjust the second section head and tail
            secondHead.prev = firstTail;
            secondTail.next = firstCard;

            // assign the first card and second card
            firstCard.prev = secondTail;
            secondCard.next = firstHead;

            head = secondHead;
        }else if(firstCard == head && secondCard != head.prev){  // first card at the top

            // adjust the second section head and tail
            secondHead.prev = secondCard;
            secondTail.next = firstCard;
            // assign the first card and second card
            firstCard.prev = secondTail;
            secondCard.next = secondHead;

            head = secondHead;
        }else if(firstCard != head && secondCard == head.prev) { // second card at the bottom
            // adjust the first section head and tail
            firstHead.prev = secondCard;
            firstTail.next = firstCard;

            // assign the first card and second card
            firstCard.prev = firstTail;
            secondCard.next = firstHead;

            head = firstCard;
        }else{  // first at the top and second at the bottom
            // do nothing
        }


    }

    /*
     * TODO: Performs a count cut on the deck. Note that if the value of the
     * bottom card is equal to a multiple of the number of cards in the deck,
     * then the method should not do anything. This method runs in O(n).
     */
    public void countCut() {
        /**** ADD CODE HERE ****/
        Card tail = head.prev;
        int cnt = tail.getValue();
        cnt = cnt % (numOfCards);
        if(cnt == numOfCards -1){
            return;
        }
        if (cnt == 0) return;

        Card begin = head;
        Card current = begin;
        for (int i = 0; i < cnt - 1; i++) {
            current = current.next;
        }
        head = current.next;
        Card tailPre = tail.prev;
        tailPre.next = begin;
        begin.prev = tailPre;
        current.next = tail;
        tail.prev = current;
        head.prev = tail;
        tail.next = head;

    }

    /*
     * TODO: Returns the card that can be found by looking at the value of the
     * card on the top of the deck, and counting down that many cards. If the
     * card found is a Joker, then the method returns null, otherwise it returns
     * the Card found. This method runs in O(n).
     */
    public Card lookUpCard() {
        /**** ADD CODE HERE ****/
        int cnt = head.getValue();
        cnt %= numOfCards;
        Card current = head;
        for (int i = 0; i < cnt; i++) {
            current = current.next;
        }
        if (current instanceof Joker) return null;
        return current;
    }

    /*
     * TODO: Uses the Solitaire algorithm to generate one value for the keystream
     * using this deck. This method runs in O(n).
     */
    public int generateNextKeystreamValue() {
        /**** ADD CODE HERE ****/
        Card current = head.prev.prev;
        Card rj = null;
        Card bj = null;
        int cnt = numOfCards;
        while (rj == null || bj == null || cnt == 0) {
            if (current instanceof Joker) {
                if (((Joker) current).getColor().equals("red")) {
                    rj = current;
                }else {
                    bj = current;
                }
            }
            current = current.next;
            cnt--;
        }
        print("raw");
        shuffle();
        print("after shuffle");
        moveCard(rj, 1);
        print("move RJ");
        moveCard(bj, 2);
        print("move BJ ");
        current = head;
        while (true) {
            if (current instanceof Joker) {
                if (rj == current) tripleCut(rj, bj);
                else tripleCut(bj, rj);
                break;
            }
            current = current.next;
        }
        print("after triplecut");
        countCut();

        print("after count cut");
        Card res = lookUpCard();

        return res == null ? 0 : res.getValue();
    }

    public void print(String msg) {
        Card current = head;
        StringBuffer sb = new StringBuffer();
        msg = String.format("%-16s", msg);
        sb.append(msg);
        sb.append(", ");
        while (current.next != head) {
            sb.append(current.toString());
            sb.append(" ");
            current = current.next;
        }
        sb.append(current);
        System.out.println(sb.toString());
    }

    public void print(String msg, Card start, Card end){
        if(start != end){
            Card p = start;
            StringBuffer sb = new StringBuffer();
            msg = String.format("%-16s", msg);
            sb.append(msg);
            sb.append(", ");
            while(p != end){
                sb.append(p.toString());
                sb.append(" ");
                p = p.next;
            }
            sb.append(p.toString());
            System.out.println(sb.toString());
        }else{
            StringBuffer sb = new StringBuffer();
            msg = String.format("%-16s", msg);
            sb.append(msg);
            sb.append(", ");
            sb.append(start.toString());
            System.out.println(sb.toString());
        }

    }

//    public void print(String msg) {
//        Card current = head.prev;
//        StringBuffer sb = new StringBuffer();
//        msg = String.format("%-16s", msg);
//        sb.append(msg);
//        sb.append(", ");
//        while (current.prev != head) {
//            sb.append(current.toString());
//            sb.append(" ");
//            current = current.prev;
//        }
//        sb.append(current);
//        System.out.println(sb.toString());
//    }

    public abstract class Card {
        public Card next;
        public Card prev;

        public abstract Card getCopy();
        public abstract int getValue();

    }

    public class PlayingCard extends Card {
        public String suit;
        public int rank;

        public PlayingCard(String s, int r) {
            this.suit = s.toLowerCase();
            this.rank = r;
        }

        public String toString() {
            String info = "";
            if (this.rank == 1) {
                //info += "Ace";
                info += "A";
            } else if (this.rank > 10) {
                String[] cards = {"Jack", "Queen", "King"};
                //info += cards[this.rank - 11];
                info += cards[this.rank - 11].charAt(0);
            } else {
                info += this.rank;
            }
            //info += " of " + this.suit;
            info = (info + this.suit.charAt(0)).toUpperCase();
            return info;
        }

        public PlayingCard getCopy() {
            return new PlayingCard(this.suit, this.rank);
        }

        public int getValue() {
            int i;
            for (i = 0; i < suitsInOrder.length; i++) {
                if (this.suit.equals(suitsInOrder[i]))
                    break;
            }

            return this.rank + 13*i;
        }

    }

    public class Joker extends Card{
        public String redOrBlack;

        public Joker(String c) {
            if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black"))
                throw new IllegalArgumentException("Jokers can only be red or black");

            this.redOrBlack = c.toLowerCase();
        }

        public String toString() {
            //return this.redOrBlack + " Joker";
            return (this.redOrBlack.charAt(0) + "J").toUpperCase();
        }

        public Joker getCopy() {
            return new Joker(this.redOrBlack);
        }

        public int getValue() {
            return numOfCards - 1;
        }

        public String getColor() {
            return this.redOrBlack;
        }
    }

}
