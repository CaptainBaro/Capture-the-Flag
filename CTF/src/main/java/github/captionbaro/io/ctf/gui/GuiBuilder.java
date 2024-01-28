package github.captionbaro.io.ctf.gui;

import github.captionbaro.io.ctf.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class GuiBuilder {

    private Inventory inventory;
    private int groesse;
    private String name;
    private int rows;

    public GuiBuilder(int groesse, String name){
        this.groesse = groesse;
        this.rows = groesse/9;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, groesse, name);
    }
    public GuiBuilder setBackground(ItemStack itemStack){
        for (int i = 0; i < groesse; i++){
            inventory.setItem(i, itemStack);
        }
        return this;
    }
    public GuiBuilder setBackButtoon(){
        inventory.setItem(groesse-5, new ItemBuilder(Material.ARROW).setName("§aZurück").setLocalizedName("Gui.Back").addItemFlags(ItemFlag.HIDE_UNBREAKABLE).addItemFlags(ItemFlag.HIDE_ATTRIBUTES).build());
        return this;
    }
    public GuiBuilder setCloseButton(){
        inventory.setItem(groesse-1, new ItemBuilder(Material.BARRIER).setName("§aInventar schließen!").setLocalizedName("Gui.Close").build());
        return this;
    }
    public Inventory build(){
        return inventory;
    }


}
