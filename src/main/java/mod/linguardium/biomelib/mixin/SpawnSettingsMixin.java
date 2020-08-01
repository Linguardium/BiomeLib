package mod.linguardium.biomelib.mixin;

import mod.linguardium.biomelib.accessors.SSAccess;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Map;

@Mixin(SpawnSettings.class)
public class SpawnSettingsMixin implements SSAccess {
    @Mutable
    @Shadow @Final private Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> spawners;

    @Override
    public void BiomeLib$setSpawners(Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> map) {
        spawners=map;
    }

    @Override
    public Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> BiomeLib$getSpawners() {
        return spawners;
    }
}
