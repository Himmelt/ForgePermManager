package org.soraworld.fpm.api;

public interface PermManager {

    boolean hasUserPerm(String player, String permission);

    void addUserPerm(String player, String permission);

    void delUserPerm(String player, String permission);

    boolean hasGroupPerm(String group, String permission);

    void addGroupPerm(String group, String permission);

    void delGroupPerm(String group, String permission);

    boolean inGroup(String player, String group);

    boolean inTheGroup(String player, String group);

    void moveTo(String player, String group);

    void moveToDefault(String player);

    void addSub(String player, String group);

    void delSub(String player, String group);

}
