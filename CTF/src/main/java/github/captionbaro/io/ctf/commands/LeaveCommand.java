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

public class LeaveCommand implements CommandExecutor {

    private CTF plugin;

    public LeaveCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        LobbyItems lobbyItems = new LobbyItems(plugin);
        ConfigLocationUtil configLocationUtil = new ConfigLocationUtil(plugin, "Lobby");
        if(sender instanceof Player){
            if(args.length == 0){
                Player player = (Player) sender;
                if(plugin.getArrayListManager().getIngamePlayers().contains(player)){
                    plugin.getArrayListManager().getIngamePlayers().remove(player);
                    if(configLocationUtil.loadLocation() != null){
                        player.teleport(configLocationUtil.loadLocation());
                    }else
                        Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
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
                    }else if(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState){
                        player.getInventory().clear();
                        plugin.getScoreboardAnimator().setup(player);
                        lobbyItems.setItems(player);

                        if(configLocationUtil.loadLocation() != null){
                            player.teleport(configLocationUtil.loadLocation());
                        }else
                            Bukkit.getConsoleSender().sendMessage("§cDie Lobby-Location wurde noch nicht gesetzt!");
                    }

                }else {
                    player.sendMessage("§cDu bist in keinem Spiel.");
                }
            }else if (args.length == 2){

            }else {
                sender.sendMessage("§cUse /leave (<name>)");
            }
        }
        return false;
    }
}
