package fr.ayato.farmranking.menu;

import com.massivecraft.factions.FPlayers;
import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.data.GetRankingMenuConfig;
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

public class RankingMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if (sender != null) {
            openMenu(player);
        }
        return false;
    }

    public static void openMenu(Player player) {
        int factionPoint = Utils.getFactionPoints(player);
        int menuSize = GetRankingMenuConfig.getMenuSize();
        int topFactionsSize = GetRankingMenuConfig.getTopFactionsSize();

        if ((Main.getInstance()).loadConfig.factionList.size() >= topFactionsSize) {
            List<FactionObject> factionList = new ArrayList<>();
            for (int i = 0; i != (Main.getInstance()).loadConfig.factionList.size(); i++) {
                factionList.add(new FactionObject((Main.getInstance()).loadConfig.factionList.get(i).points, (Main.getInstance()).loadConfig.factionList.get(i).factionName));
            }
            factionList.sort((o1, o2) -> o2.points - o1.points);

            String title = GetRankingMenuConfig.getRankingMenuTitle();
            Inventory farmInventory = Bukkit.createInventory(null, menuSize, Utils.centerTitle(title));

            for (int i = 1; i <= topFactionsSize; i++) {
                String factionName = factionList.get(i - 1).factionName;
                int factionPoints = factionList.get(i - 1).points;
                String factionNamePrefix = GetRankingMenuConfig.getFactionNamePrefix();
                String factionPointPrefix = GetRankingMenuConfig.getFactionPointPrefix();
                String factionPointSuffix = GetRankingMenuConfig.getFactionPointSuffix();
                Material TopFactionMaterial = GetRankingMenuConfig.getTopFactionMaterial(i);
                List<String> TopFactionLore = GetRankingMenuConfig.getTopFactionLore(i);
                int place = GetRankingMenuConfig.getTopFactionPlace(i);

                InventoryInstance.addItemMenu(farmInventory, place, TopFactionMaterial, factionNamePrefix + factionName + factionPointPrefix + factionPoints + factionPointSuffix, (ArrayList<String>) TopFactionLore);

            }
            ArrayList<String> lore4 = new ArrayList<>();
            lore4.add("§c§l↩");
            ArrayList<String> lore5 = new ArrayList<>();
            lore5.add("");

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
            InventoryInstance.addItemMenu(farmInventory, 42, Material.NETHER_STAR, "§eAcheter des points", lore5);
            InventoryInstance.addGlass(farmInventory, 43, Material.STAINED_GLASS_PANE, "§e☆");
            InventoryInstance.addGlass(farmInventory, 44, Material.STAINED_GLASS_PANE, "§e☆");
            player.openInventory(farmInventory);
        } else{
            player.sendMessage("§e§lIdalia§b§lMc §f» §cIl faut qu'au moins 3 factions existent !");
        }
    }
}