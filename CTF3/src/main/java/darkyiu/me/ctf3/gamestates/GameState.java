package darkyiu.me.ctf3.gamestates;

public abstract class GameState {

    public static final int LOBBY_STATE = 0,
                            INGAME__STATE = 1,
                            ENDING_STATE = 2;

    public abstract void start();
    public abstract void stop();
}
