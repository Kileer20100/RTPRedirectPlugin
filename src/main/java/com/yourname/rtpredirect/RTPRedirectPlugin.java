package com.example.rtpredirect;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RTPRedirectPlugin extends JavaPlugin implements CommandExecutor {

    private String fromWorld;
    private String toWorld;

    @Override
    public void onEnable() {
        reloadPluginConfig();
        if (getCommand("rtp") != null) {
            getCommand("rtp").setExecutor(this);
        }
        if (getCommand("rtpredirectreload") != null) {
            getCommand("rtpredirectreload").setExecutor((sender, cmd, label, args) -> {
                reloadPluginConfig();
                sender.sendMessage("§aКонфиг RTPRedirectPlugin перезагружен!");
                return true;
            });
        }
        getLogger().info("RTPRedirectPlugin включен! Перехват с '" + fromWorld + "' → '" + toWorld + "'");
    }

    private void reloadPluginConfig() {
        reloadConfig();
        saveDefaultConfig();
        fromWorld = getConfig().getString("from-world", "world");
        toWorld = getConfig().getString("to-world", "Survival");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эту команду может выполнить только игрок.");
            return true;
        }

        Player player = (Player) sender;
        World currentWorld = player.getWorld();

        if (currentWorld.getName().equalsIgnoreCase(fromWorld)) {
            World targetWorld = Bukkit.getWorld(toWorld);
            if (targetWorld == null) {
                player.sendMessage("§cЦелевой мир '" + toWorld + "' не найден!");
                return true;
            }
            // Телепортируем игрока в целевой мир
            player.teleport(targetWorld.getSpawnLocation());
            player.sendMessage("§aВы перемещены в мир: " + toWorld);

            // Через 2 тика выполняем команду /betterrtp от имени игрока в новом мире
            Bukkit.getScheduler().runTaskLater(this, () -> 
                Bukkit.dispatchCommand(player, "betterrtp"), 2L
            );

            return true;
        } else {
            // Если игрок не в fromWorld, просто выполняем /betterrtp
            Bukkit.dispatchCommand(player, "betterrtp");
            return true;
        }
    }
}
