package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class GroupManager {

    private Group base = new Group();
    private HashMap<String, Group> groups = new HashMap<>();

    public void set(@Nonnull Group base, @Nonnull HashMap<String, Group> groups) {
        this.base = base;
        this.groups = groups;
    }

}
