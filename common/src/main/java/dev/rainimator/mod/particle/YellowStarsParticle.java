package dev.rainimator.mod.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class YellowStarsParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteSet;

    protected YellowStarsParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setBoundingBoxSpacing(0.2F, 0.2F);

        this.maxAge = 42;
        this.gravityStrength = 0.0F;
        this.collidesWithWorld = true;
        this.velocityX = vx * 0.0D;
        this.velocityY = vy * 0.0D;
        this.velocityZ = vz * 0.0D;
        this.setSpriteForAge(spriteSet);
    }

    public static YellowStearsParticleProvider provider(SpriteProvider spriteSet) {
        return new YellowStearsParticleProvider(spriteSet);
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
            this.setSprite(this.spriteSet.getSprite(this.age / 2 % 7 + 1, 7));
    }

    public static class YellowStearsParticleProvider implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteSet;

        public YellowStearsParticleProvider(SpriteProvider spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(DefaultParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new YellowStarsParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}