package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class Permission extends PG {

    @Nonnull
    public HashSet<String> getGroups() {
        return names != null ? names : new HashSet<>();
    }
}
