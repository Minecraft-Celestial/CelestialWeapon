package com.xiaoyue.celestial_weapon.content.generic;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import com.xiaoyue.celestial_weapon.register.CWItems;
import com.xiaoyue.celestial_weapon.register.WeaponTier;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CMeleeBase extends TieredItem implements WeaponBase {

    public String meleeModifier = "melee_modifier";

    public CMeleeBase(Properties pProperties) {
        super(new WeaponTier(), pProperties);
        CWItems.weaponBases.add(this);
    }

    public float setDamage(ItemStack weapon) {
        return 0;
    }

    public float setSpeed(ItemStack weapon) {
        return 0;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (Screen.hasControlDown()) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.get_weapon_level",
                    ChatFormatting.AQUA, getLevel(itemStack));
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.get_has_weapon_xp",
                    ChatFormatting.AQUA, getXp(itemStack));
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.get_weapon_value_slot",
                    ChatFormatting.AQUA, getValueSlot(itemStack));
            addWeaponDescription(itemStack, level, list, tooltipFlag);
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.has_ctrl_down");
        }
        super.appendHoverText(itemStack, level, list, tooltipFlag);
    }

    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
    }

    @Override
    public Rarity getRarity(ItemStack itemStack) {
        if (getLevel(itemStack) >= 5) {
            return Rarity.EPIC;
        } else if (getLevel(itemStack) >= 4) {
            return Rarity.UNCOMMON;
        } else if (getLevel(itemStack) >= 3) {
            return Rarity.RARE;
        } else if (getLevel(itemStack) >= 2) {
            return Rarity.RARE;
        } else {
            return Rarity.COMMON;
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modify = LinkedHashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            modify.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, meleeModifier,getTier().getAttackDamageBonus() + setDamage(stack), AttributeModifier.Operation.ADDITION));
            modify.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, meleeModifier, setSpeed(stack), AttributeModifier.Operation.ADDITION));
        }
        modifyMelee(slot, stack, modify);
        return modify;
    }

    public void modifyMelee(EquipmentSlot slot, ItemStack weapon, Multimap<Attribute, AttributeModifier> modify) {
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof LivingEntity holder) {
            onWeaponTick(pStack, pLevel, holder, pSlotId, pIsSelected);
        }
    }

    public void onWeaponTick(ItemStack weapon, Level level, LivingEntity holder, int slot, boolean isSelected) {
    }

    @Override
    public @NotNull AABB getSweepHitBox(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity target) {
        return super.getSweepHitBox(stack, player, target);
    }

    @Override
    public boolean hurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
        onHurtEnemy(weapon, target, attacker);
        weapon.hurtAndBreak(WeaponBreak(), attacker, (atk) -> {
            atk.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    public void onHurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
    }

    public int WeaponBreak() {
        return 1;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }
}
