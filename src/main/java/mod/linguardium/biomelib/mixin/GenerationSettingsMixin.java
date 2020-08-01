package mod.linguardium.biomelib.mixin;

import mod.linguardium.biomelib.accessors.GSAccess;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Mixin(GenerationSettings.class)
public class GenerationSettingsMixin implements GSAccess {
    @Mutable
    @Shadow @Final private List<List<Supplier<ConfiguredFeature<?, ?>>>> features;

    @Mutable
    @Shadow @Final private Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> carvers;

    @Mutable
    @Shadow @Final private List<Supplier<ConfiguredStructureFeature<?, ?>>> starts;

    @Override
    public void BiomeLib$features(List<List<Supplier<ConfiguredFeature<?, ?>>>> list) {
        features = list;
    }

    @Override
    public void BiomeLib$setCarvers(Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> map) {
        carvers=map;
    }

    @Override
    public Map<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> BiomeLib$getCarvers() {
        return carvers;
    }

    @Override
    public void BiomeLib$setStructureStarts(List<Supplier<ConfiguredStructureFeature<?, ?>>> list) {
        starts=list;
    }
}
