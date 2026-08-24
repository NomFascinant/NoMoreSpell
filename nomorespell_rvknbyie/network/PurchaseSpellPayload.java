/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.Identifier
 *  net.minecraft.CustomPayload
 *  net.minecraft.CustomPayload$class_9154
 *  net.minecraft.RegistryByteBuf
 *  net.minecraft.PacketCodecs
 *  net.minecraft.PacketCodec
 */
package nomorespell_rvknbyie.network;

import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.RegistryByteBuf;
import net.minecraft.PacketCodecs;
import net.minecraft.PacketCodec;

public record PurchaseSpellPayload(String spellId) implements CustomPayload
{
    public static final CustomPayload.class_9154<PurchaseSpellPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"purchase_spell"));
    public static final PacketCodec<RegistryByteBuf, PurchaseSpellPayload> CODEC = PacketCodec.tuple((PacketCodec)PacketCodecs.STRING, PurchaseSpellPayload::spellId, PurchaseSpellPayload::new);

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

