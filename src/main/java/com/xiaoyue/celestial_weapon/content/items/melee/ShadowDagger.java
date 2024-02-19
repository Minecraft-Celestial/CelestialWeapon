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

public class ShadowDagger extends CMeleeBase implements WeaponAttack {
    public ShadowDagger() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 4 ? 13 : getLevel(weapon) >= 2 ? 11 : getLevel(weapon) >= 1 ? 10 : 9;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? -2.1f : getLevel(weapon) >= 3 ? -2.2f : -2.3f;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_dagger.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_dagger.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_dagger.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_dagger.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_dagger.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_dagger.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_dagger.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_dagger.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void onHurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
        if (getLevel(weapon) >= 3) {
            if (EntityUtils.isLookingBehindTarget(target, attacker.getEyePosition())) {
                EntityUtils.addEct(attacker, MobEffects.ABSORPTION, 100, 1);
            }
        }
        if (getLevel(weapon) >= 5) {
            if (target.getKillCredit() != attacker) {
                EntityUtils.addEct(attacker, MobEffects.DAMAGE_BOOST, 100, 1);
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 1) {
            if (holder.level().isNight()) {
                if (EntityUtils.isLookingBehindTarget(event.getEntity(), holder.getEyePosition())) {
                    event.setAmount(event.getAmount() * 1.5f);
                }
            }
        }
    }
}
