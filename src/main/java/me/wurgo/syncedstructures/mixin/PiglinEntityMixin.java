package me.wurgo.syncedstructures.mixin;

import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinEntity.class)
public class PiglinEntityMixin {
    @Inject(method = "isImmuneToZombification", at = @At("RETURN"), cancellable = true)
    private void disablePigmen(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
