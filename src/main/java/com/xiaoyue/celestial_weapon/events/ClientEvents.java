package com.xiaoyue.celestial_weapon.events;

import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import com.xiaoyue.celestial_weapon.network.CWMessages;
import com.xiaoyue.celestial_weapon.network.WAbilityPacket;
import com.xiaoyue.celestial_weapon.register.CWKeymapping;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_weapon.CelestialWeapon.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void renderTooltip(RenderTooltipEvent.Color event) {
        ItemStack itemStack = event.getItemStack();
        if (itemStack.getItem() instanceof WeaponBase) {
            event.setBorderStart(0xFF87CEFA);
            event.setBorderEnd(0xFF87CEFA);
        }
    }

    @SubscribeEvent
    public static void onInputKey(InputEvent.Key event) {
        if (CWKeymapping.WEAPON_ABILITY_KEY.consumeClick()) {
            CWMessages.sendToServer(new WAbilityPacket());
        }
    }
}