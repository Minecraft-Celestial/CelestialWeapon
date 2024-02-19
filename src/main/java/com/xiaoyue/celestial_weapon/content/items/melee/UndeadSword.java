package com.xiaoyue.celestial_weapon.content.items.melee;

import com.xiaoyue.celestial_core.utils.IAttackUtils;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import com.xiaoyue.celestial_weapon.content.generic.CMeleeBase;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAttack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class UndeadSword extends CMeleeBase implements WeaponAttack {
    public UndeadSword() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 4 ? 19 : getLevel(weapon) >= 3 ? 17 : getLevel(weapon) >= 2 ? 15 : getLevel(weapon) >= 1 ? 13 : 11;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? -2.5f : -2.7f;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.undead_sword.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.undead_sword.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.undead_sword.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.undead_sword.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.undead_sword.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.undead_sword.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.undead_sword.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.undead_sword.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void onHurtEnemy(ItemStack weapon, LivingEntity target, LivingEntity attacker) {
        if (getLevel(weapon) >= 1) {
            if (attacker instanceof Player player) {
                if (IAttackUtils.isFullCharged(player)) {
                    player.heal(1f);
                }
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (getLevel(weapon) >= 3) {
            if (event.getEntity().getMobType() == MobType.UNDEAD) {
                event.setAmount(event.getAmount() * 1.5f);
            }
        }
        if (getLevel(weapon) >= 5) {
            float missHealth = holder.getMaxHealth() - holder.getHealth();
            event.setAmount(event.getAmount() * (1 + (missHealth * 0.02f)));
        }
    }
}
