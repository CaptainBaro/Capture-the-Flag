package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        PermissionTester permissionTester = new PermissionTester();
        if(!(commandSender instanceof Player)){
            return false;
        }
        Player p = (Player) commandSender;
        if (!permissionTester.staffCommandTester(p)){
            p.sendMessage(CTF.NO_PERMISSION);
            return false;
        }
        if(args.length == 1){
            if(WarpManager.getWarp(args[0]) != null){
                p.teleport(WarpManager.getWarp(args[0]));
                p.sendMessage("§aDu wurdest zu dem Warp " + args[0] + " teleportiert!");
            }else{
                p.sendMessage("§cDiesen Warp gibt es nicht");
            }
        }else if(args.length == 2){
            if(!CTF.getPlugin().getPermissionManager().havePermission(p, "staff"))return false;
            if(args[0].equalsIgnoreCase("add")){
                if(WarpManager.getWarp(args[1]) == null){
                    WarpManager.createWarp(args[1], p.getLocation());
                    p.sendMessage("§aDu hast einen neuen Warp erstellt.");
                }else {
                    p.sendMessage("§cDiesen Warp gibt es schon.");
                }
            }else if(args[0].equalsIgnoreCase("delete")){
                if(WarpManager.getWarp(args[1]) != null){
                    WarpManager.deletWarp(args[1]);
                    p.sendMessage("§aDu hast den Warp " + args[1] + "gelöscht!");
                }else {
                    p.sendMessage("§cDiesen Warp gibt es nicht!");
                }
            }
        }
        return false;
    }
}
