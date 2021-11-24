// inspired by https://github.com/AzisabaNetwork/LeonGunWar

package com.temps1101.yamledit.yamledit.utils;

import org.bukkit.ChatColor;

import java.text.MessageFormat;

public class Chat {
    public static String f(String text, boolean prefix, Object... args) {
        String out = prefix ? "&l&7[&9Yaml&3Edit&7]&r: " + text : text;
        return MessageFormat.format(ChatColor.translateAlternateColorCodes('&', out), args);
    }
}
