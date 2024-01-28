package github.captionbaro.io.ctf.commands;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.LobbyCountdown;
import github.captionbaro.io.ctf.gamestates.IngameState;
import github.captionbaro.io.ctf.gamestates.Lobbystate;
import github.captionbaro.io.ctf.gui.LobbyItems;
import github.captionbaro.io.ctf.util.ConfigLocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {

    private CTF plugin;

    public HubCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(args.length == 0){
            if(commandSender instanceof Player){
                Player player = (Player) commandSender;
                if(plugin.getArrayListManager().getIngamePlayers().contains(player)){
                    plugin.getArrayListManager().getIngamePlayers().remove(player);
                    if(plugin.getGameStateManager().getCurrentGameState() instanceof Lobbystate){
                        for (Player all : plugin.getArrayListManager().getIngamePlayers()){
                            all.sendMessage(CTF.PREFIX + "§c" + player.getDisplayName() + " §7hat das Spiel verlassen. [" + plugin.getArrayListManager().getIngamePlayers().size() + "/" + Lobbystate.MAX_PLAYER + "]");
                        }
                        Lobbystate lobbystate = (Lobbystate) plugin.getGameStateManager().getCurrentGameState();
                        LobbyCountdown countdown = lobbystate.getCountdown();
                        if(plugin.getArrayListManager().getIngamePlayers().size() < Lobbystate.MIN_PLAYERS){
                            if(countdown.isRunning()){
                                countdown.stop();
                                countdown.startIdle();
                            }
                        }
                    }else if(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState) {
                        player.getInventory().clear();
                        plugin.getScoreboardAnimator().setup(player);
                        ConfigLocationUtil configLocationUtil = new ConfigLocationUtil(plugin, "Lobby");
                        LobbyItems lobbyItems = new LobbyItems(plugin);
                        lobbyItems.setItems(player);
                        if (configLocationUtil.loadLocation() != null) {
                            player.teleport(configLocationUtil.loadLocation());
                        } else
                            Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
                    }


                }else {
                    ConfigLocationUtil configLocationUtil = new ConfigLocationUtil(plugin, "Lobby");
                    if(configLocationUtil.loadLocation() != null){
                        player.teleport(configLocationUtil.loadLocation());
                    }else {
                        player.teleport(player.getWorld().getSpawnLocation());
                    }
                }
            }else {
                    commandSender.sendMessage("§cDu musst ein Spieler sein.");
                }
        }else {
            commandSender.sendMessage("§cBitte benutze /hub");
        }

        return false;
    }
}
