/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.TooltipDisplayComponent
 *  net.minecraft.Formatting
 *  net.minecraft.Hand
 *  net.minecraft.ActionResult
 *  net.minecraft.LivingEntity
 *  net.minecraft.PlayerEntity
 *  net.minecraft.Item
 *  net.minecraft.Item$class_1793
 *  net.minecraft.Item$class_9635
 *  net.minecraft.ItemStack
 *  net.minecraft.TooltipType
 *  net.minecraft.World
 *  net.minecraft.Text
 *  net.minecraft.ServerPlayerEntity
 */
package nomorespell_rvknbyie.item;

import java.util.function.Consumer;
import net.minecraft.TooltipDisplayComponent;
import net.minecraft.Formatting;
import net.minecraft.Hand;
import net.minecraft.ActionResult;
import net.minecraft.LivingEntity;
import net.minecraft.PlayerEntity;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.TooltipType;
import net.minecraft.World;
import net.minecraft.Text;
import net.minecraft.ServerPlayerEntity;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellBookData;
import nomorespell_rvknbyie.spell.SpellRegistry;

public class NomorespellItem
extends Item {
    public NomorespellItem(Item.class_1793 settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (hand != Hand.field_5808) {
            return ActionResult.PASS;
        }
        ItemStack stack = user.getStackInHand(hand);
        SpellBookData.initializeIfNeeded(stack);
        if (SpellBookData.getOwnerName(stack).isEmpty()) {
            SpellBookData.setOwnerName(stack, user.getName().getString());
        }
        return ActionResult.PASS;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return stack;
    }

    public static void castSpellFromSlot(World world, PlayerEntity player, ItemStack stack, int slotIndex) {
        String spellId;
        if (world.isClient) {
            return;
        }
        SpellBookData.initializeIfNeeded(stack);
        SpellBookData.synchronizeCooldowns(stack, world.getTime());
        if (Nomorespell.isGuiOpen(player)) {
            return;
        }
        if (!(stack.getItem() instanceof NomorespellItem)) {
            return;
        }
        long currentTick = world.getTime();
        long endTick = SpellBookData.getCooldownEndTick(stack, slotIndex);
        Nomorespell.LOGGER.info("Cooldown check: current={}, end={}, ready={}", new Object[]{currentTick, endTick, currentTick >= endTick});
        if (SpellBookData.isOnCooldown(stack, slotIndex, currentTick)) {
            if (player.hasPermissionLevel(2)) {
                player.sendMessage((Text)Text.literal((String)"Spell on cooldown!").formatted(Formatting.field_1061), true);
            }
            return;
        }
        if (slotIndex == 1) {
            spellId = SpellBookData.getEquippedSlot1(stack);
        } else {
            spellId = SpellBookData.getEquippedSlot2(stack);
            Nomorespell.LOGGER.info("Cast: Reading Slot 2 DataComponent: {}", (Object)spellId);
        }
        if (spellId != null && !spellId.isEmpty() && !SpellBookData.isSpellAvailable(stack, spellId)) {
            SpellBookData.clearEquippedSpell(stack, spellId);
            spellId = "";
        }
        if (spellId == null || spellId.isEmpty()) {
            player.sendMessage((Text)Text.literal((String)("Slot " + slotIndex + " empty")).formatted(Formatting.field_1054), true);
            return;
        }
        Nomorespell.LOGGER.info("Cast: slot {} spellId={}", (Object)slotIndex, (Object)spellId);
        Spell spell = SpellRegistry.getSpell(spellId);
        if (spell == null) {
            if (player.hasPermissionLevel(2)) {
                player.sendMessage((Text)Text.literal((String)("Unknown spell: " + spellId)).formatted(Formatting.field_1061), true);
            }
            return;
        }
        if (!SpellBookData.hasPurchasedSpell(stack, spellId)) {
            if (player.hasPermissionLevel(2)) {
                player.sendMessage((Text)Text.literal((String)"You haven't unlocked this spell!").formatted(Formatting.field_1061), true);
            }
            return;
        }
        SpellBookData.triggerSpellCooldown(stack, spellId, currentTick + (long)spell.getCooldownTicks());
        spell.cast(world, player, stack);
        if (player.hasPermissionLevel(2)) {
            player.sendMessage((Text)Text.literal((String)("Cast " + spell.getName() + "!")).formatted(Formatting.field_1060), true);
        }
    }

    private static boolean isShiftHeld(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
            return serverPlayer.isSneaking();
        }
        return player.isSneaking();
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public void appendTooltip(ItemStack stack, Item.class_9635 context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        SpellBookData.initializeIfNeeded(stack);
        if (type.isAdvanced()) {
            textConsumer.accept((Text)Text.literal((String)""));
            textConsumer.accept((Text)Text.literal((String)("XP: " + SpellBookData.getCurrentXp(stack))).formatted(Formatting.field_1060));
        }
    }
}

