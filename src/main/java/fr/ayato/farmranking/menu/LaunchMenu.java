package fr.ayato.farmranking.menu;

import com.massivecraft.factions.FPlayers;
import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.utils.FactionObject;
import fr.ayato.farmranking.utils.InventoryInstance;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LaunchMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (sender != null) {
            openMenu(player);
        }
        return false;
    }

    public static void openMenu(Player player) {
        int factionPoint = 0;
        for (int i = 0; i != (Main.getInstance()).loadConfig.factionList.size(); i++) {
            if (Objects.equals((Main.getInstance()).loadConfig.factionList.get(i).factionName, FPlayers.getInstance().getByPlayer(player).getFaction().getTag())) {
                if ((Main.getInstance()).loadConfig.factionList.get(i).points == 0) {
                    factionPoint = 0;
                } else {
                    factionPoint = (Main.getInstance()).loadConfig.factionList.get(i).points;
                }
            }
        }
        if ((Main.getInstance()).loadConfig.factionList.size() >= 3) {

            //get all the factions and their points
            List<FactionObject> factionList = new ArrayList<>();
            for (int i = 0; i != (Main.getInstance()).loadConfig.factionList.size(); i++) {
                factionList.add(new FactionObject((Main.getInstance()).loadConfig.factionList.get(i).points, (Main.getInstance()).loadConfig.factionList.get(i).factionName));
            }

            //sort the list by points
            factionList.sort((o1, o2) -> o2.points - o1.points);

            //create 3 string variables to display the top 3 factions
            String topFaction1 = "";
            String topFaction2 = "";
            String topFaction3 = "";

            //display the top 3 factions
            for (int i = 0; i != 3; i++) {
                if (i == 0) {
                    topFaction1 = factionList.get(i).factionName + " : " + factionList.get(i).points;
                } else if (i == 1) {
                    topFaction2 = factionList.get(i).factionName + " : " + factionList.get(i).points;
                } else if (i == 2) {
                    topFaction3 = factionList.get(i).factionName + " : " + factionList.get(i).points;
                }
            }

            String title = "§e☆ §b§lTop Farm §e☆";
            Inventory farmInventory = Bukkit.createInventory(null, 45, centerTitle(title));
            ArrayList<String> lore4 = new ArrayList<>();
            lore4.add("§c§l↩");
            ArrayList<String> lore5 = new ArrayList<>();
            lore5.add("");
            InventoryInstance.addItemMenu(farmInventory, 20, Material.EMERALD, topFaction1, LoreFarm.setLore1());
            InventoryInstance.addItemMenu(farmInventory, 22, Material.DIAMOND, topFaction2, LoreFarm.setLore2());
            InventoryInstance.addItemMenu(farmInventory, 24, Material.GOLD_INGOT, topFaction3, LoreFarm.setLore3());
            InventoryInstance.addItemMenu(farmInventory, 4, Material.PAPER, "§b§lTa faction possède §6§l" + factionPoint + " §bpoints !", lore5);
            InventoryInstance.addItemMenu(farmInventory, 40, Material.WOOD_DOOR, "§cRetour", lore4);


            InventoryInstance.addGlass(farmInventory, 0, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 1, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 9, Material.STAINED_GLASS_PANE, "§e☆");


            InventoryInstance.addGlass(farmInventory, 7, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 8, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 17, Material.STAINED_GLASS_PANE, "§e☆");


            InventoryInstance.addGlass(farmInventory, 27, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 36, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 37, Material.STAINED_GLASS_PANE, "§e☆");


            InventoryInstance.addGlass(farmInventory, 35, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 43, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 44, Material.STAINED_GLASS_PANE, "§e☆");
            player.closeInventory();
            player.openInventory(farmInventory);
        } else {
            player.sendMessage("§e§lIdalia§b§lMc §f» §cIl faut qu'au moins 3 factions existent !");
        }
    }

    public static String centerTitle(String title) {
        String spacer = "";
        int spaces = 27 - ChatColor.stripColor(title).length();
        for (int i = 0; i < spaces; i++) {
            spacer = spacer + " ";
        }
        return spacer + title;
    }


}