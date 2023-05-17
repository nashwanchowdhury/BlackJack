package com.blackjack;

import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double money = 200.00;

        Scanner response = new Scanner(System.in);
        // Game loops
        while (money > 0) {
            System.out.println("Available Balance: " + money);
            System.out.println("How much would you like to bet? : ");
            double bet = response.nextDouble();
            if (bet > money) {
                System.out.println("You cannot bet more than you have please try again: ");
                bet = response.nextDouble();
            }
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

//            while(true) {
            System.out.println("Here is your hand: ");
            System.out.println(playerHand.toString());
            System.out.println("Your hand value is " + playerHand.cardValue());
            System.out.println("Here is the dealers hand: ");
            System.out.println(dealerHand.getCard(0) + "\nHidden");
            boolean x = true;
            while (x) {
                System.out.println("Would you like to 1. Hit or  2. Stand?");
                int response2 = Integer.parseInt(scanner.nextLine());
                if (response2 == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew a " + playerHand.getCard(playerHand.deckSize()-1).toString());
                    System.out.println("Your new hand value is: " + playerHand.cardValue());
                    if (playerHand.cardValue() > 21) {
                        System.out.println("You Bust. Your hand value is over 21!");
                        money -= bet;
                        x = false;
                    }
                    if(playerHand.cardValue() == 21) {
                        System.out.println("You got Blackjack! You win 1.5x your bet");
                        money += (bet) * 0.5;
                        x = false;
                    }
                }
                if (response2 == 2) {
                    System.out.println("Here is the dealers hand: ");
                    System.out.println(dealerHand.toString());
                    System.out.println("The dealer's hand value is " + dealerHand.cardValue());
                    while(dealerHand.cardValue() <= 16) {
                        dealerHand.draw(playingDeck);
                        System.out.println("The dealer drew a " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
                        System.out.println("The dealer's hand value is " + dealerHand.cardValue());
                    }
                    if (dealerHand.cardValue() > 21) {
                        System.out.println("The Dealer Busts.");
                        money += bet;
                        System.out.println("You win and your new balance is now: " + money);
                        x = false;
                    }
                    if (dealerHand.cardValue() == 21 && playerHand.cardValue() < 21) {
                        System.out.println("The Dealer Wins");
                        money -= bet;
                        System.out.println("You lose and your new balance is " + money);
                        x = false;
                    }
                    if (dealerHand.cardValue() > playerHand.cardValue()) {
                        System.out.println("The Dealer Wins");
                        money -= bet;
                        System.out.println("You lose and your new balance is " + money);
                        x = false;
                    }
                    if (dealerHand.cardValue() < playerHand.cardValue()) {
                        System.out.println("You Win");
                        money += bet;
                        System.out.println("You win and your new balance is " + money);
                        x = false;
                    }
                }
            }
            if (money == 0) {
                System.out.println("Sorry, you're unfortunately out of money! Please get some more!");
            }
        }
    }
}