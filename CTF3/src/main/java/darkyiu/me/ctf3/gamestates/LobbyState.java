package darkyiu.me.ctf3.gamestates;

import darkyiu.me.ctf3.countdowns.LobbyCountdown;

public class LobbyState extends GameState{

    public static final int MIN_PLAYERS = 1, MAX_PLAYERS = 10;

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager){
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start() {
        System.out.println("LOBBYSTATE GESTRARTET");
    }

    @Override
    public void stop() {

    }
}
