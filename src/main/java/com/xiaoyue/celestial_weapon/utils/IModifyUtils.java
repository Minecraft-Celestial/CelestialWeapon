package com.xiaoyue.celestial_weapon.utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.xiaoyue.celestial_core.utils.ToolTipUtils;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.event.ItemAttributeModifierEvent;

public class IModifyUtils {

	public static Multimap<Attribute, AttributeModifier> modifyEventAdd(ImmutableMultimap.Builder<Attribute, AttributeModifier> modify, Attribute attribute, String name, double value) {
		modify.put(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.ADDITION));
		return modify.build();
	}

	public static Multimap<Attribute, AttributeModifier> modifyEventMub(ImmutableMultimap.Builder<Attribute, AttributeModifier> modify, Attribute attribute, String name, double value) {
		modify.put(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.MULTIPLY_BASE));
		return modify.build();
	}

	public static Multimap<Attribute, AttributeModifier> modifyEventAdd(Multimap<Attribute, AttributeModifier> modify, Attribute attribute, String name, double value) {
		modify.put(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.ADDITION));
		return modify;
	}

	public static Multimap<Attribute, AttributeModifier> modifyEventMub(Multimap<Attribute, AttributeModifier> modify, Attribute attribute, String name, double value) {
		modify.put(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.MULTIPLY_BASE));
        return modify;
    }

	public static void modifyEventAdd(ItemAttributeModifierEvent event, Attribute attribute, String name, double value) {
		event.addModifier(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.ADDITION));
	}

	public static void modifyEventMub(ItemAttributeModifierEvent event, Attribute attribute, String name, double value) {
		event.addModifier(attribute, new AttributeModifier(ToolTipUtils.getFUuid(), name, value, AttributeModifier.Operation.MULTIPLY_BASE));
	}
}
