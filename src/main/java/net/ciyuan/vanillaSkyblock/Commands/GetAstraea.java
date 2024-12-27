package net.ciyuan.vanillaSkyblock.Commands;

import net.ciyuan.vanillaSkyblock.Utils.ColorUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GetAstraea implements CommandExecutor
{
    private static String ChromaString(String Input)
    {
        return ColorUtil.Gradient(Input, 0.15D, "#87CEFA", "#FA8072");
    }

    private static List<String> GetLore()
    {
        List<String> Lore = new LinkedList<String>();
        Lore.add("§7Gear Score: §d1000\n");
        Lore.add("§7Damage: §c+30\n");
        Lore.add("§7Bonus Attack Speed: §e+100%\n");
        Lore.add("§7Intelligence: §a+114514\n");
        Lore.add("§6[§b✎§6]§r §6[§b⚔§6]\n");
        Lore.add("\n");
        Lore.add("§d§lUltimate Wise V§r§9, " + ChromaString("Champion 10") + "§9, \n");
        Lore.add(ChromaString("Cleave 6") + "§9, " + ChromaString("Critical 7") + "§9, \n");
        Lore.add(ChromaString("Cubism 6") + "§9, " + ChromaString("Divine Gift 3") + "§9, \n");
        Lore.add(ChromaString("Dragon Hunter 5") + "§9, " + ChromaString("Ender Slayer 7") + "§9, \n");
        Lore.add(ChromaString("Experience 5") + "§9, " + ChromaString("Fire Aspect 3") + "§9, \n");
        Lore.add(ChromaString("First Strike 5") + "§9, " + ChromaString("Giant Killer 7") + "§9, \n");
        Lore.add(ChromaString("Impaling 3") + "§9, " + ChromaString("Knockback 2") + "§9, \n");
        Lore.add(ChromaString("Lethality 6") + "§9, " + ChromaString("Looting 5") + "§9, \n");
        Lore.add(ChromaString("Luck 7") + "§9, " + ChromaString("Prosecute 6") + "§9, \n");
        Lore.add(ChromaString("Scavenger 6") + "§9, " + ChromaString("Sharpness 7") + "§9, \n");
        Lore.add(ChromaString("Smoldering 5") + "§9, " + ChromaString("Syphon 5") + "§9, \n");
        Lore.add(ChromaString("Tabasco 3") + "§9, " + ChromaString("Thunderlord 7") + "§9, \n");
        Lore.add(ChromaString("Vampirism 6") + "§9, " + ChromaString("Venomous 6") + "§9, \n");
        Lore.add(ChromaString("Vicious 5") + "§9. \n");
        Lore.add("\n");
        Lore.add("§b ◆ Meow Music Rune III\n");
        Lore.add("\n");
        Lore.add("§aScroll Abilities:\n");
        Lore.add("§b⦾§r §6Ability: Wither Impact§r  §e§lRIGHT CLICK§r\n");
        Lore.add("§7Teleport§r §a10 blocks§r §7ahead of you.§r\n");
        Lore.add("§7Then implode dealing§r §c30§r §7damage§r\n");
        Lore.add("§7to nearby enemies. Also applies the§r\n");
        Lore.add("§7wither shield scroll ability reducing§r\n");
        Lore.add("§7damage taken and granting an§r\n");
        Lore.add("§7absorption shield for 5 seconds.§r\n");
        Lore.add("§8Mana Cost:§r §3300§r\n");
        Lore.add("\n");
        Lore.add("§d§k§lw§r §d§lSHINY MYTHIC DUNGEON SWORD§r §d§k§lw§r");

        Lore.add("§8ITEM_ID: NECRONS_BLADE");
        return Lore;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender Sender, @NotNull Command Cmd, @NotNull String Label, @NotNull String[] Args)
    {
        Player player;
        if (!(Sender instanceof Player))
        {
            Sender.sendMessage("§c该命令只能由玩家使用。");
            return false;
        }
        player = (Player) Sender;
        if (Args.length != 0)
        {
            Sender.sendMessage("§c该命令不需要输入参数。");
            return false;
        }
        ItemStack Astraea = new ItemStack(Material.IRON_SWORD);
        ItemMeta Meta = Astraea.getItemMeta();
        if (Meta != null)
        {
            Meta.setDisplayName("§dShiny Suspicious Astraea §6✪✪✪✪✪§c➎");
            Meta.setLore(GetLore());
            Meta.setUnbreakable(true);
            Meta.setEnchantmentGlintOverride(true);
            Meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
        }
        Astraea.setItemMeta(Meta);
        player.getInventory().addItem(Astraea);
        player.sendMessage("§c你已经获取了一把神剑。");
        return true;
    }
}
