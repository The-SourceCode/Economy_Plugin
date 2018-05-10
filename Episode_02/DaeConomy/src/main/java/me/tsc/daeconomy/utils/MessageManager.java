package me.tsc.daeconomy.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MessageManager {

    public static void consoleGood(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + message);
    }

    public static void consoleBad(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + message);
    }

    public static void consoleInfo(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + message);
    }
}
