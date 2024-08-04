package top.alittlebot.grass_craft.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassCreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrassCraft.MOD_ID);
    public static final String GRASS_TAB_ID = "item_group." + GrassCraft.MOD_ID + ".name";
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GRASS_TAB;

    static {
        GRASS_TAB = TABS.register(GrassCraft.MOD_ID, () -> CreativeModeTab.builder()
                .title(Component.translatable(GRASS_TAB_ID))
                .icon(() -> new ItemStack(Items.SHORT_GRASS))
                .displayItems((parameters, output) -> {
                    output.accept(GrassItems.GRASS_STICK_ITEM.get());
                    output.accept(GrassItems.GRASS_BALL_ITEM.get());
                    output.accept(GrassItems.STAFF_OF_GRASS_ITEM.get());
                }).build());
    }
}
