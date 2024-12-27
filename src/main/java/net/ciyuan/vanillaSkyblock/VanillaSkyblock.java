package net.ciyuan.vanillaSkyblock;

import net.ciyuan.vanillaSkyblock.Commands.GetAstraea;
import net.ciyuan.vanillaSkyblock.Listeners.ClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class VanillaSkyblock extends JavaPlugin
{

    public static VanillaSkyblock Instance;
    public static Logger ConsoleLogger;

    @Override
    public void onEnable()
    {
        Instance = this;
        ConsoleLogger = Instance.getLogger();

        Objects.requireNonNull(Instance.getCommand("getastraea")).setExecutor(new GetAstraea());

        Bukkit.getPluginManager().registerEvents(new ClickListener(), Instance);

        ConsoleLogger.info("Welcome to use Vanilla Skyblock!");
        ConsoleLogger.info("By CiYuan!");
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
