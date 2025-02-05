package dev.buildtool;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockTransporter extends Item {
    public BlockTransporter(Properties properties) {
        super(properties);
    }

    public static @Nullable InteractionResult storeBlock(Level level, BlockPos clickedPos, ItemStack itemStack) {
        if(itemStack.is(ModItems.BLOCK_TRANSPORTER.get()))
        {
            if(!itemStack.has(ModDataComponents.ATTACHED_BLOCK))
            {
                BlockState blockState= level.getBlockState(clickedPos);
                if(blockState.hasBlockEntity())
                {
                    BlockEntity blockEntity= level.getBlockEntity(clickedPos);
                    CompoundTag compoundTag=blockEntity.saveWithoutMetadata(level.registryAccess());
                    ModDataComponents.AttachedBlock attachedBlock=new ModDataComponents.AttachedBlock(blockState,compoundTag);
                    itemStack.set(ModDataComponents.ATTACHED_BLOCK,attachedBlock);
                    level.removeBlockEntity(clickedPos);
                    level.destroyBlock(clickedPos,false);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if(stack.has(ModDataComponents.ATTACHED_BLOCK))
        {
            tooltipComponents.add(Component.literal("Has block"));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        ItemStack itemStack=context.getItemInHand();
        if(itemStack.has(ModDataComponents.ATTACHED_BLOCK))
        {
            ModDataComponents.AttachedBlock attachedBlock=itemStack.get(ModDataComponents.ATTACHED_BLOCK);
            Level level=context.getLevel();
            BlockPos clickedPos=context.getClickedPos();
            Direction clickedFace=context.getClickedFace();
            BlockPos placeAt=clickedPos.relative(clickedFace);
            level.setBlock(placeAt,attachedBlock.blockState(),2);
            BlockEntity blockEntity=level.getBlockEntity(placeAt);
            blockEntity.loadWithComponents(attachedBlock.blockData(),level.registryAccess());
            itemStack.remove(ModDataComponents.ATTACHED_BLOCK);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
