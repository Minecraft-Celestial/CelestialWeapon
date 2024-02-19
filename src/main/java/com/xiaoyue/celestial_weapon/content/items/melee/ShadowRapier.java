package com.xiaoyue.celestial_weapon.content.items.melee;

import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CMeleeBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class ShadowRapier extends CMeleeBase implements WeaponAttack {
    public ShadowRapier() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 2 ? 11 : getLevel(weapon) >= 1 ? 10 : 9;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? -1.7f : getLevel(weapon) >= 4 ? -1.8f : getLevel(weapon) >= 3 ? -1.9f : -2;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_rapier.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_rapier.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_rapier.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_rapier.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_rapier.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_rapier.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.shadow_rapier.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.shadow_rapier.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 1) {
            if (!EntityUtils.hasArmor(event.getEntity())) {
                event.setAmount(event.getAmount() * (1 + 1.5f));
            }
        }
        if (getLevel(weapon) >= 3) {
            if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                EntityUtils.addEct(holder, MobEffects.DIG_SPEED, 100, 0);
            }
        }
        if (getLevel(weapon) >= 5) {
            if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                EntityUtils.addEct(event.getEntity(), MobEffects.MOVEMENT_SLOWDOWN, 100, 0);
            }
        }
    }
}
