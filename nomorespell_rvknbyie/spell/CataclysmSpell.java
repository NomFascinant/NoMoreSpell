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

public class CataclysmSpell
extends Spell {
    public static final String SPELL_ID = "cataclysm";

    public CataclysmSpell() {
        super(SPELL_ID, "Cataclysm", "Ultimate destruction spell. Obliterates everything in a massive radius.", Spell.SpellRank.A, Spell.SpellCategory.ATTACK, 600, 5);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startCataclysm(player);
    }
}

