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
 *  net.minecraft.RotationAxis
 *  org.joml.Matrix3f
 *  org.joml.Matrix4f
 *  org.joml.Quaternionfc
 */
package nomorespell_rvknbyie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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
import net.minecraft.RotationAxis;
import nomorespell_rvknbyie.NomorespellClient;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;

@Environment(value=EnvType.CLIENT)
public final class RainOfPicksRenderer {
    private static final float TWO_PI = (float)Math.PI * 2;
    private static final int SEGMENTS = 18;
    private static final int TRAIL_SEGMENTS = 14;

    private RainOfPicksRenderer() {
    }

    public static void render(MatrixStack matrices, VertexConsumerProvider consumers, Vec3d cameraPos, AbstractClientPlayerEntity player, NomorespellClient.RainOfPicksRenderState state, float tickDelta) {
        if (player == null || state == null) {
            return;
        }
        float age = (float)(player.age - state.startAge()) + tickDelta;
        if (age < 0.0f || age > (float)state.durationTicks()) {
            return;
        }
        Vec3d basePos = player.getLerpedPos(tickDelta);
        VertexConsumer vc = consumers.getBuffer(RenderLayer.getLightning());
        List<PickVisual> picks = RainOfPicksRenderer.buildPicks(player, state, age);
        matrices.push();
        matrices.translate(basePos.x - cameraPos.x, basePos.y - cameraPos.y, basePos.z - cameraPos.z);
        RainOfPicksRenderer.drawGroundAura(matrices, vc, age, picks.size());
        picks.sort(Comparator.comparingDouble(p -> p.position.y));
        for (PickVisual pick : picks) {
            RainOfPicksRenderer.drawPick(matrices, vc, pick);
        }
        matrices.pop();
    }

