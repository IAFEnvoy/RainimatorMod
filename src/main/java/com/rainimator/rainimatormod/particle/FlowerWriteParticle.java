package com.rainimator.rainimatormod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FlowerWriteParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected FlowerWriteParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2F, 0.2F);

        this.lifetime = Math.max(1, 35 + this.random.nextInt(42) - 21);
        this.gravity = 0.0F;
        this.hasPhysics = false;
        this.xd = vx * 0.3D;
        this.yd = vy * 0.3D;
        this.zd = vz * 0.3D;
        this.setSpriteFromAge(spriteSet);
    }

    public static FlowerWriteParticleProvider provider(SpriteSet spriteSet) {
        return new FlowerWriteParticleProvider(spriteSet);
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
            this.setSprite(this.spriteSet.get(this.age / 2 % 8 + 1, 8));
    }

    public static class FlowerWriteParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public FlowerWriteParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new FlowerWriteParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}