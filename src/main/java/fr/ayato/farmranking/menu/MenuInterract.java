package fr.ayato.farmranking.menu;

import com.massivecraft.factions.FPlayers;
import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.data.GetBuyingMenuConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import static fr.ayato.farmranking.menu.RankingMenu.openMenu;

public class MenuInterract implements Listener {

    private final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    String command;

    @EventHandler
    public void interactFarmMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        Player player = (Player) e.getWhoClicked();

        if (e.getClickedInventory() != null) {
            if (inv.getName().contains("Top Farm")) {
                if (e.getCurrentItem().getType() == Material.NETHER_STAR) {
                    player.closeInventory();
                    BuyingMenu.openMenu(player);
                } else if (e.getCurrentItem().getType() == Material.WOOD_DOOR) {
                    player.closeInventory();
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void interactBuyingMenu(InventoryClickEvent e) {
        Inventory inv = e.getInventory();
        ItemStack current = e.getCurrentItem();
        Player player = (Player) e.getWhoClicked();
        String title = Main.instance.getConfig().getString("BuyingMenu.title");


        if (e.getClickedInventory() != null) {
            if (inv.getName().contains(title)) {
                if (current.hasItemMeta()) {
                    if (current.getType() != Material.WOOD_DOOR) {
                        List<String> itemsName = GetBuyingMenuConfig.getBuyingMenuItemsName();
                        int index = -1;
                        for (int i = 0; i < itemsName.size(); i++) {
                            if (current.getItemMeta().getDisplayName().equals(itemsName.get(i))) {
                                index = i;
                            }
                        }
                        int points = GetBuyingMenuConfig.getBuyingMenuNumberOfPoints(index);
                        Inventory playerInv = player.getInventory();

                        List<String> coinLore = Main.instance.getConfig().getStringList("coinLore");

                        for (int i = 0; i < playerInv.getSize(); i++) {
                            if (playerInv.getItem(i) != null) {
                                if (playerInv.getItem(i).hasItemMeta()) {
                                    if (playerInv.getItem(i).getItemMeta().getLore().equals(coinLore)) {
                                        if (playerInv.getItem(i).getAmount() >= points || playerInv.getItem(i).getAmount() == points) {
                                            if (points == 1 && playerInv.getItem(i).getAmount() == 1 || points == 5 && playerInv.getItem(i).getAmount() == 5 || points == 15 && playerInv.getItem(i).getAmount() == 15) {
                                                playerInv.clear(i);
                                            } else {
                                                playerInv.getItem(i).setAmount(playerInv.getItem(i).getAmount() - points);
                                            }
                                            this.command = "farmpoint add " + FPlayers.getInstance().getByPlayer(player).getFaction().getTag() + " " + points;
                                            Bukkit.dispatchCommand(this.console, this.command);
                                        } else {
                                            player.sendMessage("§cVous n'avez pas assez de coins pour acheter " + points + " §cpoints");
                                        }
                                    }
                                }
                            }
                        }
                    } else if (current.getType() == Material.WOOD_DOOR) {
                        player.closeInventory();
                        openMenu(player);
                    }
                    e.setCancelled(true);
                }
            }
        }
    }
}
