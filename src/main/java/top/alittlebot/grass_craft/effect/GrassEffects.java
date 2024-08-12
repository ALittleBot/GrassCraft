package top.alittlebot.grass_craft.effect;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

public class GrassEffects {
    public static final String GROW_GRASS_ID = "grow_grass";
    public static final String TO_GRASS_ID = "to_grass";
    public static final String GRASS_POISONING_ID = "grass_poisoning";

    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, GrassCraft.MOD_ID);

    public static final DeferredHolder<MobEffect, GrowGrassEffect> GROW_GRASS;
    public static final DeferredHolder<MobEffect, ToGrassEffect> TO_GRASS;
    public static final DeferredHolder<MobEffect, GrassPoisoning> GRASS_POISONING;

    static {
         GROW_GRASS = EFFECTS.register(GROW_GRASS_ID, () -> new GrowGrassEffect(MobEffectCategory.BENEFICIAL, 0x98D982));
         TO_GRASS = EFFECTS.register(TO_GRASS_ID, () -> new ToGrassEffect(MobEffectCategory.BENEFICIAL, 0x35502D));
         GRASS_POISONING = EFFECTS.register(GRASS_POISONING_ID, () -> new GrassPoisoning(MobEffectCategory.HARMFUL, 0x395832));
    }
}
