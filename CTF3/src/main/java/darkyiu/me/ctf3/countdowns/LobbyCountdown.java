package darkyiu.me.ctf3.countdowns;

import darkyiu.me.ctf3.gamestates.GameStateManager;
import darkyiu.me.ctf3.gamestates.LobbyState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown{

    private static final int IDLE_TIME = 15;

    private GameStateManager gameStateManager;
    private int idleID;
    private boolean isIdling;

    public LobbyCountdown(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    public void startIdle(){
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§7Bis zum Spielstart fehlen noch §6" +
                        (LobbyState.MIN_PLAYERS - gameStateManager.getPlugin().getArrayListManager().getPlayers().size()) +
                        " Spieler§7.");
            }
        }, 0, IDLE_TIME * 20);
    }
}
