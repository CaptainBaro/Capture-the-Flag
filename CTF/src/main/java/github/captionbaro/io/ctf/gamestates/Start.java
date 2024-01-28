package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.LobbyCountdown;

public class Start {

    private CTF plugin;

    public Start(CTF plugin){
        this.plugin = plugin;
    }
    public void init(){
        Lobbystate lobbystate = (Lobbystate) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbystate.getCountdown();
        if(plugin.getArrayListManager().getIngamePlayers().size() >= Lobbystate.MIN_PLAYERS) {
            if (!countdown.isRunning()) {
                countdown.stopIdle();
                countdown.start();

            }
        }
    }

}
