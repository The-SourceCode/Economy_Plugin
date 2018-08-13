package me.tsc.daeconomy.commands;

import me.tsc.daeconomy.DaeConomyMain;
import me.tsc.daeconomy.player.PlayerManager;
import me.tsc.daeconomy.utils.MessageManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class CreatePlayerAccountCommand extends BukkitCommand {
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();
    public CreatePlayerAccountCommand() {
        super("create");
        this.description = "Create your economy account";
        this.usageMessage = command();
        this.setPermission("daeconomy.player.createaccount");
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(this.getPermission())) {
                MessageManager.playerBad(player, "You don't have permission.");
                return true;
            }

            if (args.length != 0) {
                MessageManager.playerBad(player, command());
                return true;
            }
            if (!plugin.economyCore.hasAccount(player.getUniqueId().toString())) {
                plugin.mongoConnect.addNewPlayer(player);
                MessageManager.playerGood(player, "Your account has been created!");
            } else {
                MessageManager.playerBad(player, "Your account already exists!");
            }
        }else{
            MessageManager.consoleBad("Sorry but only players can use");
        }
        return true;
    }

    private String command(){
        return "/createaccount";
    }


}
