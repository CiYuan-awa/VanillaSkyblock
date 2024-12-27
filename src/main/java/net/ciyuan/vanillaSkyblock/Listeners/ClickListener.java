package net.ciyuan.vanillaSkyblock.Listeners;

import net.ciyuan.vanillaSkyblock.VanillaSkyblock;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ClickListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (event.getAction().toString().contains("RIGHT_CLICK") && item.getType() == Material.IRON_SWORD && item.getItemMeta().getLore().getLast().contains("NECRONS_BLADE"))
        {
            long shieldcooldown = 5000;
            long impactwarpcooldown = 100;
            long lastUseMoveAndExplode = player.getMetadata("lastUseMoveAndExplode").isEmpty() ? 0 : player.getMetadata("lastUseMoveAndExplode").get(0).asLong();
            long lastUseAbsorption = player.getMetadata("lastUseAbsorption").isEmpty() ? 0 : player.getMetadata("lastUseAbsorption").get(0).asLong();
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUseMoveAndExplode >= impactwarpcooldown)
            {
                Vector direction = player.getLocation().getDirection();
                Location targetLocation = player.getLocation();
                for (int i = 1; i <= 10; i++)
                {
                    targetLocation.add(direction);
                    if (targetLocation.getBlock().getType() != Material.AIR && targetLocation.getBlock().getType() != Material.WATER && targetLocation.getBlock().getType() != Material.SHORT_GRASS && targetLocation.getBlock().getType() != Material.TALL_GRASS && targetLocation.getBlock().getType() != Material.SEAGRASS && targetLocation.getBlock().getType() != Material.TALL_SEAGRASS)
                    {
                        targetLocation.subtract(direction);
                        break;
                    }
                }
                World world = player.getWorld();
                player.teleport(targetLocation);
                world.createExplosion(targetLocation, 0, false, false);
                Particle particle = Particle.FIREWORK;
                world.spawnParticle(particle, targetLocation, 250, 3.0, 3.0, 3.0, 0.1);

                int hitCount = 0;
                int damage = 0;
                for (Entity entity : player.getWorld().getNearbyEntities(targetLocation, 7, 7, 7))
                {
                    if (entity instanceof LivingEntity && entity.getType() != EntityType.PLAYER)
                    {
                        if (((LivingEntity) entity).getHealth() < 30.0)
                        {
                            damage += ((LivingEntity) entity).getHealth();
                            player.playSound(player.getLocation(), Sound.ENTITY_CAT_AMBIENT, 100.0F, 1.0F);
                        }
                        else
                        {
                            damage += 30;
                        }
                        if (((LivingEntity) entity).getHealth() != 0)
                        {
                            hitCount++;
                        }
                        ((LivingEntity) entity).damage(30.0, player);
                    }
                }
                if (hitCount != 0) player.sendMessage("§7Your Implosion hit§r §c" + hitCount + "§r §7enemy for§r §c" + damage + "§r §7damage.§r");
                player.setMetadata("lastUseMoveAndExplode", new FixedMetadataValue(VanillaSkyblock.Instance, System.currentTimeMillis()));
            }
            if (currentTime - lastUseAbsorption >= shieldcooldown)
            {
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 100, 3));
                player.setMetadata("lastUseAbsorption", new FixedMetadataValue(VanillaSkyblock.Instance, System.currentTimeMillis()));
            }
        }
    }
}
