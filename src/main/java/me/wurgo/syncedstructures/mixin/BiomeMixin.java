package me.wurgo.syncedstructures.mixin;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(Biome.class)
public abstract class BiomeMixin {
    @Shadow public abstract void addStructureFeature(ConfiguredStructureFeature<?, ?> configuredStructureFeature);
    @Shadow protected abstract void addSpawn(SpawnGroup group, Biome.SpawnEntry spawnEntry);

    @Inject(method = "<init>(Lnet/minecraft/world/biome/Biome$Settings;)V", at = @At("TAIL"))
    private void addStructures(Biome.Settings settings, CallbackInfo ci) {
        for (Field field : DefaultBiomeFeatures.class.getFields()) {
            if (field.getType() == ConfiguredStructureFeature.class) {
                try {
                    ConfiguredStructureFeature<?, ?> feature = (ConfiguredStructureFeature<?, ?>) field.get(null);
                    if (feature != DefaultBiomeFeatures.NETHER_FOSSIL) {
                        this.addStructureFeature(feature);
                    }
                }
                catch (IllegalAccessException e) { throw new RuntimeException(e); }
            }
        }
    }
}
