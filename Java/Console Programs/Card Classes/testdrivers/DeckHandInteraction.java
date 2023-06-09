/*
 * Test driver 4 that simulates interactions between Deck and Hand class.
 * - User picks number of players
 * - Each player's hand takes a card from the deck
 * - This interaction is tested when the deck is unshuffled and shuffled
 * - Results are printed to console
 */

package testdrivers;

import cardgame.Hand;
import cardgame.Deck;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeckHandInteraction {

   public static void main(String[] args) {
      int numOfHands = choosePlayerNum();

      // Instantiating objects
      Deck deck = new Deck(1);
      Hand hands[] = new Hand[numOfHands];
      for (int playerNum = 0; playerNum < numOfHands; playerNum++) {
         hands[playerNum] = new Hand();
      }

      // Tests
      dealAndDisplayHands(deck, hands, false); // unshuffled
      dealAndDisplayHands(deck, hands, true); // shuffled
   }

   /**
    * Asks the user for the number of players and returns it.
    * 
    * @return Number of players.
    */
   public static int choosePlayerNum() {
      Scanner scanner = new Scanner(System.in);
      int numOfHands = 0;
      System.out.print("How many hands? (1 - 10, please): ");
      while (numOfHands < 1 || numOfHands > 10) {
         try {
            numOfHands = scanner.nextInt();
            if (numOfHands < 1 || numOfHands > 10) {
               System.out.print("Invalid answer. (1 - 10, please): ");
            }
         } catch (InputMismatchException e) {
            System.out.print("Invalid answer. (1 - 10, please): ");
            scanner.nextLine();
         }
      }
      scanner.close();
      return numOfHands;
   }

   /**
    * Deals cards to the specified number of hands and displays the hands.
    * 
    * @param deck       Deck of cards
    * @param hands      Array of hands
    * @param numOfHands Number of hands
    * @param shuffle    Should the deck be shuffled? True/False
    */
   public static void dealAndDisplayHands(Deck deck, Hand[] hands,
         boolean shuffle) {
      // Reset objects to original state
      deck.initializePack(1);
      for (Hand hand : hands) {
         hand.resetHand();
      }

      if (!shuffle) {
         System.out.println("\nHere are our hands, from unshuffled deck:");
      } else {
         deck.shuffle();
         System.out.println("\nHere are our hands, from SHUFFLED deck:");
      }

      // Hands take card from deck
      while (deck.getTopCards() > 0) {
         for (Hand hand : hands) {
            if (deck.getTopCards() == 0) {
               break;
            }
            hand.takeCard(deck.dealCard());
         }
      }

      // Each hand prints their cards
      int handIndex = 1;
      for (Hand hand : hands) {
         System.out.println("Player " + handIndex + "'s " + hand.toString());
         System.out.println();
         handIndex++;
      }
   }
}


/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*

How many hands? (1 - 10, please): 6

Here are our hands, from unshuffled deck:
Player 1's Hand = ( A of spades, 2 of clubs, 4 of spades, 5 of clubs, 7 of spade
s, 8 of clubs, T of spades, J of clubs, K of spades )

Player 2's Hand = ( A of hearts, 2 of diamonds, 4 of hearts, 5 of diamonds, 7 of
 hearts, 8 of diamonds, T of hearts, J of diamonds, K of hearts )

Player 3's Hand = ( A of clubs, 3 of spades, 4 of clubs, 6 of spades, 7 of clubs
, 9 of spades, T of clubs, Q of spades, K of clubs )

Player 4's Hand = ( A of diamonds, 3 of hearts, 4 of diamonds, 6 of hearts, 7 of
 diamonds, 9 of hearts, T of diamonds, Q of hearts, K of diamonds )

Player 5's Hand = ( 2 of spades, 3 of clubs, 5 of spades, 6 of clubs, 8 of spade
s, 9 of clubs, J of spades, Q of clubs )

Player 6's Hand = ( 2 of hearts, 3 of diamonds, 5 of hearts, 6 of diamonds, 8 of
 hearts, 9 of diamonds, J of hearts, Q of diamonds )


Here are our hands, from SHUFFLED deck:
Player 1's Hand = ( T of clubs, J of spades, 3 of clubs, A of spades, 9 of diamo
nds, T of diamonds, 3 of diamonds, 3 of spades, Q of clubs )

Player 2's Hand = ( 8 of clubs, 7 of spades, J of diamonds, Q of diamonds, K of 
diamonds, A of clubs, 5 of clubs, K of hearts, 6 of clubs )

Player 3's Hand = ( J of clubs, 6 of spades, Q of spades, 5 of spades, 5 of hear
ts, 9 of hearts, A of diamonds, 2 of clubs, T of hearts )

Player 4's Hand = ( 2 of hearts, 9 of spades, T of spades, 4 of hearts, K of spa
des, 3 of hearts, K of clubs, 4 of diamonds, 7 of clubs )

Player 5's Hand = ( 8 of diamonds, 4 of clubs, 4 of spades, Q of hearts, 5 of di
amonds, 6 of diamonds, J of hearts, 8 of spades )

Player 6's Hand = ( 7 of diamonds, 9 of clubs, 6 of hearts, 2 of spades, A of he
arts, 8 of hearts, 7 of hearts, 2 of diamonds )

/******************************************************************************/