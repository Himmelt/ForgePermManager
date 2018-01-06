package org.soraworld.fpm.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class StorageManager {

    private final File root;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public StorageManager(File root) {
        this.root = root;
    }

    public Permission getGroupFromFile(String name) {
        File file = new File(root, "groups/" + name + ".dat");
        Permission group = new Permission();
        try {
            group.read(new DataInputStream(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return group;
    }

    public void savePlayer(String username, Permission group) {
        if (username != null && !username.isEmpty() && group != null) {
            File file = new File(root, "players/" + username + ".dat");
            try {
                if (!file.exists()) {
                    FileUtils.forceMkdirParent(file);
                    file.createNewFile();
                }
                group.write(new DataOutputStream(new FileOutputStream(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveGroup(String groupname, Permission group) {
        if (groupname != null && !groupname.isEmpty() && group != null) {
            File file = new File(root, "groups/" + groupname + ".dat");
            try {
                if (!file.exists()) {
                    FileUtils.forceMkdirParent(file);
                    file.createNewFile();
                }
                group.write(new DataOutputStream(new FileOutputStream(file)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportPlayerJson(String username, Permission player) {
        if (username != null && !username.isEmpty() && player != null) {
            try {
                FileUtils.writeStringToFile(new File(root, "players/" + username + ".json"), GSON.toJson(player), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exportGroupJson(String groupname, Permission group) {
        if (groupname != null && !groupname.isEmpty() && group != null) {
            try {
                FileUtils.writeStringToFile(new File(root, "groups/" + groupname + ".json"), GSON.toJson(group), "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Permission importGroupJson(String groupname) {
        if (groupname != null && !groupname.isEmpty()) {
            try {
                return GSON.fromJson(FileUtils.readFileToString(new File(root, "groups/" + groupname + ".json"), "UTF-8"), Permission.class);
            } catch (IOException ignored) {
            }
        }
        return null;
    }

/*
    private static File root = new File("data");
    private static File gRoot = new File("data/group");

    @Nonnull
    public static PlayerPerm get(@Nonnull EntityPlayer player) {
        PlayerPerm permission = new PlayerPerm();
        try {
            permission.read(new DataInputStream(new FileInputStream(new File(root, player.getName()))));
        } catch (IOException e) {
            FPManager.LOGGER.info(player.getName() + "'s data read failed or doesn't exist!");
        }
        return permission;
    }

    public static void save(String player, PlayerPerm permission) {
        if (permission == null) permission = new PlayerPerm();
        try {
            permission.write(new DataOutputStream(FileUtils.openOutputStream(new File(root, player))));
        } catch (IOException e) {
            e.printStackTrace();
            FPManager.LOGGER.error(player + "'s data file save/write failed!");
        }
    }

    public static void save(@Nonnull EntityPlayer player, PlayerPerm permission) {
        save(player.getName(), permission);
    }

    public static void init(File directory) {
        root = new File(directory, Constants.MOD_ID + "/data");
        gRoot = new File(root, "group");
    }

    public static void saveGroups(Group base, HashMap<String, Group> groups) {
        if (base == null) base = new Group();
        try {
            base.write(new DataOutputStream(FileUtils.openOutputStream(new File(gRoot, "default"))));
        } catch (IOException e) {
            e.printStackTrace();
            FPManager.LOGGER.error("default group data file save/write failed!");
        }
        if (groups != null && groups.size() > 0) {
            for (String cat : groups.keySet()) {
                Group group = groups.get(cat);
                if (group != null) {
                    try {
                        group.write(new DataOutputStream(FileUtils.openOutputStream(new File(gRoot, cat))));
                    } catch (IOException e) {
                        FPManager.LOGGER.error(cat + " group data file save/write failed!");
                    }
                }
            }
        }
    }

    public static Group getBase() {
        Group base = new Group();
        try {
            base.read(new DataInputStream(new FileInputStream(new File(gRoot, "default"))));
        } catch (IOException e) {
            FPManager.LOGGER.info("Default group data read failed or doesn't exist!");
        }
        return base;
    }

    public static HashMap<String, Group> getGroups() {
        HashMap<String, Group> groups = new HashMap<>();
        File[] files = gRoot.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                Group group = new Group();
                try {
                    group.read(new DataInputStream(new FileInputStream(file)));
                } catch (IOException e) {
                    FPManager.LOGGER.info(file.getName() + " read failed!");
                }
                groups.put(file.getName(), group);
            }
        }
        return groups;
    }

*/
}
