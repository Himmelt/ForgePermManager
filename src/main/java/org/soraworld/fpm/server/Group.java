package org.soraworld.fpm.server;

import java.util.HashSet;

public class Group {

    private final HashSet<String> permissions = new HashSet<>();

    public boolean has(String permission) {
        return false;
    }
}
