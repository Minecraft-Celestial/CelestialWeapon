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
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class GlorySword extends CMeleeBase implements WeaponAttack {
    public GlorySword() {
        super(new Properties());
    }

    @Override
    public float setDamage(ItemStack weapon) {
        return getLevel(weapon) >= 5 ? 19 : getLevel(weapon) >= 4 ? 16 : getLevel(weapon) >= 1 ? 14 : 12;
    }

    @Override
    public float setSpeed(ItemStack weapon) {
        return -2.9f;
    }

    @Override
    public void addWeaponDescription(ItemStack itemStack, Level level, List<Component> list, TooltipFlag tooltipFlag) {
        if (getLevel(itemStack) >= 1) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.glory_sword.ctrl1");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.glory_sword.ctrl1");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level1");
        }
        if (getLevel(itemStack) >= 2) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.glory_sword.ctrl2");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level2");
        }
        if (getLevel(itemStack) >= 3) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.glory_sword.ctrl2");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.glory_sword.ctrl3");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level3");
        }
        if (getLevel(itemStack) >= 4) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.glory_sword.ctrl4");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level4");
        }
        if (getLevel(itemStack) >= 5) {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.glory_sword.ctrl3");
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.attr.glory_sword.ctrl5");
        } else {
            ToolTipUtils.addLocalTooltip(list, "tooltip.celestial_weapon.is_no_weapon_level5");
        }
    }

    @Override
    public void modifyMelee(EquipmentSlot slot, ItemStack weapon, Multimap<Attribute, AttributeModifier> modify) {
        if (slot == EquipmentSlot.MAINHAND) {
            if (getLevel(weapon) >= 2) {
                IModifyUtils.modifyEventMub(modify, CAttributes.CRIT_DAMAGE.get(), meleeModifier, 0.2);
            }
            if (getLevel(weapon) >= 3) {
                IModifyUtils.modifyEventMub(modify, Attributes.ATTACK_KNOCKBACK, meleeModifier, 0.2);
            }
        }
    }

    @Override
    public void onLivingHurtEntity(LivingHurtEvent event, LivingEntity holder, ItemStack weapon) {
        if (holder instanceof Player player) {
            if (getLevel(weapon) >= 1) {
                if (IAttackUtils.isCritical(player, event.getEntity())) {
                    event.setAmount(event.getAmount() * 1.5f);
                }
            }
            if (getLevel(weapon) >= 3) {
                if (IAttackUtils.isCritical(player, event.getEntity())) {
                    player.heal(event.getAmount() * 0.25f);
                }
            }
            if (getLevel(weapon) >= 5) {
                int beneficialEffect = EntityUtils.getBeneficialEffect(holder);
                event.setAmount(event.getAmount() * (1 + (beneficialEffect * 0.06f)));
            }
        }
    }
}
