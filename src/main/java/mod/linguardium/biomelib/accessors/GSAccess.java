package mod.linguardium.biomelib.accessors;

import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface GSAccess {
    public void BiomeLib$features(List<List<Supplier<ConfiguredFeature<?, ?>>>> list);
    public void BiomeLib$setCarvers(Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> map);
    public Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> BiomeLib$getCarvers();
    public void BiomeLib$setStructureStarts(List<Supplier<ConfiguredStructureFeature<?, ?>>> list);
}
