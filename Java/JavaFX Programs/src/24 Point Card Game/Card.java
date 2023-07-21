package pointcardgame;

/*
 * Card class represent the actual card in a eg. deck / hand
 * The card's variables are set with from parent class
 */
public class Card extends CardIdentity {
   private boolean cardError;

   /* === Constructors === */
   public Card() {
      super();
      cardError = false;
   }

   public Card(char value, Suit suit) {
      super();
      if (!set(value, suit)) {
         set('A', Suit.SPADES);
         cardError = true;
      } else {
         cardError = false;
      }
   }

   /* === Mutators & Accessors === */
   public boolean getCardError() {
      return cardError;
   }

   @Override
   public boolean set(char value, Suit suit) {
      boolean valid = super.set(value, suit);
      if (!valid) {
         cardError = true;
      } else {
         cardError = false;
      }
      return valid;
   }

   /* === Methods === */
   @Override
   public String toString() {
      if (!cardError) {
         return getValue() + " of " + getSuit().toString().toLowerCase();
      } else {
         return "** illegal **";
      }
   }
   
   public boolean equals(Card card) {
      if (this.getValue() == card.getValue()
            && this.getSuit() == card.getSuit()) {
         return true;
      } else {
         return false;
      }
   }
}