package org.soraworld.fpm.core;

import java.util.HashMap;
import java.util.HashSet;

public class AbstractPermission {

    private String parent;
    private HashSet<String> subs;
    private HashMap<String, String> values;
    private Node root;

}
