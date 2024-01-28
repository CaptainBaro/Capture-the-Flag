package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.gamestates.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackListener implements Listener {

    private CTF plugin;


    public AttackListener(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if(plugin.getGameStateManager().getCurrentGameState().equals(GameState.INGAME_STATE)){
            if(event.getEntity() instanceof Player){
                if(event.getDamager() instanceof Player) {
                    if (plugin.getArrayListManager().getShadowrunPlayers().contains(event.getDamager()))event.setCancelled(true);

                    if(!plugin.getArrayListManager().getIngamePlayers().contains(event.getDamager()))event.setCancelled(true);
                    Player attacker = (Player) event.getDamager();
                    Player target = (Player) event.getEntity();
                    if (plugin.getTeamManager().getPlayerTeam(target) == plugin.getTeamManager().getPlayerTeam(attacker)) {
                        event.setCancelled(true);
                        return;
                    }
                }else {
                    event.setCancelled(true);
                }
            }else {
                event.setCancelled(true);
            }
        }else {
            event.setCancelled(true);
            return;
        }
    }

}
