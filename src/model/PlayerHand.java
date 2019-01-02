package model;

import java.util.*;

public class PlayerHand extends LinkedHashMap<String, Card> {

    public Card takeCard(String card){
        return this.remove(card);
    }

    public Card takeBestCard() {
        String bestCard = null;
        int bestValue = 0;
        for(Card card : this.values()){
            int cardStrenght = card.getStrenght();
            if (cardStrenght > bestValue){
                bestValue = cardStrenght;
                bestCard = card.toString();
            }
        }
        return this.remove(bestCard);
    }

    public Card[] takeTwoBestCards(){
        String bestCard = null;
        String secondBestCard = null;
        int bestValue = -1;
        int secondBestValue = -1;
        for(Card card : this.values()){
            int cardStrenght = card.getStrenght();
            if (cardStrenght > bestValue){
                secondBestCard = bestCard;
                secondBestValue = bestValue;
                bestValue = cardStrenght;
                bestCard = card.toString();
            }else if(cardStrenght > secondBestValue){
                secondBestCard = card.toString();
                secondBestValue = cardStrenght;
            }
        }
        return new Card[]{this.remove(bestCard), this.remove(secondBestCard)};
    }

    public boolean containsCard(CardRank cardRank, CardNum cardNum) {
        for(Card card : this.values()){
            if(card.getRank() == cardRank && card.getNum() == cardNum) return true;
        }
        return false;
    }


    public void sort(){
        List<String> keys = new ArrayList<>(keySet());
        List<Card> values = new ArrayList<>();
        //We remove all elements from the Map and put them on a list for sorting:
        for(String key : keys){
            values.add(remove(key));
        }
        Collections.sort(values);
        //We put the sorted list into the Map:
        for(Card card : values){
            this.put(card.toString(), card);
        }
    }

    public List<String> getAllCombinations(){
        List<Card> cardList = new ArrayList<>(values());
        List<String> combinations = new ArrayList<>();
        while(!cardList.isEmpty()){
            //We get the first card
            Card card = cardList.remove(0);
            int quantity = 1;
            //Check for cards with same number (aka searching for cards with same strenght as only cards with same number have same strenght):
            if(!cardList.isEmpty()){
                int size = cardList.size();
                int i = 0;
                while(i<size && quantity<4){
                    Card c = cardList.get(i);
                    //compareTo method of Card compares the strenghts and returns 0 if they are equal
                    if(card.compareTo(c) == 0){
                        cardList.remove(c);
                        quantity ++;
                        size--;
                    }else{
                        i++;
                    }
                }
            }

            //Now we have the possible combinations:
            for(int i = 0;i<quantity;i++){
                String combination = Integer.toString(i+1) +"x"+ card.getNumString();
                combinations.add(combination);
            }
        }
        return combinations;

    }
}
