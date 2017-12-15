package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class Group extends PG {

    public void setNode(@Nonnull Node node) {
        this.node = node;
    }

    @Nonnull
    public HashSet<String> getParents() {
        return names != null ? names : new HashSet<>();
    }
}
