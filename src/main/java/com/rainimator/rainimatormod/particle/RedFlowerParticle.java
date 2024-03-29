package com.rainimator.rainimatormod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class RedFlowerParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected RedFlowerParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.2F, 0.2F);
        this.quadSize *= 0.5F;
        this.lifetime = Math.max(1, 21 + this.random.nextInt(56) - 28);
        this.gravity = 0.5F;
        this.hasPhysics = false;
        this.xd = vx * 0.5D;
        this.yd = vy * 0.5D;
        this.zd = vz * 0.5D;
        this.setSpriteFromAge(spriteSet);
    }

    public static RedFlowerParticleProvider provider(SpriteSet spriteSet) {
        return new RedFlowerParticleProvider(spriteSet);
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
            this.setSprite(this.spriteSet.get(this.age % 6 + 1, 6));
    }

    public static class RedFlowerParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public RedFlowerParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new RedFlowerParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}