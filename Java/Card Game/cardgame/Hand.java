/*
 * Hand class represents a hand a what a player's holding
 * Main Features: take / play / inspect / reset
 */

package cardgame; 

public class Hand {
   public static int MAX_CARDS = 52;
   private Card[] myCards;
   private int numCards;

   public Hand() {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   public int getNumCards() {
      return numCards;
   }

   public boolean setNumCards(int numCards) {
      if (numCards >= 0 && numCards <= 52) {
         this.numCards = numCards;
         return true;
      } else {
         return false;
      }
   }

   /* Methods */
   public void resetHand() {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }

   public boolean takeCard(Card card) {
      if (getNumCards() < MAX_CARDS) {
         myCards[numCards] = new Card(card.getValue(), card.getSuit());
         setNumCards(getNumCards() + 1);
         if (card.getCardError()) {
            return true; // if card is invalid, but there is still room in Hand
         }
         return true;
      } else {
         return false;
      }
   }

   public Card playCard() {
      if (numCards > 0) {
         Card topCard = myCards[numCards - 1];
         numCards--; // "effectively" removes the card as it will be overwritten
         return topCard;
      } else {
         return null;
      }
   }

   @Override
   public String toString() {
      StringBuilder handList = new StringBuilder("Hand = ( ");
      if (numCards > 0) {
         for (int cardIndex = 0; cardIndex < numCards; cardIndex++) {
            handList.append(myCards[cardIndex].toString());
            if (cardIndex < numCards - 1) {
               handList.append(", ");
            }
         }
      }
      handList.append(" )");
      return handList.toString();
   }

   public Card inspectCard(int k) {
      if (k < 0 || k >= numCards) {
         return new Card('0', null); // if out of bounds, return error card
      } else {
         return myCards[k];
      }
   }
}