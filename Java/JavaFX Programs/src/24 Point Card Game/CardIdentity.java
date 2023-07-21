package pointcardgame;


/*
 * CardIdentity class represents a playing card's value and suit.
 * The child class Card extends CardIdentity to set the card's variables.
 */
public class CardIdentity {
   public enum Suit {
      CLUBS, DIAMONDS, HEARTS, SPADES
   }

   private char value;
   private Suit suit;

   public CardIdentity() {
      this.value = 'A';
      this.suit = Suit.SPADES;
   }

   public boolean set(char value, Suit suit) {
      if (isValid(value, suit)) {
         this.value = value;
         this.suit = suit;
         return true;
      } else {
         return false;
      }
   }

   public char getValue() {
      return this.value;
   }

   public Suit getSuit() {
      return this.suit;
   }

   private static boolean isValid(char value, Suit suit) {
      return value == 'A' || value == 'K' || value == 'Q' || value == 'J'
            || value == 'T' || (value >= '2' && value <= '9' || value == 'X');
   }
}