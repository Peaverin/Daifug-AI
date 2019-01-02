package model;

public enum CardNum{
    ACE(1, 11),
    TWO(2,12),
    THREE(3,0),
    FOUR(4,1),
    FIVE(5,2),
    SIX(6,3),
    SEVEN(7,4),
    EIGHT(8,5),
    NINE(9,6),
    TEN(10,7),
    JACK(11,8),
    QUEEN(12,9),
    KING(13,10);

    private final int number;
    private final int strenght;
    CardNum(int number, int strenght){
        this.number = number;
        this.strenght = strenght;
    }

    public int getNumber(){
        return number;
    }

    public int getStrenght(){
        return strenght;
    }

    public int comparePoints(CardNum cardNum){
        return this.getStrenght() - cardNum.getStrenght();
    }

    public static CardNum getCardNum(int points){
        for(CardNum cardNum : values()){
            if(cardNum.getStrenght() == points) return cardNum;
        }
        return null;
    }

    public boolean isNumber(int number){
        return this.number == number;
    }

    @Override
    public String toString(){
        return Integer.toString(number);
    }
}