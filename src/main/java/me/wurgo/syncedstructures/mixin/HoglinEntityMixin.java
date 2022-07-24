package me.wurgo.syncedstructures.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoglinEntity.class)
public abstract class HoglinEntityMixin extends AnimalEntity {
    @Shadow @Final private static TrackedData<Boolean> BABY;

    protected HoglinEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "isImmuneToZombification", at = @At("RETURN"), cancellable = true)
    private void makeHoglinImmune(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(!this.getDataTracker().get(BABY));
    }
}
