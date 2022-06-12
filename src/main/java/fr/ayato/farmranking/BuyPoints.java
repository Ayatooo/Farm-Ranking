package fr.ayato.farmranking;

import fr.ayato.farmranking.utils.InventoryInstance;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

import static fr.ayato.farmranking.menu.RankingMenu.centerTitle;

public class BuyPoints implements Listener {

    public static void buyingMenu(Player player) {
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add("§c§l↩");
        Inventory buyPointsInv = Bukkit.createInventory(null, 27, centerTitle("§e☆ §b§lAchat de points Farm §e☆"));
        buyPointsInv.setItem(11, createGuiItem(Material.NETHER_STAR, "§b§l1 Point Farm", "§aPrix : 10 000 000 ✰", true));
        buyPointsInv.setItem(13, createGuiItem(Material.NETHER_STAR, "§b§l5 Points Farm", "§aPrix : 50 000 000 ✰", true));
        buyPointsInv.setItem(15, createGuiItem(Material.NETHER_STAR, "§b§l15 Points Farm", "§aPrix : 150 000 000 ✰", true));
        InventoryInstance.addItemMenu(buyPointsInv, 26, Material.WOOD_DOOR, "§cRetour", lore4);

        player.openInventory(buyPointsInv);
    }

    public static ItemStack createGuiItem(Material material, String name, String lore, Boolean enhanced) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(Collections.singletonList(lore));
        if (enhanced) {
            meta.addEnchant(Enchantment.DURABILITY, 10, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }
}