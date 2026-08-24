/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.api.Environment
 *  net.minecraft.RenderLayer
 *  net.minecraft.Vec3d
 *  net.minecraft.MathHelper
 *  net.minecraft.MatrixStack
 *  net.minecraft.VertexConsumer
 *  net.minecraft.VertexConsumerProvider
 *  net.minecraft.OverlayTexture
 *  net.minecraft.AbstractClientPlayerEntity
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 */
package nomorespell_rvknbyie;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.RenderLayer;
import net.minecraft.Vec3d;
import net.minecraft.MathHelper;
import net.minecraft.MatrixStack;
import net.minecraft.VertexConsumer;
import net.minecraft.VertexConsumerProvider;
import net.minecraft.OverlayTexture;
import net.minecraft.AbstractClientPlayerEntity;
import nomorespell_rvknbyie.NomorespellClient;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

@Environment(value=EnvType.CLIENT)
public final class VerdantHaloRenderer {
    private static final int ANGLE_SEGMENTS = 96;
    private static final int CIRCLE_SEGMENTS = 512;
    private static final int CIRCLE_FILL_BANDS = 6;
    private static final int RADIAL_STEPS = 56;
    private static final float TWO_PI = (float)Math.PI * 2;
    private static final float DISK_RADIUS = 5.0f;
    private static final float DISK_Y_OFFSET = 0.02f;
    private static final float DISK_THICKNESS = 0.06f;
    private static final float CIRCLE_Y_OFFSET = 0.085f;
    private static final float LINE_Y_OFFSET = 0.09f;
    private static final float LINE_HALF_WIDTH = 0.08f;
    private static final float CIRCLE_HALF_WIDTH = 0.115f;
    private static final float CIRCLE_FILL_WIDTH = 0.22f;
    private static final float TOTAL_DURATION_TICKS = 160.0f;
    private static final int DISK_RED = 0;
    private static final int DISK_GREEN = 255;
    private static final int DISK_BLUE = 120;
    private static final int CIRCLE_RED = 0;
    private static final int CIRCLE_GREEN = 180;
    private static final int CIRCLE_BLUE = 60;
    private static final float[] CIRCLE_RADII = new float[]{1.2f, 3.0f, 4.8f};
    private static final float[] RADIAL_LINE_ANGLES = new float[]{0.0f, 1.0471976f, 2.0943952f, (float)Math.PI, 4.1887903f, 5.235988f};
    private static final float RADIUS_STEP = 0.08928572f;

    private VerdantHaloRenderer() {
    }

    public static void render(MatrixStack matrices, VertexConsumerProvider consumers, Vec3d cameraPos, AbstractClientPlayerEntity player, NomorespellClient.VerdantHaloRenderState state, float tickDelta) {
        if (player == null || state == null) {
            return;
        }
        float age = (float)(player.age - state.startAge()) + tickDelta;
        if (age < 0.0f || age > 160.0f) {
            return;
        }
        float alpha = VerdantHaloRenderer.getAlpha(age);
        if (alpha <= 0.0f) {
            return;
        }
        Vec3d playerPos = player.getLerpedPos(tickDelta);
        VertexConsumer vc = consumers.getBuffer(RenderLayer.getLightning());
        float globalRotation = age * 0.03f;
        matrices.push();
        matrices.translate(playerPos.x - cameraPos.x, playerPos.y - cameraPos.y + (double)0.02f, playerPos.z - cameraPos.z);
        VerdantHaloRenderer.drawFilledDisk(matrices, vc, age, alpha, globalRotation);
        VerdantHaloRenderer.drawCircles(matrices, vc, alpha, globalRotation);
        VerdantHaloRenderer.drawRadialLines(matrices, vc, alpha, globalRotation);
        matrices.pop();
    }

    private static float getAlpha(float age) {
        float alpha = age < 40.0f ? age / 40.0f : (age > 120.0f ? (160.0f - age) / 40.0f : 1.0f);
        return MathHelper.clamp((float)alpha, (float)0.0f, (float)1.0f);
    }

