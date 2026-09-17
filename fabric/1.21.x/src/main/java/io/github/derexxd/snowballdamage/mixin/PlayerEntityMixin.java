package io.github.derexxd.snowballdamage.mixin;

import io.github.derexxd.snowballdamage.SnowballDamage;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void snowballdamage$allowZero(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (amount != 0.0F || !SnowballDamage.allowsZeroDamage(source)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (this.isInvulnerableTo(source)
                || (player.getAbilities().invulnerable && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY))
                || this.isDead()) {
            cir.setReturnValue(false);
            return;
        }
        cir.setReturnValue(super.damage(source, amount));
    }
}
