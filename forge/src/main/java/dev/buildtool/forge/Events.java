package dev.buildtool.forge;

import dev.buildtool.BlockTransporter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void rightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        ItemStack itemStack=event.getItemStack();
        BlockPos blockPos=event.getPos();
        Level level=event.getLevel();
        InteractionResult result=BlockTransporter.storeBlock(level,blockPos,itemStack);
        if(result==InteractionResult.SUCCESS)
            event.setCancellationResult(InteractionResult.SUCCESS);
    }
}
