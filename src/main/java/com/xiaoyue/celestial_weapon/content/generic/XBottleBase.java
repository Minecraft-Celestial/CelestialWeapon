package com.xiaoyue.celestial_weapon.content.generic;

import com.xiaoyue.celestial_core.utils.INBTUtils;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class XBottleBase extends Item {
    public XBottleBase(Properties pProperties) {
        super(pProperties.rarity(Rarity.UNCOMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack mainHandItem = pPlayer.getMainHandItem();
        ItemStack offhandItem = pPlayer.getOffhandItem();
        if (mainHandItem.is(this)) {
            if (offhandItem.getItem() instanceof WeaponBase base) {
                if (base.getXp(offhandItem) < 500) {
                    int min = Math.min(500, INBTUtils.getInt(offhandItem, WeaponBase.Xp) + addXpAmount());
                    INBTUtils.setInt(offhandItem, WeaponBase.Xp, min);
                    mainHandItem.shrink(1);
                }
            }
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    public int addXpAmount() {
        return 0;
    }
}
