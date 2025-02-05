package dev.buildtool;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class ModDataComponents {
    public static final HashMap<String, DataComponentType<?>> DATA_COMPONENTS=new HashMap<>();

    public record AttachedBlock(BlockState blockState, CompoundTag blockData)
    {
        static final Codec<AttachedBlock> CODEC= RecordCodecBuilder.create(attachedBlockInstance -> attachedBlockInstance.group(
                BlockState.CODEC.fieldOf("blockState").forGetter(o -> o.blockState),
                CompoundTag.CODEC.fieldOf("blockData").forGetter(o -> o.blockData)
        ).apply(attachedBlockInstance,AttachedBlock::new));
    }

    public static final DataComponentType<AttachedBlock> ATTACHED_BLOCK=DataComponentType.<AttachedBlock>builder().persistent(AttachedBlock.CODEC).build();

    static {
        DATA_COMPONENTS.put("attache_blocks",ATTACHED_BLOCK);
    }
}
