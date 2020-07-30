package mod.linguardium.biomelib;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BiomeAdders {
    public static void addFeatureToBiome(Biome biome, net.minecraft.world.gen.GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature) {
        ConvertImmutableFeatures(biome);
        biome.method_30970().features.get(feature.ordinal()).add(()->configuredFeature);
    }
    private static void ConvertImmutableFeatures(Biome biome) {
        if (biome.method_30970().features instanceof ImmutableList) {
            List<List<Supplier<ConfiguredFeature<?,?>>>> l = biome.method_30970().features.stream().map(Lists::newArrayList).collect(Collectors.toList());
            biome.method_30970().features=l;
        }
    }
}
