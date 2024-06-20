package dev.rainimator.mod.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class EnderDaggerParticle extends SpriteBillboardParticle {
    protected EnderDaggerParticle(ClientWorld world, double x, double y, double z, double vx, double vy, double vz, SpriteProvider spriteSet) {
        super(world, x, y, z);
        this.setBoundingBoxSpacing(0.2F, 0.2F);
        this.scale *= 8.0F;
        this.maxAge = Math.max(1, 21 + this.random.nextInt(42) - 21);
        this.gravityStrength = 0.0F;
        this.collidesWithWorld = true;
        this.velocityX = vx * 0.7D;
        this.velocityY = vy * 0.7D;
        this.velocityZ = vz * 0.7D;
        this.setSprite(spriteSet);
    }

    public static EnderDaggerParticleProvider provider(SpriteProvider spriteSet) {
        return new EnderDaggerParticleProvider(spriteSet);
    }

    @Override
    public int getBrightness(float partialTick) {
        return 15728880;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_LIT;
    }

    public static class EnderDaggerParticleProvider implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteSet;

        public EnderDaggerParticleProvider(SpriteProvider spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(DefaultParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            return new EnderDaggerParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}