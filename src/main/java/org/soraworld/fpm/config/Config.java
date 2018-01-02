package org.soraworld.fpm.config;

import org.soraworld.avalon.config.AvalonConfig;
import org.soraworld.avalon.config.AvalonProperty;

import java.io.File;

public class Config extends AvalonConfig {

    @AvalonProperty(key = "defaultPerms", comment = "默认用户组权限列表")
    private String[] defaultPerms;
    @AvalonProperty(key = "userGroupNames", comment = "可加载的用户组")
    private String[] userGroupNames;
    @AvalonProperty(key = "permGroupNames", comment = "可加载的权限组")
    private String[] permGroupNames;

    public Config(File file, String version) {
        super(file, version);
    }

    public String[] getDefaultPerms() {
        return defaultPerms;
    }

    public String[] getGroupNames() {
        return userGroupNames;
    }

    public String[] getPermGroupNames() {
        return permGroupNames;
    }
}
