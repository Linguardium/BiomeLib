package mod.linguardium.biomelib;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "biomelib";
    public static final String MOD_NAME = "BiomeCompatLib";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        //TODO: Initializer
        BiomeAdders.addFeatureToBiome(Biomes.PLAINS, GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.TREES_SAVANNA);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}