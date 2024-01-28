package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.LobbyCountdown;
import github.captionbaro.io.ctf.gamestates.Lobbystate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class LobbyConnectionListener implements Listener {
    private CTF plugin;

    public LobbyConnectionListener(CTF plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        plugin.getArrayListManager().getPlayers().add(e.getPlayer());
    }
    @EventHandler
    public void handlePlayerQuit(PlayerQuitEvent e){
        plugin.getArrayListManager().getVanishedPlayers().remove(e.getPlayer());
        plugin.getArrayListManager().getPlayers().remove(e.getPlayer());
        if(plugin.getArrayListManager().getIngamePlayers().contains(e.getPlayer())){
            plugin.getArrayListManager().getIngamePlayers().remove(e.getPlayer());
        }
        e.setQuitMessage("");
        if(plugin.getGameStateManager().getCurrentGameState() instanceof Lobbystate){
            for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                all.sendMessage(CTF.PREFIX + "§c" + e.getPlayer().getDisplayName() + " §7hat das Spiel verlassen. [" + plugin.getArrayListManager().getIngamePlayers().size() + "/" + Lobbystate.MAX_PLAYER + "]");
            }
            Lobbystate lobbystate = (Lobbystate) plugin.getGameStateManager().getCurrentGameState();
            LobbyCountdown countdown = lobbystate.getCountdown();
            if(plugin.getArrayListManager().getIngamePlayers().size() < Lobbystate.MIN_PLAYERS){
                if(countdown.isRunning()){
                    countdown.stop();
                    countdown.startIdle();
                }
            }

        }
    }


}
