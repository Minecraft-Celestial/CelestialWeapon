package com.xiaoyue.celestial_weapon.network;

import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponAbility;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WAbilityPacket {

    public WAbilityPacket() {
    }

    public WAbilityPacket(FriendlyByteBuf buf) {
    }

    public void toBuf(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            ServerLevel level = player.serverLevel();
            ItemStack item = player.getMainHandItem();
            if (!player.getCooldowns().isOnCooldown(item.getItem())) {
                if (item.getItem() instanceof WeaponAbility weaponAbility) {
                    weaponAbility.getWeaponAbility(player, level, item);
                }
            }
        });
        return true;
    }
}