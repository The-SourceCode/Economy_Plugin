package me.tsc.daeconomy;

import me.tsc.daeconomy.commands.manager.CommandManager;
import me.tsc.daeconomy.player.PlayerEvents;
import me.tsc.daeconomy.player.PlayerManager;
import me.tsc.daeconomy.utils.MessageManager;
import me.tsc.daeconomy.utils.MongoConnect;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class DaeConomyMain extends JavaPlugin {
    private static DaeConomyMain plugin;
    public MongoConnect mongoConnect;
    private CommandManager commandManager;
    public EconomyCore economyCore;
    public HashMap<UUID, PlayerManager> playerManagerHashMap;

    public void onEnable() {
        plugin = this;
        instanceClasses();
        if (!setupEconomy()) {
            MessageManager.consoleBad("Economy could not be registered...Vault is missing!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        commandManager.setup();

        for (Player player : Bukkit.getOnlinePlayers()) {
            mongoConnect.addNewPlayer(player);
        }

        MessageManager.consoleGood("has successfully launched...");
    }

 //   public void onDisable(){ playerManagerHashMap.clear(); }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class, economyCore, this, ServicePriority.Low);
        MessageManager.consoleGood("Economy has been registered...");
        return true;
    }


    private void instanceClasses() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        playerManagerHashMap = new HashMap<>();
        mongoConnect = new MongoConnect();
        mongoConnect.connect();
        economyCore = new EconomyCore();
        commandManager = new CommandManager();




        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
    }

    public static DaeConomyMain getPlugin() {
        return plugin;
    }
}
