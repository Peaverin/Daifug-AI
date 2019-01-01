package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck extends ArrayDeque<Card> {
    public Deck(){
        //Populate the deck with all the cards
        for(CardRank rank : CardRank.values()){
            for(CardNum num : CardNum.values()){
                add(new Card(rank,num));
            }
        }
    }

    public void shuffle(){
        List<Card> cardList = new ArrayList<>();
        while(!isEmpty()){
            cardList.add(this.removeFirst());
        }
        Collections.shuffle(cardList);
        for(Card card : cardList){
            this.push(card);
        }
    }

    public Card takeCard(){
        return pop();
    }
}
