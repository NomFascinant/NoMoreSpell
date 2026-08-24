/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.DamageSource
 *  net.minecraft.LivingEntity
 *  net.minecraft.ServerWorld
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nomorespell_rvknbyie.mixin;

import net.minecraft.DamageSource;
import net.minecraft.LivingEntity;
import net.minecraft.ServerWorld;
import nomorespell_rvknbyie.spell.CombatXpTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={LivingEntity.class})
public class LivingEntityDamageMixin {
    @Inject(method={"damage"}, at={@At(value="RETURN")})
    private void nomorespell$onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Boolean)cir.getReturnValue()).booleanValue()) {
            return;
        }
        LivingEntityDamageMixin livingEntityDamageMixin = this;
        if (livingEntityDamageMixin instanceof LivingEntity) {
            LivingEntity target = (LivingEntity)livingEntityDamageMixin;
            CombatXpTracker.handleSpellKill(target, source);
        }
    }
}

