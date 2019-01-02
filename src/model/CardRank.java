package model;

public enum CardRank {
    CLUB("C"),
    DIAMOND("D"),
    HEARTH("H"),
    SPADE("S");

    private final String letter;
    CardRank(String letter){
        this.letter = letter;
    }

    public boolean isLetter(String letter){
        return this.letter == letter;
    }

    @Override
    public String toString() {
        return letter;
    }
}
