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

public record BloodEclipseRenderPayload(UUID casterId, boolean active, double x, double y, double z, double dirX, double dirY, double dirZ, int age, boolean laserActive, float intensity, float fade) implements CustomPayload
{
    public static final CustomPayload.class_9154<BloodEclipseRenderPayload> ID = new CustomPayload.class_9154(Identifier.of((String)"nomorespell-rvknbyie", (String)"blood_eclipse_render"));
    public static final PacketCodec<RegistryByteBuf, BloodEclipseRenderPayload> CODEC = PacketCodec.of((value, buf) -> {
        PacketCodecs.STRING.encode(buf, (Object)value.casterId().toString());
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.active() ? 1 : 0)));
        PacketCodecs.DOUBLE.encode(buf, (Object)value.x());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.y());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.z());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.dirX());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.dirY());
        PacketCodecs.DOUBLE.encode(buf, (Object)value.dirZ());
        PacketCodecs.VAR_INT.encode(buf, (Object)value.age());
        PacketCodecs.BYTE.encode(buf, (Object)((byte)(value.laserActive() ? 1 : 0)));
        PacketCodecs.FLOAT.encode(buf, (Object)Float.valueOf(value.intensity()));
        PacketCodecs.FLOAT.encode(buf, (Object)Float.valueOf(value.fade()));
    }, buf -> new BloodEclipseRenderPayload(UUID.fromString((String)PacketCodecs.STRING.decode(buf)), (Byte)PacketCodecs.BYTE.decode(buf) != 0, (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Double)PacketCodecs.DOUBLE.decode(buf), (Integer)PacketCodecs.VAR_INT.decode(buf), (Byte)PacketCodecs.BYTE.decode(buf) != 0, ((Float)PacketCodecs.FLOAT.decode(buf)).floatValue(), ((Float)PacketCodecs.FLOAT.decode(buf)).floatValue()));

    public CustomPayload.class_9154<? extends CustomPayload> getId() {
        return ID;
    }
}

