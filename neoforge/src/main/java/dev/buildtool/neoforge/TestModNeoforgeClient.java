package dev.buildtool.neoforge;

import dev.buildtool.CommonClass;
import dev.buildtool.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(value = CommonClass.ID,dist = Dist.CLIENT)
public class TestModNeoforgeClient {

    public TestModNeoforgeClient(IEventBus eventBus) {
        eventBus.addListener(this::generateData);
    }

    private void generateData(GatherDataEvent event)
    {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        dataGenerator.addProvider(true, new ItemModelProvider(packOutput, CommonClass.ID, existingFileHelper) {
            @Override
            protected void registerModels() {
                basicItem(ModItems.PROPELLER.get());
                basicItem(ModItems.SEMISTEEL.get());
            }
        });
        dataGenerator.addProvider(true, new LanguageProvider(packOutput,CommonClass.ID,"en_us") {
            @Override
            protected void addTranslations() {
                addItem(ModItems.PROPELLER,"Propeller");
                addItem(ModItems.SEMISTEEL,"Semi-steel");
            }
        });
    }
}
