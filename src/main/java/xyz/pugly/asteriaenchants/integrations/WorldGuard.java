package xyz.pugly.asteriaenchants.integrations;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class WorldGuard {

    public static boolean canBreakBlock(Block block, Player player) {
        LocalPlayer lp = WorldGuardPlugin.inst().wrapPlayer(player);
        Location loc = BukkitAdapter.adapt(block.getLocation());

        RegionContainer container = com.sk89q.worldguard.WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();

        return query.testState(loc, lp, Flags.BLOCK_BREAK);
    }

}
