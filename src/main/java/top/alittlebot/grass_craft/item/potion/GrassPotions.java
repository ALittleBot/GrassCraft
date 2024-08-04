package top.alittlebot.grass_craft.item.potion;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.effect.GrassEffects;

public class GrassPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.Items.create(Registries.POTION, GrassCraft.MOD_ID);

    public static final String POTION_KEY = "item.minecraft.potion.effect.";
    public static final String SPLASH_POTION_KEY = "item.minecraft.splash_potion.effect.";
    public static final String LINGERING_POTION_KEY = "item.minecraft.lingering_potion.effect.";
    public static final String GRASS_POTION_ID = "grass_potion";

    public static final Holder<Potion> GRASS_POTION;

    static {
        GRASS_POTION = POTIONS.register(GRASS_POTION_ID, () -> new Potion(new MobEffectInstance(GrassEffects.GROW_GRASS, 3600)));
    }
}
