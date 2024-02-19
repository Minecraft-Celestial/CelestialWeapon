package com.xiaoyue.celestial_weapon.content.generic.intf;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface WeaponAttack {

    default void onLivingAttackEntity(LivingAttackEvent event, LivingEntity holder, ItemStack weapon) {
    }

    default void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
    }

    default void onUnderHurt(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
    }

    default void onLivingDamageEntity(LivingDamageEvent event, LivingEntity holder, ItemStack weapon) {
    }

    default void onUnderDamage(LivingDamageEvent event, LivingEntity holder, ItemStack weapon) {
    }

    default void onLivingKillEntity(LivingDeathEvent event, LivingEntity holder, ItemStack weapon) {
    }
}
