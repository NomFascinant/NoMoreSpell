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

public class VerdantHaloSpell
extends Spell {
    public static final String SPELL_ID = "verdant_halo";

    public VerdantHaloSpell() {
        super(SPELL_ID, "Verdant Halo", "Summon a luminous green halo that follows you, healing allies and punishing nearby hostiles.", Spell.SpellRank.B, Spell.SpellCategory.HEAL, 900, 6);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.field_5918, this.scaleDurationForDomain(player, 300), 0, false, true, true));
        SpellVisualsManager.startVerdantHalo(player, this.scaleAmountForDomain(player, 2.0f), this.scaleAmountForDomain(player, 5.0f), this.scaleDurationForDomain(player, 400), this.scaleDurationForDomain(player, 600));
        world.playSound(null, player.getX(), player.getY(), player.getZ(), Nomorespell.GROUP_HEAL_SOUND, SoundCategory.field_15248, 0.85f, 1.2f);
    }
}

