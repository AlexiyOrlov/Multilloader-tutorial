package dev.buildtool;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.buildtool.platform.Services;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {
    public static final String ID="testmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);
    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static final Supplier<CreativeModeTab> TAB= Suppliers.memoize(() -> CreativeModeTab.builder(CreativeModeTab.Row.TOP,0).icon(() -> new ItemStack(ModItems.PROPELLER.get())).title(Component.literal("Test mod")).displayItems((itemDisplayParameters, output) -> {
        output.accept(ModItems.PROPELLER.get());
        output.accept(ModItems.SEMISTEEL.get());
        output.accept(ModItems.BLOCK_TRANSPORTER.get());
    }).build());

    public static void run() {
        LOGGER.info("Platform - {}", Services.PLATFORM.getClass().getSimpleName());
    }
}