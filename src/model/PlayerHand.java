package model;

import java.util.ArrayList;

public class PlayerHand extends ArrayList<Card> {

    public Card takeBestCard() {
        Card bestCard = null;
        int bestValue = 0;
        for(Card card : this){
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
        for(Card card : this){
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
}
