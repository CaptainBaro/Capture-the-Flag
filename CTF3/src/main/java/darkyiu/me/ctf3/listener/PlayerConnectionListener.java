package darkyiu.me.ctf3.listener;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.countdowns.LobbyCountdown;
import darkyiu.me.ctf3.gamestates.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private CTF3 plugin;

    public PlayerConnectionListener(CTF3 plugin){
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (Player vanished : plugin.getArrayListManager().getVanished()){
            event.getPlayer().hidePlayer(plugin, vanished);
        }
        /*if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState))return;
        Player player = event.getPlayer();
        plugin.getArrayListManager().getPlayers().add(player);
        event.setJoinMessage("§a" + player.getDisplayName() + " §7ist dem Spiel beigetreten. [" + plugin.getArrayListManager().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if(plugin.getArrayListManager().getPlayers().size() >= LobbyState.MIN_PLAYERS){
            if (!countdown.isRunning()){
                countdown.stopIdle();
                countdown.start();
            }
        }*/
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getArrayListManager().getPlayers().remove(event.getPlayer());
        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState))return;
        Player player = event.getPlayer();
        event.setQuitMessage("§c" + player.getDisplayName() + " §7hat das Spiel verlassen. [" + plugin.getArrayListManager().getPlayers().size() + "/" + LobbyState.MAX_PLAYERS + "]");

        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if (plugin.getArrayListManager().getPlayers().size() < LobbyState.MIN_PLAYERS){
            if (countdown.isRunning()){
                countdown.stop();
                countdown.startIdle();
            }
        }
    }


}
