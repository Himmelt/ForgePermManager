package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
    }
}
