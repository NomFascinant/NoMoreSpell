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

public class TitansBlessingSpell
extends Spell {
    public static final String SPELL_ID = "titans_blessing";

    public TitansBlessingSpell() {
        super(SPELL_ID, "Titan's Blessing", "Transform allies into unstoppable titans with overwhelming power.", Spell.SpellRank.B, Spell.SpellCategory.SUPPORT, 1800, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startTitansBlessing(player);
    }
}

