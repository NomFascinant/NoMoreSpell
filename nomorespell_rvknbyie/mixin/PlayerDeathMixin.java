/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.DamageSource
 *  net.minecraft.LivingEntity
 *  net.minecraft.ServerPlayerEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nomorespell_rvknbyie.mixin;

import net.minecraft.DamageSource;
import net.minecraft.LivingEntity;
import net.minecraft.ServerPlayerEntity;
import nomorespell_rvknbyie.spell.SpellVisualsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LivingEntity.class})
public class PlayerDeathMixin {
    @Inject(method={"onDeath"}, at={@At(value="HEAD")})
    private void nomorespell$recordDeath(DamageSource source, CallbackInfo ci) {
        PlayerDeathMixin playerDeathMixin = this;
        if (playerDeathMixin instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity)playerDeathMixin;
            if (!player.getWorld().isClient) {
                SpellVisualsManager.recordPlayerDeath(player);
            }
        }
    }
}

