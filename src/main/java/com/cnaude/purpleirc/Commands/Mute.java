/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnaude.purpleirc.Commands;

import com.cnaude.purpleirc.PurpleBot;
import com.cnaude.purpleirc.PurpleIRC;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 *
 * @author cnaude
 */
public class Mute {

    private final PurpleIRC plugin;

    public Mute(PurpleIRC plugin) {
        this.plugin = plugin;
    }

    public void dispatch(CommandSender sender, String[] args) {
        if (args.length >= 4) {
            String bot = args[1];
            String channel = args[2];
            if (plugin.ircBots.containsKey(bot)) {
                for (int i = 2; i < args.length; i++) {
                    // #channel, user
                    plugin.ircBots.get(bot).mute(channel, sender, args[i]);
                }
            } else {
                sender.sendMessage(plugin.invalidBotName.replace("%BOT%", bot));
            }
        } else {
            sender.sendMessage(ChatColor.WHITE + "Usage: " + ChatColor.GOLD + "/irc mute [bot] [channel] [user(s)]");
        }
    }
}
