package model;
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
        hand.put(card.getRank().toString() + Integer.toString(card.getNum().getNumber()),card);
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
}
