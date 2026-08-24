/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Formatting
 *  net.minecraft.StatusEffectInstance
 *  net.minecraft.StatusEffects
 *  net.minecraft.LivingEntity
 *  net.minecraft.HostileEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.Box
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.ServerWorld
 *  net.minecraft.ServerPlayerEntity
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 */
package nomorespell_rvknbyie.spell;

import java.util.ArrayList;
import net.minecraft.Formatting;
import net.minecraft.StatusEffectInstance;
import net.minecraft.StatusEffects;
import net.minecraft.LivingEntity;
import net.minecraft.HostileEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.Box;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.ServerWorld;
import net.minecraft.ServerPlayerEntity;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.spell.DomainExpansionManager;
import nomorespell_rvknbyie.spell.Spell;

public class DomainExpansionSpell
extends Spell {
    public static final String SPELL_ID = "domain_expansion";
    private static final double HORIZONTAL_RADIUS = 30.0;
    private static final double VERTICAL_RADIUS = 20.0;

    public DomainExpansionSpell() {
        super(SPELL_ID, "Domain Expansion", "Creates a void domain that captures enemies, empowers the caster, and punishes escape.", Spell.SpellRank.S, Spell.SpellCategory.UTILITY, 3600, 12);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (!(world instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld)world;
        Vec3d center = player.getPos();
        Box searchBox = new Box(center.x - 30.0, center.y - 20.0, center.z - 30.0, center.x + 30.0, center.y + 20.0, center.z + 30.0);
        ArrayList<LivingEntity> targets = new ArrayList<LivingEntity>();
        for (LivingEntity entity : serverWorld.getEntitiesByClass(LivingEntity.class, searchBox, living -> this.isValidTarget(player, (LivingEntity)living, center))) {
            targets.add(entity);
        }
        if (!(player instanceof ServerPlayerEntity)) {
            return;
        }
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
        if (!DomainExpansionManager.start(serverWorld, serverPlayer, targets)) {
            serverPlayer.sendMessage((Text)Text.literal((String)"Another domain is too close by.").formatted(Formatting.field_1061), true);
            return;
        }
        serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5910, 400, 3, false, true));
        serverPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5924, 300, 1, false, true));
        serverWorld.playSound(null, center.x, center.y, center.z, Nomorespell.DOMAIN_EXPANSION_SOUND, SoundCategory.field_15248, 0.9f, 0.9f);
        serverWorld.playSound(null, center.x, center.y, center.z, SoundEvents.field_23118, SoundCategory.field_15248, 0.8f, 0.6f);
    }

    private boolean isValidTarget(PlayerEntity caster, LivingEntity target, Vec3d center) {
        double dz;
        if (target == null || !target.isAlive() || target == caster) {
            return false;
        }
        double dx = target.getX() - center.x;
        if (dx * dx + (dz = target.getZ() - center.z) * dz > 900.0) {
            return false;
        }
        if (Math.abs(target.getY() - center.y) > 20.0) {
            return false;
        }
        if (target instanceof PlayerEntity) {
            PlayerEntity playerTarget = (PlayerEntity)target;
            return !DomainExpansionManager.areAllied(caster, playerTarget);
        }
        return target instanceof HostileEntity;
    }
}

