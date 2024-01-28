package github.captionbaro.io.ctf.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JumpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player  = (Player) commandSender;
            player.teleport((Location) player.getLocation().getWorld().getHighestBlockAt(player.getLocation()));
        }
        return false;
    }
}
