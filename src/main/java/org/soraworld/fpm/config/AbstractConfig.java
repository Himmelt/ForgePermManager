package org.soraworld.fpm.config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class AbstractConfig {

    private final Configuration config;

    public AbstractConfig(File file, String version) {
        this.config = new Configuration(file, version);
    }

    public final void load() {
        config.load();
        for (Field field : this.getClass().getDeclaredFields()) {
            int modifier = field.getModifiers();
            if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) continue;
            AvalonProperty property = field.getAnnotation(AvalonProperty.class);
            if (property != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, get(config, property, field.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public final void save() {
        for (Field field : this.getClass().getDeclaredFields()) {
            int modifier = field.getModifiers();
            if (Modifier.isStatic(modifier) || Modifier.isFinal(modifier)) continue;
            AvalonProperty property = field.getAnnotation(AvalonProperty.class);
            if (property != null) {
                try {
                    field.setAccessible(true);
                    set(config, property, field.getType(), field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        config.save();
    }

    private Object get(Configuration config, AvalonProperty pro, Class<?> type) {
        if (type == String.class) {
            return config.get(pro.cat(), pro.key(), pro.defaults(), pro.comment()).getString();
        }
        return null;
    }

    private void set(Configuration config, AvalonProperty pro, Class<?> type, Object value) {
        if (type == String.class) {
            config.get(pro.cat(), pro.key(), pro.defaults(), pro.comment()).set((String) value);
        }
    }

}
