package top.alittlebot.grass_craft.block;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GrassCraft.MOD_ID);

    public static final String GRASS_TNT_ID = "grass_tnt";

    public static final DeferredHolder<Block, Block> GRASS_TNT_BLOCK;

    static {
        GRASS_TNT_BLOCK = BLOCKS.register(GRASS_TNT_ID, () -> new GrassTNTBlock(GrassTNTBlock.Properties.of()));
    }

}
