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

public class SacredCircleSpell
extends Spell {
    public static final String SPELL_ID = "sacred_circle";

    public SacredCircleSpell() {
        super(SPELL_ID, "Sacred Circle", "Create a holy circle that heals all players inside over time.", Spell.SpellRank.C, Spell.SpellCategory.HEAL, 500, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startSacredCircle(player);
    }
}

