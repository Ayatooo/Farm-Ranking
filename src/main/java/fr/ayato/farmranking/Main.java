package fr.ayato.farmranking;

import fr.ayato.farmranking.menu.BuyingMenu;
import fr.ayato.farmranking.menu.RankingMenu;
import fr.ayato.farmranking.menu.MenuInterract;
import fr.ayato.farmranking.utils.commands.EditPointCommand;
import fr.ayato.farmranking.utils.config.ConfStorage;
import fr.ayato.farmranking.utils.config.ConfigJSON;
import fr.ayato.farmranking.utils.events.FactionEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
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
        getCommand("farmachat").setExecutor(new BuyingMenu());
        getServer().getPluginManager().registerEvents(new FactionEvents(), this);
        getServer().getPluginManager().registerEvents(new MenuInterract(), this);
    }

    public void onDisable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking stopped !");
        System.out.println("--------------------");
        this.configStorage.saveConfig();
        saveDefaultConfig();
    }
}
