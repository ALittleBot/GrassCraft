package top.alittlebot.grass_craft.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.entity.GrassEntity;

public class GrassItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrassCraft.MOD_ID);

    public static final String GRASS_STICK_ID = "grass_stick";
    public static final String STAFF_OF_GRASS_ID = "staff_of_grass";
    public static final String GRASS_BALL_ID = "grass_ball";
    public static final String VANILLA_ID = "vanilla";
    public static final String VANILLA_INGOT_ID = "vanilla_ingot";
    public static final String VANILLA_GLOVE_ID = "vanilla_glove";
    public static final String VANILLA_PUREE_ID = "vanilla_puree";
    public static final String GRASS_MOB_SPAWN_EGG_ID = "grass_mob_spawn_egg";
    public static final String GRASS_ON_A_STICK_ID = "grass_on_a_stick";
    public static final String GRASS_FISH_ID = "grass_fish";
    public static final String GRASS_HAT_ID = "grass_hat";
    public static final String GRASS_LLAMA_ID = "grass_llama_spawn_egg";

    public static final DeferredHolder<Item, Item> GRASS_STICK_ITEM;
    public static final DeferredHolder<Item, Item> STAFF_OF_GRASS_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_BALL_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_INGOT_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_GLOVE_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_PUREE_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_MOB_SPAWN_EGG_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_ON_A_STICK_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_FISH_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_HAT_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_LLAMA_SPAWN_EGG_ITEM;

    public static final DeferredHolder<Item, Item> GRASS_TNT_ITEM;
    public static final DeferredHolder<Item, Item> WEEDS_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_ROD_ITEM;

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
                .effect(() -> new MobEffectInstance(GrassEffects.GRASS_POISONING, 20 * 30, 1), 1.0F)
                .build())));

        GRASS_MOB_SPAWN_EGG_ITEM = ITEMS.register(GRASS_MOB_SPAWN_EGG_ID, () -> new DeferredSpawnEggItem(GrassEntity.GRASS_MOB_ENTITY, 0x395832, 0x98D982, new Item.Properties()));
        GRASS_ON_A_STICK_ITEM = ITEMS.register(GRASS_ON_A_STICK_ID, () -> new FoodOnAStickItem<>((new Item.Properties()).durability(25), GrassEntity.GRASS_MOB_ENTITY.get(), 7));
        GRASS_FISH_ITEM = ITEMS.register(GRASS_FISH_ID, () -> new Item(new Item.Properties()));  // 这是什么? 我也不知道 (●'◡'●)

        GRASS_TNT_ITEM = ITEMS.register(GrassBlocks.GRASS_TNT_ID, () -> new BlockItem(GrassBlocks.GRASS_TNT_BLOCK.get(), new Item.Properties()));
        WEEDS_ITEM = ITEMS.register(GrassBlocks.WEEDS_ID, () -> new BlockItem(GrassBlocks.WEEDS_BLOCK.get(), new Item.Properties()));
        VANILLA_ROD_ITEM = ITEMS.register("vanilla_rod", () -> new VanillaRodItem(GrassBlocks.VANILLA_ROD_BLOCK.get(), new Item.Properties()));
        GRASS_HAT_ITEM = ITEMS.register(GRASS_HAT_ID, () -> new GrassHatItem(new Item.Properties().stacksTo(1)));
        GRASS_LLAMA_SPAWN_EGG_ITEM = ITEMS.register(GRASS_LLAMA_ID, () -> new DeferredSpawnEggItem(GrassEntity.GRASS_LLAMA_ENTITY, 0x207E14, 0xB3FAB6, new Item.Properties()));
    }
}
