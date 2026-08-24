/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  java.lang.MatchException
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.minecraft.Formatting
 *  net.minecraft.Util
 *  net.minecraft.PlayerEntity
 *  net.minecraft.ItemStack
 *  net.minecraft.ParticleEffect
 *  net.minecraft.ParticleTypes
 *  net.minecraft.Vec3d
 *  net.minecraft.Text
 *  net.minecraft.MinecraftClient
 *  net.minecraft.DrawContext
 *  net.minecraft.SoundEvent
 *  net.minecraft.SoundEvents
 *  net.minecraft.MathHelper
 *  net.minecraft.Element
 *  net.minecraft.Drawable
 *  net.minecraft.PressableWidget
 *  net.minecraft.Screen
 *  net.minecraft.StringVisitable
 *  net.minecraft.Random
 *  net.minecraft.NarrationMessageBuilder
 *  net.minecraft.CustomPayload
 */
package nomorespell_rvknbyie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.Formatting;
import net.minecraft.Util;
import net.minecraft.PlayerEntity;
import net.minecraft.ItemStack;
import net.minecraft.ParticleEffect;
import net.minecraft.ParticleTypes;
import net.minecraft.Vec3d;
import net.minecraft.Text;
import net.minecraft.MinecraftClient;
import net.minecraft.DrawContext;
import net.minecraft.SoundEvent;
import net.minecraft.SoundEvents;
import net.minecraft.MathHelper;
import net.minecraft.Element;
import net.minecraft.Drawable;
import net.minecraft.PressableWidget;
import net.minecraft.Screen;
import net.minecraft.StringVisitable;
import net.minecraft.Random;
import net.minecraft.NarrationMessageBuilder;
import net.minecraft.CustomPayload;
import nomorespell_rvknbyie.Nomorespell;
import nomorespell_rvknbyie.item.NomorespellItem;
import nomorespell_rvknbyie.network.EquipSpellPayload;
import nomorespell_rvknbyie.network.PurchaseSpellPayload;
import nomorespell_rvknbyie.network.SacrificeLevelsPayload;
import nomorespell_rvknbyie.spell.Spell;
import nomorespell_rvknbyie.spell.SpellBookData;
import nomorespell_rvknbyie.spell.SpellRegistry;

