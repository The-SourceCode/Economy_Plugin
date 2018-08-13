package me.tsc.daeconomy.commands;

import me.tsc.daeconomy.DaeConomyMain;
import me.tsc.daeconomy.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BalanceCommand extends BukkitCommand {
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    public BalanceCommand() {
        super("balance");
        this.description = "Check your current on player balance.";
        this.usageMessage = command();
        this.setPermission("daeconomy.player.balance");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(this.getPermission())) {
                MessageManager.playerBad(player, "You don't have permission.");
                return true;
            }

            if (args.length != 0) {
                MessageManager.playerBad(player, command());
                MessageManager.playerBad(player, command() + " <player>");
                return true;
            }

            double balance = plugin.economyCore.getBalance(player.getUniqueId().toString());
            MessageManager.playerGood(player, "Your balance is " + ChatColor.GREEN + "$" + balance);
        }
        return true;
    }

    private String command(){
        return "/balance";
    }
}
