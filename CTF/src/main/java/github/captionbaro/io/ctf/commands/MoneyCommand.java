package github.captionbaro.io.ctf.commands;


import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import me.TSMR.Currency.Balance;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyCommand implements CommandExecutor, TabCompleter {

    private CTF plugin;


    public MoneyCommand(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            PermissionTester permissionTester = new PermissionTester();
            if (!permissionTester.developerCommandTester((Player) sender)){
                sender.sendMessage(CTF.NO_PERMISSION);
                return false;
            }
        }
        if(args.length != 3){
            sender.sendMessage("§c/money <add|remove|set <name> <number>");
            return false;
        }
        if (args[0].equalsIgnoreCase("add")){
            if(Bukkit.matchPlayer(args[1] ) != null){
                Player player = Bukkit.getPlayer(args[1]);
                Balance.getInstance().add(player, Integer.parseInt(args[2]));
                plugin.getScoreboardAnimator().setup(player);
            }else {
                sender.sendMessage("§cDieser Spieler ist gerade nicht online.");
            }
        }
        else if(args[0].equalsIgnoreCase("remove")){
            if(Bukkit.matchPlayer(args[1]) != null){
                Player player = Bukkit.getPlayer(args[1]);
                Balance.getInstance().withdraw(player, Integer.parseInt(args[2]));
                plugin.getScoreboardAnimator().setup(player);
            }else {
                sender.sendMessage("§cDieser Spieler ist gerade nicht online.");
            }
        }else if(args[0].equalsIgnoreCase("set")){
            if(Bukkit.matchPlayer(args[1]) != null){
                Player player = Bukkit.getPlayer(args[1]);
                Balance.getInstance().set(player, Integer.parseInt(args[2]));
                plugin.getScoreboardAnimator().setup(player);
            }else {
                sender.sendMessage("§cDieser Spieler ist gerade nicht online.");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        if (args.length == 0)return list;
        if(args.length == 1){
            list.add("add");
            list.add("remove");
            list.add("set");
            return list;
        }else return null;

    }
}
