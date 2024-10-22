package xyz.pugly.asteriaenchants.enchants.tool.pickaxe;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Priority;
import xyz.pugly.asteriaenchants.enchants.Trigger;
import xyz.pugly.asteriaenchants.events.AEBlockBreakEvent;

import java.util.Collection;
import java.util.HashSet;

import static xyz.pugly.asteriaenchants.utils.BlockUtils.getRelativeBlocks;

public class VeinMinerEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("veinminer"), Trigger.BLOCK_BREAK);

    private static final int BLOCKS_PER_LEVEL = 4;

    @Override
    public void breakTrigger(AEBlockBreakEvent event, int level) {
        Block block = event.getBlock();

        if (!isApplicable(block)) {
            return;
        }

        Collection<Block> blocksToBreak = getVein(block, level*BLOCKS_PER_LEVEL);

        for (Block b : blocksToBreak) {
            breakBlock(b, event.getPlayer());
        }
    }

    private Collection<Block> getVein(Block origin, int count) {
        HashSet<Block> vein = new HashSet<>();
        getVein(origin, vein, count-1);
        return vein;
    }

    private Collection<Block> getVein(Block block, Collection<Block> vein, int count) {
        if (count <= 0)
            return vein;

        vein.add(block);

        Collection<Block> relatives = getRelativeBlocks(block);
        for (Block b : relatives) {
            if (vein.contains(b))
                continue;

            if (b.getType() == block.getType()) {
                getVein(b, vein, count-1);
            }
        }
        return vein;
    }

    private boolean isApplicable(Block b) {
        Material m = b.getType();
        return Tag.COAL_ORES.isTagged(m)
                || Tag.COPPER_ORES.isTagged(m)
                || Tag.IRON_ORES.isTagged(m)
                || Tag.GOLD_ORES.isTagged(m)
                || Tag.REDSTONE_ORES.isTagged(m)
                || Tag.LAPIS_ORES.isTagged(m)
                || Tag.DIAMOND_ORES.isTagged(m)
                || Tag.EMERALD_ORES.isTagged(m)
                || m.equals(Material.NETHER_GOLD_ORE)
                || m.equals(Material.NETHER_QUARTZ_ORE)
                || m.equals(Material.ANCIENT_DEBRIS);
    }

}
