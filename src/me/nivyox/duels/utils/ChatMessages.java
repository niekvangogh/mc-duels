package me.nivyox.duels.utils;

import org.bukkit.ChatColor;

/**
 * Created by Niek on 24-1-2017.
 */
public class ChatMessages {

    public static String game_no_args = ChatColor.RED + "Invalid arguments! Please use /game [GAMETYPE] [PLAYERNAME]!";
    public static String game_created_game = ChatColor.GREEN + "Created a game for you!";

    public static String game_countdown = ChatColor.GREEN + "The game will start in %%TIME%% seconds!";
    public static String game_player_killed = ChatColor.GREEN + "%%KILLER%% has killed %%KILLED%%!";
    public static String no_permissions = ChatColor.RED + "You do not have enough permission to do that!";
    public static String no_player = ChatColor.RED + "You must be a player in order to do that!";
    public static String player_is_ingame = ChatColor.RED + "The selected player is ingame!";
    public static String no_gametype_like_that = ChatColor.RED + "There is no Game Type like that!";
    public static String player_is_not_ingame = ChatColor.RED + "The selected player is not in a game!";
    public static String player_is_not_online = ChatColor.RED + "Player is not online!";
    public static String you_are_in_game = ChatColor.RED + "You can not do this action if you are in a game!";
    public static String spectate_usage = ChatColor.GREEN + "/spectate [USERNAME] - Spectate a user in a game";
}
