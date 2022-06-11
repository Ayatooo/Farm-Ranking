package fr.ayato.farmranking.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MenuInterract implements Listener
{
    @EventHandler
    public void interractMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();

        if(inv.getName().contains("Top Farm")) {
            e.setCancelled(true);
        }
    }
}
