package model;

public class Card {
    private CardNum num;
    private CardRank rank;

    public Card(CardNum num, CardRank rank){
        this.num = num;
        this.rank = rank;
    }

    public CardNum getNum(){
        return num;
    }

    public CardRank getRank() {
        return rank;
    }

    public int compareTo(Card card){
        return num.comparePoints(card.getNum());
    }
}