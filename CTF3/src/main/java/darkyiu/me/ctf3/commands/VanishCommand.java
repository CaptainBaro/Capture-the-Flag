package darkyiu.me.ctf3.commands;

import darkyiu.me.ctf3.CTF3;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    private CTF3 plugin;
    public VanishCommand(CTF3 plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player))return false;
        Player player = (Player) commandSender;
        if (plugin.getArrayListManager().getVanished().contains(player)){
            for (Player player1 : Bukkit.getOnlinePlayers()){
                player1.showPlayer(plugin,player);
            }
            plugin.getArrayListManager().getVanished().remove(player);
            player.sendMessage(CTF3.prefix + "§7You are now visible again.");
        }else {
            for (Player player1 : Bukkit.getOnlinePlayers()){
                player1.hidePlayer(plugin, player);
            }
            plugin.getArrayListManager().getVanished().add(player);
            player.sendMessage(CTF3.prefix + "§7You are now invisible to other players.");
        }
        return false;
    }
}
