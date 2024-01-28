package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.ImageManager;
import github.captionbaro.io.ctf.util.ImageRenderer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;

public class MapCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        Player player = (Player)commandSender;
        PermissionTester permissionTester = new PermissionTester();
        if(!permissionTester.staffCommandTester(player)){
            player.sendMessage(CTF.NO_PERMISSION);
            return false;
        }
        if(args.length == 0){
            player.sendMessage("§cWrong Usage, /map <link>");
            return true;
        }

        MapView view = Bukkit.createMap(player.getWorld());
        view.getRenderers().clear();

        ImageRenderer renderer = new ImageRenderer();
        if(!renderer.load(args[0])){
            player.sendMessage("§cImage could not be loaded!");
            return true;
        }
        view.addRenderer(renderer);

        ItemStack map = new ItemStack(Material.FILLED_MAP);
        MapMeta meta = (MapMeta) map.getItemMeta();

        meta.setMapView(view);
        map.setItemMeta(meta);

        player.getInventory().addItem(map);
        player.sendMessage("§aImage created.");

        ImageManager manager = ImageManager.getInstance();
        manager.saveImage(view.getId(), args[0]);
        return true;
    }
}
