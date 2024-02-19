package com.xiaoyue.celestial_weapon.events;

import com.xiaoyue.celestial_core.register.COItems;
import com.xiaoyue.celestial_weapon.content.generic.intf.WeaponBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.xiaoyue.celestial_weapon.CelestialWeapon.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RecipeEvents {

    @SubscribeEvent
    public static void addWeaponLevel(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft().copy();
        ItemStack right = event.getRight();
        if (left.getItem() instanceof WeaponBase base) {
            if (base.getXp(left) == 500 && base.getLevel(left) == 0) {
                if (right.is(Items.NETHERITE_INGOT)) {
                    event.setMaterialCost(1);
                    event.setCost(10);
                    base.addValueSlot(left);
                    base.setLevel(left, 1);
                    event.setOutput(left);
                }
            }
            if (base.getXp(left) == 500 && base.getLevel(left) == 1) {
                if (right.is(COItems.TREASURE_FRAGMENT.get())) {
                    event.setMaterialCost(1);
                    event.setCost(20);
                    base.setLevel(left, 2);
                    event.setOutput(left);
                }
            }
            if (base.getXp(left) == 500 && base.getLevel(left) == 2) {
                if (right.is(Items.ENCHANTED_GOLDEN_APPLE)) {
                    event.setMaterialCost(1);
                    event.setCost(30);
                    base.addValueSlot(left);
                    base.setLevel(left, 3);
                    event.setOutput(left);
                }
            }
            if (base.getXp(left) == 500 && base.getLevel(left) == 3) {
                if (right.is(Items.NETHER_STAR)) {
                    event.setMaterialCost(1);
                    event.setCost(40);
                    base.setLevel(left, 4);
                    event.setOutput(left);
                }
            }
            if (base.getXp(left) == 500 && base.getLevel(left) == 4) {
                if (right.is(Items.DRAGON_HEAD)) {
                    event.setMaterialCost(1);
                    event.setCost(50);
                    base.addValueSlot(left);
                    base.setLevel(left, 5);
                    event.setOutput(left);
                }
            }
        }
    }
}
