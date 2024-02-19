package com.xiaoyue.celestial_weapon.events;

import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import com.xiaoyue.celestial_weapon.register.CWItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_weapon.CelestialWeapon.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CWeaponEvents {

    @SubscribeEvent
    public static void onAddWeaponXp(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Entity attack = event.getSource().getEntity();
        if (attack instanceof LivingEntity livingAtk) {
            ItemStack item = livingAtk.getMainHandItem();
            if (item.getItem() instanceof WeaponBase base) {
                int addXp = (int) (entity.getMaxHealth() / 20);
                if (base.getXp(item) < 500) {
                    int min = Math.min(500, base.getXp(item) + addXp);
                    base.setXp(item, min);
                }
            }
        }
        if (attack instanceof Player) {
            float maxHealth = entity.getMaxHealth();
            if (maxHealth >= 75) {
                float weight = maxHealth * 0.001f;
                if (Math.random() >= weight) {
                    entity.spawnAtLocation(CWItems.GOLD_WEAPON_BOX.get());
                }
            }
        }
        if (Math.random() < 0.3) {
            if (entity.getMaxHealth() >= 300) {
                entity.spawnAtLocation(CWItems.XP_BOTTLE_BIG.get());
            } else if (entity.getMaxHealth() >= 200) {
                entity.spawnAtLocation(CWItems.XP_BOTTLE_BASE.get());
            } else if (entity.getMaxHealth() >= 100) {
                entity.spawnAtLocation(CWItems.XP_BOTTLE_SMALL.get());
            }
        }
    }
}
