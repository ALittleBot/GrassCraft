package top.alittlebot.grass_craft.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassEffects {
    public static final String GROW_GRASS_ID = "grow_grass";
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, GrassCraft.MODID);

    public static final DeferredHolder<MobEffect, GrowGrassEffect> GROW_GRASS = EFFECTS.register(GROW_GRASS_ID, () -> new GrowGrassEffect(
            MobEffectCategory.BENEFICIAL, 0x98D982
    ));

    public static void registerItems(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }
}
