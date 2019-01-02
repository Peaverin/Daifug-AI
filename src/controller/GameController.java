package controller;

import model.ModelFacade;
import model.PIFException;

import java.util.Scanner;

public class GameController {
    ModelFacade modelFacade;
    private String grandMillionare;
    private String millionaire;
    private String poor;
    private String extremelyPoor;
    private String currentPlayer;
    private boolean turnStarted;
    Scanner sc;
    public GameController(){
        modelFacade = new ModelFacade();
        sc = new Scanner(System.in);
    }

    public void startGame() {
        setPlayers();
        modelFacade.resetDeck();
        modelFacade.shuffleDeck();
        //Nothing Should Happen because there are not positions in the first game. I let it here to remind myself of using it later.
        modelFacade.dealCards(grandMillionare, millionaire, poor, extremelyPoor);
        modelFacade.exchangeCards(grandMillionare, millionaire, poor, extremelyPoor);
        //
        currentPlayer = modelFacade.getPlayerWithThreeOfClubs();
        roundLoop();
    }

    private void setPlayers() {
        int num = 0;
        do{
            System.out.println("Please enter the number of players: ");
            num = sc.nextInt();
            sc.nextLine();
        }while(num <= 0);
        int i = 0;
        while(i<num){
            System.out.println("Enter name of player " + Integer.toString(i+1) + ": ");
            String player = sc.nextLine();
            boolean okey = false;
            while(!okey){
                System.out.println("Available intelligences: 'Greedy', 'Manual'.");
                System.out.println("Enter intelligence of player " + Integer.toString(i+1) + ": ");
                String intelligence = sc.nextLine();
                try {
                    modelFacade.addPlayer(player, intelligence);
                    okey = true;
                    i++;
                }catch(PIFException e){
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    private void roundLoop(){
        System.out.println("A new round starts. It's " + currentPlayer + "'s turn.");
        //Current player starts the turn:
        String[] playerMovesList = modelFacade.getPlayerPossibleMoves(null);
        String playerMoves = "";
        for(String move : playerMovesList){
            playerMoves += "'"+move+"', ";
        }
        System.out.println("Possible moves for " + currentPlayer + "are: " + playerMoves + ".")
        ;
    }

}
