package fr.ayato.farmranking;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Economy econ = null;

    //public ConfigJSON loadedConfigpvp = null;
    //public ConfigStorage configStoragepvp;
    //public ConfigJSON loadedConfigfarm = null;
    //public ConfStorage configStorage;

    public static Main instance;
    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking initialized !");
        System.out.println("--------------------");
        instance = this;
        //this.configStorage = new ConfStorage();
        //if (!this.configStorage.configExist())
        //    this.configStorage.saveDefaultConfig();
        //this.configStorage.loadConfig();
        //getCommand("farm").setExecutor((CommandExecutor)new LaunchMenu());
        //getServer().getPluginManager().registerEvents((Listener)new MenuInterract(), (Plugin)this);
        setupEconomy();
    }

    public void onDisable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking stopped !");
        System.out.println("--------------------");
        //this.configStorage.saveConfig();
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
}
