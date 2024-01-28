package github.captionbaro.io.ctf.commands;

import github.captionbaro.io.ctf.gui.Gamble;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GambleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            Gamble.gamble(player);
        }
        return false;
    }
}
