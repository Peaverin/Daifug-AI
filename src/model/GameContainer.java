package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GameContainer {
    private Deck deck;
    private Map<String, Player> playerList;

    public Collection<String> getPlayerNames() {
        return playerList.keySet();
    }

    public void addPlayer(String name, PlayerIntelligence pI){
        playerList.put(name, new Player(name, pI));
    }

    public void resetDeck(){
        deck = new Deck();
    }

    public void shuffleDeck(){
        deck.shuffle();
    }

    public Card takeCardFromDeck() {
        return deck.takeCard();
    }

    public void addCardToPlayer(String player, Card card) {
        playerList.get(player).addCard(card);
    }

    public int getNumberOfPlayers(){
        return playerList.size();
    }

    public boolean deckIsEmpty() {
        return deck.isEmpty();
    }

    public Card takeBestCardFromPlayer(String player){
        return playerList.get(player).takeBestCard();
    }

    public Card[] takeTwoBestCardsFromPlayer(String player){
        return playerList.get(player).takeTwoBestCards();
    }
}
