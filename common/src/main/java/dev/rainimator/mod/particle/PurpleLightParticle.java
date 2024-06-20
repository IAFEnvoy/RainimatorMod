package dev.rainimator.mod.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class PurpleLightParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteSet;

    protected PurpleLightParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setBoundingBoxSpacing(0.5F, 0.5F);

        this.maxAge = Math.max(1, 21 + this.random.nextInt(56) - 28);
        this.gravityStrength = 0.0F;
        this.collidesWithWorld = false;
        this.velocityX = vx * 0.3D;
        this.velocityY = vy * 0.3D;
        this.velocityZ = vz * 0.3D;
        this.setSpriteForAge(spriteSet);
    }

    public static PurpleLightParticleProvider provider(SpriteProvider spriteSet) {
        return new PurpleLightParticleProvider(spriteSet);
    }

    @Override
    public int getBrightness(float partialTick) {
        return 15728880;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.dead)
            this.setSprite(this.spriteSet.getSprite(this.age % 11 + 1, 11));
    }

    public static class PurpleLightParticleProvider implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteSet;

        public PurpleLightParticleProvider(SpriteProvider spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(DefaultParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new PurpleLightParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}