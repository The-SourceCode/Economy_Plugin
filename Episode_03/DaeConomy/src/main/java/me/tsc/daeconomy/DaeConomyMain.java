package me.tsc.daeconomy;

import me.tsc.daeconomy.commands.manager.CommandManager;
import me.tsc.daeconomy.player.PlayerManager;
import me.tsc.daeconomy.utils.MessageManager;
import me.tsc.daeconomy.utils.MongoConnect;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class DaeConomyMain extends JavaPlugin {
    private static DaeConomyMain plugin;
    public MongoConnect mongoConnect;
    private CommandManager commandManager;
    public EconomyCore economyCore;
    public HashMap<UUID, PlayerManager> playerManagerHashMap = new HashMap<>();

    public void onEnable() {
        plugin = this;
        instanceClasses();

        mongoConnect.connect();
        commandManager.setup();

        if (!setupEconomy()) {
            MessageManager.consoleBad("Economy could not be registered...Vault is missing!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        MessageManager.consoleGood("has successfully launched...");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class, economyCore, this, ServicePriority.Highest);
        MessageManager.consoleGood("Economy has been registered...");
        return true;
    }


    private void instanceClasses() {
        mongoConnect = new MongoConnect();
        commandManager = new CommandManager();
        economyCore = new EconomyCore();

        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
    }

    public static DaeConomyMain getPlugin() {
        return plugin;
    }
}
