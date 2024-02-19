package com.xiaoyue.celestial_weapon.content.items.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.register.CAttributes;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.IAttackUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CMeleeBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import com.xiaoyue.celestial_weapon.utils.IModifyUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class AbyssSword extends CMeleeBase implements WeaponAttack {
    public AbyssSword() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 4 ? 16 : getLevel(weapon) >= 3 ? 14 : getLevel(weapon) >= 2 ? 12 : getLevel(weapon) >= 1 ? 10 : 8;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return -2.5f;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.abyss_sword.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.abyss_sword.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.abyss_sword.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.abyss_sword.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.abyss_sword.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.abyss_sword.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.abyss_sword.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.abyss_sword.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void modifyMelee(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> modify) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (getLevel(stack) >= 5) {
                IModifyUtils.modifyEventMub(modify, CAttributes.CRIT_RATE.get(), meleeModifier, 0.1);
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 1) {
            int harmfulEffect = EntityUtils.getHarmfulEffect(event.getEntity());
            event.setAmount(event.getAmount() * (1 + (harmfulEffect * 0.08f)));
        }
        if (getLevel(weapon) >= 3) {
            IAttackUtils.attackEntitySecondary(holder.damageSources().fellOutOfWorld(), event.getAmount() * 0.5f, event.getEntity());
        }
        if (getLevel(weapon) >= 5) {
            weapon.hurtAndBreak(10, holder, h -> h.broadcastBreakEvent(EquipmentSlot.MAINHAND));
            event.setAmount(event.getAmount() * 1.5f);
        }
    }
}
