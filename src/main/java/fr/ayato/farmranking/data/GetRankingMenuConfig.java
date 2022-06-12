package fr.ayato.farmranking.data;

import fr.ayato.farmranking.Main;
import org.bukkit.Material;

import java.util.List;

import static fr.ayato.farmranking.Main.instance;

public class GetRankingMenuConfig {

    //Get the title of the menu
    public static String getRankingMenuTitle() {
        return instance.getConfig().getString("RankingMenu.title");
    }

    //Get the faction name prefix
    public static String getFactionNamePrefix() {
        return instance.getConfig().getString("RankingMenu.factionNamePrefix");
    }

    //Get the faction points prefix
    public static String getFactionPointPrefix() {
        return instance.getConfig().getString("RankingMenu.factionPointPrefix");
    }

    //Get the faction points suffix
    public static String getFactionPointSuffix() {
        return instance.getConfig().getString("RankingMenu.factionPointSuffix");
    }

    //Get the size of the menu
    public static int getMenuSize() {
        return instance.getConfig().getInt("RankingMenu.size");
    }

    //Get the size of the topFactions
    public static int getTopFactionsSize() {
        return Main.getInstance().getConfig().getConfigurationSection("RankingMenu.topFactions").getKeys(false).size();
    }

    //Get the faction material of the topFactions
    public static Material getTopFactionMaterial(int i) {
        return Material.getMaterial(Main.getInstance().getConfig().getString("RankingMenu.topFactions." + i + ".material"));
    }

    //Get the faction lore of the topFactions
    public static List<String> getTopFactionLore(int i) {
        return Main.getInstance().getConfig().getStringList("RankingMenu.topFactions." + i + ".lore");
    }

    //Get the faction place of the topFactions
    public static int getTopFactionPlace(int i) {
        return Main.getInstance().getConfig().getInt("RankingMenu.topFactions." + i + ".place");
    }

}
