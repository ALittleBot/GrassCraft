package top.alittlebot.grass_craft;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.datagen.GrassBlockModelProvider;
import top.alittlebot.grass_craft.datagen.GrassItemModelProvider;
import top.alittlebot.grass_craft.datagen.GrassLanguageProvider;
import top.alittlebot.grass_craft.datagen.GrassRecipeProvider;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.renderer.GrassTNTRenderer;
import top.alittlebot.grass_craft.event.GrassRegisterBrewingRecipesEvent;
import top.alittlebot.grass_craft.item.GrassItems;
import top.alittlebot.grass_craft.item.potion.GrassPotions;

@Mod(GrassCraft.MOD_ID)
public class GrassCraft {

    public static final String MOD_ID = "grass_craft";

    public GrassCraft(IEventBus modEventBus) {
        GrassItems.ITEMS.register(modEventBus);
        GrassBlocks.BLOCKS.register(modEventBus);
        GrassEntity.ENTITIES.register(modEventBus);
        GrassEffects.EFFECTS.register(modEventBus);
        GrassPotions.POTIONS.register(modEventBus);
        GrassCreativeTab.TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(GrassRegisterBrewingRecipesEvent.class);

        modEventBus.addListener(GrassLanguageProvider::onGatherData);
        modEventBus.addListener(GrassBlockModelProvider::onGatherData);
        modEventBus.addListener(GrassItemModelProvider::onGatherData);
        modEventBus.addListener(GrassRecipeProvider::onGatherData);
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(GrassEntity.GRASS_TNT_ENTITY.get(), GrassTNTRenderer::new);
        }
    }
}
