package dev.buildtool;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;

public class ModItems {
    public static final HashMap<String, Supplier<Item>> ITEMS=new HashMap<>();

    public static final Supplier<Item> PROPELLER = Suppliers.memoize(() -> new Item(new Item.Properties()));
    public static final Supplier<Item> SEMISTEEL =Suppliers.memoize(() -> new Item(new Item.Properties()));
    public static final Supplier<Item> BLOCK_TRANSPORTER=Suppliers.memoize(() -> new BlockTransporter(new Item.Properties()));

    static {
        ITEMS.put("propeller", PROPELLER);
        ITEMS.put("semi_steel", SEMISTEEL);
        ITEMS.put("block_transporter",BLOCK_TRANSPORTER);
    }
}
