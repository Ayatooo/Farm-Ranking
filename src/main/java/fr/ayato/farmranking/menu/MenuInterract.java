package fr.ayato.farmranking.menu;

import com.massivecraft.factions.FPlayers;
import fr.ayato.farmranking.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static fr.ayato.farmranking.menu.RankingMenu.openMenu;

public class MenuInterract implements Listener {

    private final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    String command;
    String command2;

    @EventHandler
    public void interactFarmMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();

        if(inv.getName().contains("Top Farm")) {
            if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                player.closeInventory();
                BuyingMenu.openMenu(player);
            } else if (e.getCurrentItem().getType() == Material.WOOD_DOOR) {
                player.closeInventory();
            }
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void interactBuyingMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        ItemStack current = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        int i = 0;
        int value = 0;

        if (inv.getName().contains("Achat de points Farm")) {
            if (current.hasItemMeta()) {
                if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§b§l1 Point Farm")) {
                    if (Main.getEconomy().has(player, 10000001.0D)) {
                        i = 1;
                        value = 10000000;
                        this.command = "eco take " + player.getName() + " " + value;
                        Bukkit.dispatchCommand(this.console, this.command);
                        player.sendMessage("§e§lIdalia§b§lMc §f» §eTu viens d'acheter 1 point de Farm");
                    }
                } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§b§l5 Points Farm")) {
                    if (Main.getEconomy().has(player, 50000001.0D)) {
                        i = 5;
                        value = 50000000;
                        this.command = "eco take " + player.getName() + " " + value;
                        Bukkit.dispatchCommand(this.console, this.command);
                        player.sendMessage("§e§lIdalia§b§lMc §f» §eTu viens d'acheter 5 points de Farm");
                    }
                } else if (current.getItemMeta().getDisplayName().equalsIgnoreCase("§b§l15 Points Farm")) {
                    if (Main.getEconomy().has(player, 1.5000001E8D)) {
                        i = 15;
                        value = 150000000;
                        this.command = "eco take " + player.getName() + " " + value;
                        Bukkit.dispatchCommand(this.console, this.command);
                        player.sendMessage("§e§lIdalia§b§lMc §f» §eTu viens d'acheter 15 points de Farm");
                    }
                } else if (current.getType() == Material.WOOD_DOOR) {
                    i = 9;
                }
                if (i == 1 || i == 5 || i == 15) {
                    this.command2 = "farmpoint add " + FPlayers.getInstance().getByPlayer(player).getFaction().getTag() + " " + i;
                    Bukkit.dispatchCommand(this.console, this.command2);
                } else if (i == 9) {
                    player.closeInventory();
                    openMenu(player);
                } else {
                    player.sendMessage("§e§lIdalia§b§lMc §f» §cTu n'as pas assez d'argent !");
                }
                e.setCancelled(true);
            }
        }
    }
}
