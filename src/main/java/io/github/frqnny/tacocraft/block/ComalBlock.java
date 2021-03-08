package io.github.frqnny.tacocraft.block;

import io.github.frqnny.tacocraft.TacoCraft;
import io.github.frqnny.tacocraft.blockentity.ComalBlockEntity;
import io.github.frqnny.tacocraft.init.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;


public class ComalBlock extends BlockWithEntity {
    public static final Identifier ID = TacoCraft.id("comal");

    public ComalBlock(Settings settings) {
        super(settings.nonOpaque());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new ComalBlockEntity();
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 1.0D, 14.0D);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.PASS;
        BlockEntity be = world.getBlockEntity(pos);

        if (be instanceof ComalBlockEntity) {
            ComalBlockEntity comal = (ComalBlockEntity) be;

            boolean handIsTortillaDough = player.getStackInHand(hand).copy().getItem() == ModItems.TORTILLA_DOUGH;
            boolean itemIsNotCooking = !comal.isCooking();
            boolean furnaceIsLit = world.getBlockState(pos.down()).get(FurnaceBlock.LIT);

            if (handIsTortillaDough && itemIsNotCooking && furnaceIsLit) {

                player.getStackInHand(hand).decrement(1);
                comal.startCooking();
                comal.sync();

                return ActionResult.SUCCESS;
            } else if (comal.isFinished()) {
                comal.spawnTortilla();
                comal.sync();
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

}