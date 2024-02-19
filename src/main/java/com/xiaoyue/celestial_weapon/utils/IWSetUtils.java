package com.xiaoyue.celestial_weapon.utils;

import com.xiaoyue.celestial_core.utils.INBTUtils;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IWSetUtils {

    public static ItemStack setWLevel(Item item, int level) {
        ItemStack itemStack = new ItemStack(item);
        INBTUtils.setInt(itemStack, WeaponBase.WLevel, level);
        return itemStack;
    }
}
