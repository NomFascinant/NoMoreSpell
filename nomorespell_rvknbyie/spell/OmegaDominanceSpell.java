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

public class OmegaDominanceSpell
extends Spell {
    public static final String SPELL_ID = "omega_dominance";

    public OmegaDominanceSpell() {
        super(SPELL_ID, "Omega Dominance", "Ultimate battlefield control. Absolute power over all allies and enemies.", Spell.SpellRank.S, Spell.SpellCategory.SUPPORT, 6000, 10);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startOmegaDominance(player);
    }
}

