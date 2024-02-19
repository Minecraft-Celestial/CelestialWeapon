package com.xiaoyue.celestial_weapon.content.items.item;

import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WTalentBook extends Item {

    public WTalentBook() {
        super(new Properties().stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        ToolTipUtils.addLocalTooltip(pTooltipComponents, "tooltip.celestial_weapon.");
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}
