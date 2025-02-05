package dev.buildtool.fabric;

import dev.buildtool.BlockTransporter;
import dev.buildtool.CommonClass;
import dev.buildtool.ModDataComponents;
import dev.buildtool.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;

public class TestModFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.run();
        ModItems.ITEMS.forEach((string, itemSupplier) -> Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(CommonClass.ID,string),itemSupplier.get()));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,ResourceLocation.fromNamespaceAndPath(CommonClass.ID,"tab"),CommonClass.TAB.get());
        ModDataComponents.DATA_COMPONENTS.forEach((string, dataComponentType) -> Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,ResourceLocation.fromNamespaceAndPath(CommonClass.ID,string),dataComponentType));

        UseBlockCallback.EVENT.register((player, level, interactionHand, blockHitResult) -> {
            ItemStack itemStack=player.getItemInHand(interactionHand);
            InteractionResult success = BlockTransporter.storeBlock(level, blockHitResult.getBlockPos(), itemStack);
            if (success != null) return success;
            return InteractionResult.PASS;
        });
    }

}
