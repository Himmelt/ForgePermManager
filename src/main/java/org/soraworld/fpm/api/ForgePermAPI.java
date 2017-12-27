package org.soraworld.fpm.api;

import org.soraworld.fpm.core.PermissionManager;

public final class ForgePermAPI {

    private static PermManager manager;

    public static PermManager getPermManager() {
        return manager == null ? manager = new PermissionManager() : manager;
    }

}
