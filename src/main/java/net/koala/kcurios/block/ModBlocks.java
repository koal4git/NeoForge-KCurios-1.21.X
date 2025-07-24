package net.koala.kcurios.block;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.custom.AmethystLampBlock;
import net.koala.kcurios.block.custom.MagicBlock;
import net.koala.kcurios.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Kcurios.MOD_ID);

    public static final DeferredBlock<Block> CRUSHED_EMERALD_BLOCK = registerBlock("crushed_emerald_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> CRUSHED_AMETHYST_BLOCK = registerBlock("crushed_amethyst_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> STEEL_ORE = registerBlock("steel_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_STEEL_ORE = registerBlock("deepslate_steel_ore",
            () -> new DropExperienceBlock(UniformInt.of(3,6),
                    BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> MAGIC_BLOCK = registerBlock("magic_block",
            () -> new MagicBlock(BlockBehaviour.Properties.of()
                    .strength(3f).sound(SoundType.SPONGE)));

    //non block blocks
    public static final DeferredBlock<StairBlock> STEEL_STAIRS = registerBlock("steel_stairs",
            () -> new StairBlock(ModBlocks.STEEL_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> STEEL_SLAB = registerBlock("steel_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));

    //redsotne stuff button/presureplate
    public static final DeferredBlock<PressurePlateBlock> STEEL_PRESSURE_PLATE = registerBlock("steel_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<ButtonBlock> STEEL_BUTTON = registerBlock("steel_button",
            () -> new ButtonBlock(BlockSetType.IRON, 60,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().noCollission())); //no collisionj cus its a button

    // fence stuff and walls
    public static final DeferredBlock<FenceBlock> STEEL_FENCE = registerBlock("steel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<FenceGateBlock> STEEL_FENCE_GATE = registerBlock("steel_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK,BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<WallBlock> STEEL_WALL = registerBlock("steel_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops()));

    //doors
    public static final DeferredBlock<DoorBlock> STEEL_DOOR = registerBlock("steel_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().noOcclusion())); // no occulsion
    public static final DeferredBlock<TrapDoorBlock> STEEL_TRAPDOOR = registerBlock("steel_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of()
                    .strength(2f).requiresCorrectToolForDrops().noOcclusion()));


    public static final DeferredBlock<Block> AMETHYST_LAMP = registerBlock("amethyst_lamp",
            () -> new AmethystLampBlock(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(AmethystLampBlock.CLICKED) ? 12 : 0).sound(SoundType.AMETHYST)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
