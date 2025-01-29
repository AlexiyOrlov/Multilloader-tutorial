package dev.buildtool.neoforge;

import dev.buildtool.CommonClass;
import dev.buildtool.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(CommonClass.ID)
public class TestModNeoforge {
    static final DeferredRegister<Item> ITEMS=DeferredRegister.createItems(CommonClass.ID);
    static final DeferredRegister<CreativeModeTab> TABS=DeferredRegister.create(Registries.CREATIVE_MODE_TAB,CommonClass.ID);

    public TestModNeoforge(IEventBus eventBus) {
        CommonClass.run();
        ModItems.ITEMS.forEach(ITEMS::register);
        ITEMS.register(eventBus);
        TABS.register("tab",CommonClass.TAB);
        TABS.register(eventBus);
    }
}
