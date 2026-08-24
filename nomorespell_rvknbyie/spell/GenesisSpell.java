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

public class GenesisSpell
extends Spell {
    public static final String SPELL_ID = "genesis";

    public GenesisSpell() {
        super(SPELL_ID, "Genesis", "Ultimate healing miracle. Creates a garden of life that heals and revives infinitely.", Spell.SpellRank.S, Spell.SpellCategory.HEAL, 4800, 10);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startGenesis(player);
    }
}

