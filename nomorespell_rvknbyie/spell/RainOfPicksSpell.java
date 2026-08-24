/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.LivingEntity
 *  net.minecraft.HostileEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.SoundEvents
 *  net.minecraft.SoundCategory
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.LivingEntity;
import net.minecraft.HostileEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.SoundEvents;
import net.minecraft.SoundCategory;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellVisualsManager;

public class RainOfPicksSpell
extends Spell {
    public static final String SPELL_ID = "rain_of_picks";

    public RainOfPicksSpell() {
        super(SPELL_ID, "Rain of Picks", "Call down a heavy crimson-silver storm of homing pickaxes that bend through the air and tear into nearby enemies.", Spell.SpellRank.C, Spell.SpellCategory.ATTACK, 500, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        int homingProjectiles = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(15.0, 8.0, 15.0), entity -> {
            if (entity == null || !entity.isAlive() || entity == player) {
                return false;
            }
            if (entity instanceof PlayerEntity) {
                PlayerEntity otherPlayer = (PlayerEntity)entity;
                return !otherPlayer.isSpectator() && !SpellVisualsManager.areAllied(player, otherPlayer);
            }
            return entity instanceof HostileEntity;
        }).isEmpty() ? 0 : 21;
        SpellVisualsManager.startRainOfPicks(player, 30, homingProjectiles);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_14896, SoundCategory.field_15248, 0.9f, 1.35f);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_14675, SoundCategory.field_15248, 0.8f, 0.7f);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.field_14833, SoundCategory.field_15248, 0.45f, 1.45f);
    }
}

