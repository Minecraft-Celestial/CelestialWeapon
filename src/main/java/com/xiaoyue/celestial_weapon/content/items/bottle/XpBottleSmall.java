package com.xiaoyue.celestial_weapon.content.items.bottle;

import com.xiaoyue.celestial_weapon.content.generic.XBottleBase;
import net.minecraft.world.item.Item;

public class XpBottleSmall extends XBottleBase {
    public XpBottleSmall() {
        super(new Item.Properties());
    }

    @Override
    public int addXpAmount() {
        return 50;
    }
}
