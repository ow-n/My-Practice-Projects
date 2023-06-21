/*
 * Deck class represents a standard deck of playing cards.
 * Main Features: initialize / shuffle / deal
 */

package cardgame;

import java.util.Random;
import cardgame.CardIdentity.Suit;

public class Deck {
   private static final int MAX_PACKS = 6;
   private static final int NUM_CARDS_PER_PACK = 52;
   private static final int MAX_CARDS_PER_DECK = MAX_PACKS * NUM_CARDS_PER_PACK;

   private static Card[] masterPack; // points to the 52 standard card
                                     // references
   private Card[] cards;
   private int topCard; // essentially is 'number of cards'
   private int numPacks;

   /* Constructors */
   public Deck() {
      this(1);
   }

   public Deck(int numPacks) {
      if (masterPack == null) {
         allocateMasterPack();
      }
      if (!initializePack(numPacks)) {
         this.numPacks = 1;
         initializePack(1);
      }
   }

   /* Accessors */
   public int getTopCards() {
      return topCard;
   }

   public Card inspectCard(int k) {
      try {
         return new Card(cards[k].getValue(), cards[k].getSuit());
      } catch (IndexOutOfBoundsException e) {
         return new Card('0', null); // if card out of bounds, return error card
      }
   }

   /* Methods */
   public boolean initializePack(int numPacks) {
      if (numPacks < 1 || numPacks > MAX_PACKS) {
         return false;
      }
      this.numPacks = numPacks;
      cards = new Card[MAX_CARDS_PER_DECK];
      topCard = this.numPacks * NUM_CARDS_PER_PACK;

      for (int pack = 0; pack < this.numPacks; pack++) {
         for (int card = 0; card < NUM_CARDS_PER_PACK; card++) {
            cards[(pack * NUM_CARDS_PER_PACK) + card] = new Card(
                  masterPack[card].getValue(), masterPack[card].getSuit());

         }
      }
      return true;
   }

   public void shuffle() {
      Random rand = new Random();
      for (int cardIndex = 0; cardIndex < topCard; cardIndex++) {
         int swapIndex = rand.nextInt(topCard);
         Card tempCard = cards[cardIndex];
         cards[cardIndex] = cards[swapIndex];
         cards[swapIndex] = tempCard;
      }
   }

   public Card dealCard() {
      if (topCard < 1) {
         return null;
      }
      topCard--;
      return new Card(cards[topCard].getValue(), cards[topCard].getSuit());
   }

   private static void allocateMasterPack() {
      if (masterPack != null) {
         return;
      }
      masterPack = new Card[NUM_CARDS_PER_PACK];
      int index = 0;
      for (char value : "KQJT98765432A".toCharArray()) {
         for (Suit suit : Card.Suit.values()) {
            masterPack[index++] = new Card(value, suit);
         }
      }
   }

}
