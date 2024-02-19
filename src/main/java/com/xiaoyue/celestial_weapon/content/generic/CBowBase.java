package com.xiaoyue.celestial_weapon.content.generic;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import com.xiaoyue.celestial_weapon.register.CWItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CBowBase extends BowItem implements WeaponBase {

    public String bowModifier = "bow_modifier";
    public int bowDurability;

    public CBowBase(Properties pProperties, int durability) {
        super(pProperties.durability(durability));
        bowDurability = durability;
        CWItems.bows.add(this);
        CWItems.weaponBases.add(this);
    }

    public float setBowSpeed(ItemStack bow) {
        return 20;
    }

    public double setBowDamage(ItemStack bow) {
        return 0;
    }

    public double setArrowSpeed(ItemStack bow) {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.bow.damage",
                ChatFormatting.BLUE, setBowDamage(itemStack));
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.bow.speed",
                ChatFormatting.BLUE, setBowSpeed(itemStack) / 20);
        ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.arrow.speed",
                ChatFormatting.BLUE, setArrowSpeed(itemStack));
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

    public void releaseUsing(ItemStack bow, Level pLevel, LivingEntity user, int pTimeLeft) {
        if (user instanceof Player player) {
            boolean flag = player.getAbilities().instabuild || bow.getEnchantmentLevel(Enchantments.INFINITY_ARROWS) > 0;
            ItemStack ammo = player.getProjectile(bow);
            int change = this.getUseDuration(bow) - pTimeLeft;
            change = ForgeEventFactory.onArrowLoose(bow, pLevel, player, change, !ammo.isEmpty() || flag);
            if (change < 0) {
                return;
            }

            if (!ammo.isEmpty() || flag) {
                if (ammo.isEmpty()) {
                    ammo = new ItemStack(Items.ARROW);
                }

                float pull = getBowPowerForTime(change, bow);
                if ((double) pull < 0.1) {
                    return;
                }
                boolean flag1 = player.getAbilities().instabuild || ammo.getItem() instanceof ArrowItem && ((ArrowItem) ammo.getItem()).isInfinite(ammo, bow, player);
                if (!pLevel.isClientSide) {
                    ArrowItem arrowitem = (ArrowItem)(ammo.getItem() instanceof ArrowItem ? ammo.getItem() : Items.ARROW);
                    AbstractArrow arrow = arrowitem.createArrow(pLevel, ammo, player);
                    arrow = this.customArrow(arrow);
                    arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, pull * 3.0F, 1.0F);

                    arrow.setBaseDamage(arrow.getBaseDamage() + setBowDamage(bow));
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(setArrowSpeed(bow)));

                    if (pull == 1.0F) {
                        arrow.setCritArrow(true);
                    }

                    int s = bow.getEnchantmentLevel(Enchantments.POWER_ARROWS);
                    if (s > 0) {
                        arrow.setBaseDamage(arrow.getBaseDamage() + (double) s * 0.5 + 0.5);
                    }

                    int p = bow.getEnchantmentLevel(Enchantments.PUNCH_ARROWS);
                    if (p > 0) {
                        arrow.setKnockback(p);
                    }

                    if (bow.getEnchantmentLevel(Enchantments.FLAMING_ARROWS) > 0) {
                        arrow.setSecondsOnFire(100);
                    }

                    bow.hurtAndBreak(1, player, (play) -> {
                        play.broadcastBreakEvent(player.getUsedItemHand());
                    });
                    if (flag1 || player.getAbilities().instabuild && (ammo.is(Items.SPECTRAL_ARROW) || ammo.is(Items.TIPPED_ARROW))) {
                        arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    pLevel.addFreshEntity(arrow);
                }

                pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + pull * 0.5F);
                if (!flag1 && !player.getAbilities().instabuild) {
                    ammo.shrink(1);
                    if (ammo.isEmpty()) {
                        player.getInventory().removeItem(ammo);
                    }
                }

                player.awardStat(Stats.ITEM_USED.get(this));
            }
        }
    }

    public float getPullForTime(float time, ItemStack bow) {
        float f = (time / setBowSpeed(bow)) * 1.5f;
        return Math.min(1, f);
    }

    public float getBowPowerForTime(float time, ItemStack bow) {
        float f = getPullForTime(time, bow);
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return Math.min(1, f);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public AbstractArrow customArrow(AbstractArrow arrow) {
        return super.customArrow(arrow);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modify = LinkedHashMultimap.create();
        return modifyBow(slot, stack, modify);
    }

    public Multimap<Attribute, AttributeModifier> modifyBow(EquipmentSlot slot, ItemStack stack, Multimap<Attribute, AttributeModifier> modify) {
        return modify;
    }

    @Override
    public int getEnchantmentValue() {
        return super.getEnchantmentValue();
    }

}
