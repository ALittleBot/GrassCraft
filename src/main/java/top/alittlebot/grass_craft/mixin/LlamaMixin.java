package top.alittlebot.grass_craft.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Variant;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.alittlebot.grass_craft.entity.GrassBallEntity;
import top.alittlebot.grass_craft.entity.GrassEntity;

@Mixin(Llama.class)
public abstract class LlamaMixin extends AbstractChestedHorse implements VariantHolder<Variant>, RangedAttackMob {
    protected LlamaMixin(EntityType<? extends AbstractChestedHorse> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "spit", at = @At("HEAD"), cancellable = true)
    public void spit(LivingEntity target, CallbackInfo ci) {
        if (this.getType() == GrassEntity.GRASS_LLAMA_ENTITY.get()) {
            ci.cancel();
            GrassBallEntity grassBallEntity = new GrassBallEntity(
                    this.getX() - (double)(this.getBbWidth() + 1.0F) * 0.5 * (double) Mth.sin(this.yBodyRot * 0.017453292F),
                    this.getEyeY() - 0.10000000149011612,
                    this.getZ() + (double)(this.getBbWidth() + 1.0F) * 0.5 * (double)Mth.cos(this.yBodyRot * 0.017453292F
                    ),
                    this.level());
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333) - grassBallEntity.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2) * 0.20000000298023224;
            grassBallEntity.shoot(d0, d1 + d3, d2, 1.5F, 10.0F);
            if (!this.isSilent()) {
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LLAMA_SPIT, this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            }

            this.level().addFreshEntity(grassBallEntity);
        }
    }
}
