package com.xiaoyue.celestial_weapon.content.items.melee;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CMeleeBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class PastMachete extends CMeleeBase implements WeaponAttack {
    public PastMachete() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? 13 : getLevel(weapon) >= 4 ? 11 : getLevel(weapon) >= 2 ? 10 : getLevel(weapon) >= 1 ? 9 : 8;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return getLevel(weapon) >= 3 ? -2.3f : -2.4f;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.past_machete.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.past_machete.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.past_machete.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.past_machete.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.past_machete.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.past_machete.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.past_machete.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.past_machete.ctrl4");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.past_machete.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    public int doubleHit;

    @Override
    public void onWeaponTick(ItemStack weapon, Level level, LivingEntity holder, int slot, boolean isSelected) {
        if (doubleHit > 0) {
            if (getLevel(weapon) >= 5) {
                if (holder.tickCount % 200 == 0) {
                    doubleHit--;
                }
            } else if (holder.tickCount % 100 == 0) {
                doubleHit--;
            }
        }
    }

    @Override
    public void onHurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
        if (getLevel(weapon) >= 1) {
            if (doubleHit < 15) {
                doubleHit++;
            }
        }
        if (getLevel(weapon) >= 3) {
            if (doubleHit >= 10) {
                EntityUtils.addEct(attacker, MobEffects.MOVEMENT_SPEED, 100, 0);
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 1) {
            event.setAmount(event.getAmount() * (1 + (doubleHit * 0.1f)));
        }
    }
}
