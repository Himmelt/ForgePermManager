package org.soraworld.fpm.api;

public final class ForgePermAPI {

    private static PermManager manager;

    public static boolean isReady() {
        return manager != null;
    }

    public static PermManager getPermManager() {
        return manager;
    }

    public static void setPermManager(PermManager manager) {
        ForgePermAPI.manager = manager;
    }

}
