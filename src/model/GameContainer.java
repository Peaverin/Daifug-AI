package model;

import java.util.*;

public class GameContainer {
    private Deck deck;
    private Map<String, Player> playerList;
    private ArrayDeque<Card[]> tableHeap;


    public GameContainer(){
        playerList = new LinkedHashMap<>();
        tableHeap = new ArrayDeque<>();
    }

    public Collection<String> getPlayerNamesCollection() {
        return playerList.keySet();
    }

    public Collection<Player> getPlayerCollection(){
        return playerList.values();
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

    public String getNextPlayer(String currentPlayer) {
        List<String> stringList = new ArrayList<>(playerList.keySet());
        int nextIndex = stringList.indexOf(currentPlayer);
        nextIndex++;
        if(nextIndex >= stringList.size()) nextIndex = 0;
        return stringList.get(nextIndex);
    }

    public TurnState getPlayerTurnState(String player) {
        return playerList.get(player).getTurnState();
    }

    public List<String> getAllPlayerCombinations(String player){
        return playerList.get(player).getAllCombinations();
    }

    public void sortPlayersHands() {
        for(String key : playerList.keySet()){
            playerList.get(key).sortHand();
        }
    }
}
