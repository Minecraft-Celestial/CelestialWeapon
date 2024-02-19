package com.xiaoyue.celestial_weapon.content.generic.intf;

import com.xiaoyue.celestial_core.utils.INBTUtils;
import net.minecraft.world.item.ItemStack;

public interface WeaponBase {

    String
            Xp = "weapon_xp",
            WLevel = "weapon_level",
            ValueSlot = "weapon_value_slot";

    default int getXp(ItemStack weapon) {
        return INBTUtils.getInt(weapon, Xp);
    }

    default void setXp(ItemStack weapon, int xp) {
        INBTUtils.setInt(weapon, Xp, xp);
    }

    default void addXp(ItemStack weapon, int xp) {
        INBTUtils.setInt(weapon, Xp, getXp(weapon) + xp);
    }

    default int getLevel(ItemStack weapon) {
        return INBTUtils.getInt(weapon, WLevel);
    }

    default void setLevel(ItemStack weapon, int level) {
        INBTUtils.setInt(weapon, WLevel, level);
    }

    default int getValueSlot(ItemStack weapon) {
        return INBTUtils.getInt(weapon, ValueSlot);
    }

    default void addValueSlot(ItemStack weapon) {
        INBTUtils.setInt(weapon, ValueSlot, getValueSlot(weapon) + 1);
    }

}
