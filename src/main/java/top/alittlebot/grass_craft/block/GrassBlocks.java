package top.alittlebot.grass_craft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GrassCraft.MOD_ID);

    public static final String GRASS_TNT_ID = "grass_tnt";
    public static final String WEEDS_ID = "weeds";
    public static final String VANILLA_ROD = "vanilla_rod";

    public static final DeferredHolder<Block, Block> GRASS_TNT_BLOCK;
    public static final DeferredHolder<Block, Block> WEEDS_BLOCK;
    public static final DeferredHolder<Block, Block> VANILLA_ROD_BLOCK;  // 这一定很有趣, 吧……

    static {
        GRASS_TNT_BLOCK = BLOCKS.register(GRASS_TNT_ID, () -> new GrassTNTBlock(GrassTNTBlock.Properties.of().sound(SoundType.GRASS)));
        WEEDS_BLOCK = BLOCKS.register(WEEDS_ID, () -> new WeedsBlock(Block.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.DESTROY)));
        VANILLA_ROD_BLOCK = BLOCKS.register(VANILLA_ROD, () -> new VanillaRodBlock(BlockBehaviour.Properties.of().forceSolidOff().instabreak().lightLevel(p_187435_ -> 14).sound(SoundType.WOOD).noOcclusion()));
    }

}
