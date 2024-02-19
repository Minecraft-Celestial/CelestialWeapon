package com.xiaoyue.celestial_weapon.content.items.bottle;

import com.xiaoyue.celestial_weapon.content.generic.XBottleBase;

public class XpBottleBig extends XBottleBase {
    public XpBottleBig() {
        super(new Properties());
    }

    @Override
    public int addXpAmount() {
        return 200;
    }
}