@Environment(value=EnvType.CLIENT)
public class SpellBookScreen
extends Screen {
    private static final int MIN_FONT_SIZE = 10;
    private static final int TOOLTIP_DELAY_MS = 500;
    private static final int ENTRY_HEIGHT = 90;
    private static final int SCROLLBAR_WIDTH = 10;
    private static final int MIN_TOUCH_SIZE = 44;
    private final MinecraftClient clientRef;
    private ItemStack bookStack;
    private long openTimeMs;
    private int activeTab = 0;
    private double scrollOffset = 0.0;
    private int cachedLevel;
    private int cachedXp;
    private int cachedSouls;
    private String cachedSlot1;
    private String cachedSlot2;
    private int cachedXpNeeded;
    private int lastSyncSouls;
    private float progressFill = 0.0f;
    private float progressFillTarget = 0.0f;
    private long progressAnimStartMs = 0L;
    private int lastXpSnapshot = 0;
    private int pendingXpPopup = 0;
    private long pendingXpPopupMs = 0L;
    private long xpPopupStartMs = 0L;
    private int xpPopupAmount = 0;
    private long levelFlashStartMs = 0L;
    private TabButton spellsButton;
    private TabButton upgradesButton;
    private TabButton closeButton;
    private long activeHoverIdMs = 0L;
    private String activeHoverId = "";
    private String searchText = "";
    private boolean searchFocused = false;
    private boolean sortAscending = true;
    private boolean ownedOnly = false;
    private long lastSearchEditMs = 0L;
    private long lastScrollUpdateMs = 0L;
    private double scrollVelocity = 0.0;
    private long scrollStopMs = 0L;
    private long entryHoverStartMs = 0L;
    private String hoverSpellId = "";
    private int pendingReplaceSlot = 0;
    private Spell pendingReplaceSpell = null;
    private long slotHighlightSlot1Ms = 0L;
    private long slotHighlightSlot2Ms = 0L;
    private float slotClearFade = 0.0f;
    private long slotClearFadeStartMs = 0L;
    private long purchaseFlashStartMs = 0L;
    private String purchaseFlashId = "";
    private long purchaseErrorStartMs = 0L;
    private String purchaseErrorMessage = "";
    private long equipCheckStartMs = 0L;
    private String equipCheckId = "";
    private final List<Spell> filteredSpells = new ArrayList<Spell>();
    private final Map<String, CachedSpellText> cachedText = new HashMap<String, CachedSpellText>();
    private final List<ClickRegion> clickRegions = new ArrayList<ClickRegion>();
    private final Map<String, Long> clickAnimations = new HashMap<String, Long>();
    private String statusMessage = "";
    private int statusColor = 0xFF5555;
    private long statusStartMs = 0L;
    private int pendingClearSlot = 0;
    private int sacrificeSelectedLevels = 0;
    private int sacrificeCalculatedXp = 0;
    private int sacrificePlayerLevel = 0;
    private int sacrificeMaxLevel = 0;
    private long sacrificeCalcMs = 0L;
    private long sacrificePulseMs = 0L;
    private long sacrificeSliderFocusMs = 0L;
    private boolean sacrificeDragging = false;
    private float sacrificePreviewScale = 1.0f;
    private long sacrificePreviewScaleMs = 0L;
    private long sacrificeSuccessMs = 0L;
    private int sacrificeSliderX = 0;
    private int sacrificeSliderWidth = 0;
    private long soulsPopupStartMs = 0L;
    private int soulsPopupAmount = 0;

    public SpellBookScreen(ItemStack bookStack) {
        super((Text)Text.literal((String)"SSpellBook"));
        this.clientRef = MinecraftClient.getInstance();
        this.bookStack = bookStack;
    }

    private void refreshBookStack() {
        if (this.clientRef == null || this.clientRef.player == null) {
            return;
        }
        ItemStack main = this.clientRef.player.getMainHandStack();
        ItemStack off = this.clientRef.player.getOffHandStack();
        if (main.getItem() instanceof NomorespellItem) {
            this.bookStack = main;
        } else if (off.getItem() instanceof NomorespellItem) {
            this.bookStack = off;
        }
    }

    protected void init() {
        this.openTimeMs = Util.getMeasuringTimeMs();
        this.refreshBookStack();
        SpellBookData.initializeIfNeeded(this.bookStack);
        this.cachedLevel = SpellBookData.getCurrentLevel(this.bookStack);
        this.cachedXp = SpellBookData.getCurrentXp(this.bookStack);
        this.cachedSouls = SpellBookData.getSoulsPoints(this.bookStack);
        this.cachedSlot1 = SpellBookData.getEquippedSlot1(this.bookStack);
        this.cachedSlot2 = SpellBookData.getEquippedSlot2(this.bookStack);
        this.cachedXpNeeded = SpellBookData.getXpNeededForLevel(this.cachedLevel);
        this.lastSyncSouls = this.cachedSouls;
        this.progressFill = this.progressFillTarget = this.cachedXpNeeded > 0 ? (float)this.cachedXp / (float)this.cachedXpNeeded : 0.0f;
        this.lastXpSnapshot = this.cachedXp;
        this.pendingXpPopup = 0;
        this.pendingXpPopupMs = 0L;
        this.xpPopupStartMs = 0L;
        this.xpPopupAmount = 0;
        this.levelFlashStartMs = 0L;
        this.searchText = "";
        this.searchFocused = false;
        this.sortAscending = true;
        this.ownedOnly = false;
        this.scrollOffset = 0.0;
        this.scrollVelocity = 0.0;
        this.purchaseFlashId = "";
        this.purchaseErrorMessage = "";
        this.pendingReplaceSlot = 0;
        this.pendingReplaceSpell = null;
        this.updateFilteredList(true);
        this.sacrificeSelectedLevels = 0;
        this.sacrificeCalculatedXp = 0;
        this.sacrificeMaxLevel = this.sacrificePlayerLevel = this.clientRef.player != null ? this.clientRef.player.experienceLevel : 0;
        this.sacrificeCalcMs = 0L;
        this.sacrificePulseMs = 0L;
        this.sacrificeDragging = false;
        this.sacrificePreviewScale = 1.0f;
        this.sacrificePreviewScaleMs = 0L;
        this.sacrificeSuccessMs = 0L;
        this.addTabButtons();
    }

    private void addTabButtons() {
        this.clearChildren();
        int panelWidth = Math.max(260, Math.round((float)this.width * 0.75f));
        int panelHeight = Math.max(220, Math.round((float)this.height * 0.75f));
        int panelX = (this.width - panelWidth) / 2;
        int panelY = (this.height - panelHeight) / 2;
        int buttonWidth = Math.max(96, Math.round((float)panelWidth * 0.24f));
        int buttonHeight = Math.max(20, Math.round((float)panelHeight * 0.075f));
        int navHeight = Math.max(26, Math.round((float)panelHeight * 0.11f));
        int tabRowWidth = buttonWidth * 2 + 20;
        int tabRowX = panelX + (panelWidth - tabRowWidth) / 2;
        int buttonY = panelY + (navHeight - buttonHeight) / 2;
        int spellsX = tabRowX;
        int upgradesX = tabRowX + buttonWidth + 20;
        this.spellsButton = new TabButton(spellsX, buttonY, buttonWidth, buttonHeight, (Text)Text.literal((String)"SPELL"), () -> this.setActiveTab(0));
        this.upgradesButton = new TabButton(upgradesX, buttonY, buttonWidth, buttonHeight, (Text)Text.literal((String)"UPGRADE"), () -> this.setActiveTab(1));
        this.closeButton = new TabButton(panelX + panelWidth - 22, panelY + 12, 16, 16, (Text)Text.literal((String)"\u00d7"), this::close);
        this.addDrawableChild((Element)this.spellsButton);
        this.addDrawableChild((Element)this.upgradesButton);
        this.addDrawableChild((Element)this.closeButton);
    }

    private void setActiveTab(int tab) {
        this.activeTab = tab;
    }

    public void close() {
        Nomorespell.setGuiOpen((PlayerEntity)this.clientRef.player, false);
        super.close();
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (this.activeTab != 0) {
            return false;
        }
        this.scrollVelocity += -verticalAmount * 14.0;
        this.lastScrollUpdateMs = Util.getMeasuringTimeMs();
        return true;
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean handled = false;
        for (ClickRegion region : this.clickRegions) {
            if (!region.contains(mouseX, mouseY)) continue;
            this.clickAnimations.put(region.id, Util.getMeasuringTimeMs());
            region.onClick.run();
            handled = true;
            break;
        }
        if (this.activeTab == 0) {
            if (!handled) {
                this.searchFocused = false;
            }
            return handled || super.mouseClicked(mouseX, mouseY, button);
        }
        return handled || super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.activeTab == 1 && this.sacrificeDragging) {
            this.updateSacrificeFromMouse(mouseX);
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (this.activeTab == 1 && this.sacrificeDragging) {
            this.sacrificeDragging = false;
            return true;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (this.pendingReplaceSlot > 0 && keyCode == 256) {
            this.pendingReplaceSlot = 0;
            this.pendingReplaceSpell = null;
            return true;
        }
        if (this.pendingClearSlot > 0 && keyCode == 256) {
            this.pendingClearSlot = 0;
            return true;
        }
        if (this.activeTab == 1 && this.sacrificeMaxLevel > 0) {
            if (keyCode == 263) {
                this.updateSacrificeSelection(this.sacrificeSelectedLevels - 1, true);
                return true;
            }
            if (keyCode == 262) {
                this.updateSacrificeSelection(this.sacrificeSelectedLevels + 1, true);
                return true;
            }
            if (keyCode == 266) {
                this.updateSacrificeSelection(this.sacrificeSelectedLevels + 10, true);
                return true;
            }
            if (keyCode == 267) {
                this.updateSacrificeSelection(this.sacrificeSelectedLevels - 10, true);
                return true;
            }
        }
        if (this.activeTab == 0 && this.searchFocused && keyCode == 259 && !this.searchText.isEmpty()) {
            this.searchText = this.searchText.substring(0, this.searchText.length() - 1);
            this.lastSearchEditMs = Util.getMeasuringTimeMs();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int modifiers) {
        if (this.activeTab == 0 && this.searchFocused && (Character.isLetterOrDigit(chr) || Character.isWhitespace(chr) || chr == '-' || chr == '_')) {
            this.searchText = this.searchText + chr;
            this.lastSearchEditMs = Util.getMeasuringTimeMs();
            return true;
        }
        return super.charTyped(chr, modifiers);
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.refreshBookStack();
        long elapsed = Util.getMeasuringTimeMs() - this.openTimeMs;
        float fadeIn = MathHelper.clamp((float)((float)elapsed / 300.0f), (float)0.0f, (float)1.0f);
        int shakeOffsetX = 0;
        int shakeOffsetY = 0;
        if (elapsed < 200L) {
            float shakeStrength = 2.5f * (1.0f - (float)elapsed / 200.0f);
            Random rand = Random.create((long)elapsed);
            shakeOffsetX = Math.round((rand.nextFloat() - 0.5f) * shakeStrength * 2.0f);
            shakeOffsetY = Math.round((rand.nextFloat() - 0.5f) * shakeStrength * 2.0f);
        }
        context.getMatrices().push();
        context.getMatrices().translate((float)shakeOffsetX, (float)shakeOffsetY, 0.0f);
        this.renderBackground(context, mouseX, mouseY, delta);
        int panelWidth = Math.max(260, Math.round((float)this.width * 0.75f));
        int panelHeight = Math.max(220, Math.round((float)this.height * 0.75f));
        int panelX = (this.width - panelWidth) / 2;
        int panelY = (this.height - panelHeight) / 2;
        int borderColor = this.withAlpha(8141549, (int)(200.0f * fadeIn));
        int panelColor = this.withAlpha(0x141414, (int)(230.0f * fadeIn));
        context.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, panelColor);
        context.drawBorder(panelX, panelY, panelWidth, panelHeight, borderColor);
        this.renderTopNavigation(context, panelX, panelY, panelWidth, panelHeight, fadeIn);
        if (this.activeTab == 0) {
            this.renderSpellsPanel(context, panelX, panelY, panelWidth, panelHeight, mouseX, mouseY, fadeIn);
        } else {
            this.renderUpgradesPanel(context, panelX, panelY, panelWidth, panelHeight, fadeIn);
        }
        this.updateScrollPhysics();
        this.renderTopBar(context, fadeIn);
        for (Element drawable : this.children()) {
            if (!(drawable instanceof Drawable)) continue;
            Drawable drawableWidget = (Drawable)drawable;
            drawableWidget.render(context, mouseX, mouseY, delta);
        }
        this.renderTooltips(context, mouseX, mouseY);
        context.getMatrices().pop();
        this.renderGlowOverlay(context, elapsed, fadeIn);
    }

    private void renderTopBar(DrawContext context, float fadeIn) {
        this.spellsButton.setActive(this.activeTab == 0);
        this.upgradesButton.setActive(this.activeTab == 1);
        this.spellsButton.setFade(fadeIn);
        this.upgradesButton.setFade(fadeIn);
        this.closeButton.setFade(fadeIn);
    }

    private boolean isHighContrast() {
        if (this.clientRef == null || this.clientRef.options == null) {
            return false;
        }
        try {
            return (Boolean)this.clientRef.options.getHighContrast().getValue();
        }
        catch (Exception e) {
            return false;
        }
    }

    private void renderSpellsPanel(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        this.clickRegions.clear();
        this.refreshBookStack();
        int navHeight = Math.max(24, Math.round((float)height * 0.1f));
        int controlsHeight = Math.max(24, Math.round((float)height * 0.1f));
        int slotsHeight = Math.max(28, Math.round((float)height * 0.15f));
        int contentY = y + navHeight + controlsHeight;
        int contentHeight = height - navHeight - controlsHeight - slotsHeight;
        int padding = Math.max(8, Math.min(12, width / 40));
        this.renderControlsBar(context, x, y + navHeight, width, controlsHeight, fadeIn);
        this.updateFilteredList(false);
        this.renderTopControls(context, x + padding, y + navHeight + padding, width - padding * 2, controlsHeight - padding * 2, mouseX, mouseY, fadeIn);
        this.renderSpellList(context, x + padding, contentY + padding, width - padding, contentHeight - padding * 2, mouseX, mouseY, fadeIn);
        this.renderSlotsPanel(context, x, y + height - slotsHeight, width, slotsHeight, mouseX, mouseY, fadeIn);
        if (this.pendingClearSlot > 0) {
            this.renderClearConfirm(context, x, y, width, height, mouseX, mouseY, fadeIn);
        }
        if (this.pendingReplaceSlot > 0 && this.pendingReplaceSpell != null) {
            this.renderReplaceConfirm(context, x, y, width, height, mouseX, mouseY, fadeIn);
        }
    }

    public void removed() {
        if (this.clientRef != null && this.clientRef.player != null) {
            Nomorespell.setGuiOpen((PlayerEntity)this.clientRef.player, false);
        }
        super.removed();
    }

    private void renderTopControls(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        int sortWidth;
        int controlHeight = Math.max(18, Math.min(30, height));
        int gap = Math.max(6, width / 50);
        int searchWidth = Math.max(120, (int)((float)width * 0.5f));
        int filterWidth = Math.max(70, width - searchWidth - (sortWidth = Math.max(70, (int)((float)width * 0.22f))) - gap * 2);
        if (filterWidth < 70 && (sortWidth = Math.max(70, width - searchWidth - (filterWidth = 70) - gap * 2)) < 70) {
            sortWidth = 70;
            searchWidth = Math.max(100, width - sortWidth - filterWidth - gap * 2);
        }
        int searchX = x;
        int sortX = x + searchWidth + gap;
        int filterX = sortX + sortWidth + gap;
        int controlY = y + (height - controlHeight) / 2;
        int searchColor = this.withAlpha(2042167, (int)(220.0f * fadeIn));
        int borderColor = this.withAlpha(this.searchFocused ? 8141549 : 4937059, (int)(180.0f * fadeIn));
        context.fill(searchX, controlY, searchX + searchWidth, controlY + controlHeight, searchColor);
        context.drawBorder(searchX, controlY, searchWidth, controlHeight, borderColor);
        String placeholder = "Search spells...";
        String displayText = this.searchText.isEmpty() ? placeholder : this.searchText;
        int textColor = this.searchText.isEmpty() ? 7041664 : 15067115;
        int textY = controlY + (controlHeight - 10) / 2 + 1;
        context.drawText(this.textRenderer, (Text)Text.literal((String)displayText), searchX + 6, textY, this.withAlpha(textColor, (int)(255.0f * fadeIn)), true);
        this.clickRegions.add(new ClickRegion("search", searchX, controlY, searchX + searchWidth, controlY + controlHeight, () -> {
            this.searchFocused = true;
        }));
        if (!this.searchText.isEmpty()) {
            int clearX = searchX + searchWidth - 14;
            int clearY = controlY + 6;
            context.drawText(this.textRenderer, (Text)Text.literal((String)"\u00d7"), clearX + 2, clearY, this.withAlpha(10265519, (int)(255.0f * fadeIn)), true);
            this.clickRegions.add(new ClickRegion("search_clear", clearX - 2, clearY - 2, clearX + 12, clearY + 12, () -> {
                this.searchText = "";
                this.lastSearchEditMs = Util.getMeasuringTimeMs();
            }));
        }
        String sortLabel = this.sortAscending ? "Weakest \u2192 Strongest" : "Strongest \u2192 Weakest";
        this.renderControlButton(context, "sort", sortX, controlY, sortWidth, controlHeight, sortLabel, mouseX, mouseY, fadeIn, () -> {
            this.sortAscending = !this.sortAscending;
            this.updateFilteredList(true);
        });
        String ownedLabel = this.ownedOnly ? "Owned Only" : "Show All";
        this.renderControlButton(context, "owned", filterX, controlY, filterWidth, controlHeight, ownedLabel, mouseX, mouseY, fadeIn, () -> {
            this.ownedOnly = !this.ownedOnly;
            this.updateFilteredList(true);
        });
    }

    private void renderSpellList(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        int listY = y;
        int listHeight = height;
        int totalHeight = this.filteredSpells.size() * 90;
        this.scrollOffset = MathHelper.clamp((double)this.scrollOffset, (double)0.0, (double)Math.max(0, totalHeight - listHeight));
        int startIndex = Math.max(0, (int)(this.scrollOffset / 90.0) - 2);
        int visibleCount = Math.min(this.filteredSpells.size(), listHeight / 90 + 4);
        int endIndex = Math.min(this.filteredSpells.size(), startIndex + visibleCount);
        int clipX = x;
        int clipY = listY;
        int clipWidth = width - 10 - 2;
        int clipHeight = listHeight;
        context.enableScissor(clipX, clipY, clipX + clipWidth, clipY + clipHeight);
        for (int i = startIndex; i < endIndex; ++i) {
            Spell spell = this.filteredSpells.get(i);
            int entryY = listY + i * 90 - (int)this.scrollOffset;
            this.renderSpellEntry(context, spell, x, entryY, clipWidth, 80, mouseX, mouseY, fadeIn);
        }
        context.disableScissor();
        this.renderScrollBar(context, x + width - 10, listY, 10, listHeight, totalHeight, fadeIn);
        if (this.filteredSpells.isEmpty()) {
            String empty = this.searchText.isEmpty() ? "Purchase spells to get started!" : "No spells found matching '" + this.searchText + "'";
            context.drawText(this.textRenderer, (Text)Text.literal((String)empty), x + 12, listY + listHeight / 2 - 4, this.withAlpha(10265519, (int)(220.0f * fadeIn)), true);
        }
    }

    private void renderSpellEntry(DrawContext context, Spell spell, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        if (y + height < 0 || y > this.height) {
            return;
        }
        boolean owned = SpellBookData.hasPurchasedSpell(this.bookStack, spell.getId());
        int rankColor = this.getRankColor(spell.getRank());
        int bgBase = 0x242428;
        int bgColor = owned ? this.adjustBrightness(bgBase, 0.06f) : bgBase;
        boolean hoveredEntry = this.isHovered(x, y, width, height, mouseX, mouseY);
        float hoverProgress = this.getHoverProgress(spell.getId(), hoveredEntry);
        float hoverBoost = 0.08f * hoverProgress;
        int finalBg = this.adjustBrightness(bgColor, hoverBoost);
        context.getMatrices().push();
        float scale = 1.0f + 0.02f * hoverProgress;
        context.getMatrices().translate((float)x + (float)width / 2.0f, (float)y + (float)height / 2.0f, 0.0f);
        context.getMatrices().scale(scale, scale, 1.0f);
        context.getMatrices().translate(-((float)x + (float)width / 2.0f), -((float)y + (float)height / 2.0f), 0.0f);
        context.fill(x, y, x + width, y + height, this.withAlpha(finalBg, (int)(220.0f * fadeIn)));
        context.drawBorder(x, y, width, height, this.withAlpha(3881798, (int)(200.0f * fadeIn)));
        context.fill(x, y, x + 3, y + height, this.withAlpha(rankColor, (int)(160.0f * fadeIn)));
        int leftWidth = Math.max(160, (int)((float)width * 0.66f));
        int rightWidth = width - leftWidth;
        CachedSpellText cached = this.getCachedSpellText(spell, Math.max(160, leftWidth - 20));
        boolean hoveredLeft = this.isHovered(x, y, leftWidth, height, mouseX, mouseY);
        if (hoveredLeft) {
            context.fill(x, y, x + leftWidth, y + height, this.withAlpha(this.adjustBrightness(bgColor, 0.12f), (int)(140.0f * fadeIn)));
        }
        String rankLabel = spell.getRank().getLabel();
        int badgeWidth = 18;
        int badgeHeight = 12;
        int badgeX = x + leftWidth - badgeWidth - 10;
        int badgeY = y + 8;
        context.fill(badgeX, badgeY, badgeX + badgeWidth, badgeY + badgeHeight, this.withAlpha(rankColor, (int)(220.0f * fadeIn)));
        context.drawText(this.textRenderer, (Text)Text.literal((String)rankLabel).formatted(Formatting.field_1067), badgeX + 5, badgeY + 2, this.withAlpha(986895, (int)(240.0f * fadeIn)), true);
        String icon = this.getSpellIcon(spell.getId(), spell.getCategory());
        int iconColor = this.getCategoryTint(spell.getCategory());
        int nameY = y + 10;
        int iconX = x + 12;
        int iconY = nameY;
        int iconWidth = this.textRenderer.getWidth(icon);
        context.drawText(this.textRenderer, (Text)Text.literal((String)icon), iconX, iconY, this.withAlpha(iconColor, (int)(230.0f * fadeIn)), true);
        int nameX = iconX + iconWidth + 6;
        context.drawText(this.textRenderer, (Text)Text.literal((String)cached.name).formatted(Formatting.field_1067), nameX, nameY, this.withAlpha(15067115, (int)(255.0f * fadeIn)), true);
        int descY = y + 28;
        for (int i = 0; i < cached.descriptionLines.size(); ++i) {
            this.renderColoredDescriptionLine(context, cached.descriptionLines.get(i), nameX, descY + i * 10, fadeIn);
        }
        this.renderSpellInfoLine(context, spell, cached, nameX, y + 56, fadeIn);
        if (hoveredLeft) {
            this.updateHoverSpell(spell.getId(), true);
        } else if (this.hoverSpellId.equals(spell.getId())) {
            this.updateHoverSpell(spell.getId(), false);
        }
        int rightX = x + leftWidth;
        int buttonWidth = Math.max(70, rightWidth - 16);
        if (!owned) {
            String buyLabel = "Buy - " + spell.getSoulsCost() + " Soul" + (spell.getSoulsCost() == 1 ? "" : "s");
            this.renderActionButton(context, spell.getId() + "_buy", rightX + 8, y + 20, buttonWidth, 26, buyLabel, 2042167, mouseX, mouseY, fadeIn, () -> this.tryPurchase(spell), false, spell.getId());
            if (this.purchaseFlashId.equals(spell.getId()) && Util.getMeasuringTimeMs() - this.purchaseErrorStartMs < 2000L) {
                context.drawText(this.textRenderer, (Text)Text.literal((String)this.purchaseErrorMessage), rightX - 6, y - 6, this.withAlpha(0xEF4444, (int)(230.0f * fadeIn)), true);
            }
        } else {
            String slot1Label = this.getEquipLabel(spell, 1);
            boolean slot1Disabled = slot1Label.startsWith("Equipped");
            this.renderActionButton(context, spell.getId() + "_slot1", rightX + 8, y + 12, buttonWidth, 24, slot1Label, 2042167, mouseX, mouseY, fadeIn, () -> this.equipSpell(spell, 1), slot1Disabled, spell.getId());
            String slot2Label = this.getEquipLabel(spell, 2);
            boolean slot2Disabled = slot2Label.startsWith("Equipped");
            this.renderActionButton(context, spell.getId() + "_slot2", rightX + 8, y + 44, buttonWidth, 24, slot2Label, 2042167, mouseX, mouseY, fadeIn, () -> this.equipSpell(spell, 2), slot2Disabled, spell.getId());
        }
        if (hoveredEntry) {
            int outline = this.withAlpha(rankColor, (int)(120.0f + 80.0f * hoverProgress));
            context.drawBorder(x, y, width, height, outline);
        }
        context.getMatrices().pop();
    }

    private float getHoverProgress(String id, boolean hovered) {
        long now = Util.getMeasuringTimeMs();
        Long start = this.clickAnimations.get(id + "_hover");
        if (hovered) {
            if (!this.activeHoverId.equals(id)) {
                if (!this.activeHoverId.isEmpty()) {
                    this.clickAnimations.remove(this.activeHoverId + "_hover");
                }
                this.activeHoverId = id;
                this.activeHoverIdMs = now;
            }
            if (start == null) {
                this.clickAnimations.put(id + "_hover", now);
                return 0.0f;
            }
            float t = MathHelper.clamp((float)((float)(now - start) / 150.0f), (float)0.0f, (float)1.0f);
            return t;
        }
        if (start != null) {
            this.clickAnimations.remove(id + "_hover");
        }
        if (this.activeHoverId.equals(id)) {
            this.activeHoverId = "";
            this.activeHoverIdMs = 0L;
        }
        return 0.0f;
    }

    private void renderScrollBar(DrawContext context, int x, int y, int width, int height, int contentHeight, float fadeIn) {
        int hoverMouseY;
        int hoverMouseX;
        boolean show;
        long now = Util.getMeasuringTimeMs();
        boolean bl = show = now - this.scrollStopMs < 1000L || this.scrollVelocity != 0.0;
        if (!show) {
            return;
        }
        context.fill(x, y, x + width, y + height, this.withAlpha(4988309, (int)(200.0f * fadeIn)));
        if (contentHeight <= height) {
            return;
        }
        double ratio = (double)height / (double)contentHeight;
        int barHeight = Math.max(24, (int)((double)height * ratio));
        int barY = y + (int)(this.scrollOffset / (double)(contentHeight - height) * (double)(height - barHeight));
        int thumbColor = this.isHovered(x, barY, width, barHeight, hoverMouseX = (int)(this.clientRef.mouse.getX() * (double)this.width / (double)this.clientRef.getWindow().getScaledWidth()), hoverMouseY = (int)(this.clientRef.mouse.getY() * (double)this.height / (double)this.clientRef.getWindow().getScaledHeight())) ? 9133302 : 8141549;
        context.fill(x, barY, x + width, barY + barHeight, this.withAlpha(thumbColor, (int)(220.0f * fadeIn)));
    }

    private void renderSpellInfoLine(DrawContext context, Spell spell, CachedSpellText cached, int x, int y, float fadeIn) {
        int currentX = x;
        String category = spell.getCategory().name().substring(0, 1) + spell.getCategory().name().substring(1).toLowerCase(Locale.ROOT);
        int categoryColor = this.getCategoryColor(spell.getCategory());
        String categoryLabel = "Cat: ";
        context.drawText(this.textRenderer, (Text)Text.literal((String)categoryLabel), currentX, y, this.withAlpha(9741240, (int)(220.0f * fadeIn)), true);
        context.drawText(this.textRenderer, (Text)Text.literal((String)category), currentX += this.textRenderer.getWidth(categoryLabel), y, this.withAlpha(categoryColor, (int)(230.0f * fadeIn)), true);
        String rankIcon = this.getRankIcon(spell.getRank());
        String rankLabel = "Rnk: " + rankIcon + spell.getRank().getLabel();
        context.drawText(this.textRenderer, (Text)Text.literal((String)rankLabel), currentX += this.textRenderer.getWidth(category) + 10, y, this.withAlpha(this.getRankColor(spell.getRank()), (int)(230.0f * fadeIn)), true);
        String cooldown = "CD: " + spell.getCooldownTicks() / 20 + "s";
        context.drawText(this.textRenderer, (Text)Text.literal((String)cooldown), currentX += this.textRenderer.getWidth(rankLabel) + 10, y, this.withAlpha(16498468, (int)(230.0f * fadeIn)), true);
    }

    private int getCategoryColor(Spell.SpellCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case Spell.SpellCategory.ATTACK -> 0xEF4444;
            case Spell.SpellCategory.SUPPORT -> 2278750;
            case Spell.SpellCategory.HEAL -> 3462041;
            case Spell.SpellCategory.UTILITY -> 6333946;
        };
    }

    private int getCategoryTint(Spell.SpellCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case Spell.SpellCategory.ATTACK -> 0xEF4444;
            case Spell.SpellCategory.SUPPORT -> 2278750;
            case Spell.SpellCategory.HEAL -> 3462041;
            case Spell.SpellCategory.UTILITY -> 6333946;
        };
    }

    private int getCategoryHeaderTint(Spell.SpellCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case Spell.SpellCategory.ATTACK -> 7020319;
            case Spell.SpellCategory.SUPPORT -> 2047834;
            case Spell.SpellCategory.HEAL -> 2047834;
            case Spell.SpellCategory.UTILITY -> 0x1B4B4B;
        };
    }

    private void renderColoredDescriptionLine(DrawContext context, String line, int x, int y, float fadeIn) {
        if (line == null || line.isEmpty()) {
            return;
        }
        String lower = line.toLowerCase(Locale.ROOT);
        context.drawText(this.textRenderer, (Text)Text.literal((String)line), x, y, this.withAlpha(10265519, (int)(220.0f * fadeIn)), false);
        if (lower.contains("damage")) {
            this.highlightWord(context, line, "damage", 0xEF4444, x, y, fadeIn);
        }
        if (lower.contains("cooldown")) {
            this.highlightWord(context, line, "cooldown", 8246268, x, y, fadeIn);
        }
        if (lower.contains("souls")) {
            this.highlightWord(context, line, "souls", 11032055, x, y, fadeIn);
        }
        if (lower.contains("range")) {
            this.highlightWord(context, line, "range", 16096779, x, y, fadeIn);
        }
        if (lower.contains("fire")) {
            this.highlightWord(context, line, "fire", 16436245, x, y, fadeIn);
        }
        if (lower.contains("ignite")) {
            this.highlightWord(context, line, "ignite", 16436245, x, y, fadeIn);
        }
        if (lower.contains("burn")) {
            this.highlightWord(context, line, "burn", 16436245, x, y, fadeIn);
        }
        this.highlightNumbersBeforeKeywords(context, line, x, y, fadeIn);
    }

    private void highlightWord(DrawContext context, String line, String word, int color, int x, int y, float fadeIn) {
        int index = line.toLowerCase(Locale.ROOT).indexOf(word.toLowerCase(Locale.ROOT));
        if (index < 0) {
            return;
        }
        String prefix = line.substring(0, index);
        String target = line.substring(index, index + word.length());
        int highlightX = x + this.textRenderer.getWidth(prefix);
        context.drawText(this.textRenderer, (Text)Text.literal((String)target), highlightX, y, this.withAlpha(color, (int)(240.0f * fadeIn)), false);
    }

    private void highlightNumbersBeforeKeywords(DrawContext context, String line, int x, int y, float fadeIn) {
        String lower = line.toLowerCase(Locale.ROOT);
        String[] keywords = new String[]{"damage", "cooldown", "souls", "range", "s"};
        int[] colors = new int[]{0xEF4444, 8246268, 11032055, 16096779, 8246268};
        for (int i = 0; i < keywords.length; ++i) {
            String number;
            int start;
            String keyword = keywords[i];
            int idx = lower.indexOf(keyword);
            if (idx <= 0) continue;
            for (start = idx - 1; start >= 0 && (Character.isDigit(line.charAt(start)) || line.charAt(start) == '.'); --start) {
            }
            if (++start >= idx || (number = line.substring(start, idx).trim()).isEmpty()) continue;
            String prefix = line.substring(0, start);
            int highlightX = x + this.textRenderer.getWidth(prefix);
            context.drawText(this.textRenderer, (Text)Text.literal((String)number), highlightX, y, this.withAlpha(colors[i], (int)(240.0f * fadeIn)), false);
        }
    }

    private void renderTopNavigation(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int navHeight = Math.max(26, Math.round((float)height * 0.11f));
        int bg = this.withAlpha(2039589, (int)(235.0f * fadeIn));
        context.fill(x, y, x + width, y + navHeight, bg);
        context.fill(x, y + navHeight - 2, x + width, y + navHeight, this.withAlpha(8141549, (int)(200.0f * fadeIn)));
        int padding = Math.max(10, width / 35);
        int soulBoxSize = Math.max(18, Math.min(28, navHeight - 10));
        int soulsLabelWidth = this.textRenderer.getWidth(String.valueOf(this.cachedSouls));
        int soulBoxX = x + width - padding - soulBoxSize - 6 - soulsLabelWidth - 15;
        int soulBoxY = y + (navHeight - soulBoxSize) / 2;
        int soulsBg = this.withAlpha(1120295, (int)(220.0f * fadeIn));
        int soulsBorder = this.withAlpha(9133302, (int)(180.0f * fadeIn));
        int soulsPadding = 6;
        int soulsTextWidth = this.textRenderer.getWidth(String.valueOf(this.cachedSouls));
        int soulBoxWidth = soulBoxSize + soulsPadding + soulsTextWidth + 6;
        context.fill(soulBoxX, soulBoxY, soulBoxX + soulBoxWidth, soulBoxY + soulBoxSize, soulsBg);
        context.drawBorder(soulBoxX, soulBoxY, soulBoxWidth, soulBoxSize, soulsBorder);
        context.drawText(this.textRenderer, (Text)Text.literal((String)"\u2b1f"), soulBoxX + 6, soulBoxY + (soulBoxSize - 10) / 2 + 1, this.withAlpha(15324671, (int)(230.0f * fadeIn)), true);
        String soulsLabel = String.valueOf(this.cachedSouls);
        int soulsX = soulBoxX + soulBoxSize + soulsPadding;
        int soulsY = soulBoxY + (soulBoxSize - 10) / 2 + 1;
        int maxTextWidth = soulBoxWidth - (soulsX - soulBoxX) - 6;
        int textWidth = this.textRenderer.getWidth(soulsLabel);
        if (textWidth > maxTextWidth && maxTextWidth > 0) {
            float scale = MathHelper.clamp((float)((float)maxTextWidth / (float)textWidth), (float)0.6f, (float)1.0f);
            context.getMatrices().push();
            context.getMatrices().translate((float)soulsX, (float)soulsY, 0.0f);
            context.getMatrices().scale(scale, scale, 1.0f);
            context.drawText(this.textRenderer, (Text)Text.literal((String)soulsLabel), 0, 0, this.withAlpha(15324671, (int)(240.0f * fadeIn)), true);
            context.getMatrices().pop();
        } else {
            context.drawText(this.textRenderer, (Text)Text.literal((String)soulsLabel), soulsX, soulsY, this.withAlpha(15324671, (int)(240.0f * fadeIn)), true);
        }
        if (this.lastSyncSouls != this.cachedSouls) {
            int delta = this.cachedSouls - this.lastSyncSouls;
            if (delta > 0) {
                this.soulsPopupAmount = delta;
                this.soulsPopupStartMs = Util.getMeasuringTimeMs();
            }
            this.lastSyncSouls = this.cachedSouls;
        }
    }

    private void renderControlsBar(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int bg = this.withAlpha(2039588, (int)(220.0f * fadeIn));
        context.fill(x, y, x + width, y + height, bg);
        context.fill(x, y + height - 1, x + width, y + height, this.withAlpha(8141549, (int)(180.0f * fadeIn)));
    }

    private void drawPanel(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int bgColor = this.withAlpha(2039589, (int)(220.0f * fadeIn));
        int borderColor = this.withAlpha(4988309, (int)(140.0f * fadeIn));
        context.fill(x, y, x + width, y + height, bgColor);
        context.drawBorder(x, y, width, height, borderColor);
    }

    private void renderSlotsPanel(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        this.refreshBookStack();
        SpellBookData.sanitizeRuntimeData(this.bookStack);
        this.cachedSlot1 = SpellBookData.getEquippedSlot1(this.bookStack);
        this.cachedSlot2 = SpellBookData.getEquippedSlot2(this.bookStack);
        context.fill(x, y, x + width, y + height, this.withAlpha(0x2D2D2D, (int)(220.0f * fadeIn)));
        context.fill(x, y, x + width, y + 2, this.withAlpha(8141549, (int)(200.0f * fadeIn)));
        int padding = Math.max(10, Math.min(16, width / 30));
        int slotGap = Math.max(10, width / 30);
        int slotHeight = Math.max(26, Math.min(32, height - 8));
        int slotWidth = Math.max(120, (width - padding * 2 - slotGap) / 2);
        int slotY = y + (height - slotHeight) / 2;
        int slot1X = x + padding;
        int slot2X = slot1X + slotWidth + slotGap;
        this.renderSlotLine(context, slot1X, slotY, slotWidth, slotHeight, 1, mouseX, mouseY, fadeIn);
        this.renderSlotLine(context, slot2X, slotY, slotWidth, slotHeight, 2, mouseX, mouseY, fadeIn);
        if (!this.statusMessage.isEmpty()) {
            long elapsed = Util.getMeasuringTimeMs() - this.statusStartMs;
            if (elapsed < 2000L) {
                float alpha = 1.0f - MathHelper.clamp((float)((float)elapsed / 2000.0f), (float)0.0f, (float)1.0f);
                int color = this.withAlpha(this.statusColor, (int)(255.0f * alpha));
                context.drawText(this.textRenderer, (Text)Text.literal((String)this.statusMessage), x + width - this.textRenderer.getWidth(this.statusMessage) - 8, y + height - 12, color, true);
            } else {
                this.statusMessage = "";
            }
        }
    }

    private void renderSlotLine(DrawContext context, int x, int y, int width, int height, int slotIndex, int mouseX, int mouseY, float fadeIn) {
        String slotId = slotIndex == 1 ? this.cachedSlot1 : this.cachedSlot2;
        Spell spell = slotId != null && !slotId.isEmpty() ? SpellRegistry.getSpell(slotId) : null;
        long currentTick = this.clientRef != null && this.clientRef.world != null ? this.clientRef.world.getTime() : 0L;
        boolean slotLocked = !SpellBookData.canModifySlot(this.bookStack, slotIndex, currentTick);
        String label = spell != null ? spell.getName() : "No spell equipped";
        int rankColor = spell != null ? this.getRankColor(spell.getRank()) : 7041664;
        String icon = spell != null ? this.getSlotIcon(spell.getCategory()) : "";
        String displayLabel = spell != null ? icon + label : label;
        boolean highlighted = slotIndex == 1 && Util.getMeasuringTimeMs() - this.slotHighlightSlot1Ms < 400L || slotIndex == 2 && Util.getMeasuringTimeMs() - this.slotHighlightSlot2Ms < 400L;
        int baseColor = this.adjustBrightness(0x1F1F1F, this.isHovered(x, y, width, height, mouseX, mouseY) ? 0.15f : 0.0f);
        if (highlighted) {
            baseColor = this.adjustBrightness(baseColor, 0.2f);
        }
        context.fill(x, y, x + width, y + height, this.withAlpha(baseColor, (int)(200.0f * fadeIn)));
        context.drawBorder(x, y, width, height, this.withAlpha(9133302, (int)((float)(highlighted ? 200 : 120) * fadeIn)));
        String prefix = "Slot " + slotIndex + ": ";
        int prefixColor = this.withAlpha(13358581, (int)(220.0f * fadeIn));
        context.drawText(this.textRenderer, (Text)Text.literal((String)prefix), x + 6, y + 8, prefixColor, true);
        int nameX = x + 6 + this.textRenderer.getWidth(prefix);
        String displayName = displayLabel;
        int nameMaxWidth = width - 50 - this.textRenderer.getWidth(prefix);
        if (this.textRenderer.getWidth(displayName) > nameMaxWidth && spell != null) {
            displayName = this.marqueeText(displayLabel, nameMaxWidth);
        }
        float clearFade = this.slotClearFade;
        if (this.slotClearFadeStartMs > 0L) {
            long elapsed = Util.getMeasuringTimeMs() - this.slotClearFadeStartMs;
            if (elapsed < 200L) {
                clearFade = 1.0f - (float)elapsed / 200.0f;
            } else if (elapsed < 400L) {
                clearFade = ((float)elapsed - 200.0f) / 200.0f;
            } else {
                this.slotClearFadeStartMs = 0L;
                clearFade = 1.0f;
            }
        }
        context.drawText(this.textRenderer, (Text)Text.literal((String)displayName), nameX, y + 8, this.withAlpha(rankColor, (int)(230.0f * fadeIn * clearFade)), true);
        int clearX = x + width - 20;
        int clearColor = slotLocked ? 7041664 : 16281969;
        context.drawText(this.textRenderer, (Text)Text.literal((String)(slotLocked ? "\ud83d\udd12" : "\u00d7")), clearX, y + 8, this.withAlpha(clearColor, (int)(230.0f * fadeIn)), true);
        if (!slotLocked) {
            this.clickRegions.add(new ClickRegion("clear_" + slotIndex, clearX - 6, y + 4, clearX + 10, y + 18, () -> {
                this.pendingClearSlot = slotIndex;
            }));
        }
    }

    private void renderClearConfirm(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        long currentTick;
        int boxWidth = 240;
        int boxHeight = 90;
        int boxX = x + (width - boxWidth) / 2;
        int boxY = y + (height - boxHeight) / 2;
        context.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, this.withAlpha(2039589, (int)(235.0f * fadeIn)));
        context.drawBorder(boxX, boxY, boxWidth, boxHeight, this.withAlpha(9133302, (int)(140.0f * fadeIn)));
        String slotId = this.pendingClearSlot == 1 ? this.cachedSlot1 : this.cachedSlot2;
        Spell spell = slotId != null && !slotId.isEmpty() ? SpellRegistry.getSpell(slotId) : null;
        String name = spell != null ? spell.getName() : "Empty";
        long l = currentTick = this.clientRef != null && this.clientRef.world != null ? this.clientRef.world.getTime() : 0L;
        if (!SpellBookData.canModifySlot(this.bookStack, this.pendingClearSlot, currentTick)) {
            this.pendingClearSlot = 0;
            this.statusMessage = "Slot is locked until cooldown ends";
            this.statusColor = 0xEF4444;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.playUiSound(false);
            return;
        }
        context.drawText(this.textRenderer, (Text)Text.literal((String)("Clear " + name + " from Slot " + this.pendingClearSlot + "?")), boxX + 12, boxY + 10, this.withAlpha(15067115, (int)(240.0f * fadeIn)), false);
        int yesX = boxX + 20;
        int noX = boxX + 130;
        this.renderActionButton(context, "confirm_yes", yesX, boxY + 46, 80, 18, "Confirm", 2278750, mouseX, mouseY, fadeIn, () -> {
            if (this.pendingClearSlot == 1) {
                this.cachedSlot1 = "";
                ClientPlayNetworking.send((CustomPayload)new EquipSpellPayload(1, ""));
                this.slotHighlightSlot1Ms = Util.getMeasuringTimeMs();
            } else if (this.pendingClearSlot == 2) {
                this.cachedSlot2 = "";
                ClientPlayNetworking.send((CustomPayload)new EquipSpellPayload(2, ""));
                this.slotHighlightSlot2Ms = Util.getMeasuringTimeMs();
            }
            this.slotClearFadeStartMs = Util.getMeasuringTimeMs();
            this.playUiSound(false);
            this.statusMessage = "Slot cleared";
            this.statusColor = 9741240;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.pendingClearSlot = 0;
        }, false, "confirm_clear");
        this.renderActionButton(context, "confirm_no", noX, boxY + 46, 80, 18, "Cancel", 7041664, mouseX, mouseY, fadeIn, () -> {
            this.pendingClearSlot = 0;
        }, false, "confirm_cancel");
    }

    private void renderActionButton(DrawContext context, String id, int x, int y, int width, int height, String label, int baseColor, int mouseX, int mouseY, float fadeIn, Runnable onClick, boolean disabled, String spellId) {
        long elapsed;
        int phase;
        int effectiveHeight;
        int effectiveWidth = Math.max(width, 44);
        boolean hovered = this.isHovered(x, y, effectiveWidth, effectiveHeight = Math.max(height, 22), mouseX, mouseY);
        float boost = hovered ? 0.08f : 0.0f;
        int color = this.adjustBrightness(baseColor, boost);
        float scale = Math.min(1.02f, this.getClickScale(id));
        if (this.purchaseFlashId.equals(spellId) && this.purchaseFlashStartMs > 0L && (phase = (int)((elapsed = Util.getMeasuringTimeMs() - this.purchaseFlashStartMs) / 100L)) < 6 && phase % 2 == 0) {
            color = 0xEF4444;
            scale = 1.02f;
        }
        if (disabled) {
            color = this.adjustBrightness(baseColor, -0.2f);
        }
        context.getMatrices().push();
        context.getMatrices().translate((float)x + (float)width / 2.0f, (float)y + (float)height / 2.0f, 0.0f);
        context.getMatrices().scale(scale, scale, 1.0f);
        context.getMatrices().translate(-((float)x + (float)width / 2.0f), -((float)y + (float)height / 2.0f), 0.0f);
        context.fill(x, y, x + width, y + height, this.withAlpha(color, (int)(220.0f * fadeIn)));
        int borderAlpha = this.isHighContrast() ? 140 : 100;
        int borderColor = hovered ? 12289788 : 9133302;
        context.drawBorder(x, y, width, height, this.withAlpha(borderColor, (int)((float)borderAlpha * fadeIn)));
        int textX = x + (width - this.textRenderer.getWidth(label)) / 2;
        int textY = y + (height - 10) / 2 + 1;
        context.drawText(this.textRenderer, (Text)Text.literal((String)label), textX, textY, this.withAlpha(disabled ? 10265519 : 0xFFFFFF, (int)(240.0f * fadeIn)), true);
        if (this.equipCheckId.equals(id) && Util.getMeasuringTimeMs() - this.equipCheckStartMs < 500L) {
            context.drawText(this.textRenderer, (Text)Text.literal((String)"\u2713"), x + width - 10, y + 4, this.withAlpha(2278750, (int)(240.0f * fadeIn)), true);
        }
        context.getMatrices().pop();
        if (!disabled) {
            this.clickRegions.add(new ClickRegion(id, x, y, x + width, y + height, onClick));
        }
    }

    private void renderControlButton(DrawContext context, String id, int x, int y, int width, int height, String label, int mouseX, int mouseY, float fadeIn, Runnable onClick) {
        boolean hovered = this.isHovered(x, y, width, height, mouseX, mouseY);
        int baseColor = hovered ? 3621201 : 2042167;
        context.fill(x, y, x + width, y + height, this.withAlpha(baseColor, (int)(220.0f * fadeIn)));
        int borderAlpha = this.isHighContrast() ? 160 : 120;
        context.drawBorder(x, y, width, height, this.withAlpha(12289788, (int)((float)borderAlpha * fadeIn)));
        if (hovered) {
            context.drawBorder(x - 1, y - 1, width + 2, height + 2, this.withAlpha(12289788, (int)(90.0f * fadeIn)));
        }
        int textX = x + (width - this.textRenderer.getWidth(label)) / 2;
        int textY = y + (height - 10) / 2 + 1;
        context.drawText(this.textRenderer, (Text)Text.literal((String)label), textX, textY, this.withAlpha(15067115, (int)(240.0f * fadeIn)), true);
        this.clickRegions.add(new ClickRegion(id, x, y, x + width, y + height, onClick));
    }

    private void tryPurchase(Spell spell) {
        int currentSouls;
        if (SpellBookData.hasPurchasedSpell(this.bookStack, spell.getId())) {
            this.statusMessage = "Already purchased";
            this.statusColor = 9741240;
            this.statusStartMs = Util.getMeasuringTimeMs();
            return;
        }
        this.cachedSouls = currentSouls = SpellBookData.getSoulsPoints(this.bookStack);
        if (this.cachedSouls >= spell.getSoulsCost()) {
            this.cachedSouls -= spell.getSoulsCost();
            SpellBookData.setSoulsPoints(this.bookStack, this.cachedSouls);
            this.updateFilteredList(true);
            ClientPlayNetworking.send((CustomPayload)new PurchaseSpellPayload(spell.getId()));
            this.playUiSound(true);
            this.statusMessage = "Purchased " + spell.getName();
            this.statusColor = 2278750;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.spawnPurchaseParticles();
        } else {
            int missing = spell.getSoulsCost() - this.cachedSouls;
            this.playUiSound(false);
            this.statusMessage = "Need " + missing + " more Souls";
            this.statusColor = 0xEF4444;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.purchaseFlashId = spell.getId();
            this.purchaseFlashStartMs = Util.getMeasuringTimeMs();
            this.purchaseErrorMessage = "Need " + missing + " more Souls";
            this.purchaseErrorStartMs = Util.getMeasuringTimeMs();
        }
    }

    private void equipSpell(Spell spell, int slotIndex) {
        long currentTick;
        String current;
        String string = current = slotIndex == 1 ? this.cachedSlot1 : this.cachedSlot2;
        if (spell.getId().equals(current)) {
            return;
        }
        long l = currentTick = this.clientRef != null && this.clientRef.world != null ? this.clientRef.world.getTime() : 0L;
        if (!SpellBookData.canModifySlot(this.bookStack, slotIndex, currentTick)) {
            this.statusMessage = "Slot is locked until cooldown ends";
            this.statusColor = 0xEF4444;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.playUiSound(false);
            return;
        }
        if (current != null && !current.isEmpty()) {
            this.pendingReplaceSlot = slotIndex;
            this.pendingReplaceSpell = spell;
            return;
        }
        this.applyEquip(spell, slotIndex);
    }

    private String getEquipLabel(Spell spell, int slotIndex) {
        Spell other;
        long currentTick;
        String current = slotIndex == 1 ? this.cachedSlot1 : this.cachedSlot2;
        long l = currentTick = this.clientRef != null && this.clientRef.world != null ? this.clientRef.world.getTime() : 0L;
        if (spell.getId().equals(current)) {
            return "Equipped \u2713";
        }
        if (!SpellBookData.canModifySlot(this.bookStack, slotIndex, currentTick)) {
            return "Cooldown Locked";
        }
        if (current != null && !current.isEmpty() && (other = SpellRegistry.getSpell(current)) != null) {
            return "Replace";
        }
        return "Equip Slot " + slotIndex;
    }

    private int getEquipColor(Spell spell, int slotIndex) {
        long currentTick;
        String current = slotIndex == 1 ? this.cachedSlot1 : this.cachedSlot2;
        long l = currentTick = this.clientRef != null && this.clientRef.world != null ? this.clientRef.world.getTime() : 0L;
        if (spell.getId().equals(current)) {
            return 2278750;
        }
        if (!SpellBookData.canModifySlot(this.bookStack, slotIndex, currentTick)) {
            return 7041664;
        }
        if (current != null && !current.isEmpty()) {
            return 16347926;
        }
        return 8141549;
    }

    private CachedSpellText getCachedSpellText(Spell spell, int maxWidth) {
        boolean truncated;
        CachedSpellText cached = this.cachedText.get(spell.getId());
        if (cached != null && cached.maxWidth == maxWidth) {
            return cached;
        }
        int nameMaxWidth = Math.max(80, Math.min(160, maxWidth / 2));
        String name = this.textRenderer.getWidth(spell.getName()) > nameMaxWidth ? this.textRenderer.trimToWidth(spell.getName(), Math.max(10, nameMaxWidth - 6)) + "..." : spell.getName();
        ArrayList<String> descLines = new ArrayList<String>();
        String description = spell.getDescription();
        String trimmedDesc = description.length() > 80 ? description.substring(0, 80) : description;
        String line1 = this.trimByChars(trimmedDesc, 40);
        String remaining = trimmedDesc.substring(Math.min(trimmedDesc.length(), line1.length())).stripLeading();
        String line2 = remaining.isEmpty() ? "" : this.trimByChars(remaining, 40);
        descLines.add(this.textRenderer.trimToWidth(line1, maxWidth));
        if (!line2.isEmpty()) {
            descLines.add(this.textRenderer.trimToWidth(line2, maxWidth));
        }
        boolean bl = truncated = description.length() > line1.length() + line2.length();
        if (truncated && !descLines.isEmpty()) {
            int idx = descLines.size() - 1;
            String last = (String)descLines.get(idx);
            descLines.set(idx, this.textRenderer.trimToWidth(last + "...", maxWidth));
        }
        String category = spell.getCategory().name().substring(0, 1) + spell.getCategory().name().substring(1).toLowerCase(Locale.ROOT);
        String rankIcon = this.getRankIcon(spell.getRank());
        String infoLine = "Cat: " + category + " | Rnk: " + rankIcon + spell.getRank().getLabel() + " | CD: " + spell.getCooldownTicks() / 20 + "s";
        cached = new CachedSpellText(name, descLines, infoLine, maxWidth);
        cached.truncated = truncated;
        this.cachedText.put(spell.getId(), cached);
        return cached;
    }

    private String trimByChars(String text, int maxChars) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxChars) {
            return text;
        }
        return text.substring(0, maxChars);
    }

    private String getRankIcon(Spell.SpellRank rank) {
        return switch (rank) {
            default -> throw new MatchException(null, null);
            case Spell.SpellRank.E -> "\u25c6";
            case Spell.SpellRank.D -> "\u25c8";
            case Spell.SpellRank.C -> "\u25c9";
            case Spell.SpellRank.B -> "\u2605";
            case Spell.SpellRank.A -> "\u2726";
            case Spell.SpellRank.S -> "\u2739";
        };
    }

    private void updateFilteredList(boolean force) {
        long now = Util.getMeasuringTimeMs();
        if (!force && now - this.lastSearchEditMs < 16L) {
            return;
        }
        this.filteredSpells.clear();
        if (this.bookStack != null) {
            SpellBookData.sanitizeRuntimeData(this.bookStack);
        }
        String query = this.searchText.trim().toLowerCase(Locale.ROOT);
        for (Spell spell : SpellRegistry.getAllSpells()) {
            boolean matches = query.isEmpty() || spell.getName().toLowerCase(Locale.ROOT).contains(query);
            if (!matches || this.ownedOnly && !SpellBookData.hasPurchasedSpell(this.bookStack, spell.getId())) continue;
            this.filteredSpells.add(spell);
        }
        Comparator<Spell> comparator = Comparator.comparing(s -> s.getRank().ordinal()).thenComparing(Spell::getName, String.CASE_INSENSITIVE_ORDER);
        if (!this.sortAscending) {
            comparator = comparator.reversed();
        }
        this.filteredSpells.sort(comparator);
    }

    private void playUiSound(boolean success) {
        if (this.clientRef == null || this.clientRef.player == null) {
            return;
        }
        if (success) {
            this.clientRef.player.playSound((SoundEvent)SoundEvents.field_14725.comp_349(), 0.6f, 1.2f);
        } else {
            this.clientRef.player.playSound((SoundEvent)SoundEvents.field_14624.comp_349(), 0.5f, 0.8f);
        }
    }

    private String marqueeText(String text, int maxWidth) {
        if (this.textRenderer.getWidth(text) <= maxWidth || text.length() <= 30) {
            return text;
        }
        long time = Util.getMeasuringTimeMs();
        long cycle = time / 2000L % 2L;
        if (cycle == 0L && time % 2000L < 400L) {
            return this.textRenderer.trimToWidth(text, maxWidth);
        }
        int overflow = this.textRenderer.getWidth(text) - maxWidth;
        int offset = (int)(time / 70L % (long)(overflow + 10));
        return this.textRenderer.trimToWidth(text, maxWidth + offset);
    }

    private float getClickScale(String id) {
        Long last = this.clickAnimations.get(id);
        if (last == null) {
            return 1.0f;
        }
        long elapsed = Util.getMeasuringTimeMs() - last;
        if (elapsed > 100L) {
            return 1.0f;
        }
        return 0.98f;
    }

    private boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    private int getRankColor(Spell.SpellRank rank) {
        return switch (rank) {
            default -> throw new MatchException(null, null);
            case Spell.SpellRank.E -> 0x9E9E9E;
            case Spell.SpellRank.D -> 5025616;
            case Spell.SpellRank.C -> 2201331;
            case Spell.SpellRank.B -> 10233776;
            case Spell.SpellRank.A -> 16750592;
            case Spell.SpellRank.S -> 9133302;
        };
    }

    private String getSlotIcon(Spell.SpellCategory category) {
        return switch (category) {
            default -> throw new MatchException(null, null);
            case Spell.SpellCategory.ATTACK -> "\ud83d\udd25 ";
            case Spell.SpellCategory.HEAL -> "\u2764 ";
            case Spell.SpellCategory.SUPPORT -> "\ud83d\udee1 ";
            case Spell.SpellCategory.UTILITY -> "\u26a1 ";
        };
    }

    private String getSpellIcon(String spellId, Spell.SpellCategory category) {
        if ("fireball".equals(spellId)) {
            return "\ud83d\udd25";
        }
        return switch (category) {
            default -> throw new MatchException(null, null);
            case Spell.SpellCategory.ATTACK -> "\ud83d\udd25";
            case Spell.SpellCategory.HEAL -> "\u2764";
            case Spell.SpellCategory.SUPPORT -> "\ud83d\udee1";
            case Spell.SpellCategory.UTILITY -> "\u26a1";
        };
    }

    private int adjustBrightness(int color, float boost) {
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        r = MathHelper.clamp((int)((int)((float)r + 255.0f * boost)), (int)0, (int)255);
        g = MathHelper.clamp((int)((int)((float)g + 255.0f * boost)), (int)0, (int)255);
        b = MathHelper.clamp((int)((int)((float)b + 255.0f * boost)), (int)0, (int)255);
        return r << 16 | g << 8 | b;
    }

    private void renderUpgradesPanel(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        this.refreshBookStack();
        int navHeight = Math.max(26, Math.round((float)height * 0.11f));
        int contentY = y + navHeight;
        int contentHeight = height - navHeight;
        int padding = Math.max(8, Math.min(12, width / 40));
        int innerX = x + padding;
        int innerY = contentY + padding;
        int innerWidth = width - padding * 2;
        int innerHeight = contentHeight - padding * 2;
        this.refreshUpgradeCache();
        int topPanelHeight = Math.max(90, (int)((float)innerHeight * 0.48f));
        int bottomPanelHeight = Math.max(90, innerHeight - topPanelHeight - padding);
        int currentY = innerY;
        this.drawPanel(context, innerX, currentY, innerWidth, topPanelHeight, fadeIn);
        int topPadding = 10;
        String headerLabel = "Player Level";
        context.drawText(this.textRenderer, (Text)Text.literal((String)headerLabel), innerX + topPadding, currentY + 8, this.withAlpha(12891645, (int)(230.0f * fadeIn)), true);
        String levelValue = "Lv " + this.cachedLevel;
        context.drawText(this.textRenderer, (Text)Text.literal((String)levelValue).formatted(Formatting.field_1067), innerX + innerWidth - this.textRenderer.getWidth(levelValue) - 12, currentY + 8, this.withAlpha(16382715, (int)(240.0f * fadeIn)), true);
        int barY = currentY + 26;
        this.renderProgressDisplay(context, innerX + topPadding, barY, innerWidth - topPadding * 2, topPanelHeight - 44, fadeIn);
        int infoY = currentY + topPanelHeight - 24;
        int remainingXp = Math.max(0, this.cachedXpNeeded - this.cachedXp);
        String xpInfo = this.cachedXp + " / " + this.cachedXpNeeded + " XP";
        String remainingInfo = remainingXp + " XP to level";
        context.drawText(this.textRenderer, (Text)Text.literal((String)xpInfo), innerX + topPadding, infoY, this.withAlpha(15067115, (int)(235.0f * fadeIn)), true);
        context.drawText(this.textRenderer, (Text)Text.literal((String)remainingInfo), innerX + innerWidth - this.textRenderer.getWidth(remainingInfo) - 12, infoY, this.withAlpha(10265519, (int)(220.0f * fadeIn)), true);
        this.drawPanel(context, innerX, currentY += topPanelHeight + padding, innerWidth, bottomPanelHeight, fadeIn);
        this.renderSacrificeSection(context, innerX + 10, currentY + 8, innerWidth - 20, bottomPanelHeight - 16, fadeIn);
    }

    private void drawSection(DrawContext context, int x, int y, int width, int height, float fadeIn, String label) {
        int bgColor = this.withAlpha(0x2D2D2D, (int)(200.0f * fadeIn));
        int borderColor = this.withAlpha(8141549, (int)(140.0f * fadeIn));
        context.fill(x, y, x + width, y + height, bgColor);
        context.drawBorder(x, y, width, height, borderColor);
        int textColor = this.withAlpha(12891645, (int)(220.0f * fadeIn));
        context.drawText(this.textRenderer, (Text)Text.literal((String)label), x + 6, y + 6, textColor, true);
    }

    private void refreshUpgradeCache() {
        float target;
        this.refreshBookStack();
        int newLevel = SpellBookData.getCurrentLevel(this.bookStack);
        int newXp = SpellBookData.getCurrentXp(this.bookStack);
        int newSouls = SpellBookData.getSoulsPoints(this.bookStack);
        if (newLevel != this.cachedLevel) {
            this.levelFlashStartMs = Util.getMeasuringTimeMs();
        }
        if (newXp != this.cachedXp) {
            int delta = newXp - this.cachedXp;
            if (delta > 0) {
                this.pendingXpPopup += delta;
                this.pendingXpPopupMs = Util.getMeasuringTimeMs();
            }
            this.cachedXp = newXp;
        }
        this.cachedLevel = newLevel;
        this.cachedSouls = newSouls;
        this.cachedXpNeeded = SpellBookData.getXpNeededForLevel(this.cachedLevel);
        float f = target = this.cachedXpNeeded > 0 ? (float)this.cachedXp / (float)this.cachedXpNeeded : 0.0f;
        if (Math.abs(target - this.progressFillTarget) > 0.001f) {
            this.progressFillTarget = MathHelper.clamp((float)target, (float)0.0f, (float)1.0f);
            this.progressAnimStartMs = Util.getMeasuringTimeMs();
        }
        if (this.pendingXpPopup > 0 && Util.getMeasuringTimeMs() - this.pendingXpPopupMs > 500L) {
            this.xpPopupAmount = this.pendingXpPopup;
            this.xpPopupStartMs = Util.getMeasuringTimeMs();
            this.pendingXpPopup = 0;
        }
    }

    private void renderProgressDisplay(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        String popup;
        int popupY;
        float alpha;
        long elapsed;
        int barHeight = Math.max(8, Math.min(12, height - 12));
        int barWidth = width;
        int barX = x;
        int barY = y + 2;
        context.fill(barX, barY, barX + barWidth, barY + barHeight, this.withAlpha(2042167, (int)(230.0f * fadeIn)));
        long now = Util.getMeasuringTimeMs();
        if (this.progressAnimStartMs > 0L) {
            float t = MathHelper.clamp((float)((float)(now - this.progressAnimStartMs) / 300.0f), (float)0.0f, (float)1.0f);
            this.progressFill = MathHelper.lerp((float)t, (float)this.progressFill, (float)this.progressFillTarget);
            if (t >= 1.0f) {
                this.progressFill = this.progressFillTarget;
                this.progressAnimStartMs = 0L;
            }
        } else {
            this.progressFill = this.progressFillTarget;
        }
        int fillWidth = Math.max(0, Math.round((float)barWidth * this.progressFill));
        if (fillWidth > 0) {
            int startColor = this.withAlpha(8141549, (int)(235.0f * fadeIn));
            int endColor = this.withAlpha(14239471, (int)(235.0f * fadeIn));
            context.fillGradient(barX, barY, barX + fillWidth, barY + barHeight, startColor, endColor);
        }
        String xpLabel = this.cachedXp + " / " + this.cachedXpNeeded + " XP";
        int xpX = barX + (barWidth - this.textRenderer.getWidth(xpLabel)) / 2;
        int xpY = barY + barHeight + 2;
        context.drawText(this.textRenderer, (Text)Text.literal((String)xpLabel), xpX, xpY, this.withAlpha(15067115, (int)(235.0f * fadeIn)), true);
        if (this.xpPopupStartMs > 0L) {
            elapsed = now - this.xpPopupStartMs;
            if (elapsed < 1000L) {
                alpha = 1.0f - (float)elapsed / 1000.0f;
                popupY = barY - 12 - (int)((float)elapsed / 8.0f);
                popup = "+" + this.xpPopupAmount + " XP";
                context.drawText(this.textRenderer, (Text)Text.literal((String)popup), x + width - this.textRenderer.getWidth(popup) - 6, popupY, this.withAlpha(2278750, (int)(255.0f * alpha)), true);
            } else {
                this.xpPopupStartMs = 0L;
                this.xpPopupAmount = 0;
            }
        }
        if (this.levelFlashStartMs > 0L) {
            elapsed = now - this.levelFlashStartMs;
            if (elapsed < 400L) {
                alpha = 1.0f - (float)elapsed / 400.0f;
                context.fill(x, y, x + width, y + height, this.withAlpha(9133302, (int)(60.0f * alpha)));
            } else {
                this.levelFlashStartMs = 0L;
            }
        }
        if (this.soulsPopupStartMs > 0L) {
            elapsed = now - this.soulsPopupStartMs;
            if (elapsed < 900L) {
                alpha = 1.0f - (float)elapsed / 900.0f;
                popupY = barY - 24 - (int)((float)elapsed / 10.0f);
                popup = "+" + this.soulsPopupAmount + " Souls";
                context.drawText(this.textRenderer, (Text)Text.literal((String)popup), x + width - this.textRenderer.getWidth(popup) - 6, popupY, this.withAlpha(15324671, (int)(255.0f * alpha)), true);
            } else {
                this.soulsPopupStartMs = 0L;
                this.soulsPopupAmount = 0;
            }
        }
    }

    private void renderCombatXpInfo(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int boxColor = this.withAlpha(2042167, (int)(200.0f * fadeIn));
        int borderColor = this.withAlpha(8141549, (int)(160.0f * fadeIn));
        context.fill(x, y, x + width, y + height, boxColor);
        context.drawBorder(x, y, width, height, borderColor);
        int textColor = this.withAlpha(10265519, (int)(230.0f * fadeIn));
        String[] lines = new String[]{"Combat XP Earned", "Deal damage to gain XP", "1 damage dealt = 1 XP"};
        int lineHeight = 12;
        int totalHeight = lineHeight * lines.length;
        int startY = y + (height - totalHeight) / 2;
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            int lineX = x + (width - this.textRenderer.getWidth(line)) / 2;
            context.drawText(this.textRenderer, (Text)Text.literal((String)line), lineX, startY + i * lineHeight, textColor, true);
        }
    }

    private void renderSacrificeSection(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int thumbY;
        boolean thumbHover;
        int titleColor = this.withAlpha(16317180, (int)(240.0f * fadeIn));
        context.drawText(this.textRenderer, (Text)Text.literal((String)"Sacrifice Levels"), x + 4, y + 2, titleColor, true);
        this.refreshBookStack();
        int playerLevel = this.clientRef.player != null ? this.clientRef.player.experienceLevel : 0;
        int clampedMax = Math.min(playerLevel, 10000);
        if (playerLevel != this.sacrificePlayerLevel || clampedMax != this.sacrificeMaxLevel) {
            this.sacrificePlayerLevel = playerLevel;
            this.sacrificeMaxLevel = clampedMax;
            if (this.sacrificeSelectedLevels > this.sacrificeMaxLevel) {
                this.updateSacrificeSelection(this.sacrificeMaxLevel, false);
            }
        }
        this.cachedLevel = SpellBookData.getCurrentLevel(this.bookStack);
        this.cachedXp = SpellBookData.getCurrentXp(this.bookStack);
        this.cachedSouls = SpellBookData.getSoulsPoints(this.bookStack);
        this.cachedXpNeeded = SpellBookData.getXpNeededForLevel(this.cachedLevel);
        if (this.sacrificeMaxLevel <= 0) {
            context.drawText(this.textRenderer, (Text)Text.literal((String)"No player levels available"), x + 4, y + height / 2, this.withAlpha(10265519, (int)(220.0f * fadeIn)), true);
            this.sacrificeSelectedLevels = 0;
            this.sacrificeCalculatedXp = 0;
            return;
        }
        context.drawText(this.textRenderer, (Text)Text.literal((String)("Player Level: " + this.sacrificePlayerLevel)), x + 4, y + 16, this.withAlpha(13358581, (int)(230.0f * fadeIn)), true);
        int sliderWidth = (int)((float)width * 0.68f);
        int sliderX = x + 6;
        int sliderY = y + 32;
        int sliderHeight = 8;
        this.sacrificeSliderX = sliderX;
        this.sacrificeSliderWidth = sliderWidth;
        float ratio = this.sacrificeMaxLevel == 0 ? 0.0f : (float)this.sacrificeSelectedLevels / (float)this.sacrificeMaxLevel;
        int fillWidth = Math.round((float)sliderWidth * ratio);
        int trackColor = this.withAlpha(2042167, (int)(220.0f * fadeIn));
        int fillColor = this.withAlpha(8141549, (int)(230.0f * fadeIn));
        context.fill(sliderX, sliderY, sliderX + sliderWidth, sliderY + sliderHeight, trackColor);
        context.fill(sliderX, sliderY, sliderX + fillWidth, sliderY + sliderHeight, fillColor);
        int thumbX = sliderX + fillWidth - 6;
        if (this.sacrificeSelectedLevels <= 0) {
            thumbX = sliderX - 6;
        }
        int thumbColor = (thumbHover = this.isHovered(thumbX, thumbY = sliderY - 4, 12, 12, (int)this.clientRef.mouse.getX() * this.width / this.clientRef.getWindow().getScaledWidth(), (int)this.clientRef.mouse.getY() * this.height / this.clientRef.getWindow().getScaledHeight())) ? 10980346 : 9133302;
        context.fill(thumbX, thumbY, thumbX + 12, thumbY + 12, this.withAlpha(thumbColor, (int)(230.0f * fadeIn)));
        String selectedLabel = this.sacrificeSelectedLevels + " levels";
        int selectedLabelX = sliderX + sliderWidth + 8;
        context.drawText(this.textRenderer, (Text)Text.literal((String)selectedLabel), selectedLabelX, sliderY - 2, this.withAlpha(15067115, (int)(230.0f * fadeIn)), true);
        long now = Util.getMeasuringTimeMs();
        if (this.sacrificeCalcMs == 0L || now - this.sacrificeCalcMs > 50L) {
            int newXp = this.calculateSacrificeXp(this.sacrificePlayerLevel, this.sacrificeSelectedLevels);
            if (newXp != this.sacrificeCalculatedXp) {
                this.sacrificeCalculatedXp = newXp;
                this.sacrificePulseMs = now;
                this.sacrificePreviewScaleMs = now;
            }
            this.sacrificeCalcMs = now;
        }
        int previewColor = this.getSacrificePreviewColor(this.sacrificeCalculatedXp);
        String xpLabel = "+" + this.formatNumber(this.sacrificeCalculatedXp) + " XP";
        int xpX = sliderX + sliderWidth + 8;
        int xpY = sliderY + 12;
        context.drawText(this.textRenderer, (Text)Text.literal((String)xpLabel), xpX, xpY, this.withAlpha(previewColor, (int)(240.0f * fadeIn)), true);
        int buttonWidth = Math.max(120, (int)((float)width * 0.42f));
        int buttonHeight = Math.max(18, (int)((float)height * 0.28f));
        int buttonX = x + (width - buttonWidth) / 2;
        int buttonY = y + height - buttonHeight - 6;
        boolean enabled = this.sacrificeSelectedLevels > 0 && this.sacrificeSelectedLevels <= this.sacrificeMaxLevel;
        int baseColor = enabled ? 2042167 : 3621201;
        String buttonLabel = "SACRIFICE";
        this.renderSacrificeButton(context, buttonX, buttonY, buttonWidth, buttonHeight, buttonLabel, baseColor, enabled, fadeIn);
        this.clickRegions.add(new ClickRegion("sacrifice_slider", sliderX, sliderY - 6, sliderX + sliderWidth, sliderY + sliderHeight + 6, () -> {
            this.sacrificeDragging = true;
            this.updateSacrificeFromMouse(this.clientRef.mouse.getX() * (double)this.width / (double)this.clientRef.getWindow().getScaledWidth());
        }));
    }

    private void renderSoulsInfo(DrawContext context, int x, int y, int width, int height, float fadeIn) {
        int boxColor = this.withAlpha(2042167, (int)(200.0f * fadeIn));
        int borderColor = this.withAlpha(8141549, (int)(160.0f * fadeIn));
        context.fill(x, y, x + width, y + height, boxColor);
        context.drawBorder(x, y, width, height, borderColor);
        int nextMilestone = (this.cachedLevel / 5 + 1) * 5;
        boolean nearMilestone = nextMilestone - this.cachedLevel <= 1;
        int highlightColor = nearMilestone ? 16498468 : 10265519;
        String line1 = "Souls: " + this.cachedSouls + " \u2b1f";
        String line2 = "Earn 1 Soul every 5 levels";
        String line3 = "Next Soul at level " + nextMilestone;
        int lineHeight = 12;
        int startY = y + 10;
        context.drawText(this.textRenderer, (Text)Text.literal((String)line1), x + 12, startY, this.withAlpha(15067115, (int)(240.0f * fadeIn)), true);
        context.drawText(this.textRenderer, (Text)Text.literal((String)line2), x + 12, startY + lineHeight + 4, this.withAlpha(10265519, (int)(220.0f * fadeIn)), true);
        context.drawText(this.textRenderer, (Text)Text.literal((String)line3), x + 12, startY + (lineHeight + 4) * 2, this.withAlpha(highlightColor, (int)(240.0f * fadeIn)), true);
    }

    private void renderSacrificeButton(DrawContext context, int x, int y, int width, int height, String label, int baseColor, boolean enabled, float fadeIn) {
        float scale;
        boolean hovered = this.isHovered(x, y, width, height, (int)this.clientRef.mouse.getX() * this.width / this.clientRef.getWindow().getScaledWidth(), (int)this.clientRef.mouse.getY() * this.height / this.clientRef.getWindow().getScaledHeight());
        float f = scale = enabled && hovered ? 1.02f : 1.0f;
        if (!enabled) {
            scale = 1.0f;
        }
        float clickScale = this.getClickScale("sacrifice_button");
        int color = enabled ? this.adjustBrightness(baseColor, hovered ? 0.2f : 0.0f) : baseColor;
        context.getMatrices().push();
        context.getMatrices().translate((float)x + (float)width / 2.0f, (float)y + (float)height / 2.0f, 0.0f);
        context.getMatrices().scale(scale *= clickScale, scale, 1.0f);
        context.getMatrices().translate(-((float)x + (float)width / 2.0f), -((float)y + (float)height / 2.0f), 0.0f);
        int startColor = enabled ? this.withAlpha(8141549, (int)(230.0f * fadeIn)) : this.withAlpha(4937059, (int)(220.0f * fadeIn));
        int endColor = enabled ? this.withAlpha(7153881, (int)(230.0f * fadeIn)) : this.withAlpha(4937059, (int)(220.0f * fadeIn));
        context.fillGradient(x, y, x + width, y + height, startColor, endColor);
        context.drawBorder(x, y, width, height, this.withAlpha(9133302, (int)(120.0f * fadeIn)));
        int textX = x + (width - this.textRenderer.getWidth(label)) / 2;
        int textY = y + (height - 10) / 2 + 2;
        int textColor = enabled ? 0xFFFFFF : 10265519;
        context.drawText(this.textRenderer, (Text)Text.literal((String)label), textX, textY, this.withAlpha(textColor, (int)(240.0f * fadeIn)), true);
        context.getMatrices().pop();
        if (enabled) {
            this.clickRegions.add(new ClickRegion("sacrifice_button", x, y, x + width, y + height, this::executeSacrifice));
        }
    }

    private void executeSacrifice() {
        if (this.sacrificeSelectedLevels <= 0 || this.sacrificeSelectedLevels > this.sacrificeMaxLevel) {
            return;
        }
        if (this.clientRef == null || this.clientRef.player == null) {
            return;
        }
        int xp = this.calculateSacrificeXp(this.sacrificePlayerLevel, this.sacrificeSelectedLevels);
        if (xp <= 0) {
            return;
        }
        ClientPlayNetworking.send((CustomPayload)new SacrificeLevelsPayload(this.sacrificeSelectedLevels));
        this.sacrificeSelectedLevels = 0;
        this.sacrificeCalculatedXp = 0;
        this.sacrificeSuccessMs = Util.getMeasuringTimeMs();
        this.updateSacrificeSelection(0, false);
        this.spawnSacrificeParticles();
        this.playSacrificeSound();
        this.statusMessage = "Sacrifice sent";
        this.statusColor = 16498468;
        this.statusStartMs = Util.getMeasuringTimeMs();
    }

    private void updateSacrificeSelection(int newValue, boolean pulse) {
        int clamped;
        this.sacrificeSelectedLevels = clamped = MathHelper.clamp((int)newValue, (int)0, (int)this.sacrificeMaxLevel);
        if (pulse) {
            this.sacrificePreviewScaleMs = Util.getMeasuringTimeMs();
        }
    }

    private void updateSacrificeFromMouse(double mouseX) {
        if (this.sacrificeSliderWidth <= 0) {
            return;
        }
        double ratio = MathHelper.clamp((double)((mouseX - (double)this.sacrificeSliderX) / (double)this.sacrificeSliderWidth), (double)0.0, (double)1.0);
        int value = (int)Math.round(ratio * (double)this.sacrificeMaxLevel);
        this.updateSacrificeSelection(value, true);
    }

    private int calculateSacrificeXp(int playerLevel, int selectedLevels) {
        if (playerLevel <= 0 || selectedLevels <= 0) {
            return 0;
        }
        int total = 0;
        int start = Math.max(1, playerLevel - selectedLevels + 1);
        for (int lvl = playerLevel; lvl >= start; --lvl) {
            total += SpellBookData.getSacrificeXpForLevel(lvl);
        }
        return total;
    }

    private int getSacrificeMaxClamp(int playerLevel) {
        return Math.min(playerLevel, 10000);
    }

    private int getSacrificePreviewColor(int value) {
        if (value >= 5000) {
            return 16498468;
        }
        if (value >= 1000) {
            return 2278750;
        }
        return 10265519;
    }

    private String formatNumber(int value) {
        return String.format(Locale.ROOT, "%,d", value);
    }

    private void spawnSacrificeParticles() {
        if (this.clientRef == null || this.clientRef.player == null || this.clientRef.world == null) {
            return;
        }
        Vec3d pos = this.clientRef.player.getPos();
        for (int i = 0; i < 12; ++i) {
            double angle = Math.PI * 2 * (double)i / 12.0;
            double radius = 0.6;
            double x = pos.x + Math.cos(angle) * radius;
            double z = pos.z + Math.sin(angle) * radius;
            double y = pos.y + 1.0 + (double)(i % 3) * 0.05;
            this.clientRef.particleManager.addParticle((ParticleEffect)ParticleTypes.field_11207, x, y, z, 0.0, 0.02, 0.0);
        }
    }

    private void playSacrificeSound() {
        if (this.clientRef == null || this.clientRef.player == null) {
            return;
        }
        this.clientRef.player.playSound(Nomorespell.SACRIFICE_CHIME_SOUND, 0.7f, 1.2f);
    }

    private void renderGlowOverlay(DrawContext context, long elapsed, float fadeIn) {
        float glow = 1.0f - MathHelper.clamp((float)((float)elapsed / 400.0f), (float)0.0f, (float)1.0f);
        if (glow <= 0.0f) {
            return;
        }
        int glowColor = this.withAlpha(8141549, (int)(80.0f * glow));
        context.fill(0, 0, this.width, this.height, glowColor);
    }

    private void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        Spell spell;
        if (this.spellsButton.isHovered() && this.spellsButton.getHoverTimeMs() > 500L) {
            context.drawTooltip(this.textRenderer, (Text)Text.literal((String)"View spells"), mouseX, mouseY);
        } else if (this.upgradesButton.isHovered() && this.upgradesButton.getHoverTimeMs() > 500L) {
            context.drawTooltip(this.textRenderer, (Text)Text.literal((String)"View upgrades"), mouseX, mouseY);
        } else if (this.closeButton.isHovered() && this.closeButton.getHoverTimeMs() > 500L) {
            context.drawTooltip(this.textRenderer, (Text)Text.literal((String)"Close"), mouseX, mouseY);
        }
        if (!this.hoverSpellId.isEmpty() && Util.getMeasuringTimeMs() - this.entryHoverStartMs > 500L && (spell = SpellRegistry.getSpell(this.hoverSpellId)) != null) {
            context.drawTooltip(this.textRenderer, (Text)Text.literal((String)spell.getDescription()), mouseX, mouseY);
        }
    }

    private int withAlpha(int rgb, int alpha) {
        return MathHelper.clamp((int)alpha, (int)0, (int)255) << 24 | rgb & 0xFFFFFF;
    }

    private void updateScrollPhysics() {
        if (this.activeTab != 0) {
            return;
        }
        long now = Util.getMeasuringTimeMs();
        if (now - this.lastScrollUpdateMs < 16L) {
            return;
        }
        this.lastScrollUpdateMs = now;
        this.scrollOffset = Math.max(0.0, this.scrollOffset + this.scrollVelocity);
        this.scrollVelocity *= 0.86;
        if (Math.abs(this.scrollVelocity) < 0.01) {
            this.scrollVelocity = 0.0;
            this.scrollStopMs = now;
        }
    }

    private void spawnPurchaseParticles() {
    }

    private void updateHoverSpell(String spellId, boolean hovered) {
        long now = Util.getMeasuringTimeMs();
        if (hovered) {
            if (!this.hoverSpellId.equals(spellId)) {
                this.hoverSpellId = spellId;
                this.entryHoverStartMs = now;
            }
        } else {
            this.hoverSpellId = "";
            this.entryHoverStartMs = 0L;
        }
    }

    private void renderReplaceConfirm(DrawContext context, int x, int y, int width, int height, int mouseX, int mouseY, float fadeIn) {
        int boxWidth = 260;
        int boxHeight = 90;
        int boxX = x + (width - boxWidth) / 2;
        int boxY = y + (height - boxHeight) / 2;
        context.fill(boxX, boxY, boxX + boxWidth, boxY + boxHeight, this.withAlpha(2039589, (int)(235.0f * fadeIn)));
        context.drawBorder(boxX, boxY, boxWidth, boxHeight, this.withAlpha(9133302, (int)(140.0f * fadeIn)));
        String current = this.pendingReplaceSlot == 1 ? this.cachedSlot1 : this.cachedSlot2;
        Spell oldSpell = current != null && !current.isEmpty() ? SpellRegistry.getSpell(current) : null;
        String oldName = oldSpell != null ? oldSpell.getName() : "Empty";
        context.drawText(this.textRenderer, (Text)Text.literal((String)("Replace " + oldName + " with " + this.pendingReplaceSpell.getName() + "?")), boxX + 10, boxY + 10, this.withAlpha(15067115, (int)(240.0f * fadeIn)), false);
        int yesX = boxX + 26;
        int noX = boxX + 140;
        this.renderActionButton(context, "replace_yes", yesX, boxY + 48, 80, 18, "Yes", 2278750, mouseX, mouseY, fadeIn, () -> {
            this.applyEquip(this.pendingReplaceSpell, this.pendingReplaceSlot);
            this.pendingReplaceSlot = 0;
            this.pendingReplaceSpell = null;
        }, false, "replace_confirm");
        this.renderActionButton(context, "replace_no", noX, boxY + 48, 80, 18, "No", 7041664, mouseX, mouseY, fadeIn, () -> {
            this.pendingReplaceSlot = 0;
            this.pendingReplaceSpell = null;
        }, false, "replace_cancel");
    }

    private void applyEquip(Spell spell, int slotIndex) {
        if (!SpellBookData.hasPurchasedSpell(this.bookStack, spell.getId())) {
            this.statusMessage = "Spell not owned";
            this.statusColor = 0xEF4444;
            this.statusStartMs = Util.getMeasuringTimeMs();
            this.playUiSound(false);
            return;
        }
        if (slotIndex == 1) {
            this.cachedSlot1 = spell.getId();
            ClientPlayNetworking.send((CustomPayload)new EquipSpellPayload(1, this.cachedSlot1));
            this.slotHighlightSlot1Ms = Util.getMeasuringTimeMs();
        } else {
            this.cachedSlot2 = spell.getId();
            ClientPlayNetworking.send((CustomPayload)new EquipSpellPayload(2, this.cachedSlot2));
            Nomorespell.LOGGER.info("GUI: Equipped Slot 2 with {}", (Object)this.cachedSlot2);
            this.slotHighlightSlot2Ms = Util.getMeasuringTimeMs();
        }
        this.equipCheckId = spell.getId() + "_slot" + slotIndex;
        this.equipCheckStartMs = Util.getMeasuringTimeMs();
        this.playUiSound(true);
        this.statusMessage = "Equipped " + spell.getName();
        this.statusColor = 2278750;
        this.statusStartMs = Util.getMeasuringTimeMs();
    }

    @Environment(value=EnvType.CLIENT)
    private class TabButton
    extends PressableWidget {
        private final Runnable onClick;
        private boolean active;
        private float fade;
        private long hoverStart;

        public TabButton(int x, int y, int width, int height, Text message, Runnable onClick) {
            super(x, y, width, height, message);
            this.fade = 1.0f;
            this.hoverStart = 0L;
            this.onClick = onClick;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public void setFade(float fade) {
            this.fade = fade;
        }

        public long getHoverTimeMs() {
            if (!this.isHovered()) {
                return 0L;
            }
            if (this.hoverStart == 0L) {
                this.hoverStart = Util.getMeasuringTimeMs();
            }
            return Util.getMeasuringTimeMs() - this.hoverStart;
        }

        public void onPress() {
            this.onClick.run();
        }

        protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
            int textColor;
            if (!this.isHovered()) {
                this.hoverStart = 0L;
            }
            int baseColor = 2039589;
            int alpha = (int)(235.0f * this.fade);
            context.fill(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), SpellBookScreen.this.withAlpha(baseColor, alpha));
            int n = textColor = this.active ? 14137343 : 12289788;
            if (!this.active) {
                textColor = 10125264;
            }
            if (this.active) {
                context.fill(this.getX(), this.getY() + this.getHeight() - 2, this.getX() + this.getWidth(), this.getY() + this.getHeight(), SpellBookScreen.this.withAlpha(12289788, (int)(220.0f * this.fade)));
            }
            int textX = this.getX() + (this.getWidth() - SpellBookScreen.this.textRenderer.getWidth((StringVisitable)this.getMessage())) / 2;
            int textY = this.getY() + (this.getHeight() - 10) / 2 + 1;
            context.drawText(SpellBookScreen.this.textRenderer, (Text)this.getMessage().copy().formatted(Formatting.field_1067), textX, textY, SpellBookScreen.this.withAlpha(textColor, (int)(255.0f * this.fade)), true);
        }

        protected void appendClickableNarrations(NarrationMessageBuilder builder) {
            this.appendDefaultNarrations(builder);
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static class ClickRegion {
        final String id;
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Runnable onClick;

        ClickRegion(String id, int x1, int y1, int x2, int y2, Runnable onClick) {
            this.id = id;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.onClick = onClick;
        }

        boolean contains(double x, double y) {
            return x >= (double)this.x1 && x <= (double)this.x2 && y >= (double)this.y1 && y <= (double)this.y2;
        }
    }

    @Environment(value=EnvType.CLIENT)
    private static class CachedSpellText {
        final String name;
        final List<String> descriptionLines;
        final String infoLine;
        final int maxWidth;
        boolean truncated;

        CachedSpellText(String name, List<String> descriptionLines, String infoLine, int maxWidth) {
            this.name = name;
            this.descriptionLines = descriptionLines;
            this.infoLine = infoLine;
            this.maxWidth = maxWidth;
        }
    }
}

