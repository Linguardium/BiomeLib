package mod.linguardium.biomelib.mixin;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(GenerationSettings.class)
public interface GSAccess {
    @Accessor("features")
    public void BiomeLib$features(List<List<Supplier<ConfiguredFeature<?, ?>>>> list);
    @Accessor("carvers")
    public void BiomeLib$setCarvers(Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> map);
    @Accessor("carvers")
    public Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> BiomeLib$getCarvers();
    @Accessor("structureFeatures")
    public void BiomeLib$setStructureStarts(List<Supplier<ConfiguredStructureFeature<?, ?>>> list);


}
