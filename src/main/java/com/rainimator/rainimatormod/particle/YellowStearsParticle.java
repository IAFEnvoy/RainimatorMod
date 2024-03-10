package com.rainimator.rainimatormod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class YellowStearsParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected YellowStearsParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2F, 0.2F);

        this.lifetime = 42;
        this.gravity = 0.0F;
        this.hasPhysics = true;
        this.xd = vx * 0.0D;
        this.yd = vy * 0.0D;
        this.zd = vz * 0.0D;
        this.setSpriteFromAge(spriteSet);
    }

    public static YellowStearsParticleProvider provider(SpriteSet spriteSet) {
        return new YellowStearsParticleProvider(spriteSet);
    }

    @Override
    public int getLightColor(float partialTick) {
        return 15728880;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_LIT;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed)
            this.setSprite(this.spriteSet.get(this.age / 2 % 7 + 1, 7));
    }

    public static class YellowStearsParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public YellowStearsParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new YellowStearsParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}