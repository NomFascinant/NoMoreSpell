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

public class ChainLightningSpell
extends Spell {
    public static final String SPELL_ID = "chain_lightning";

    public ChainLightningSpell() {
        super(SPELL_ID, "Chain Lightning", "Lightning that chains between enemies, dealing decreasing damage.", Spell.SpellRank.C, Spell.SpellCategory.ATTACK, 160, 4);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startChainLightning(player);
    }
}

