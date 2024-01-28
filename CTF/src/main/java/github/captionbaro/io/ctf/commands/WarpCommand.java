package github.captionbaro.io.ctf.commands;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.warp.WarpManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        WarpManager warpManager = CTF.getPlugin().getWarpManager();
        if(!(commandSender instanceof Player)){
            return false;
        }
        Player p = (Player) commandSender;
        if(args.length == 1){
            if(warpManager.getWarp(args[0]) != null){
                p.teleport(warpManager.getWarp(args[0]));
                p.sendMessage("§aDu wurdest zu dem Warp " + args[0] + " teleportiert!");
            }else{
                p.sendMessage("§cDiesen Warp gibt es nicht");
            }
        }else if(args.length == 2){

            if(args[0].equalsIgnoreCase("add")){
                if(warpManager.getWarp(args[1]) == null){
                    warpManager.createWarp(args[1], p.getLocation());
                    p.sendMessage("§aDu hast einen neuen Warp erstellt.");
                }else {
                    p.sendMessage("§cDiesen Warp gibt es schon.");
                }
            }
        }else if(args[0].equalsIgnoreCase("delete")){
            if(warpManager.getWarp(args[1]) != null){
                warpManager.deleteWarp(args[1]);
                p.sendMessage("§aDu hast den Warp " + args[1] + "gelöscht!");
            }else {
                p.sendMessage("§cDiesen Warp gibt es nicht!");
            }
        }
        return false;
    }
}