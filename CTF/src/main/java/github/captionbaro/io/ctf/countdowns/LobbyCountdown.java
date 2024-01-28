package github.captionbaro.io.ctf.countdowns;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gamestates.GameState;
import github.captionbaro.io.ctf.gamestates.GameStateManager;
import github.captionbaro.io.ctf.gamestates.Lobbystate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class    LobbyCountdown extends Countdown{

    private static final int COUNTDOWN_TIME = 60, IDLE_TIME = 15;

    private GameStateManager gameStateManager;

    private int seconds;
    private int idleID;
    private boolean isRunning;
    private boolean isIdling;
    private CTF plugin;


    public LobbyCountdown(GameStateManager gameStateManager, CTF plugin) {
        this.gameStateManager = gameStateManager;
        this.plugin = plugin;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (seconds){
                    case 60 :  case 30: case 20: case 10: case 5: case 3: case 2:
                        for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                            all.sendMessage(CTF.PREFIX + "§7Das Spiel startet in §a" + seconds + " Sekunden§7.");
                        }
                        System.out.println(CTF.PREFIX + "§7Das Spiel startet in §a" + seconds + " Sekunden§7.");
                        break;
                    case 1:
                        for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                            all.sendMessage(CTF.PREFIX + "§7Das Spiel startet in §aeiner Sekunde§7.");
                        }
                        System.out.println(CTF.PREFIX + "§7Das Spiel startet in §aeiner Sekunde§7.");
                        break;
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        },0, 20);
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
                for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                    all.sendMessage(CTF.PREFIX + "§7Bis zum Spielstart fehlen noch §6" + (Lobbystate.MIN_PLAYERS - gameStateManager.getPlugin().getArrayListManager().getIngamePlayers().size()) + " Spieler§7.");
                }
            }
        }, 0, 20 * IDLE_TIME);
    }

    public void stopIdle(){
        if(isIdling){
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
