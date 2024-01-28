package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
    if(!CTF.getPlugin().getPermissionManager().havePermission(event.getPlayer(), "build")){
        event.setCancelled(true);
    }

    }
}
