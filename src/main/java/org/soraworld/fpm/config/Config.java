package org.soraworld.fpm.config;

import java.io.File;

public class Config extends AbstractConfig {

    @AvalonProperty(cat = "test", key = "key")
    private String test = "test value";
    @AvalonProperty(cat = "groupNames", key = "key")
    private String groupNames = "groupNames value";

    public Config(File file) {
        super(file);
    }

    public String[] getGroupNames() {
        String[] strings = new String[1];
        strings[0] = groupNames;
        return strings;
    }
}
