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

public class MiracleSpell
extends Spell {
    public static final String SPELL_ID = "miracle";

    public MiracleSpell() {
        super(SPELL_ID, "Miracle", "Divine miracle that heals, revives, and purifies all allies in a massive area.", Spell.SpellRank.A, Spell.SpellCategory.HEAL, 2400, 5);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startMiracle(player);
    }
}

