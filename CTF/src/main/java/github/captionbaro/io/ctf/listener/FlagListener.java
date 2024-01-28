package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.teams.Team;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class FlagListener implements Listener {

    private CTF plugin;

    public FlagListener(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onTakeFlag(BlockBreakEvent e){
        if(plugin.getArrayListManager().getIngamePlayers().contains(e.getPlayer())){
            if(!(plugin.getArrayListManager().getIngamePlayers().contains(e.getPlayer())))return;
            if(e.getBlock().getType() == Material.RED_BANNER){
                    Player player = (Player) e.getPlayer();
                    e.setCancelled(true);
                    if(plugin.getTeamManager().getPlayerTeam(player).equals(Team.BLUE)) {
                        e.getBlock().setType(Material.AIR);
                        player.getInventory().setHelmet(new ItemStack(Material.RED_BANNER));
                        plugin.getTeamManager().getPlayerTeam(player).setFlagStolen(true);
                    }
            }else if (e.getBlock().getType() == Material.BLUE_BANNER){
                    Player player = (Player) e.getPlayer();
                    e.setCancelled(true);
                    if(plugin.getTeamManager().getPlayerTeam(player).equals(Team.RED)){
                        e.getBlock().setType(Material.AIR);
                        player.getInventory().setHelmet(new ItemStack(Material.BLUE_BANNER));
                        plugin.getTeamManager().getPlayerTeam(player).setFlagStolen(true);
                    }
            }else {
                if(e.getPlayer().getGameMode() == GameMode.CREATIVE){
                    if(plugin.getPermissionManager().havePermission(e.getPlayer(), "build")){
                        return;
                    }else
                        e.setCancelled(true);
                }else {
                    e.setCancelled(true);
                }
            }
        }else{
            if(plugin.getPermissionManager().havePermission(e.getPlayer(), "build")){
                return;
            }else {
                e.setCancelled(true);
            }
        }
    }

}
