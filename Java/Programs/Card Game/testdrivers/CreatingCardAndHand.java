/*
 * Test driver 2 for the Card and Hand class
 * - Create 2 Card objects, 1 Hand object
 * - Use takeCard() and populate hand with maximum cards
 * - Display hand using toString()
 * - 'Play' each card until hand is empty
 * - Test 'inspectCard()' with both legal and illegal arguments
 */

package testdrivers;

import cardgame.Card;
import cardgame.Hand;

public class CreatingCardAndHand {
   public static void main(String[] args) {
      Card card1 = new Card('3', Card.Suit.CLUBS);
      Card card2 = new Card('T', Card.Suit.CLUBS);
      Card card3 = new Card('9', Card.Suit.HEARTS);
      Hand hand = new Hand();

      while (hand.getNumCards() < Hand.MAX_CARDS) {
         hand.takeCard(card1);
         hand.takeCard(card2);
         hand.takeCard(card3);
      }

      System.out.println("Hand full");
      System.out.println("After deal");
      System.out.println(hand.toString());

      System.out.println("\nTesting inspectCard()");
      System.out.println(hand.inspectCard(2));
      System.out.println(hand.inspectCard(100));

      System.out.println("Playing all cards");
      while (hand.getNumCards() > 0) {
         Card playedCard = hand.playCard();
         System.out.println("Playing " + playedCard.toString());
      }

      System.out.println("\nAfter playing all cards");
      System.out.println(hand.toString());
   }
}


/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*

Hand full
After deal
Hand = ( 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of heart
s, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 o
f clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of club
s, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T o
f clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of club
s, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 o
f hearts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hear
ts, 3 of clubs, T of clubs, 9 of hearts, 3 of clubs, T of clubs, 9 of hearts, 3 
of clubs )

Testing inspectCard()
9 of hearts
** illegal **
Playing all cards
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs
Playing 9 of hearts
Playing T of clubs
Playing 3 of clubs

After playing all cards
Hand = (  )

/******************************************************************************/