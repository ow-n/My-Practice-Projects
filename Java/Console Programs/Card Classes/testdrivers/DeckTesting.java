/*
 * Test driver 3 for the Deck class.
 * - Declares 2 packs
 * - Deal all cards until deck is empty and display
 * - Reset deck by initializing
 * - Shuffle deck
 * - Deal shuffled deck until empty and display
 * - Repeat these steps for 1 pack
 */

package testdrivers;

import cardgame.Deck;

public class DeckTesting {

   public static void main(String[] args) {
      testDeck(2);
      testDeck(1);
   }

   public static void testDeck(int deckNum) {
      Deck deck = new Deck(deckNum);
      while (deck.getTopCards() > 0) {
         System.out.print(deck.dealCard() + " / ");
      }
      System.out.println("\n");

      deck.initializePack(deckNum);
      deck.shuffle();

      while (deck.getTopCards() > 0) {
         System.out.print(deck.dealCard() + " / ");
      }
      System.out.println("\n");
   }

}

/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*

A of spades / A of hearts / A of clubs / A of diamonds / 2 of spades / 2 of hear
ts / 2 of clubs / 2 of diamonds / 3 of spades / 3 of hearts / 3 of clubs / 3 of 
diamonds / 4 of spades / 4 of hearts / 4 of clubs / 4 of diamonds / 5 of spades 
/ 5 of hearts / 5 of clubs / 5 of diamonds / 6 of spades / 6 of hearts / 6 of cl
ubs / 6 of diamonds / 7 of spades / 7 of hearts / 7 of clubs / 7 of diamonds / 8
 of spades / 8 of hearts / 8 of clubs / 8 of diamonds / 9 of spades / 9 of heart
s / 9 of clubs / 9 of diamonds / T of spades / T of hearts / T of clubs / T of d
iamonds / J of spades / J of hearts / J of clubs / J of diamonds / Q of spades /
 Q of hearts / Q of clubs / Q of diamonds / K of spades / K of hearts / K of clu
bs / K of diamonds / A of spades / A of hearts / A of clubs / A of diamonds / 2 
of spades / 2 of hearts / 2 of clubs / 2 of diamonds / 3 of spades / 3 of hearts
 / 3 of clubs / 3 of diamonds / 4 of spades / 4 of hearts / 4 of clubs / 4 of di
amonds / 5 of spades / 5 of hearts / 5 of clubs / 5 of diamonds / 6 of spades / 
6 of hearts / 6 of clubs / 6 of diamonds / 7 of spades / 7 of hearts / 7 of club
s / 7 of diamonds / 8 of spades / 8 of hearts / 8 of clubs / 8 of diamonds / 9 o
f spades / 9 of hearts / 9 of clubs / 9 of diamonds / T of spades / T of hearts 
/ T of clubs / T of diamonds / J of spades / J of hearts / J of clubs / J of dia
monds / Q of spades / Q of hearts / Q of clubs / Q of diamonds / K of spades / K
 of hearts / K of clubs / K of diamonds / 

6 of diamonds / 8 of clubs / J of spades / 3 of hearts / 4 of spades / 3 of hear
ts / 2 of clubs / J of diamonds / 3 of clubs / 4 of diamonds / T of clubs / A of
 hearts / T of hearts / Q of spades / 8 of spades / T of spades / 8 of clubs / 4
 of hearts / 7 of clubs / Q of clubs / 9 of diamonds / 4 of spades / 9 of hearts
 / K of spades / 5 of spades / K of clubs / 6 of diamonds / 8 of diamonds / 6 of
 clubs / 7 of spades / 6 of clubs / 9 of diamonds / 2 of hearts / 2 of diamonds 
/ 5 of spades / 6 of hearts / 8 of spades / 3 of spades / Q of diamonds / 7 of h
earts / 9 of spades / 2 of diamonds / 9 of clubs / A of clubs / 5 of hearts / Q 
of hearts / T of diamonds / 3 of diamonds / 2 of spades / K of diamonds / K of h
earts / 7 of clubs / 2 of hearts / 4 of clubs / 3 of diamonds / 8 of hearts / 9 
of spades / 8 of diamonds / 2 of clubs / 7 of diamonds / 2 of spades / A of diam
onds / 7 of spades / 9 of hearts / Q of clubs / A of spades / 7 of hearts / 6 of
 hearts / J of hearts / J of spades / 5 of clubs / 6 of spades / 4 of hearts / K
 of spades / J of clubs / 5 of diamonds / 8 of hearts / A of diamonds / A of clu
bs / 5 of clubs / J of diamonds / K of clubs / T of diamonds / Q of hearts / 6 o
f spades / K of hearts / J of clubs / 9 of clubs / A of spades / 4 of diamonds /
 Q of spades / T of hearts / 3 of spades / A of hearts / 3 of clubs / Q of diamo
nds / T of clubs / 5 of diamonds / T of spades / 5 of hearts / K of diamonds / 7
 of diamonds / J of hearts / 4 of clubs / 

A of spades / A of hearts / A of clubs / A of diamonds / 2 of spades / 2 of hear
ts / 2 of clubs / 2 of diamonds / 3 of spades / 3 of hearts / 3 of clubs / 3 of 
diamonds / 4 of spades / 4 of hearts / 4 of clubs / 4 of diamonds / 5 of spades 
/ 5 of hearts / 5 of clubs / 5 of diamonds / 6 of spades / 6 of hearts / 6 of cl
ubs / 6 of diamonds / 7 of spades / 7 of hearts / 7 of clubs / 7 of diamonds / 8
 of spades / 8 of hearts / 8 of clubs / 8 of diamonds / 9 of spades / 9 of heart
s / 9 of clubs / 9 of diamonds / T of spades / T of hearts / T of clubs / T of d
iamonds / J of spades / J of hearts / J of clubs / J of diamonds / Q of spades /
 Q of hearts / Q of clubs / Q of diamonds / K of spades / K of hearts / K of clu
bs / K of diamonds / 

J of hearts / 7 of diamonds / Q of spades / J of diamonds / 2 of clubs / K of sp
ades / 3 of clubs / 5 of diamonds / 3 of hearts / 9 of spades / K of diamonds / 
3 of diamonds / T of hearts / A of hearts / Q of diamonds / 5 of spades / 8 of s
pades / 9 of diamonds / 6 of spades / 6 of clubs / T of spades / 7 of hearts / 9
 of hearts / J of spades / T of diamonds / K of hearts / 4 of spades / 8 of club
s / 9 of clubs / 4 of clubs / A of clubs / 8 of diamonds / 2 of spades / Q of cl
ubs / 3 of spades / A of diamonds / 6 of diamonds / 7 of spades / 7 of clubs / 4
 of diamonds / 5 of clubs / 6 of hearts / 2 of diamonds / 8 of hearts / 2 of hea
rts / J of clubs / A of spades / 4 of hearts / Q of hearts / 5 of hearts / K of 
clubs / T of clubs / 

/******************************************************************************/