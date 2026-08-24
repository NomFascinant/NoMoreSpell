/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.DamageSource
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package nomorespell_rvknbyie.mixin;

import net.minecraft.DamageSource;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import nomorespell_rvknbyie.spell.DomainExpansionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ServerPlayerEntity.class})
public class ServerPlayerEntityDamageMixin {
    @Inject(method={"damage"}, at={@At(value="HEAD")}, cancellable=true)
    private void nomorespell$protectDomainAllies(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (DomainExpansionManager.isFriendlyToAnyDomain((ServerPlayerEntity)this)) {
            cir.setReturnValue((Object)false);
        }
    }
}

