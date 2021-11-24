package com.temps1101.yamledit.yamledit;

import com.temps1101.yamledit.yamledit.utils.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class YamlEditCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        args = parseArguments(args);

        if (args.length == 0) {
            sender.sendMessage(Chat.f("&c====&l&9Yaml&3Edit&r&c====", false));
            sender.sendMessage(Chat.f("version: v{0}", false, YamlEdit.getPlugin().getDescription().getVersion()));
            sender.sendMessage(Chat.f("website: https://github.com/temps1101/YamlEdit", false));
            sender.sendMessage(Chat.f("&c=====&3usage&c=====", false));
            sender.sendMessage(Chat.f("&3Select a file you want to edit", false));
            sender.sendMessage(Chat.f("&f/edit select [file directory]", false));
            sender.sendMessage(Chat.f("&3Edit dictionary", false));
            sender.sendMessage(Chat.f("&f/edit m [key] [value]", false));
            sender.sendMessage(Chat.f("&3Append value to the selected key", false));
            sender.sendMessage(Chat.f("&f/edit a [key] [value]", false));
            sender.sendMessage(Chat.f("&3Append new dictionary to the selected key", false));
            sender.sendMessage(Chat.f("&f/edit a [key1] [key2] [value]", false));
            sender.sendMessage(Chat.f("", false));
            sender.sendMessage(Chat.f("See the website for more infos!", false));

            return true;
        }

        if (args[0].equalsIgnoreCase("select")) {
            if (args.length == 2) {
                File configfile = new File(YamlEdit.getPlugin().getDataFolder().getParent(), args[1]);

                if (configfile.exists()) {
                    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(configfile);

                    if (yamlConfiguration.getKeys(true).size() == 0) {
                        sender.sendMessage(Chat.f("&cThe selected file {0} seems to have an empty content or failed to load the file.", true, args[1]));
                    }

                    YamlEdit.getSelectedYamlCache().put((Entity) sender, configfile);
                    sender.sendMessage(Chat.f("Selected {0} !", true, args[1]));

                    return true;
                } else {
                    sender.sendMessage(Chat.f("&cThe File {0} does not exist", true, args[1]));
                    return true;
                }
            } else {
                sender.sendMessage(Chat.f("&cThe statement is wrong", true));
                sender.sendMessage(Chat.f("&3=====&3usage&c=====", false));
                sender.sendMessage(Chat.f("&f/edit select [file directory]", false));
                sender.sendMessage(Chat.f("", false));
                sender.sendMessage(Chat.f("See the website for more infos!", false));

                return true;
            }

        } else if (args[0].equalsIgnoreCase("m")) {
            if (args.length == 3) {
                File yamlFilePath = YamlEdit.getSelectedYamlCache().get((Entity) sender);

                if (yamlFilePath != null) {
                    YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(yamlFilePath);
                    String key = args[1];
                    String value = args[2];

                    if (yamlConfiguration.contains(key)) {
                        yamlConfiguration.set(key, value);

                        try {
                            yamlConfiguration.save(yamlFilePath);
                            sender.sendMessage(Chat.f("&cModified {0}'s value to {1} file {2}", true, yamlFilePath, value, key));
                            return true;
                        } catch (IOException exception) {
                            sender.sendMessage(Chat.f("&cError occurred when saving change. See the server's log for more information", true, key));
                            exception.printStackTrace();
                            return true;
                        }

                    } else {
                        sender.sendMessage(Chat.f("&cThe key {0} is not contained in the selected YAML file", true, key));
                        return true;
                    }
                } else {
                    sender.sendMessage(Chat.f("&cIt seems that you have not selected the file to edit yet. Please select the file then run this command again", true));
                    return true;
                }
            } else {
                sender.sendMessage(Chat.f("&cThe statement of modify mode is wrong!", true));
                sender.sendMessage(Chat.f("&3=====&3usage&c=====", false));
                sender.sendMessage(Chat.f("&f/edit m [key] [value]", false));
                sender.sendMessage(Chat.f("", false));
                sender.sendMessage(Chat.f("See the website for more infos!", false));
                return true;
            }
        } else if (args[0].equalsIgnoreCase("a")) {

        } else {
            sender.sendMessage(Chat.f("&cThe first argument is wrong!", true));
            sender.sendMessage(Chat.f("&c=====&3usage&c=====", false));
            sender.sendMessage(Chat.f("&3Select a file you want to edit", false));
            sender.sendMessage(Chat.f("&f/edit select [file directory]", false));
            sender.sendMessage(Chat.f("&3Edit dictionary", false));
            sender.sendMessage(Chat.f("&f/edit m [key] [value]", false));
            sender.sendMessage(Chat.f("&3Append value to the selected key", false));
            sender.sendMessage(Chat.f("&f/edit a [key] [value]", false));
            sender.sendMessage(Chat.f("&3Append new dictionary to the selected key", false));
            sender.sendMessage(Chat.f("&f/edit a [key1] [key2] [value]", false));
            sender.sendMessage(Chat.f("", false));
            sender.sendMessage(Chat.f("See the website for more infos!", false));

            return false;
        }

        return true;
    }

    private final Collection<Character> QUOTATION_MARKS = Arrays.asList('\'', '\"');
    private final char SPACE = ' ';

    private String[] parseArguments(String[] arguments) {
        char[] chars = String.join(" ", arguments).toCharArray();
        ArrayList<String> parsed_arguments = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();

        boolean in_quote = false;
        int counter = 0;

        for (char c : chars) {
            counter++;

            if (!in_quote && c == SPACE) {
                addAndClear(stringBuilder, parsed_arguments);
                continue;
            }

            if (QUOTATION_MARKS.contains(c)) {
                in_quote = !in_quote;
                continue;
            }

            stringBuilder.append(c);

            if (counter >= chars.length) {
                addAndClear(stringBuilder, parsed_arguments);
            }
        }

        return parsed_arguments.toArray(new String[0]);
    }

    private void addAndClear(StringBuilder stringBuilder, ArrayList<String> arguments) {
        arguments.add(stringBuilder.toString().trim());
        stringBuilder.setLength(0);
    }
}
