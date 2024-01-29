package darkyiu.me.ctf3.commands;

import darkyiu.me.ctf3.CTF3;
import darkyiu.me.ctf3.kits.Kits;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return false;
        if(strings[0]==null)return false;
        if(Kits.getKit(strings[0])==null)return false;
        Player player = (Player) commandSender;
        Kits kits = Kits.getKit(strings[0]);
        CTF3.getPlugin().getKitManager().giveKit(player, kits);
        return false;
    }
}
