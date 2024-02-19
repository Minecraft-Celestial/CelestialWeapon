package com.xiaoyue.celestial_weapon;

import com.mojang.logging.LogUtils;
import com.xiaoyue.celestial_weapon.content.generic.CBowBase;
import com.xiaoyue.celestial_weapon.events.AGenericEvents;
import com.xiaoyue.celestial_weapon.network.CWMessages;
import com.xiaoyue.celestial_weapon.register.CWItems;
import com.xiaoyue.celestial_weapon.register.CWKeymapping;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static com.xiaoyue.celestial_weapon.register.CWGroups.CREATIVE_MODE_TABS;
import static com.xiaoyue.celestial_weapon.register.CWItems.ITEM;

@Mod(CelestialWeapon.MODID)
public class CelestialWeapon
{
    public static final String MODID = "celestial_weapon";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CelestialWeapon()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ITEM.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new AGenericEvents());

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        CWMessages.register();
    }

}
