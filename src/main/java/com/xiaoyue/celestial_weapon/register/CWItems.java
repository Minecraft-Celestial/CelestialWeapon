package com.xiaoyue.celestial_weapon.register;

import com.xiaoyue.celestial_weapon.content.generic.CBowBase;
import com.xiaoyue.celestial_weapon.content.items.bottle.XpBottleSmall;
import com.xiaoyue.celestial_weapon.content.items.bottle.XpBottleBase;
import com.xiaoyue.celestial_weapon.content.items.bottle.XpBottleBig;
import com.xiaoyue.celestial_weapon.content.items.bow.ElvenBow;
import com.xiaoyue.celestial_weapon.content.items.box.GoldWeaponBox;
import com.xiaoyue.celestial_weapon.content.items.melee.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static com.xiaoyue.celestial_weapon.CelestialWeapon.MODID;

public class CWItems {

    public static List<CBowBase> bows = new ArrayList<>();
    public static List<Item> weaponBases = new ArrayList<>();

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> XP_BOTTLE_SMALL = ITEM.register("xp_bottle_small", XpBottleSmall::new);
    public static final RegistryObject<Item> XP_BOTTLE_BASE = ITEM.register("xp_bottle_base", XpBottleBase::new);
    public static final RegistryObject<Item> XP_BOTTLE_BIG = ITEM.register("xp_bottle_big", XpBottleBig::new);
    public static final RegistryObject<Item> GOLD_WEAPON_BOX = ITEM.register("gold_weapon_box", GoldWeaponBox::new);

    public static final RegistryObject<Item> SHADOW_DAGGER = ITEM.register("shadow_dagger", ShadowDagger::new);
    public static final RegistryObject<Item> UNDEAD_SWORD = ITEM.register("undead_sword", UndeadSword::new);
    public static final RegistryObject<Item> ABYSS_SWORD = ITEM.register("abyss_sword", AbyssSword::new);
    public static final RegistryObject<Item> GLORY_SWORD = ITEM.register("glory_sword", GlorySword::new);
    public static final RegistryObject<Item> PAST_MACHETE = ITEM.register("past_machete", PastMachete::new);
    public static final RegistryObject<Item> SHADOW_RAPIER = ITEM.register("shadow_rapier", ShadowRapier::new);
    public static final RegistryObject<Item> SKELETON_SWORD = ITEM.register("skeleton_sword", SkeletonSword::new);
    public static final RegistryObject<Item> ELVEN_BOW = ITEM.register("elven_bow", ElvenBow::new);

}
