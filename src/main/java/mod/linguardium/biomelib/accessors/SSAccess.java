package mod.linguardium.biomelib.accessors;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;

import java.util.List;
import java.util.Map;

public interface SSAccess {

    public void BiomeLib$setSpawners(Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> map);
    public Map<SpawnGroup, List<SpawnSettings.SpawnEntry>> BiomeLib$getSpawners();

}
