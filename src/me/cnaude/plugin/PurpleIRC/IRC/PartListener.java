/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.cnaude.plugin.PurpleIRC.IRC;

import java.util.regex.Matcher;
import me.cnaude.plugin.PurpleIRC.PurpleBot;
import me.cnaude.plugin.PurpleIRC.PIRCMain;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PartEvent;

/**
 *
 * @author cnaude
 */
public class PartListener extends ListenerAdapter {
    
    PIRCMain plugin;
    PurpleBot ircBot;

    public PartListener(PIRCMain plugin, PurpleBot ircBot) {
        this.plugin = plugin;
        this.ircBot = ircBot;
    }
    
    @Override
    public void onPart(PartEvent event) {
        Channel channel = event.getChannel();
        User user = event.getUser();        
        
        if (!ircBot.botChannels.contains(channel.getName())) {
            return;
        }
        if (ircBot.enabledMessages.get(channel.getName()).contains("irc-part")) {
            plugin.getServer().broadcast(plugin.colorConverter.ircColorsToGame(Matcher.quoteReplacement(plugin.ircPart)
                    .replaceAll("%NAME%", user.getNick())
                    .replaceAll("%CHANNEL%", channel.getName())), "irc.message.part");
        }
    }
}
