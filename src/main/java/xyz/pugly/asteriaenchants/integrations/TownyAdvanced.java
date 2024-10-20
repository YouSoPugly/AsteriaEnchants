package xyz.pugly.asteriaenchants.integrations;

import com.palmergames.bukkit.towny.object.TownyPermission;
import com.palmergames.bukkit.towny.utils.PlayerCacheUtil;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TownyAdvanced {

    public static boolean canBreakBlock(Block block, Player player) {
        return PlayerCacheUtil.getCachePermission(player, block.getLocation(), block.getType(), TownyPermission.ActionType.DESTROY);
    }

}
