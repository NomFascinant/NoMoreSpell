/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.World
 */
package nomorespell_rvknbyie.spell;

import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.World;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellVisualsManager;

public class AnnihilationBeamSpell
extends Spell {
    public static final String SPELL_ID = "annihilation_beam";

    public AnnihilationBeamSpell() {
        super(SPELL_ID, "Annihilation Beam", "Apocalyptic laser that annihilates everything in its path. Takes 90 seconds to charge.", Spell.SpellRank.S, Spell.SpellCategory.ATTACK, 3600, 10);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startAnnihilationBeam(player);
    }
}

