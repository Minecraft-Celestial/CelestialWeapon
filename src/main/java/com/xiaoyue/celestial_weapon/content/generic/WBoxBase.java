package com.xiaoyue.celestial_weapon.content.generic;

import com.xiaoyue.celestial_core.utils.LevelUtils;
import com.xiaoyue.celestial_weapon.register.CWItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

import java.util.List;

public class WBoxBase extends Item {
    public WBoxBase() {
        super(new Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    public Item roll(RandomSource random) {
        List<Item> bases = CWItems.weaponBases;
        int i = random.nextInt(bases.size() * 10);
        int j = 0;
        for (Item item : bases) {
            j += 10;
            if (i < j) {
                return item;
            }
        }
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (LevelUtils.isServerLevel(pLevel)) {
            ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
            itemInHand.shrink(1);
            Item roll = roll(pPlayer.getRandom());
            pPlayer.addItem(new ItemStack(roll));
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }
}
