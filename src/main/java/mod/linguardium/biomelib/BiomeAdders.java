package mod.linguardium.biomelib;

import com.google.common.collect.*;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BiomeAdders {
    public static void addFeatureToBiome(Biome biome, net.minecraft.world.gen.GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature) {
        ConvertImmutableFeatures(biome);
        List l = biome.method_30970().features;
        while (l.size()<feature.ordinal()+1)
            l.add(Lists.newArrayList());
        biome.method_30970().features.get(feature.ordinal()).add(()->configuredFeature);
    }
    public static void addStructureFeatureToBiome(Biome biome, ConfiguredStructureFeature<?, ?> configuredStructureFeature ) {
        ConvertImmutableStructureFeatures(biome);
        biome.method_30970().starts.add(()->configuredStructureFeature);
    }
    public static void addSpawnToBiome(Biome biome, SpawnGroup group, SpawnSettings.SpawnEntry entry) {
        ConvertImmutableSpawnList(biome);
        if (!biome.method_30966().spawners.containsKey(group)) {
            biome.method_30966().spawners.put(group,Lists.newArrayList());
        }
        biome.method_30966().method_31004(group).add(entry);
    }
    public static <C extends CarverConfig> void addCarverToBiome(Biome biome, GenerationStep.Carver carver, ConfiguredCarver<C> configuredCarver) {
        ConvertImmutableCarvers(biome);
        biome.method_30970().carvers.computeIfAbsent(carver,(a)->Lists.newArrayList()).add(()->configuredCarver);

    }
    private static void ConvertImmutableSpawnList(Biome biome) {
        if (biome.method_30966().spawners instanceof ImmutableMap) {
            HashMap<SpawnGroup, List<SpawnSettings.SpawnEntry>> map = Maps.newHashMap();

            biome.method_30966().spawners.forEach((k,v)->{
                map.put(k,Lists.newArrayList(v));
            });
            biome.method_30966().spawners=map;
        }
    }
    private static void ConvertImmutableStructureFeatures(Biome biome) {
        if (biome.method_30970().starts instanceof ImmutableList) {
            biome.method_30970().starts = Lists.newArrayList(biome.method_30970().starts);
        }
    }
    private static void ConvertImmutableCarvers(Biome biome) {
        if (biome.method_30970().carvers instanceof ImmutableMap) {
            HashBiMap<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> map = HashBiMap.create();
            biome.method_30970().carvers.forEach((k,v)-> {
                map.put(k, Lists.newArrayList(v));
            });
            biome.method_30970().carvers=map;
        }
    }
    private static void ConvertImmutableFeatures(Biome biome) {
        if (biome.method_30970().features instanceof ImmutableList) {
            biome.method_30970().features= biome.method_30970().features.stream().map(Lists::newArrayList).collect(Collectors.toList());
        }
    }
}
