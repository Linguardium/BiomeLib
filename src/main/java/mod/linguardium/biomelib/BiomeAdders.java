package mod.linguardium.biomelib;

import com.google.common.collect.*;
import mod.linguardium.biomelib.accessors.GSAccess;
import mod.linguardium.biomelib.accessors.SSAccess;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BiomeAdders {
    private static class BiomeFeature {
        GenerationStep.Feature feature;
        ConfiguredFeature<?,?> configuredFeature;
        Function<Biome, Boolean> predicate;
        BiomeFeature(GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature, Function<Biome, Boolean> predicate) {
            this.feature=feature;
            this.configuredFeature=configuredFeature;
            this.predicate=predicate;
        }
    }
    private static class StructureFeature {
        ConfiguredStructureFeature<?, ?> configuredStructureFeature;
        Function<Biome, Boolean> predicate;
        StructureFeature( ConfiguredStructureFeature<?, ?> structureFeature, Function<Biome, Boolean> predicate) {
            this.configuredStructureFeature=structureFeature;
            this.predicate=predicate;
        }

    }
    private static List<BiomeFeature> BiomeFeatures = new ArrayList<>();
    private static List<StructureFeature> StructureFeatures = new ArrayList<>();
    public static void addFeatureToBiomes(net.minecraft.world.gen.GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature, Function<Biome, Boolean> predicate) {
        BiomeFeatures.add(new BiomeFeature(feature,configuredFeature,predicate));
        for (Biome biome : BuiltinRegistries.BIOME) {
            if (predicate.apply(biome))
                addFeatureToBiome(biome,feature,configuredFeature);
        }
    }
    public static void addStructureToBiomes(ConfiguredStructureFeature<?, ?> configuredFeature, Function<Biome, Boolean> predicate) {
        StructureFeatures.add(new StructureFeature(configuredFeature,predicate));
        for (Biome biome : BuiltinRegistries.BIOME) {
            if (predicate.apply(biome))
                addStructureFeatureToBiome(biome,configuredFeature);
        }
    }
    public static void addFeatureToBiome(Biome biome, net.minecraft.world.gen.GenerationStep.Feature feature, ConfiguredFeature<?, ?> configuredFeature) {
        ConvertImmutableFeatures(biome);
        List l = biome.method_30970().method_30983();
        while (l.size()<feature.ordinal()+1)
            l.add(Lists.newArrayList());
        biome.method_30970().method_30983().get(feature.ordinal()).add(()->configuredFeature);
    }
    public static void addStructureFeatureToBiome(Biome biome, ConfiguredStructureFeature<?, ?> configuredStructureFeature ) {
        ConvertImmutableStructureFeatures(biome);
        biome.method_30970().method_30975().add(()->configuredStructureFeature);
    }
    public static void addSpawnToBiome(Biome biome, SpawnGroup group, SpawnSettings.SpawnEntry entry) {
        ConvertImmutableSpawnList(biome);
        if (!((SSAccess)biome.method_30966()).BiomeLib$getSpawners().containsKey(group)) {
            ((SSAccess)biome.method_30966()).BiomeLib$getSpawners().put(group,Lists.newArrayList());
        }
        biome.method_30966().method_31004(group).add(entry);
    }
    public static <C extends CarverConfig> void addCarverToBiome(Biome biome, GenerationStep.Carver carver, ConfiguredCarver<C> configuredCarver) {
        ConvertImmutableCarvers(biome);
        ((GSAccess)biome.method_30970()).BiomeLib$getCarvers().computeIfAbsent(carver,(a)->Lists.newArrayList()).add(()->configuredCarver);

    }
    private static void ConvertImmutableSpawnList(Biome biome) {
        if (((SSAccess)biome.method_30966()).BiomeLib$getSpawners() instanceof ImmutableMap) {
            HashMap<SpawnGroup, List<SpawnSettings.SpawnEntry>> map = Maps.newHashMap();

            ((SSAccess)biome.method_30966()).BiomeLib$getSpawners().forEach((k,v)->{
                map.put(k,Lists.newArrayList(v));
            });
            ((SSAccess)biome.method_30966()).BiomeLib$setSpawners(map);
        }
    }
    private static void ConvertImmutableStructureFeatures(Biome biome) {
        if (biome.method_30970().method_30975() instanceof ImmutableList) {
            ((GSAccess)biome.method_30970()).BiomeLib$setStructureStarts(Lists.newArrayList(biome.method_30970().method_30975()));
        }
    }
    private static void ConvertImmutableCarvers(Biome biome) {
        if (((GSAccess)biome.method_30970()).BiomeLib$getCarvers() instanceof ImmutableMap) {
            HashBiMap<GenerationStep.Carver, List<Supplier<ConfiguredCarver<?>>>> map = HashBiMap.create();
            ((GSAccess)biome.method_30970()).BiomeLib$getCarvers().forEach((k,v)-> {
                map.put(k, Lists.newArrayList(v));
            });
            ((GSAccess)biome.method_30970()).BiomeLib$setCarvers(map);
        }
    }
    private static void ConvertImmutableFeatures(Biome biome) {
        if (biome.method_30970().method_30983() instanceof ImmutableList) {
            ((GSAccess)biome.method_30970()).BiomeLib$features(biome.method_30970().method_30983().stream().map(Lists::newArrayList).collect(Collectors.toList()));
        }
    }


    public static void init() {
        RegistryEntryAddedCallback.event(BuiltinRegistries.BIOME).register((i, id, biome)->{
            for (BiomeFeature f : BiomeFeatures) {
                if (f.predicate.apply(biome))
                    addFeatureToBiome(biome,f.feature,f.configuredFeature);
            }
            for (StructureFeature f : StructureFeatures) {
                if (f.predicate.apply(biome))
                    addStructureFeatureToBiome(biome,f.configuredStructureFeature);
            }
        });

    }

}
