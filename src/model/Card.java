package model;

public class Card implements  Comparable{
    private CardNum num;
    private CardRank rank;

    public Card(CardRank rank,CardNum num) {
        this.num = num;
        this.rank = rank;
    }

    public CardNum getNum(){
        return num;
    }

    public CardRank getRank() {
        return rank;
    }



    public int getStrenght (){
        return num.getStrenght();
    }

    /**
     * Compares the strenght of the card with the strenght of the given card.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Card card = (Card)o;
        return compareTo(card);
    }
    private int compareTo(Card card){
        return num.comparePoints(card.getNum());
    }

    /**
     * The String representation is the one with the format "RX" where R is the card's rank and X is the card's number. Ex: "H8" would be 8 of Hearths.
     * @return
     */
    @Override
    public String toString(){
        return rank.toString() + num.toString();
    }

    public String getNumString(){
        return num.toString();
    }
}