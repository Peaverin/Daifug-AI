package model;

public class PlayerIntelligenceFactory {
    public static PlayerIntelligence createPlayerIntelligence(String strategy) throws PIFException{
        PlayerIntelligence playerIntelligence;
        try{
            String name = PlayerIntelligence.class.getPackage().getName();
            playerIntelligence = (PlayerIntelligence)Class.forName(name + "." + strategy + "PlayerIntelligence").newInstance();
            return playerIntelligence;
        }catch (Exception e){
            throw new PIFException("Invalid player intelligence name!");
        }
    }
}
