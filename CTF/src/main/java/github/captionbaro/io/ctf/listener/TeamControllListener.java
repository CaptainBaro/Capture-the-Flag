package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gamestates.IngameState;
import github.captionbaro.io.ctf.teams.Team;
import github.captionbaro.io.ctf.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TeamControllListener implements Listener {

    private CTF plugin;

    public TeamControllListener(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerChat(AsyncPlayerChatEvent event){
        Player p = event.getPlayer();
        event.setCancelled(true);
        if(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState){
            if(!plugin.getArrayListManager().getIngamePlayers().contains(p)){
                Bukkit.broadcastMessage(p.getDisplayName() + "§f : " + event.getMessage());
                return;
            }
            if(!event.getMessage().startsWith("@all")){
                TeamManager  teamManager = plugin.getTeamManager();
                Team team = teamManager.getPlayerTeam(p);
                for (Player current : plugin.getArrayListManager().getPlayers()){
                    if(teamManager.getPlayerTeam(current) == team){
                        current.sendMessage("§7[" + team.getChatColor() + "Team§7] " + p.getDisplayName() + "§f : " + event.getMessage());
                    }

                }
            }else {
                String message = event.getMessage().replace("@all", "");
                Bukkit.broadcastMessage("§7[@all] " + p.getDisplayName() + "§f :" + message);

            }
        }else {
            Bukkit.broadcastMessage(p.getDisplayName() + "§f : " + event.getMessage());
        }
    }

}
