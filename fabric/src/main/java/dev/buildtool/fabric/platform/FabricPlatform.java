package dev.buildtool.fabric.platform;

import dev.buildtool.platform.IPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatform implements IPlatform {

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType()== EnvType.CLIENT;
    }

    @Override
    public boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType()==EnvType.SERVER;
    }

    @Override
    public boolean isFabric() {
        return true;
    }

    @Override
    public boolean isNeoforge() {
        return false;
    }

    @Override
    public boolean isForge() {
        return false;
    }
}
