package darkyiu.me.ctf3.gamestates;

import darkyiu.me.ctf3.CTF3;

public class GameStateManager {

    private CTF3 plugin;
    private GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(CTF3 plugin){
        this.plugin = plugin;
        gameStates = new GameState[3];

        gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
        gameStates[GameState.INGAME__STATE] = new IngameState();
        gameStates[GameState.ENDING_STATE] = new EndingState();
    }

    public void setGameState(int gameStateID){
        if(currentGameState != null)currentGameState.stop();
        currentGameState = gameStates[gameStateID];
        currentGameState.start();
    }

    public void stopCurrentGameState(){
        if(currentGameState!=null){
            currentGameState.stop();
            currentGameState = null;
        }
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public CTF3 getPlugin() {
        return plugin;
    }
}
