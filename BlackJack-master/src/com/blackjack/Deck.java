package com.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // generate cards
       for (Suits cardSuit : Suits.values()) {
           for (Values cardValue : Values.values()) {
               this.deck.add(new Card(cardSuit, cardValue));
           }
       }
    }

    public String toString() {
        String cardList = "";
        int i = 0;
        for (Card aCard : this.deck) {
            cardList += "\n" + i + "-" + aCard.toString();
            i ++;
        }
        return cardList;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }



    public Card getCard(int i){
        return this.deck.get(i);
    }

    public void removeCard(int i){
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
         this.deck.add(comingFrom.getCard(0));
         comingFrom.removeCard(0);
       
    }
    public int cardValue() {
        int score = 0;
        int aces = 0;

        for(Card card: this.deck) {
            switch(card.getValue()){
                case TWO: score += 2; break;
                case THREE: score += 3; break;
                case FOUR: score += 4; break;
                case FIVE: score += 5; break;
                case SIX: score += 6; break;
                case SEVEN: score += 7; break;
                case EIGHT: score += 8; break;
                case NINE: score += 9; break;
                case TEN: score += 10; break;
                case JACK: score += 10; break;
                case QUEEN: score += 10; break;
                case KING: score += 10; break;
                case ACE: aces += 1; break;
            }
        }
        for(int i = 0; i < aces; i++) {
            if (score > 10) {
                score += 1;
            } else {
                score += 11;
            }
        }

        return score;
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
      
    }


}