    private static void drawFilledDisk(MatrixStack matrices, VertexConsumer vc, float age, float alpha, float globalRotation) {
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        float rotationCos = MathHelper.cos((float)globalRotation);
        float rotationSin = MathHelper.sin((float)globalRotation);
        int pulsedGreen = VerdantHaloRenderer.getPulsedDiskGreen(age);
        int vertexAlpha = MathHelper.clamp((int)((int)(alpha * 255.0f)), (int)0, (int)255);
        float topY = 0.08f;
        for (int rIndex = 0; rIndex < 56; ++rIndex) {
            float r1 = (float)rIndex * 0.08928572f;
            float r2 = (float)(rIndex + 1) * 0.08928572f;
            for (int i = 0; i < 96; ++i) {
                float angle1 = (float)Math.PI * 2 * ((float)i / 96.0f);
                float angle2 = (float)Math.PI * 2 * ((float)(i + 1) / 96.0f);
                float x11 = MathHelper.cos((float)angle1) * r1;
                float z11 = MathHelper.sin((float)angle1) * r1;
                float x12 = MathHelper.cos((float)angle2) * r1;
                float z12 = MathHelper.sin((float)angle2) * r1;
                float x21 = MathHelper.cos((float)angle1) * r2;
                float z21 = MathHelper.sin((float)angle1) * r2;
                float x22 = MathHelper.cos((float)angle2) * r2;
                float z22 = MathHelper.sin((float)angle2) * r2;
                VerdantHaloRenderer.addQuad(vc, position, normal, x11, topY, z11, x21, topY, z21, x22, topY, z22, x12, topY, z12, rotationCos, rotationSin, 0, pulsedGreen, 120, vertexAlpha);
            }
        }
        for (int i = 0; i < 96; ++i) {
            float angle1 = (float)Math.PI * 2 * ((float)i / 96.0f);
            float angle2 = (float)Math.PI * 2 * ((float)(i + 1) / 96.0f);
            float outerX1 = MathHelper.cos((float)angle1) * 5.0f;
            float outerZ1 = MathHelper.sin((float)angle1) * 5.0f;
            float outerX2 = MathHelper.cos((float)angle2) * 5.0f;
            float outerZ2 = MathHelper.sin((float)angle2) * 5.0f;
            VerdantHaloRenderer.addQuad(vc, position, normal, outerX1, 0.02f, outerZ1, outerX1, topY, outerZ1, outerX2, topY, outerZ2, outerX2, 0.02f, outerZ2, rotationCos, rotationSin, 0, pulsedGreen, 120, vertexAlpha);
        }
    }

    private static void drawCircles(MatrixStack matrices, VertexConsumer vc, float alpha, float globalRotation) {
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        float rotationCos = MathHelper.cos((float)globalRotation);
        float rotationSin = MathHelper.sin((float)globalRotation);
        int vertexAlpha = MathHelper.clamp((int)((int)(alpha * 255.0f)), (int)0, (int)255);
        for (float radius : CIRCLE_RADII) {
            float innerRadius = Math.max(0.0f, radius - 0.11f);
            float outerRadius = radius + 0.11f;
            for (int band = 0; band < 6; ++band) {
                float bandStart = innerRadius + (outerRadius - innerRadius) * ((float)band / 6.0f);
                float bandEnd = innerRadius + (outerRadius - innerRadius) * ((float)(band + 1) / 6.0f);
                float angularOffset = (band & 1) == 0 ? 0.0f : 0.0061359233f;
                for (int i = 0; i < 512; ++i) {
                    float angle1 = (float)Math.PI * 2 * ((float)i / 512.0f) + angularOffset;
                    float angle2 = (float)Math.PI * 2 * ((float)(i + 1) / 512.0f) + angularOffset;
                    float innerX1 = MathHelper.cos((float)angle1) * bandStart;
                    float innerZ1 = MathHelper.sin((float)angle1) * bandStart;
                    float innerX2 = MathHelper.cos((float)angle2) * bandStart;
                    float innerZ2 = MathHelper.sin((float)angle2) * bandStart;
                    float outerX1 = MathHelper.cos((float)angle1) * bandEnd;
                    float outerZ1 = MathHelper.sin((float)angle1) * bandEnd;
                    float outerX2 = MathHelper.cos((float)angle2) * bandEnd;
                    float outerZ2 = MathHelper.sin((float)angle2) * bandEnd;
                    VerdantHaloRenderer.addQuad(vc, position, normal, innerX1, 0.085f, innerZ1, outerX1, 0.085f, outerZ1, outerX2, 0.085f, outerZ2, innerX2, 0.085f, innerZ2, rotationCos, rotationSin, 0, 180, 60, vertexAlpha);
                }
            }
            for (int i = 0; i < 512; ++i) {
                float angle1 = (float)Math.PI * 2 * ((float)i / 512.0f);
                float angle2 = (float)Math.PI * 2 * ((float)(i + 1) / 512.0f);
                float cos1 = MathHelper.cos((float)angle1);
                float sin1 = MathHelper.sin((float)angle1);
                float cos2 = MathHelper.cos((float)angle2);
                float sin2 = MathHelper.sin((float)angle2);
                float x1 = cos1 * radius;
                float z1 = sin1 * radius;
                float x2 = cos2 * radius;
                float z2 = sin2 * radius;
                VerdantHaloRenderer.addLineSegment(vc, position, normal, x1, 0.085f, z1, x2, 0.085f, z2, 0.115f, rotationCos, rotationSin, 0, 180, 60, vertexAlpha);
            }
        }
    }

