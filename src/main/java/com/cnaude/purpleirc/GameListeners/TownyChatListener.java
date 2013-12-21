/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnaude.purpleirc.GameListeners;

import com.cnaude.purpleirc.PurpleBot;
import com.cnaude.purpleirc.PurpleIRC;
import com.palmergames.bukkit.TownyChat.event.TownyChatEvent;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

/**
 *
 * @author cnaude
 */
public class TownyChatListener implements Listener {

    final PurpleIRC plugin;

    /**
     *
     * @param plugin
     */
    public TownyChatListener(PurpleIRC plugin) {
        this.plugin = plugin;
    }

    /**
     *
     * @param event
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onTownyChatEvent(TownyChatEvent event) {
        Resident resident = event.getResident();

        plugin.logDebug("TC Format: " + event.getFormat());

        Player player = event.getEvent().getPlayer();
        if (player.hasPermission("irc.message.gamechat")) {
            for (PurpleBot ircBot : plugin.ircBots.values()) {
                if (ircBot.isConnected()) {
                    ircBot.townyChat(player, resident, event.getMessage());
                }
            }
        }
    }
}
