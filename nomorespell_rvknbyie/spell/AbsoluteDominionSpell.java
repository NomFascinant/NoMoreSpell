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

public class AbsoluteDominionSpell
extends Spell {
    public static final String SPELL_ID = "absolute_dominion";

    public AbsoluteDominionSpell() {
        super(SPELL_ID, "Absolute Dominion", "Dominate the battlefield. Empower allies and crush all enemies.", Spell.SpellRank.A, Spell.SpellCategory.SUPPORT, 1800, 5);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startAbsoluteDominion(player);
    }
}

