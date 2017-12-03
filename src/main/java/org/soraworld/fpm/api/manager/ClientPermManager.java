package org.soraworld.fpm.api.manager;

public interface ClientPermManager {

    void init();

    boolean available();

    boolean has(String permission);

}
