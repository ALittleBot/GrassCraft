package top.alittlebot.grass_craft.item;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.Items.createItems(GrassCraft.MOD_ID);

    public static final String GRASS_STICK_ID = "grass_stick";
    public static final String STAFF_OF_GRASS_ID = "staff_of_grass";
    public static final String GRASS_BALL_ID = "grass_ball";
    public static final String VANILLA_ID = "vanilla";

    public static final DeferredHolder<Item, Item> GRASS_STICK_ITEM;
    public static final DeferredHolder<Item, Item> STAFF_OF_GRASS_ITEM;
    public static final DeferredHolder<Item, Item> GRASS_BALL_ITEM;
    public static final DeferredHolder<Item, Item> VANILLA_ITEM;

    static {
        GRASS_STICK_ITEM = ITEMS.register(GRASS_STICK_ID, () -> new Item(new Item.Properties()));
        STAFF_OF_GRASS_ITEM = ITEMS.register(STAFF_OF_GRASS_ID, () -> new StaffOfGrassItem(new Item.Properties()));
        GRASS_BALL_ITEM = ITEMS.register(GRASS_BALL_ID, () -> new GrassBallItem(new Item.Properties()));
        VANILLA_ITEM = ITEMS.register(VANILLA_ID, () -> new Item(new Item.Properties()));
    }
}
