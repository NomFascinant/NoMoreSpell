/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Entity
 *  net.minecraft.LivingEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ProjectileUtil
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.Box
 *  net.minecraft.HitResult$class_240
 *  net.minecraft.Vec3d
 *  net.minecraft.ServerWorld
 *  net.minecraft.SoundCategory
 *  net.minecraft.RaycastContext
 *  net.minecraft.RaycastContext$class_242
 *  net.minecraft.RaycastContext$class_3960
 *  net.minecraft.BlockHitResult
 *  net.minecraft.EntityHitResult
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.Entity;
import net.minecraft.LivingEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ProjectileUtil;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.Box;
import net.minecraft.HitResult;
import net.minecraft.Vec3d;
import net.minecraft.ServerWorld;
import net.minecraft.SoundCategory;
import net.minecraft.RaycastContext;
import net.minecraft.BlockHitResult;
import net.minecraft.EntityHitResult;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.entity.SpellFireballEntity;
import nomorespell_rvknbyie.spell.Spell;

public class FireballSpell
extends Spell {
    public static final String SPELL_ID = "fireball";

    public FireballSpell() {
        super(SPELL_ID, "Fireball", "A basic fire projectile that ignites and damages targets. Deals 5 damage.", Spell.SpellRank.E, Spell.SpellCategory.ATTACK, 20, 0);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(), Nomorespell.FIREBALL_CAST_SOUND, SoundCategory.field_15248, 0.6f, 1.0f);
        if (!(world instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld)world;
        Vec3d eyePos = player.getEyePos();
        Vec3d look = player.getRotationVec(1.0f);
        Vec3d spawnPos = eyePos.add(look.multiply(0.2));
        float damage = this.scaleAmountForDomain(player, 5.0f);
        LivingTargetHit closeTarget = this.findCloseTarget(serverWorld, player, eyePos, look);
        if (closeTarget != null) {
            SpellFireballEntity.applyDirectHit(serverWorld, (LivingEntity)player, closeTarget.target(), closeTarget.hitPos(), damage, 4);
            return;
        }
        SpellFireballEntity fireball = new SpellFireballEntity(serverWorld, (LivingEntity)player);
        fireball.setSpellPower(damage, 4);
        fireball.init(spawnPos, look);
        serverWorld.spawnEntity((Entity)fireball);
    }

    private LivingTargetHit findCloseTarget(ServerWorld world, PlayerEntity player, Vec3d eyePos, Vec3d look) {
        Entity class_12972;
        Vec3d end = eyePos.add(look.multiply(7.0));
        EntityHitResult entityHit = ProjectileUtil.raycast((Entity)player, (Vec3d)eyePos, (Vec3d)end, (Box)player.getBoundingBox().stretch(look.multiply(7.0)).expand(1.5), entity -> {
            LivingEntity living;
            return entity instanceof LivingEntity && (living = (LivingEntity)entity).isAlive() && entity != player;
        }, (double)49.0);
        if (entityHit != null && (class_12972 = entityHit.getEntity()) instanceof LivingEntity) {
            LivingEntity living = (LivingEntity)class_12972;
            BlockHitResult blockHit = world.raycast(new RaycastContext(eyePos, entityHit.getPos(), RaycastContext.class_3960.field_17558, RaycastContext.class_242.field_1348, (Entity)player));
            if (blockHit.getType() == HitResult.class_240.field_1333) {
                return new LivingTargetHit(living, entityHit.getPos());
            }
        }
        return null;
    }

    private record LivingTargetHit(LivingEntity target, Vec3d hitPos) {
    }
}