    private static List<PickVisual> buildPicks(AbstractClientPlayerEntity player, NomorespellClient.RainOfPicksRenderState state, float age) {
        Random random = new Random(state.seed());
        int pickCount = Math.max(1, state.projectileCount());
        int homingCount = Math.max(0, Math.min(pickCount, state.homingProjectileCount()));
        int targetCount = Math.max(1, Math.min(6, Math.max(1, homingCount == 0 ? 1 : homingCount / 4)));
        ArrayList<Vec3d> targets = new ArrayList<Vec3d>();
        for (int i = 0; i < targetCount; ++i) {
            double angle = random.nextDouble() * Math.PI * 2.0;
            double distance = 4.0 + random.nextDouble() * 8.5;
            double yOffset = -0.1 + random.nextDouble() * 1.4;
            targets.add(new Vec3d(Math.cos(angle) * distance, yOffset, Math.sin(angle) * distance));
        }
        ArrayList<PickVisual> visuals = new ArrayList<PickVisual>();
        float castFade = Math.min(MathHelper.clamp((float)(age / 10.0f), (float)0.0f, (float)1.0f), MathHelper.clamp((float)(((float)state.durationTicks() - age) / 12.0f), (float)0.0f, (float)1.0f));
        for (int i = 0; i < pickCount; ++i) {
            float fadeOut;
            float fadeIn;
            float alpha;
            Vec3d velocity;
            Vec3d position;
            boolean homing;
            float spawnDelay = random.nextFloat() * 75.0f;
            float localAge = age - spawnDelay;
            if (localAge < -10.0f || localAge > 40.0f) continue;
            double angle = random.nextDouble() * Math.PI * 2.0;
            double distance = 10.0 + random.nextDouble() * 5.0;
            Vec3d spawn = new Vec3d(Math.cos(angle) * distance, 15.0 + random.nextDouble() * 1.25, Math.sin(angle) * distance);
            Vec3d target = new Vec3d(Math.cos(angle + 0.65 + random.nextDouble() * 0.8) * (distance * 0.42), 0.0, Math.sin(angle + 0.65 + random.nextDouble() * 0.8) * (distance * 0.42));
            boolean bl = homing = i < homingCount;
            if (homing && !targets.isEmpty()) {
                target = (Vec3d)targets.get(i % targets.size());
            }
            Vec3d toTarget = target.subtract(spawn);
            Vec3d horizontal = new Vec3d(toTarget.x, 0.0, toTarget.z);
            Vec3d curve = horizontal.lengthSquared() > 1.0E-4 ? horizontal.normalize().rotateY((float)((random.nextDouble() - 0.5) * 0.85)).multiply(0.55 + random.nextDouble() * 0.7) : new Vec3d(0.0, 0.0, 0.0);
            Vec3d baseVelocity = new Vec3d(curve.x + (random.nextDouble() - 0.5) * 0.09, -0.22 - random.nextDouble() * 0.18, curve.z + (random.nextDouble() - 0.5) * 0.09);
            if (localAge <= 0.0f) {
                position = spawn;
                velocity = baseVelocity;
            } else {
                float t = Math.min(localAge, 32.0f);
                Vec3d homingForce = homing ? new Vec3d(toTarget.x * 0.0036, toTarget.y * 0.0022, toTarget.z * 0.0036) : new Vec3d(Math.sin((float)i * 1.7f + t * 0.12f) * (double)0.002f, 0.0, Math.cos((float)i * 1.3f + t * 0.11f) * (double)0.002f);
                double gravity = homing ? 0.024 : 0.036 + (double)(i % 5) * 0.004;
                position = spawn.add(baseVelocity.multiply((double)t)).add(homingForce.multiply((double)(t * t) * 0.5)).add(0.0, -gravity * (double)t * (double)t, 0.0);
                velocity = baseVelocity.add(homingForce.multiply((double)t)).add(0.0, -2.0 * gravity * (double)t, 0.0);
            }
            if (position.y < 0.02) {
                position = new Vec3d(position.x, 0.02, position.z);
            }
            if ((alpha = (fadeIn = MathHelper.clamp((float)((localAge + 10.0f) / 10.0f), (float)0.0f, (float)1.0f)) * (fadeOut = position.y <= 1.1 ? (float)MathHelper.clamp((double)(position.y / 1.1), (double)0.0, (double)1.0) : 1.0f) * castFade) <= 0.01f) continue;
            float yaw = (float)Math.atan2(velocity.x, velocity.z);
            float pitch = (float)(-Math.atan2(velocity.y, Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z)));
            float spinX = localAge * (homing ? 7.0f : 12.0f + (float)(i % 7) * 1.7f) + (float)i * 18.0f;
            float spinY = localAge * (9.0f + (float)(i % 5) * 1.4f) + (float)i * 23.0f;
            float spinZ = localAge * (16.0f + (float)(i % 6) * 2.1f) + (float)i * 11.0f;
            float scale = 0.9f + (float)(i % 4) * 0.08f;
            visuals.add(new PickVisual(position, velocity, alpha, yaw, pitch, spinX, spinY, spinZ, scale, homing));
        }
        return visuals;
    }

    private static void drawGroundAura(MatrixStack matrices, VertexConsumer vc, float age, int activePicks) {
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        float envelope = Math.min(MathHelper.clamp((float)(age / 12.0f), (float)0.0f, (float)1.0f), MathHelper.clamp((float)((100.0f - age) / 14.0f), (float)0.0f, (float)1.0f));
        int alpha = MathHelper.clamp((int)((int)((26.0f + (float)activePicks) * envelope)), (int)0, (int)115);
        float radius = 10.8f + MathHelper.sin((float)(age * 0.07f)) * 0.25f;
        for (int ring = 0; ring < 4; ++ring) {
            float inner = radius - (float)ring * 1.35f;
            float outer = inner + 0.95f;
            for (int i = 0; i < 72; ++i) {
                float a1 = (float)Math.PI * 2 * (float)i / 72.0f;
                float a2 = (float)Math.PI * 2 * (float)(i + 1) / 72.0f;
                RainOfPicksRenderer.addQuad(vc, position, normal, MathHelper.cos((float)a1) * inner, 0.03f, MathHelper.sin((float)a1) * inner, MathHelper.cos((float)a2) * inner, 0.03f, MathHelper.sin((float)a2) * inner, MathHelper.cos((float)a2) * outer, 0.03f, MathHelper.sin((float)a2) * outer, MathHelper.cos((float)a1) * outer, 0.03f, MathHelper.sin((float)a1) * outer, 185, 35, 35, Math.max(0, alpha - ring * 12));
            }
        }
    }

    private static void drawPick(MatrixStack matrices, VertexConsumer vc, PickVisual pick) {
        matrices.push();
        matrices.translate(pick.position.x, pick.position.y, pick.position.z);
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotation(pick.yaw));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotation(pick.pitch));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_X.rotationDegrees(pick.spinX));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Y.rotationDegrees(pick.spinY));
        matrices.multiply((Quaternionfc)RotationAxis.POSITIVE_Z.rotationDegrees(pick.spinZ));
        matrices.scale(pick.scale, pick.scale, pick.scale);
        Matrix4f position = matrices.peek().getPositionMatrix();
        Matrix3f normal = matrices.peek().getNormalMatrix();
        int alpha = MathHelper.clamp((int)((int)(pick.alpha * 255.0f)), (int)0, (int)255);
        int glowAlpha = MathHelper.clamp((int)((int)(pick.alpha * (pick.homing ? 160.0f : 120.0f))), (int)0, (int)255);
        RainOfPicksRenderer.drawTrail(vc, position, normal, pick, glowAlpha);
        RainOfPicksRenderer.drawHandle(vc, position, normal, alpha);
        RainOfPicksRenderer.drawHead(vc, position, normal, alpha, glowAlpha);
        if (pick.position.y < 2.4) {
            RainOfPicksRenderer.drawImpactHint(vc, position, normal, 0.4f + (2.4f - (float)pick.position.y) * 0.18f, glowAlpha);
        }
        matrices.pop();
    }

    private static void drawTrail(VertexConsumer vc, Matrix4f position, Matrix3f normal, PickVisual pick, int alpha) {
        double speed = pick.velocity.length();
        float trailLen = 0.85f + (float)Math.min(1.6, speed * 0.55);
        float width = pick.homing ? 0.17f : 0.13f;
        for (int i = 0; i < 14; ++i) {
            float t1 = (float)i / 14.0f;
            float t2 = (float)(i + 1) / 14.0f;
            float z1 = trailLen * t1;
            float z2 = trailLen * t2;
            float w1 = width * (1.0f - t1 * 0.8f);
            float w2 = width * (1.0f - t2 * 0.8f);
            int a1 = Math.max(0, (int)((float)alpha * (1.0f - t1 * 0.65f)));
            int a2 = Math.max(0, (int)((float)alpha * (1.0f - t2 * 0.65f)));
            RainOfPicksRenderer.addQuadGradient(vc, position, normal, -w1, 0.08f, z1, w1, 0.08f, z1, w2, 0.02f, z2, -w2, 0.02f, z2, 255, 85, 85, a1, 255, 220, 240, a2);
        }
    }

    private static void drawHandle(VertexConsumer vc, Matrix4f position, Matrix3f normal, int alpha) {
        RainOfPicksRenderer.addPrism(vc, position, normal, -0.08f, -0.08f, -0.9f, 0.08f, 0.08f, 0.95f, 118, 78, 40, alpha);
    }

    private static void drawHead(VertexConsumer vc, Matrix4f position, Matrix3f normal, int alpha, int glowAlpha) {
        RainOfPicksRenderer.addPrism(vc, position, normal, -0.42f, -0.09f, -0.1f, 0.42f, 0.09f, 0.18f, 205, 214, 230, alpha);
        RainOfPicksRenderer.addPrism(vc, position, normal, -0.1f, -0.12f, 0.04f, 0.1f, 0.12f, 0.52f, 185, 194, 214, alpha);
        RainOfPicksRenderer.addPrism(vc, position, normal, -0.5f, -0.02f, 0.12f, -0.18f, 0.16f, 0.3f, 238, 242, 250, alpha);
        RainOfPicksRenderer.addPrism(vc, position, normal, 0.18f, -0.02f, 0.12f, 0.5f, 0.16f, 0.3f, 238, 242, 250, alpha);
        RainOfPicksRenderer.addPrism(vc, position, normal, -0.46f, -0.14f, 0.02f, 0.46f, 0.14f, 0.08f, 255, 90, 90, glowAlpha);
    }

    private static void drawImpactHint(VertexConsumer vc, Matrix4f position, Matrix3f normal, float radius, int alpha) {
        for (int i = 0; i < 24; ++i) {
            float a1 = (float)Math.PI * 2 * (float)i / 24.0f;
            float a2 = (float)Math.PI * 2 * (float)(i + 1) / 24.0f;
            RainOfPicksRenderer.addTriangle(vc, position, normal, 0.0f, -0.02f, 0.0f, MathHelper.cos((float)a1) * radius, -0.02f, MathHelper.sin((float)a1) * radius, MathHelper.cos((float)a2) * radius, -0.02f, MathHelper.sin((float)a2) * radius, 255, 235, 235, alpha / 3);
        }
    }

    private static void addPrism(VertexConsumer vc, Matrix4f position, Matrix3f normal, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, int r, int g, int b, int a) {
        RainOfPicksRenderer.addQuad(vc, position, normal, minX, minY, minZ, maxX, minY, minZ, maxX, maxY, minZ, minX, maxY, minZ, r, g, b, a);
        RainOfPicksRenderer.addQuad(vc, position, normal, maxX, minY, maxZ, minX, minY, maxZ, minX, maxY, maxZ, maxX, maxY, maxZ, r, g, b, a);
        RainOfPicksRenderer.addQuad(vc, position, normal, minX, minY, maxZ, minX, minY, minZ, minX, maxY, minZ, minX, maxY, maxZ, r, g, b, a);
        RainOfPicksRenderer.addQuad(vc, position, normal, maxX, minY, minZ, maxX, minY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, r, g, b, a);
        RainOfPicksRenderer.addQuad(vc, position, normal, minX, maxY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, minX, maxY, maxZ, r, g, b, a);
        RainOfPicksRenderer.addQuad(vc, position, normal, minX, minY, maxZ, maxX, minY, maxZ, maxX, minY, minZ, minX, minY, minZ, r, g, b, a);
    }

    private static void addQuadGradient(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int r1, int g1, int b1, int a1, int r2, int g2, int b2, int a2) {
        RainOfPicksRenderer.addVertex(vc, position, normal, x1, y1, z1, r1, g1, b1, a1);
        RainOfPicksRenderer.addVertex(vc, position, normal, x2, y2, z2, r1, g1, b1, a1);
        RainOfPicksRenderer.addVertex(vc, position, normal, x3, y3, z3, r2, g2, b2, a2);
        RainOfPicksRenderer.addVertex(vc, position, normal, x1, y1, z1, r1, g1, b1, a1);
        RainOfPicksRenderer.addVertex(vc, position, normal, x3, y3, z3, r2, g2, b2, a2);
        RainOfPicksRenderer.addVertex(vc, position, normal, x4, y4, z4, r2, g2, b2, a2);
    }

    private static void addQuad(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4, int r, int g, int b, int a) {
        RainOfPicksRenderer.addVertex(vc, position, normal, x1, y1, z1, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x2, y2, z2, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x3, y3, z3, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x1, y1, z1, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x3, y3, z3, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x4, y4, z4, r, g, b, a);
    }

    private static void addTriangle(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, int r, int g, int b, int a) {
        RainOfPicksRenderer.addVertex(vc, position, normal, x1, y1, z1, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x2, y2, z2, r, g, b, a);
        RainOfPicksRenderer.addVertex(vc, position, normal, x3, y3, z3, r, g, b, a);
    }

    private static void addVertex(VertexConsumer vc, Matrix4f position, Matrix3f normal, float x, float y, float z, int r, int g, int b, int a) {
        vc.vertex(position, x, y, z).color(r, g, b, a).texture(0.0f, 0.0f).overlay(OverlayTexture.DEFAULT_UV).light(0xF000F0).normal(0.0f, 1.0f, 0.0f);
    }

    @Environment(value=EnvType.CLIENT)
    private record PickVisual(Vec3d position, Vec3d velocity, float alpha, float yaw, float pitch, float spinX, float spinY, float spinZ, float scale, boolean homing) {
    }
}

