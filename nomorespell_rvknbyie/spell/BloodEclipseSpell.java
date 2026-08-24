/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.StatusEffectInstance
 *  net.minecraft.StatusEffects
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 *  net.minecraft.SoundCategory
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.StatusEffectInstance;
import net.minecraft.StatusEffects;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.minecraft.SoundCategory;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellVisualsManager;

public class BloodEclipseSpell
extends Spell {
    public static final String SPELL_ID = "blood_eclipse";

    public BloodEclipseSpell() {
        super(SPELL_ID, "Blood Eclipse", "Summons a swirling red-and-black magic circle that fires a dense bloodflame laser, igniting everything caught in its path.", Spell.SpellRank.A, Spell.SpellCategory.ATTACK, 500, 8);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5918, this.scaleDurationForDomain(player, 300), 0, false, true, true));
        SpellVisualsManager.startBloodEclipse(player);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), Nomorespell.BLOOD_ECLIPSE_BEAM_SOUND, SoundCategory.field_15248, 0.9f, 0.8f);
    }
}

