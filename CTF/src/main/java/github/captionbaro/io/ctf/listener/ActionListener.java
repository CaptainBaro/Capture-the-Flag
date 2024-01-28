package github.captionbaro.io.ctf.listener;

import github.captionbaro.io.ctf.CTF;
import github.captionbaro.io.ctf.countdowns.FahigkeitenNormal1Manager;
import github.captionbaro.io.ctf.countdowns.FähigkeitenNormal2Manager;
import github.captionbaro.io.ctf.countdowns.UltiManager;
import github.captionbaro.io.ctf.gui.ShopGui;
import github.captionbaro.io.ctf.gui.WarpGui;
import github.captionbaro.io.ctf.kit.fähigkeiten.Fähigkeiten;
import github.captionbaro.io.ctf.kit.utils.Kit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;



public class ActionListener implements Listener {


    private int taskID;

    private CTF plugin;

    public ActionListener(CTF plugin){
        this.plugin = plugin;

    }

    @EventHandler
    public void on_Right_Click(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getItem() == null)return;
            if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.RED_WOOL) {
                if (event.getPlayer().isInsideVehicle()) {
                    Entity vehicle = event.getPlayer().getVehicle();
                    vehicle.setVelocity(event.getPlayer().getLocation().getDirection().multiply(5));
                    return;
                }
                Location loc = event.getPlayer().getLocation();
                Entity minecart = loc.getWorld().spawnEntity(loc.add(loc.getDirection()), EntityType.MINECART);
                minecart.setPassenger(event.getPlayer());
                minecart.setGravity(false);
                minecart.setVelocity(loc.getDirection().multiply(5));
                return;
            }else if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aShop")) {
                ShopGui shopGui = new ShopGui(plugin);
                shopGui.openMainPage(event.getPlayer(), plugin);
                event.setCancelled(true);
            }else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aTeleporter")){
                WarpGui warpGui = new WarpGui();
                warpGui.openTeleporterMenu(event.getPlayer());
                event.setCancelled(true);
            }else if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.SLIME_BALL){
                event.setCancelled(true);
                Location location = event.getPlayer().getLocation();
                ItemStack itemStack = new ItemStack(Material.CROSSBOW);
                CrossbowMeta crossbowMeta = (CrossbowMeta) itemStack.getItemMeta();
            } else if (plugin.getArrayListManager().getIngamePlayers().contains(event.getPlayer())) {
                if (plugin.getArrayListManager().getFähigkeitenNormal1namen().contains(event.getItem().getItemMeta().getLocalizedName())){
                    event.setCancelled(true);
                    Player player = event.getPlayer();
                    long timeLeft = System.currentTimeMillis() - plugin.getFähigkeitenNromal1Manager().getCooldown(player.getUniqueId());
                    if (TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= FahigkeitenNormal1Manager.DEFAULT_COOLDOWN) {
                        plugin.getFähigkeitenNromal1Manager().setCooldown(player.getUniqueId(), System.currentTimeMillis());
                        Fähigkeiten fähigkeiten = new Fähigkeiten(player, plugin);
                        switch (event.getPlayer().getInventory().getItemInMainHand().getType()) {
                            case GOLDEN_APPLE:
                                fähigkeiten.aphroditenormal();
                                break;
                            case STICK:
                                fähigkeiten.hermesNormal();
                                break;
                            default:
                                break;
                        }
                    } else {
                        long cooldown = TimeUnit.MILLISECONDS.toSeconds(timeLeft) - FahigkeitenNormal1Manager.DEFAULT_COOLDOWN;

                        if(CTF.getPlugin().getKitManager().getPlayerKit(player).equals(Kit.ATHENE)){
                            if (plugin.getArrayListManager().getAphroditeNormal1().contains(player)){

                            }
                        }

                        player.sendMessage("§cYou have to waite " + cooldown * -1 + " Seconds before you can use your Attack again.");
                    }
                }else if (plugin.getArrayListManager().getFähigkeitennormal2namen().contains(event.getItem().getItemMeta().getLocalizedName())) {
                    event.setCancelled(true);
                    if(event.getPlayer().getInventory().getItemInMainHand().getType() == Material.SKELETON_SKULL){
                        if(plugin.getArrayListManager().getShadowrunPlayers().contains(event.getPlayer())) {
                            Fähigkeiten fähigkeiten = new Fähigkeiten(event.getPlayer(), plugin);
                            fähigkeiten.hadesNormal2Stop();
                            return;
                        }
                    }
                    Player player = event.getPlayer();
                    long timeLeft = System.currentTimeMillis() - plugin.getFähigkeitenNormal2Manager().getCooldown(player.getUniqueId());
                    if (TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= FähigkeitenNormal2Manager.DEFAULT_COOLDOWN) {
                        plugin.getFähigkeitenNormal2Manager().setCooldown(player.getUniqueId(), System.currentTimeMillis());
                        Fähigkeiten fähigkeiten = new Fähigkeiten(player, plugin);
                        switch (event.getPlayer().getInventory().getItemInMainHand().getType()) {
                            case GOLDEN_APPLE:
                                fähigkeiten.aphroditenormal2();
                                break;
                            case STICK:
                                fähigkeiten.hermesNormal2();
                                break;
                            case SKELETON_SKULL:
                                if(plugin.getArrayListManager().getShadowrunPlayers().contains(player)){
                                    fähigkeiten.hadesNormal2Stop();
                                    break;
                                }else {
                                    fähigkeiten.hadesNormal2();
                                    break;
                                }
                            default:
                                break;
                        }
                    } else {
                        long cooldown = TimeUnit.MILLISECONDS.toSeconds(timeLeft) - FähigkeitenNormal2Manager.DEFAULT_COOLDOWN;
                        player.sendMessage("§cYou have to waite " + cooldown * -1 + " Seconds before you can use your Attack again.");
                    }
                }else if(plugin.getArrayListManager().getUltinamen().contains(event.getItem().getItemMeta().getLocalizedName())){
                    event.setCancelled(true);
                    Player player = event.getPlayer();
                    long timeLeft = System.currentTimeMillis() - plugin.getUltiCountdownManager().getCooldown(player.getUniqueId());
                    if (TimeUnit.MILLISECONDS.toSeconds(timeLeft) >= UltiManager.DEFAULT_COOLDOWN) {
                        plugin.getUltiCountdownManager().setCooldown(player.getUniqueId(), System.currentTimeMillis());
                        Fähigkeiten fähigkeiten = new Fähigkeiten(player, plugin);
                        switch (event.getPlayer().getInventory().getItemInMainHand().getType()) {
                            case IRON_AXE:
                                fähigkeiten.aresulti();
                                break;
                            case GOLDEN_APPLE:
                                fähigkeiten.aphroditeUlti();
                            default:
                                break;
                        }
                    } else {
                        long cooldown = TimeUnit.MILLISECONDS.toSeconds(timeLeft) - UltiManager.DEFAULT_COOLDOWN;
                        player.sendMessage("§cYou have to waite " + cooldown * -1 + " Seconds before you can use your Ulti again.");
                    }
                }
            }
        }
    }

}
