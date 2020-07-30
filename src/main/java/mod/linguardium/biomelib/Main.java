package mod.linguardium.biomelib;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraft.world.gen.feature.ConfiguredStructureFeatures.END_CITY;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "biomelib";
    public static final String MOD_NAME = "BiomeCompatLib";

    @Override
    public void onInitialize() {
        //log(Level.INFO, "Initializing");
        //TODO: Initializer
        /*for (Biome biome : BuiltinRegistries.BIOME) {
            System.out.println(biome.toString() + ": " + String.valueOf(biome.method_30970().features.size()));
        }
        BiomeAdders.addFeatureToBiome(Biomes.PLAINS, GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TREES_SAVANNA);
        BiomeAdders.addStructureFeatureToBiome(Biomes.PLAINS,END_CITY);
        BiomeAdders.addSpawnToBiome(Biomes.PLAINS, SpawnGroup.MONSTER,new SpawnSettings.SpawnEntry(EntityType.HOGLIN,200,2,3));
        BiomeAdders.addCarverToBiome(Biomes.PLAINS, GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE);
*/
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}