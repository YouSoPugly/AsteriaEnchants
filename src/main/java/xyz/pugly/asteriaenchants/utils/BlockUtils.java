package xyz.pugly.asteriaenchants.utils;

import org.bukkit.block.Block;

import java.util.Collection;
import java.util.HashSet;

public class BlockUtils {

    public static Collection<Block> getCardinalRelatives(Block block) {
        HashSet<Block> blocks = new HashSet<>();

        blocks.add(block.getRelative(1, 0, 0));
        blocks.add(block.getRelative(-1, 0, 0));
        blocks.add(block.getRelative(0, 1, 0));
        blocks.add(block.getRelative(0, -1, 0));
        blocks.add(block.getRelative(0, 0, 1));
        blocks.add(block.getRelative(0, 0, -1));

        return blocks;
    }

    public static Collection<Block> getDiagonalRelatives(Block block) {
        HashSet<Block> blocks = new HashSet<>();

        blocks.add(block.getRelative(1, 1, 0));
        blocks.add(block.getRelative(-1, 1, 0));
        blocks.add(block.getRelative(1, -1, 0));
        blocks.add(block.getRelative(-1, -1, 0));

        blocks.add(block.getRelative(1, 0, 1));
        blocks.add(block.getRelative(-1, 0, 1));
        blocks.add(block.getRelative(1, 0, -1));
        blocks.add(block.getRelative(-1, 0, -1));

        blocks.add(block.getRelative(0, 1, 1));
        blocks.add(block.getRelative(0, -1, 1));
        blocks.add(block.getRelative(0, 1, -1));
        blocks.add(block.getRelative(0, -1, -1));

        return blocks;
    }

    public static Collection<Block> getRelativeBlocks(Block block) {
        HashSet<Block> blocks = new HashSet<>();

        blocks.addAll(getCardinalRelatives(block));
        blocks.addAll(getDiagonalRelatives(block));

        return blocks;
    }

}
