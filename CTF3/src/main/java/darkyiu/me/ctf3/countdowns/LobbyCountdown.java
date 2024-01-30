package darkyiu.me.ctf3.countdowns;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.gamestates.GameState;
import darkyiu.me.ctf3.gamestates.GameStateManager;
import darkyiu.me.ctf3.gamestates.LobbyState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown{

    private static final int COUNTDOWN_TIME = 20,IDLE_TIME = 15;

    private GameStateManager gameStateManager;

    private int seconds;
    private boolean isRunning;
    private int idleID;
    private boolean isIdling;

    public LobbyCountdown(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (seconds){
                    case 20: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(CTF3.prefix + "§7Das Spiel startet in §a " + seconds + " Sekunden§7.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(CTF3.prefix + "§7Das Spiel startet in §aeiner Sekunde§7");
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME__STATE);
                        break;
                    default:
                        break;
                }
                seconds--;
            }
        },0,20);
    }

    @Override
    public void stop() {
        if(isRunning){
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle(){
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage("§7Bis zum Spielstart fehlen noch §6" +
                        (LobbyState.MIN_PLAYERS - gameStateManager.getPlugin().getArrayListManager().getPlayers().size()) +
                        " Spieler§7.");
            }
        }, 0, IDLE_TIME * 20);
    }

    public void stopIdle(){
        if (isIdling){
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
