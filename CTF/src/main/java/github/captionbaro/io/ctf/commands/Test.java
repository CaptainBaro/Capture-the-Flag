package github.captionbaro.io.ctf.commands;

import de.baro.cryptoscore.permissions.PermissionTester;
import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.util.Effects;
import github.captionbaro.io.ctf.util.ItemBuilder;
import github.captionbaro.io.ctf.util.ParticleData;
import github.captionbaro.io.ctf.util.PrestigeManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Test implements CommandExecutor {

    private CTF plugin;

    public Test(CTF plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            PermissionTester permissionTester = new PermissionTester();
            if(!permissionTester.developerCommandTester(player)){
                player.sendMessage(CTF.NO_PERMISSION);
                return false;
            }
            ParticleData particle = new ParticleData(player.getUniqueId().toString() + ".Explosion");
            if(args[0].equalsIgnoreCase("effect")){
                if (particle.hasID()){
                    particle.endTask();
                    particle.removeID();
                    return true;
                }
                Effects trails = new Effects(player, plugin);
                trails.startExplosion();
            }else if(args[0].equalsIgnoreCase("skull")){
                ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                skullMeta.setOwningPlayer(player);
                skull.setItemMeta(skullMeta);
                player.getInventory().addItem(skull);
            }else if (args[0].equalsIgnoreCase("item")){
                ItemStack itemStack = new ItemBuilder(Material.STICK).setName("§aCadaceus").setLore("§3Hermes heiliger Stab!").setModelData(13).build();
                player.getInventory().addItem(itemStack);
                ItemStack itemStack1 = new ItemStack(Material.STONE_SWORD);
                itemStack1.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(player.getName(),10, AttributeModifier.Operation.ADD_NUMBER));
                player.getInventory().addItem(itemStack1);
            }else if(args[0].equalsIgnoreCase("book")){
               ItemStack itemStack = new ItemStack(Material.BOOK);
                BookMeta bookMeta = (BookMeta) itemStack;
                bookMeta.setTitle("§aDas heilige Buch des Ordens");
                bookMeta.setPage(0, ChatColor.AQUA + "Dies ist das heilige Buch des Ordens.\n" + ChatColor.DARK_RED +
                        "Wer ihm nicht gehorcht und sein Leben nicht nach ihm ordnet, ist ein Ketzter und gehört geopfert.\n" + ChatColor.GREEN +
                        "Doch wer ihm dient, der soll ein angesehener Mann sein.");
                bookMeta.setAuthor("§kOrioan");
                itemStack.setItemMeta(bookMeta);
                player.getInventory().addItem(itemStack);
                return true;
            }else if (args[0].equalsIgnoreCase("levelup")){
                PrestigeManager.setEP(player, 100);
            }



        }
        return false;
    }
}
