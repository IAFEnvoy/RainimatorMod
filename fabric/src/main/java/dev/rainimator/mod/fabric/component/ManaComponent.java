package dev.rainimator.mod.fabric.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ManaComponent implements ComponentV3, AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<ManaComponent> MANA_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(Identifier.of(RainimatorMod.MOD_ID, "mana"), ManaComponent.class);

    private final LivingEntity entity;
    private final ManaData data;

    public ManaComponent(LivingEntity entity) {
        this.entity = entity;
        this.data = new ManaData(entity);
    }

    public static boolean tryUse(PlayerEntity player, double amount) {
        if (player.isCreative()) return true;
        if (ManaComponent.MANA_COMPONENT.get(player).data.tryUseMana(amount)) return true;
        player.sendMessage(Text.translatable("message.rainimator.mana.not_enough"), true);
        return false;
    }

    public LivingEntity getEntity() {
        return this.entity;
    }

    public ManaData getData() {
        return this.data;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.data.decode(tag);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        this.data.encode(tag);
    }

    @Override
    public void tick() {
        this.data.tick();
    }
}
