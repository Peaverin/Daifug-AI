package model;

import java.util.Collection;
import java.util.List;

public class ModelFacade {
    private GameContainer gameContainer;

    public ModelFacade(){
        gameContainer = new GameContainer();
    }
    public Collection<String> getPlayerNames(){
        return gameContainer.getPlayerNamesCollection();
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
    public void dealCards(String grandMillionare, String millionaire, String poor, String extremelyPoor){
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

    public void exchangeCards(String grandMillionare, String millionaire, String poor, String extremelyPoor){
        if(grandMillionare != null){
            for(Card card : gameContainer.takeTwoBestCardsFromPlayer(extremelyPoor)){
                gameContainer.addCardToPlayer(grandMillionare, card);
            }
        }
        if(millionaire != null){
            gameContainer.addCardToPlayer(millionaire, gameContainer.takeBestCardFromPlayer(poor));
        }
    }


    private String getNextPlayer(String currentPlayer) {
        String nextPlayer = gameContainer.getNextPlayer(currentPlayer);
        while(gameContainer.getPlayerTurnState(nextPlayer) != TurnState.PLAYING){
            nextPlayer = gameContainer.getNextPlayer(nextPlayer);
        }
        return nextPlayer;
    }

    public String getPlayerWithThreeOfClubs() {
        for(Player player : gameContainer.getPlayerCollection()){
            if(player.hasCard(CardRank.CLUB, CardNum.THREE)){
                return player.getName();
            }
        }
        return null;
    }

    /**
     * The logic of this method takes place here because I consider it might change depending on the applied rules, so it's part of the Facade Pattern.
     * @param cardsOnTable
     * @return
     */
    public String[] getPlayerPossibleMoves(List<String> cardsOnTable) {
        if(cardsOnTable == null){//This means there's nothing on table
            
        }
    }
}
