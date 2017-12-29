package org.soraworld.fpm.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Property;
import org.soraworld.fpm.Constants;

import java.io.File;

@Config(modid = Constants.MOD_ID, name = "fpm/settings")
public class ConfigManager {

    //private transient final File root;
    @Config.Name("groupNames")
    @Config.Comment("Group Name List")
    private Property groupNames;

    public ConfigManager(File root) {
        //this.root = root;
    }

    public String[] getGroupNames() {
        return null;
    }
}
