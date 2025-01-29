package dev.buildtool.forge.platform;

import dev.buildtool.platform.IPlatform;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatform implements IPlatform {

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isClient() {
        return FMLEnvironment.dist==Dist.CLIENT;
    }

    @Override
    public boolean isServer() {
        return FMLEnvironment.dist== Dist.DEDICATED_SERVER;
    }

    @Override
    public boolean isFabric() {
        return false;
    }

    @Override
    public boolean isNeoforge() {
        return false;
    }

    @Override
    public boolean isForge() {
        return true;
    }
}