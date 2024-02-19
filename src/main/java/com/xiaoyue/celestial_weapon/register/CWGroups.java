package com.xiaoyue.celestial_weapon.register;

import com.xiaoyue.celestial_weapon.utils.IWSetUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.xiaoyue.celestial_weapon.CelestialWeapon.MODID;
import static com.xiaoyue.celestial_weapon.register.CWItems.*;

public class CWGroups {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> WEAPON = CREATIVE_MODE_TABS.register("weapon", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .title(Component.translatable("itemGroup.celestial_weapon.weapon"))
            .icon(() -> UNDEAD_SWORD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GOLD_WEAPON_BOX.get());
                output.accept(XP_BOTTLE_SMALL.get());
                output.accept(XP_BOTTLE_BASE.get());
                output.accept(XP_BOTTLE_BIG.get());
                output.accept(SHADOW_DAGGER.get());
                output.accept(IWSetUtils.setWLevel(SHADOW_DAGGER.get(), 1));
                output.accept(IWSetUtils.setWLevel(SHADOW_DAGGER.get(), 2));
                output.accept(IWSetUtils.setWLevel(SHADOW_DAGGER.get(), 3));
                output.accept(IWSetUtils.setWLevel(SHADOW_DAGGER.get(), 4));
                output.accept(IWSetUtils.setWLevel(SHADOW_DAGGER.get(), 5));
                output.accept(UNDEAD_SWORD.get());
                output.accept(IWSetUtils.setWLevel(UNDEAD_SWORD.get(), 1));
                output.accept(IWSetUtils.setWLevel(UNDEAD_SWORD.get(), 2));
                output.accept(IWSetUtils.setWLevel(UNDEAD_SWORD.get(), 3));
                output.accept(IWSetUtils.setWLevel(UNDEAD_SWORD.get(), 4));
                output.accept(IWSetUtils.setWLevel(UNDEAD_SWORD.get(), 5));
                output.accept(ABYSS_SWORD.get());
                output.accept(IWSetUtils.setWLevel(ABYSS_SWORD.get(), 1));
                output.accept(IWSetUtils.setWLevel(ABYSS_SWORD.get(), 2));
                output.accept(IWSetUtils.setWLevel(ABYSS_SWORD.get(), 3));
                output.accept(IWSetUtils.setWLevel(ABYSS_SWORD.get(), 4));
                output.accept(IWSetUtils.setWLevel(ABYSS_SWORD.get(), 5));
                output.accept(GLORY_SWORD.get());
                output.accept(IWSetUtils.setWLevel(GLORY_SWORD.get(), 1));
                output.accept(IWSetUtils.setWLevel(GLORY_SWORD.get(), 2));
                output.accept(IWSetUtils.setWLevel(GLORY_SWORD.get(), 3));
                output.accept(IWSetUtils.setWLevel(GLORY_SWORD.get(), 4));
                output.accept(IWSetUtils.setWLevel(GLORY_SWORD.get(), 5));
                output.accept(PAST_MACHETE.get());
                output.accept(IWSetUtils.setWLevel(PAST_MACHETE.get(), 1));
                output.accept(IWSetUtils.setWLevel(PAST_MACHETE.get(), 2));
                output.accept(IWSetUtils.setWLevel(PAST_MACHETE.get(), 3));
                output.accept(IWSetUtils.setWLevel(PAST_MACHETE.get(), 4));
                output.accept(IWSetUtils.setWLevel(PAST_MACHETE.get(), 5));
                output.accept(SHADOW_RAPIER.get());
                output.accept(IWSetUtils.setWLevel(SHADOW_RAPIER.get(), 1));
                output.accept(IWSetUtils.setWLevel(SHADOW_RAPIER.get(), 2));
                output.accept(IWSetUtils.setWLevel(SHADOW_RAPIER.get(), 3));
                output.accept(IWSetUtils.setWLevel(SHADOW_RAPIER.get(), 4));
                output.accept(IWSetUtils.setWLevel(SHADOW_RAPIER.get(), 5));
                output.accept(SKELETON_SWORD.get());
                output.accept(IWSetUtils.setWLevel(SKELETON_SWORD.get(), 1));
                output.accept(IWSetUtils.setWLevel(SKELETON_SWORD.get(), 2));
                output.accept(IWSetUtils.setWLevel(SKELETON_SWORD.get(), 3));
                output.accept(IWSetUtils.setWLevel(SKELETON_SWORD.get(), 4));
                output.accept(IWSetUtils.setWLevel(SKELETON_SWORD.get(), 5));
                output.accept(ELVEN_BOW .get());
                output.accept(IWSetUtils.setWLevel(ELVEN_BOW.get(), 1));
                output.accept(IWSetUtils.setWLevel(ELVEN_BOW.get(), 2));
                output.accept(IWSetUtils.setWLevel(ELVEN_BOW.get(), 3));
                output.accept(IWSetUtils.setWLevel(ELVEN_BOW.get(), 4));
                output.accept(IWSetUtils.setWLevel(ELVEN_BOW.get(), 5));
            }).build());
}
