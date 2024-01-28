package github.captionbaro.io.ctf.kit.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public enum Kit {

    POSEIDON("Poseidon", Inventorys.Poseidon, ArmorContents.PoseidonArmorontents),
    APHRODITE("Aphrodite", Inventorys.Aphrodite, ArmorContents.AphroditeArmorcontents),
    HERMES("Hermes", Inventorys.Hermes, ArmorContents.HermesContents),
    ATHENE("Athene", Inventorys.Athene, ArmorContents.Athene),
    DIONYSUS("Dionysus", Inventorys.Dionysos, ArmorContents.Dionysus),
    HADES("Hades", Inventorys.HADES, ArmorContents.HADES),
    ARES("Ares", Inventorys.ARES, ArmorContents.Ares);

    private String kitName;
    private Inventory inventory;
    private ItemStack[] armorcontents;


    Kit(String kitName, Inventory inventory, ItemStack[] armorcontents){
        this.kitName = kitName;
        this.inventory = inventory;
        this.armorcontents = armorcontents;

    }

    public String getKitName() {
        return kitName;
    }
    public ItemStack[] getContents(){
        return inventory.getContents();
    }
    public ItemStack[] getArmorcontents(){
        return armorcontents;
    }
}
