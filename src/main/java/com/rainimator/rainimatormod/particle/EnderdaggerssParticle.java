package com.rainimator.rainimatormod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class EnderdaggerssParticle extends TextureSheetParticle {
    protected EnderdaggerssParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.setSize(0.2F, 0.2F);
        this.quadSize *= 8.0F;
        this.lifetime = Math.max(1, 21 + this.random.nextInt(42) - 21);
        this.gravity = 0.0F;
        this.hasPhysics = true;
        this.xd = vx * 0.7D;
        this.yd = vy * 0.7D;
        this.zd = vz * 0.7D;
        this.pickSprite(spriteSet);
    }

    public static EnderdaggerssParticleProvider provider(SpriteSet spriteSet) {
        return new EnderdaggerssParticleProvider(spriteSet);
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
    }

    public static class EnderdaggerssParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public EnderdaggerssParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EnderdaggerssParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}