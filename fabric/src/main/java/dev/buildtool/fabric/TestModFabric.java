package dev.buildtool.fabric;

import dev.buildtool.CommonClass;
import dev.buildtool.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public class TestModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.run();
        ModItems.ITEMS.forEach((string, itemSupplier) -> Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(CommonClass.ID,string),itemSupplier.get()));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,ResourceLocation.fromNamespaceAndPath(CommonClass.ID,"tab"),CommonClass.TAB.get());
    }
}
