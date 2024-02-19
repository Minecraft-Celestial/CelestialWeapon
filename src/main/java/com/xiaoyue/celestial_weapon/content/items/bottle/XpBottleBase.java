package com.xiaoyue.celestial_weapon.content.items.bottle;

import com.xiaoyue.celestial_weapon.content.generic.XBottleBase;

public class XpBottleBase extends XBottleBase {
    public XpBottleBase() {
        super(new Properties());
    }

    @Override
    public int addXpAmount() {
        return 100;
    }
}
