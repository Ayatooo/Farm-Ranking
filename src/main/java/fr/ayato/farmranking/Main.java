package fr.ayato.farmranking;

import fr.ayato.farmranking.menu.RankingMenu;
import fr.ayato.farmranking.menu.MenuInterract;
import fr.ayato.farmranking.utils.commands.EditPointCommand;
import fr.ayato.farmranking.utils.config.ConfStorage;
import fr.ayato.farmranking.utils.config.ConfigJSON;
import fr.ayato.farmranking.utils.events.FactionEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    private static Economy econ = null;

    public ConfigJSON loadConfig = null;
    public ConfStorage configStorage;
    public static Main instance;
    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking initialized !");
        System.out.println("--------------------");
        saveDefaultConfig();
        instance = this;
        this.configStorage = new ConfStorage();
        if (!this.configStorage.configExist())
            this.configStorage.saveDefaultConfig();
        this.configStorage.loadConfig();
        getCommand("farm").setExecutor(new RankingMenu());
        getCommand("farmpoint").setExecutor(new EditPointCommand());
        getServer().getPluginManager().registerEvents(new FactionEvents(), this);
        getServer().getPluginManager().registerEvents(new MenuInterract(), this);

        setupEconomy();
    }

    public void onDisable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking stopped !");
        System.out.println("--------------------");
        this.configStorage.saveConfig();
        saveDefaultConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        econ = rsp.getProvider();
        return (econ != null);
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static List<String> getBuyingMenuName() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".name"));
        }
        return items;
    }

    public static List<String> getBuyingMenuMaterial() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".material"));
        }
        return items;
    }

    public static List<String> getBuyingMenuPrice() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(String.valueOf(instance.getConfig().getInt("BuyingMenu.items." + i + ".price")));
        }
        return items;
    }

    public static List<String> getBuyingMenuLore() {
        int size = getBuyingMenuItemsSize();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getString("BuyingMenu.items." + i + ".lore"));
        }
        return items;
    }

    public static List<Integer> getBuyingMenuPlace() {
        int size = getBuyingMenuItemsSize();
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(instance.getConfig().getInt("BuyingMenu.items." + i + ".place"));
        }
        return items;
    }

    public static int getBuyingMenuSize() {
        return instance.getConfig().getInt("BuyingMenu.size");
    }

    public static int getBuyingMenuItemsSize() {
        return Main.getInstance().getConfig().getConfigurationSection("BuyingMenu.items").getKeys(false).size();
    }
}
