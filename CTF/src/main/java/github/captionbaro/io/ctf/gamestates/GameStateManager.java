package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.kit.utils.KitManager;

public class GameStateManager {

    private CTF plugin;
    private KitManager kitManager;
    private GameState[] gameStates;
    private GameState currentGameState;


    public GameStateManager(CTF plugin){
        this.plugin = plugin;
        gameStates = new GameState[3];
        kitManager = new KitManager();


        gameStates[GameState.LOBBY_STATE] = new Lobbystate(this, getPlugin()) ;
        gameStates[GameState.INGAME_STATE] = new IngameState(getPlugin(), kitManager);
        gameStates[GameState.ENDING_STATE] = new EndingState(getPlugin());
    }
    public void setGameState(int gameStateID){
        if (currentGameState != null)
            currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }
    public void stopCurrentGameState(){
        if(currentGameState != null) {
            currentGameState.stop();
        }
        currentGameState = null;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public CTF getPlugin() {
        return plugin;
    }
}
