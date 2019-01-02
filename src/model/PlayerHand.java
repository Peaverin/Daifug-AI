package model;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerHand extends HashMap<String, Card> {

    public Card takeBestCard() {
        Card bestCard = null;
        int bestValue = 0;
        for(Card card : this.values()){
            int cardStrenght = card.getStrenght();
            if (cardStrenght > bestValue){
                bestValue = cardStrenght;
                bestCard = card;
            }
        }
        return bestCard;
    }

    public Card[] takeTwoBestCards(){
        Card bestCard = null;
        Card secondBestCard = null;
        int bestValue = -1;
        int secondBestValue = -1;
        for(Card card : this.values()){
            int cardStrenght = card.getStrenght();
            if (cardStrenght > bestValue){
                secondBestCard = bestCard;
                secondBestValue = bestValue;
                bestValue = cardStrenght;
                bestCard = card;
            }else if(cardStrenght > secondBestValue){
                secondBestCard = card;
                secondBestValue = cardStrenght;
            }
        }
        return new Card[]{bestCard, secondBestCard};
    }

    public boolean containsCard(CardRank cardRank, CardNum cardNum) {
        for(Card card : this.values()){
            if(card.getRank() == cardRank && card.getNum() == cardNum) return true;
        }
        return false;
    }
}
