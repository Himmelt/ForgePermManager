package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;

public class PermGroup {

    private Node root;
    private String name;
    private HashSet<PermGroup> parents;

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        if (parents != null) {
            for (PermGroup permGroup : parents) {
                if (permGroup.hasNodes(nodes)) return true;
            }
        }
        return root != null && root.hasNodes(nodes);
    }
}
