package me.tsc.daeconomy.commands.manager;

import me.tsc.daeconomy.commands.BalanceCommand;
import me.tsc.daeconomy.commands.CreatePlayerAccountCommand;
import me.tsc.daeconomy.commands.PayCommand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;

public class CommandManager {

    public void setup() {
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("create", new CreatePlayerAccountCommand());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("balance", new BalanceCommand());
        ((CraftServer) Bukkit.getServer()).getCommandMap().register("pay", new PayCommand());
    }
}
