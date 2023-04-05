/*
 * Test driver 1 for the Card class
 * - Create 2 valid cards and 1 invalid card
 * - "Fix" the bad card, and make a good card bad
 */


package testdrivers;

import cardgame.Card;
import cardgame.CardIdentity.Suit;

public class CreatingCards {
   public static void main(String[] args) {
      Card goodCard1 = new Card();
      Card badCard = new Card('#', Suit.DIAMONDS);
      Card goodCard2 = new Card('J', Suit.CLUBS);

      System.out.println(goodCard1);
      System.out.println(badCard);
      System.out.println(goodCard2);
      System.out.println();

      goodCard1.set('#', Suit.DIAMONDS);
      badCard.set('Q', Suit.SPADES);

      System.out.println(goodCard1);
      System.out.println(badCard);
      System.out.println(goodCard2);
   }
}


/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*

A of spades
** illegal **
J of clubs

** illegal **
Q of spades
J of clubs

/****************************************************************************//*

Test:
- Instantiates three cards
- 2 legally and 1 illegally using 2 the default and parameter constructor
- Set goodcard with illegal value and Fix badcard

/******************************************************************************/