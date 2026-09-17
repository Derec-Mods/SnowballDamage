package io.github.derexxd.snowballdamage.mixin;

import io.github.derexxd.snowballdamage.SnowballDamage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EggEntity.class)
public class EggEntityMixin {
    @ModifyArg(method = "onEntityHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), index = 1)
    private float snowballdamage$damage(float amount) {
        return SnowballDamage.resolveDamage((Entity) (Object) this, amount);
    }
}
