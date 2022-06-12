package fr.ayato.farmranking.menu;

import fr.ayato.farmranking.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import java.util.List;
import static fr.ayato.farmranking.menu.RankingMenu.centerTitle;

public class BuyingMenu implements Listener {

    public static void openMenu(Player player) {
        //Get the data from the config file
        int ItemsSize = Main.getBuyingMenuItemsSize();
        int MenuSize = Main.getBuyingMenuSize();
        List<String> name = Main.getBuyingMenuName();
        List<Integer> place = Main.getBuyingMenuPlace();
        List<String> material = Main.getBuyingMenuMaterial();
        List<String> lore = Main.getBuyingMenuLore();
        String title = Main.getInstance().getConfig().getString("BuyingMenu.title");

        //Create the inventory
        Inventory buyPointsInv = Bukkit.createInventory(null, MenuSize, centerTitle(title));

        // Add the items to the inventory
        for (int i = 0; i < ItemsSize; i++) {
            buyPointsInv.setItem(place.get(i), Utils.createGuiItem(Material.valueOf(material.get(i)), name.get(i), Utils.splitStr(lore.get(i))));
        }
        player.openInventory(buyPointsInv);
    }

}