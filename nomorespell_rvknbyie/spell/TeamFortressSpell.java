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

public class TeamFortressSpell
extends Spell {
    public static final String SPELL_ID = "team_fortress";

    public TeamFortressSpell() {
        super(SPELL_ID, "Team Fortress", "Create a protective dome granting powerful buffs to all allies inside.", Spell.SpellRank.C, Spell.SpellCategory.SUPPORT, 900, 3);
    }

    @Override
    public void cast(World world, PlayerEntity player, ItemStack bookStack) {
        if (world.isClient) {
            return;
        }
        SpellVisualsManager.startTeamFortress(player);
    }
}

