package dev.buildtool.neoforge;

import dev.buildtool.BlockTransporter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;

@EventBusSubscriber
public class Events {

    @SubscribeEvent
    private static void onItemUse(UseItemOnBlockEvent event)
    {
        ItemStack stack=event.getItemStack();
        BlockPos blockPos=event.getPos();
        Level level=event.getLevel();
        InteractionResult result= BlockTransporter.storeBlock(level,blockPos,stack);
        if(result==InteractionResult.SUCCESS)
            event.cancelWithResult(ItemInteractionResult.SUCCESS);
    }
}
