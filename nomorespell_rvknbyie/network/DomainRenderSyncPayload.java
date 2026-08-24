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

import java.util.UUID;
import net.minecraft.Identifier;
import net.minecraft.CustomPayload;
import net.minecraft.RegistryByteBuf;
import net.minecraft.PacketCodecs;
import net.minecraft.PacketCodec;

public record DomainRenderSyncPayload(UUID casterId, double x, double y, double z, int age, float radius, float alpha, boolean active, boolean ending, int endAge) implements CustomPayload
{
    public static final CustomPayload.class_9154<DomainRenderSyncPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"domain_render_sync"));
    public static final PacketCodec<RegistryByteBuf, DomainRenderSyncPayload> CODEC = PacketCodec.of((value, buf) -> {
        PacketCodecs.STRING.encode(buf, (Object)value.casterId().toString());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.x());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.y());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.z());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.age());
        PacketCodecs.FLOAT.encode(buf, (Object)Float.valueOf(value.radius()));
        PacketCodecs.FLOAT.encode(buf, (Object)Float.valueOf(value.alpha()));
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.active() ? 1 : 0)));
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.ending() ? 1 : 0)));
        PacketCodecs.VAR_INT.encode(buf, (Object)value.endAge());
    }, buf -> new DomainRenderSyncPayload(UUID.fromString((String)PacketCodecs.STRING.decode(buf)), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf), ((Float)PacketCodecs.FLOAT.decode(buf)).floatValue(), ((Float)PacketCodecs.FLOAT.decode(buf)).floatValue(), (Byte)PacketCodecs.BYTE.decode(buf) != 0, (Byte)PacketCodecs.BYTE.decode(buf) != 0, (Integer)PacketCodecs.VAR_INT.decode(buf)));

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

