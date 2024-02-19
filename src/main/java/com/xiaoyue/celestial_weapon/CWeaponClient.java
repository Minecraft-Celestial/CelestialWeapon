package com.xiaoyue.celestial_weapon;

import com.xiaoyue.celestial_weapon.content.generic.CBowBase;
import com.xiaoyue.celestial_weapon.register.CWItems;
import com.xiaoyue.celestial_weapon.register.CWKeymapping;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CelestialWeapon.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CWeaponClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(CWeaponClient::registerItemProperties);
	}

	public static void registerItemProperties() {
		for (CBowBase bow : CWItems.bows) {
			ItemProperties.register(bow, new ResourceLocation("pull"), (stack, level, entity, i) -> entity == null || entity.getUseItem() != stack ? 0.0F : bow.getBowPowerForTime(stack.getUseDuration() - entity.getUseItemRemainingTicks(), stack));
			ItemProperties.register(bow, new ResourceLocation("pulling"), (stack, level, entity, i) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);
		}
	}

	@SubscribeEvent
	public static void keyRegister(RegisterKeyMappingsEvent event) {
		event.register(CWKeymapping.WEAPON_ABILITY_KEY);
	}
}
