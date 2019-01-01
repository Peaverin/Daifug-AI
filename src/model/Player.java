package model;

public class Player {
    private String name;
    private PlayerHand hand;
    private PlayerIntelligence playerIntelligence;
    public Player(String name, PlayerIntelligence pI){
        this.name = name;
        this.playerIntelligence = pI;
        hand = new PlayerHand();
    }
    public void addCard(Card card){
        hand.add(card);
    }

    public Card takeBestCard(){
        return hand.takeBestCard();
    }

    public Card[] takeTwoBestCards(){
        return hand.takeTwoBestCards();
    }
}
