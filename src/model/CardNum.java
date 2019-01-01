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
    private final int points;
    CardNum(int number, int points){
        this.number = number;
        this.points = points;
    }

    public int getNumber(){
        return number;
    }

    public int getPoints(){
        return points;
    }

    public int comparePoints(CardNum cardNum){
        int substraction = this.getPoints() - cardNum.getPoints();
        if (substraction == 0){ return 0;}else if(substraction > 0) {return 1;}else return -1;
    }

}