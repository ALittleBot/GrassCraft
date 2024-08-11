package top.alittlebot.grass_craft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.effect.GrassEffects;

public class GrassItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrassCraft.MOD_ID);

    public static final String GRASS_STICK_ID = "grass_stick";
    public static final String STAFF_OF_GRASS_ID = "staff_of_grass";
    public static final String GRASS_BALL_ID = "grass_ball";
    public static final String VANILLA_ID = "vanilla";
    public static final String VANILLA_INGOT_ID = "vanilla_ingot";
    public static final String VANILLA_GLOVE_ID = "vanilla_glove";
    public static final String VANILLA_PUREE_ID = "vanilla_puree";

    public static final DeferredHolder<Item, Item> GRASS_STICK_ITEM;
    public static final DeferredHolder<Item, Item> STAFF_OF_GRASS_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_BALL_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_INGOT_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_TNT_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_GLOVE_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_PUREE_ITEM;

    static {
        GRASS_STICK_ITEM = ITEMS.register(GRASS_STICK_ID, () -> new Item(new Item.Properties()));
        STAFF_OF_GRASS_ITEM = ITEMS.register(STAFF_OF_GRASS_ID, () -> new StaffOfGrassItem(new Item.Properties()));
        GRASS_BALL_ITEM = ITEMS.register(GRASS_BALL_ID, () -> new GrassBallItem(new Item.Properties()));
        VANILLA_ITEM = ITEMS.register(VANILLA_ID, () -> new Item(new Item.Properties()));
        VANILLA_INGOT_ITEM = ITEMS.register(VANILLA_INGOT_ID, () -> new Item(new Item.Properties()));
        VANILLA_GLOVE_ITEM = ITEMS.register(VANILLA_GLOVE_ID, () -> new VanillaGloveItem(new Item.Properties()));
        VANILLA_PUREE_ITEM = ITEMS.register(VANILLA_PUREE_ID, () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .usingConvertsTo(Items.BOWL)
                .nutrition(6)
                .saturationModifier(7.2F)
                .alwaysEdible()
                .effect(() -> new MobEffectInstance(GrassEffects.GROW_GRASS, 20 * 30, 0), 1.0F)
                .build())));

        GRASS_TNT_ITEM = ITEMS.register(GrassBlocks.GRASS_TNT_ID, () -> new BlockItem(GrassBlocks.GRASS_TNT_BLOCK.get(), new Item.Properties()));
    }
}
