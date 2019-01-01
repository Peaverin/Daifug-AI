package model;

import java.util.Collection;
import java.util.List;

public class ModelFacade {
    private GameContainer gameContainer;
    private String grandMillionare;
    private String millionaire;
    private String poor;
    private String extremelyPoor;
    public ModelFacade(){
        gameContainer = new GameContainer();
    }
    public Collection<String> getPlayerNames(){
        return gameContainer.getPlayerNames();
    }
    public void addPlayer(String name, String intelligence) throws PIFException{
        gameContainer.addPlayer(name, PlayerIntelligenceFactory.createPlayerIntelligence(intelligence));
    }

    public void resetDeck(){
        gameContainer.resetDeck();
    }

    public void shuffleDeck(){
        gameContainer.shuffleDeck();
    }

    /**
     * We allow the richest players to have the fewest cards. So if there isn't an exact amount of cards, we first give cards to the extremelyPoor,
     * then the poor, then commoners, and then millionare.
     */
    public void dealCards(){
        int numberOfPlayers = gameContainer.getNumberOfPlayers();
        int totalCards = (CardNum.values().length * CardRank.values().length);
        int remainder = totalCards % numberOfPlayers;
        if(remainder == 0){
            dealAllCards();
        }else{
            //We deal an equally amount of cards to each player
            dealCardsXTimes( (totalCards-remainder) / numberOfPlayers );
            //Now we deal cards to the poorests players
            if(extremelyPoor != null){
                dealOneCardToPlayer(extremelyPoor);
                remainder--;
            }
            if(remainder >0 && poor != null){
                dealOneCardToPlayer(poor);
                remainder--;
            }
            //To the commoners, if there's any
            for(String player : getPlayerNames()){
                if(!player.equals(grandMillionare) && !player.equals(millionaire) && remainder > 0){
                    dealOneCardToPlayer(player);
                    remainder--;
                }
                if(remainder == 0) break;
            }
            //Finally, there should be at max 1 more card. We give it to the millionare
            if(remainder >0) dealOneCardToPlayer(millionaire);
        }
    }

    private void dealAllCards(){
        while(!gameContainer.deckIsEmpty()){
            dealOneCardToEach();
        }
    }
    private void dealCardsXTimes(int x) {
        for(int i = 0; i<x;i++){
            dealOneCardToEach();
        }
    }

    private void dealOneCardToEach(){
        for(String player : getPlayerNames()){
            dealOneCardToPlayer(player);
        }
    }

    private void dealOneCardToPlayer(String player){
        gameContainer.addCardToPlayer(player, gameContainer.takeCardFromDeck());
    }

    public void exchangeCards(){
        if(grandMillionare != null){
            for(Card card : gameContainer.takeTwoBestCardsFromPlayer(extremelyPoor)){
                gameContainer.addCardToPlayer(grandMillionare, card);
            }
        }
        if(millionaire != null){
            gameContainer.addCardToPlayer(millionaire, gameContainer.takeBestCardFromPlayer(poor));
        }
    }


}
