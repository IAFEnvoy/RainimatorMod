package com.rainimator.rainimatormod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class PurplelightParticle extends TextureSheetParticle {
    private final SpriteSet spriteSet;

    protected PurplelightParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setSize(0.5F, 0.5F);

        this.lifetime = Math.max(1, 21 + this.random.nextInt(56) - 28);
        this.gravity = 0.0F;
        this.hasPhysics = false;
        this.xd = vx * 0.3D;
        this.yd = vy * 0.3D;
        this.zd = vz * 0.3D;
        this.setSpriteFromAge(spriteSet);
    }

    public static PurplelightParticleProvider provider(SpriteSet spriteSet) {
        return new PurplelightParticleProvider(spriteSet);
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
            this.setSprite(this.spriteSet.get(this.age % 11 + 1, 11));
    }

    public static class PurplelightParticleProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public PurplelightParticleProvider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(@NotNull SimpleParticleType typeIn, @NotNull ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new PurplelightParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}