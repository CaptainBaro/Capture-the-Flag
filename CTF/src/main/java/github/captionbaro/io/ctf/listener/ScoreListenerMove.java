package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gamestates.GameState;
import github.captionbaro.io.ctf.teams.Team;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class ScoreListenerMove implements Listener {

    private CTF plugin;

    public ScoreListenerMove(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void on_move(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getHelmet() == null)return;
        if(player.getInventory().getHelmet().getType() == Material.RED_BANNER){
            Location abovePlayer = new Location(player.getWorld(),player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 8);
            abovePlayer.getWorld().playEffect(abovePlayer, Effect.ENDER_SIGNAL, 8);

            Team playerTeam = plugin.getTeamManager().getPlayerTeam(player);
            if(playerTeam != null){
                double distance = player.getLocation().distance(playerTeam.getBannerLocation());
                if(distance < 5){
                    player.getInventory().remove(Material.RED_BANNER);
                    player.getInventory().setHelmet(plugin.getKitManager().getPlayerKit(player).getArmorcontents()[3]);
                    Team scoredTeam = playerTeam;
                    Team.RED.setFlagStolen(false);
                    Team.BLUE.setFlagStolen(false);
                    plugin.getScoreManager().setScoreBlue(plugin.getScoreManager().getScoreBlue() + 1);
                    plugin.getGameStateManager().setGameState(GameState.ENDING_STATE);
                }
            }
        }else if(player.getInventory().getHelmet().getType() == Material.BLUE_BANNER){
            Location abovePlayer = new Location(player.getWorld(),player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
            player.getLocation().getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 8);
            abovePlayer.getWorld().playEffect(abovePlayer, Effect.ENDER_SIGNAL, 8);

            Team playerTeam = plugin.getTeamManager().getPlayerTeam(player);
            if(playerTeam != null){
                double distance = player.getLocation().distance(playerTeam.getBannerLocation());
                if(distance < 5){
                    player.getInventory().remove(Material.BLUE_BANNER);
                    player.getInventory().setHelmet(plugin.getKitManager().getPlayerKit(player).getArmorcontents()[3]);
                    Team scoredTeam = playerTeam;
                    Team.BLUE.setFlagStolen(false);
                    Team.RED.setFlagStolen(false);
                    plugin.getScoreManager().setScoreRed(plugin.getScoreManager().getScoreRed() + 1);
                    plugin.getGameStateManager().setGameState(GameState.ENDING_STATE);
                }
            }
        }
    }

}
