package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.LobbyCountdown;
import github.captionbaro.io.ctf.teams.Team;
import github.captionbaro.io.ctf.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class Lobbystate extends GameState{

    public static final int MIN_PLAYERS = 1,
                            MAX_PLAYER = 10;
    private CTF plugin;

    private LobbyCountdown countdown;

    public Lobbystate(GameStateManager gameStateManager, CTF plugin){
        this.plugin = plugin;
        countdown = new LobbyCountdown(gameStateManager, plugin);
    }

    @Override
    public void start() {
        countdown.startIdle();
    }

    @Override
    public void stop() {
        TeamManager teamManager = plugin.getTeamManager();
        for (Player current : plugin.getArrayListManager().getIngamePlayers()){
            if(teamManager.getPlayerTeam(current) == null){
                teamManager.setPlayerTeam(current, Team.values()[new Random().nextInt(Team.values().length)]);
                current.sendMessage(CTF.PREFIX + "§aDu wurdest einem Team zugewiesen.");
                current.setDisplayName(teamManager.getPlayerTeam(current).getChatColor() + current.getName());
            }
            current.sendMessage(CTF.PREFIX + "§aDu bist in Team " + teamManager.getPlayerTeam(current).getTeamName() + "§a.");
        }
    
        Bukkit.broadcastMessage("Wir wären jetzt im IngameState!");
    }

    public LobbyCountdown getCountdown() {
        return countdown;
    }
}
