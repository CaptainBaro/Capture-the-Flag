package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class NickCommand implements CommandExecutor {
    public static Scoreboard scoreboard;
    public final int HEALTH = 5;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            PermissionTester permissionTester = new PermissionTester();
            Player player = (Player) sender;
            if(!permissionTester.spartanUpCommandTester(player)){
                player.sendMessage("§cDu musst mindestens Spartan sein um das zu machen.");
                return false;
            }
            if(args.length <= 1){
                player.sendMessage("/nick <player> <name>");
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            target.setDisplayName(args[1]);
            target.setPlayerListName(args[1]);

        }

        return true;
    }
}
