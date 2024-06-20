package dev.rainimator.mod.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class LightingArcParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteSet;

    protected LightingArcParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteSet) {
        super(world, x, y, z);
        this.spriteSet = spriteSet;
        this.setBoundingBoxSpacing(0.2F, 0.2F);
        this.scale *= 2.0F;
        this.maxAge = Math.max(1, 28 + this.random.nextInt(70) - 35);
        this.gravityStrength = 1.0F;
        this.collidesWithWorld = true;
        this.velocityX = vx * 0.8D;
        this.velocityY = vy * 0.8D;
        this.velocityZ = vz * 0.8D;
        this.setSpriteForAge(spriteSet);
    }

    public static LightingArcParticleProvider provider(SpriteProvider spriteSet) {
        return new LightingArcParticleProvider(spriteSet);
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
            this.setSprite(this.spriteSet.getSprite(this.age % 4 + 1, 4));
    }

    public static class LightingArcParticleProvider implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteSet;

        public LightingArcParticleProvider(SpriteProvider spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(DefaultParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new LightingArcParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}