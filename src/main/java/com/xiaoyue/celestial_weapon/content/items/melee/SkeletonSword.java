package com.xiaoyue.celestial_weapon.content.items.melee;

import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.EntityUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CMeleeBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import com.xiaoyue.celestial_weapon.utils.IModifyUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SkeletonSword extends CMeleeBase implements WeaponAttack {
    public SkeletonSword() {
        super(new Properties());
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return -2.9f;
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? 21 : getLevel(weapon) >= 4 ? 19 : getLevel(weapon) >= 3 ? 17 : getLevel(weapon) >= 1 ? 15 : 14;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.skeleton_sword.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.skeleton_sword.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.skeleton_sword.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.skeleton_sword.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.skeleton_sword.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.skeleton_sword.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.skeleton_sword.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.skeleton_sword.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void modifyMelee(EquipmentSlot slot, ItemStack weapon, Multimap<Attribute, AttributeModifier> modify) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (getLevel(weapon) >= 2) {
                IModifyUtils.modifyEventAdd(modify, ForgeMod.ENTITY_REACH.get(), meleeModifier, 1);
            }
        }
    }

    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return target.getBoundingBox().inflate(2.0, 0.5, 2.0);
    }

    @Override
    public void onHurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
        if (getLevel(weapon) >= 5) {
            if (!EntityUtils.hasArmor(target)) {
                EntityUtils.addEct(target, MobEffects.MOVEMENT_SLOWDOWN, 100, 1);
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 3) {
            event.setAmount(event.getAmount() + event.getEntity().getMaxHealth() * 0.05f);
        }
    }
}
