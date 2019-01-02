package model;

import java.util.List;

enum TurnState{
    PLAYING,
    LOST_TURN,
    PASS_TURN;
}

public class Player {
    private String name;
    private PlayerHand hand;
    private PlayerIntelligence playerIntelligence;
    private TurnState turnState;
    public Player(String name, PlayerIntelligence pI){
        this.name = name;
        this.playerIntelligence = pI;
        this.turnState = TurnState.PLAYING;
        hand = new PlayerHand();
    }
    public void addCard(Card card){
        hand.put(card.toString() ,card);
    }

    public Card takeBestCard(){
        return hand.takeBestCard();
    }

    public Card[] takeTwoBestCards(){
        return hand.takeTwoBestCards();
    }

    public String getName() {
        return name;
    }

    public boolean hasCard(CardRank cardRank, CardNum cardNum) {
        return hand.containsCard(cardRank, cardNum);
    }

    public void setTurnState(TurnState turnState){
        this.turnState = turnState;
    }

    public TurnState getTurnState() {
        return turnState;
    }

    public List<String> getAllCombinations(){
        return hand.getAllCombinations();
    }

    public void sortHand(){
        hand.sort();
    }
}
