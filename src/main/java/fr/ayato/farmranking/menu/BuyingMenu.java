package fr.ayato.farmranking.menu;

import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.data.GetBuyingMenuConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import java.util.List;

public class BuyingMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (sender != null) {
            BuyingMenu.openMenu(player);
        }
        return false;
    }

    public static void openMenu(Player player) {
        //Get the data from the config file
        int ItemsSize = GetBuyingMenuConfig.getBuyingMenuItemsSize();
        int MenuSize = GetBuyingMenuConfig.getBuyingMenuSize();
        List<String> name = GetBuyingMenuConfig.getBuyingMenuName();
        List<Integer> place = GetBuyingMenuConfig.getBuyingMenuPlace();
        List<String> material = GetBuyingMenuConfig.getBuyingMenuMaterial();
        List<String> lore = GetBuyingMenuConfig.getBuyingMenuLore();
        String title = Main.instance.getConfig().getString("BuyingMenu.title");

        //Create the inventory
        Inventory buyPointsInv = Bukkit.createInventory(null, MenuSize, Utils.centerTitle(title));

        // Add the items to the inventory
        for (int i = 0; i < ItemsSize; i++) {
            buyPointsInv.setItem(place.get(i), Utils.createGuiItem(Material.valueOf(material.get(i)), name.get(i), Utils.splitStr(lore.get(i))));
        }
        player.openInventory(buyPointsInv);
    }

}