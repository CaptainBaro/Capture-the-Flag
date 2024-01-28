package github.captionbaro.io.ctf.commands;

import github.captionbaro.io.ctf.CTF;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    private CTF plugin;

    public RankCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        if(args.length == 3){
            if(args[0].equalsIgnoreCase("add")){
                if(Bukkit.matchPlayer(args[1]) != null){
                    Player  player = Bukkit.getPlayer(args[1]);
                    if (!plugin.getPermissionManager().havePermission(player, args[2])){
                        plugin.getPermissionManager().addPermission(player, args[2]);
                        plugin.getScoreboardAnimator().setup(player);
                    }else {
                        sender.sendMessage("§cDer Spieler hat die Permission schon!");
                    }
                }else {
                    sender.sendMessage("§cNicht online.");
                }
            }else if (args[0].equalsIgnoreCase("remove")){
                if(Bukkit.matchPlayer(args[1]) != null){
                    Player player = Bukkit.getPlayer(args[1]);
                    if(plugin.getPermissionManager().havePermission(player, args[2])){
                        plugin.getPermissionManager().removePermission(player, args[2]);
                        plugin.getScoreboardAnimator().setup(player);
                    }else {
                        sender.sendMessage("§cDieser Spieler hat die Permission nicht.");
                    }
                }else {
                    sender.sendMessage("§cNicht online!");
                }
            }
        }else {
            sender.sendMessage("§c/rank <add|remove> <name> <permission>");
        }
    return false;
    }


}
