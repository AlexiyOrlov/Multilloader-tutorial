package dev.buildtool.forge;

import dev.buildtool.CommonClass;
import dev.buildtool.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(CommonClass.ID)
public class TestModForge {

    static final DeferredRegister<Item> ITEMS=DeferredRegister.create(Registries.ITEM,CommonClass.ID);
    static final DeferredRegister<CreativeModeTab> TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,CommonClass.ID);
    public TestModForge() {
        IEventBus eventBus= FMLJavaModLoadingContext.get().getModEventBus();
        CommonClass.run();
        ModItems.ITEMS.forEach(ITEMS::register);
        ITEMS.register(eventBus);
        TABS.register("tag",CommonClass.TAB);
        TABS.register(eventBus);
    }
}