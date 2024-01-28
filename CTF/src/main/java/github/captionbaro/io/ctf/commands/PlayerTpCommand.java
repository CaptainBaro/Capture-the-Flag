package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PlayerTpCommand implements CommandExecutor, Listener {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            PermissionTester permissionTester = new PermissionTester();
            if(!permissionTester.staffCommandTester(((Player) commandSender))){
                commandSender.sendMessage(CTF.NO_PERMISSION);
            }
            ((Player) commandSender).openInventory(CTF.getTeleportInv());
        }else {
            commandSender.sendMessage("§cDu musst ein Spieler sein um das zu machen.");
        }
        return false;
    }
}
