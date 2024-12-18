package top.alittlebot.grass_craft.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.alittlebot.grass_craft.GrassCraft;

import java.util.function.Supplier;

public class GrassEntity {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, GrassCraft.MOD_ID);

    public static final String GRASS_TNT_ENTITY_ID = "grass_tnt_entity";
    public static final String GRASS_MOB_ENTITY_ID = "grass_mob";
    public static final String GRASS_BALL_ENTITY_ID = "grass_ball";
    public static final String GRASS_LLAMA_ENTITY_ID = "grass_llama";

    public static final Supplier<EntityType<GrassTNTEntity>> GRASS_TNT_ENTITY;
    public static final Supplier<EntityType<GrassMobEntity>> GRASS_MOB_ENTITY;
    public static final Supplier<EntityType<GrassBallEntity>> GRASS_BALL_ENTITY;
    public static final Supplier<EntityType<GrassLlamaEntity>> GRASS_LLAMA_ENTITY;

    static {
        GRASS_TNT_ENTITY = ENTITIES.register(GRASS_TNT_ENTITY_ID, () -> EntityType.Builder.<GrassTNTEntity>of(GrassTNTEntity::new, MobCategory.MISC)
                        .fireImmune()
                        .sized(0.98F, 0.98F)
                        .eyeHeight(0.15F)
                        .clientTrackingRange(10)
                        .build(GRASS_TNT_ENTITY_ID));

        GRASS_MOB_ENTITY = ENTITIES.register(GRASS_MOB_ENTITY_ID, () -> EntityType.Builder.of(GrassMobEntity::new, MobCategory.CREATURE)
                .fireImmune()
                .sized(1.0F, 1.0F)
                .build(GRASS_TNT_ENTITY_ID));
        GRASS_BALL_ENTITY = ENTITIES.register(GRASS_BALL_ENTITY_ID, () -> EntityType.Builder.<GrassBallEntity>of(GrassBallEntity::new, MobCategory.MISC)
                .sized(0.25F, 0.25F)
                .clientTrackingRange(4)
                .updateInterval(10)
                .build(GRASS_BALL_ENTITY_ID));
        GRASS_LLAMA_ENTITY = ENTITIES.register(GRASS_LLAMA_ENTITY_ID, () -> EntityType.Builder.of(GrassLlamaEntity::new, MobCategory.CREATURE)
                .sized(0.9F, 1.87F)
                .eyeHeight(1.7765F)
                .passengerAttachments(new Vec3(0.0, 1.37, -0.3))
                .clientTrackingRange(10)
                .build(GRASS_LLAMA_ENTITY_ID));
    }
}
