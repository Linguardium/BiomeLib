package mod.linguardium.biomelib.mixin;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;
@Mixin(SpawnSettings.class)
public interface SSAccess {
    @Accessor("spawners")
    public void BiomeLib$setSpawners(Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> map);
    @Accessor("spawners")
    public Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> BiomeLib$getSpawners();

}
