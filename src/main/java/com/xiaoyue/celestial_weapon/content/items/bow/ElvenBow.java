package com.xiaoyue.celestial_weapon.content.items.bow;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CBowBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ElvenBow extends CBowBase implements WeaponAttack {
    public ElvenBow() {
        super(new Properties(), 800);
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.elven_bow.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.elven_bow.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.elven_bow.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.elven_bow.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.elven_bow.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.elven_bow.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.elven_bow.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.elven_bow.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public double setBowDamage(ItemStack bow) {
        return getLevel(bow);
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (event.getSource().getDirectEntity() instanceof AbstractArrow) {
            if (getLevel(weapon) >= 1) {
                EntityUtils.addEct(holder, MobEffects.MOVEMENT_SPEED, 100, 0);
            }
            if (getLevel(weapon) >= 3) {
                float distance = holder.distanceTo(event.getEntity());
                event.setAmount(event.getAmount() * (1 + (distance * 0.05f)));
            }
            if (getLevel(weapon) >= 5) {
                if (!EntityUtils.hasArmor(event.getEntity())) {
                    event.setAmount(event.getAmount() * (1 + 1f));
                }
            }
        }
    }
}
