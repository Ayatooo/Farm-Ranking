package fr.ayato.farmranking;

import fr.ayato.farmranking.utils.commands.EditPointCommand;
import fr.ayato.farmranking.utils.config.ConfStorage;
import fr.ayato.farmranking.utils.config.ConfigJSON;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

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
        instance = this;
        this.configStorage = new ConfStorage();
        if (!this.configStorage.configExist())
            this.configStorage.saveDefaultConfig();
        this.configStorage.loadConfig();
        getCommand("farmpoint").setExecutor(new EditPointCommand());
        //getCommand("farm").setExecutor((CommandExecutor)new LaunchMenu());
        //getServer().getPluginManager().registerEvents((Listener)new MenuInterract(), this);
        setupEconomy();
    }

    public void onDisable() {
        System.out.println("--------------------");
        System.out.println(ChatColor.AQUA + "Farm-Ranking stopped !");
        System.out.println("--------------------");
        this.configStorage.saveConfig();
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
