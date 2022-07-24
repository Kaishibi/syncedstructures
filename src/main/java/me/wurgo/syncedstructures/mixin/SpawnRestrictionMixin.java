package me.wurgo.syncedstructures.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnRestriction.class)
public abstract class SpawnRestrictionMixin {
    @Shadow
    private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {}

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/SpawnRestriction;register(Lnet/minecraft/entity/EntityType;Lnet/minecraft/entity/SpawnRestriction$Location;Lnet/minecraft/world/Heightmap$Type;Lnet/minecraft/entity/SpawnRestriction$SpawnPredicate;)V",ordinal = 9))
    private static <T extends Entity> void e(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate){
        register((EntityType<? extends MobEntity>) type, location, heightmapType, (type1, world, spawnReason, pos, random) -> true);
    }
}
