package com.xiaoyue.celestial_weapon.content.generic.intf;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public interface WeaponAbility {

    default void getWeaponAbility(ServerPlayer holder, ServerLevel level, ItemStack weapon) {
    }
}
