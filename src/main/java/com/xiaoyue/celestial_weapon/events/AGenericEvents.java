package com.xiaoyue.celestial_weapon.events;

import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AGenericEvents {

    @SubscribeEvent
    public void onLivingAttackEntity(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            ItemStack item = entity.getMainHandItem();
            if (item.getItem() instanceof WeaponAttack weaponAttack) {
                weaponAttack.onLivingAttackEntity(event, entity, item);
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurtEntity(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            ItemStack item = entity.getMainHandItem();
            if (item.getItem() instanceof WeaponAttack weaponAttack) {
                weaponAttack.onLivingHurtEntity(event, entity, item);
            }
        }
    }

    @SubscribeEvent
    public void onUnderHurt(LivingHurtEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = entity.getMainHandItem();
        if (item.getItem() instanceof WeaponAttack weaponAttack) {
            weaponAttack.onUnderHurt(event, entity, item);
        }
    }

    @SubscribeEvent
    public void onLivingDamageEntity(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            ItemStack item = entity.getMainHandItem();
            if (item.getItem() instanceof WeaponAttack weaponAttack) {
                weaponAttack.onLivingDamageEntity(event, entity, item);
            }
        }
    }

    @SubscribeEvent
    public void onUnderDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = entity.getMainHandItem();
        if (item.getItem() instanceof WeaponAttack weaponAttack) {
            weaponAttack.onLivingDamageEntity(event, entity, item);
        }
    }

    @SubscribeEvent
    public void onLivingKillEntity(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof LivingEntity entity) {
            ItemStack item = entity.getMainHandItem();
            if (item.getItem() instanceof WeaponAttack weaponAttack) {
                weaponAttack.onLivingKillEntity(event, entity, item);
            }
        }
    }
}