    private static void drawRadialLines(MatrixStack matrices, VertexConsumer vc, float alpha, float globalRotation) {
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        float rotationCos = MathHelper.cos((float)globalRotation);
        float rotationSin = MathHelper.sin((float)globalRotation);
        int vertexAlpha = MathHelper.clamp((int)((int)(alpha * 255.0f)), (int)0, (int)255);
        for (float baseAngle : RADIAL_LINE_ANGLES) {
            float prevRadius = 0.0f;
            for (float targetRadius : CIRCLE_RADII) {
                float startX = MathHelper.cos((float)baseAngle) * prevRadius;
                float startZ = MathHelper.sin((float)baseAngle) * prevRadius;
                float endX = MathHelper.cos((float)baseAngle) * targetRadius;
                float endZ = MathHelper.sin((float)baseAngle) * targetRadius;
                VerdantHaloRenderer.addLineSegment(vc, position, normal, startX, 0.09f, startZ, endX, 0.09f, endZ, 0.08f, rotationCos, rotationSin, 0, 180, 60, vertexAlpha);
                prevRadius = targetRadius;
            }
        }
    }

    private static int getPulsedDiskGreen(float age) {
        float brightness = 0.95f + 0.05f * MathHelper.sin((float)(age * 0.1f));
        return MathHelper.clamp((int)((int)(255.0f * brightness)), (int)0, (int)255);
    }

    private static void addRotatedVertex(VertexConsumer vc, Matrix4f position, Matrix3f normal, float localX, float y, float localZ, float rotationCos, float rotationSin, int red, int green, int blue, int alpha) {
        float x = rotationCos * localX - rotationSin * localZ;
        float z = rotationSin * localX + rotationCos * localZ;
        VerdantHaloRenderer.addVertex(vc, position, normal, x, y, z, red, green, blue, alpha);
    }

    private static void addTriangle(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float rotationCos, float rotationSin, int red, int green, int blue, int alpha) {
        VerdantHaloRenderer.addRotatedVertex(vc, position, normal, x1, y1, z1, rotationCos, rotationSin, red, green, blue, alpha);
        VerdantHaloRenderer.addRotatedVertex(vc, position, normal, x2, y2, z2, rotationCos, rotationSin, red, green, blue, alpha);
        VerdantHaloRenderer.addRotatedVertex(vc, position, normal, x3, y3, z3, rotationCos, rotationSin, red, green, blue, alpha);
    }

    private static void addQuad(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, float rotationCos, float rotationSin, int red, int green, int blue, int alpha) {
        VerdantHaloRenderer.addTriangle(vc, position, normal, x1, y1, z1, x2, y2, z2, x3, y3, z3, rotationCos, rotationSin, red, green, blue, alpha);
        VerdantHaloRenderer.addTriangle(vc, position, normal, x1, y1, z1, x3, y3, z3, x4, y4, z4, rotationCos, rotationSin, red, green, blue, alpha);
    }

    private static void addLineSegment(VertexConsumer vc, Matrix4f position, Matrix3f normal, float startX, float startY, float startZ, float endX, float endY, float endZ, float halfWidth, float rotationCos, float rotationSin, int red, int green, int blue, int alpha) {
        float dx = endX - startX;
        float dz = endZ - startZ;
        float length = MathHelper.sqrt((float)(dx * dx + dz * dz));
        if (length <= 1.0E-4f) {
            return;
        }
        float perpX = -dz / length * halfWidth;
        float perpZ = dx / length * halfWidth;
        VerdantHaloRenderer.addQuad(vc, position, normal, startX - perpX, startY, startZ - perpZ, startX + perpX, startY, startZ + perpZ, endX + perpX, endY, endZ + perpZ, endX - perpX, endY, endZ - perpZ, rotationCos, rotationSin, red, green, blue, alpha);
    }

    private static void addVertex(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x, float y, float z, int red, int green, int blue, int alpha) {
        vc.vertex(position, x, y, z).color(red, green, blue, alpha).texture(0.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(0xF000F0).normal(0.0f, 1.0f, 0.0f);
    }
}

