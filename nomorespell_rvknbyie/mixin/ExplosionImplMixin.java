/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.BlockPos
 *  net.minecraft.ExplosionImpl
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package nomorespell_rvknbyie.mixin;

import java.util.List;
import net.minecraft.BlockPos;
import net.minecraft.ExplosionImpl;
import nomorespell_rvknbyie.spell.DomainExpansionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ExplosionImpl.class})
public class ExplosionImplMixin {
    @Inject(method={"destroyBlocks"}, at={@At(value="HEAD")})
    private void nomorespell$protectDomainBlocks(List<BlockPos> positions, CallbackInfo ci) {
        positions.removeIf(this::nomorespell$isProtectedDomainBlock);
    }

    @Unique
    private boolean nomorespell$isProtectedDomainBlock(BlockPos pos) {
        return DomainExpansionManager.isAnyProtectedDomainBlock(pos);
    }
}

