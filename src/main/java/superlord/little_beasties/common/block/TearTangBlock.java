package superlord.little_beasties.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;
import superlord.little_beasties.init.LBItems;
import superlord.little_beasties.init.LBTags;

public class TearTangBlock extends BushBlock implements BonemealableBlock, SimpleWaterloggedBlock {
	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(0, 0, 0, 16, 16, 16)};

	public TearTangBlock(Properties p_51021_) {
		super(p_51021_);
		this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(WATERLOGGED, false));
	}

	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
	}

	protected boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return state.isFaceSturdy(worldIn, pos, Direction.UP) || state.is(LBTags.TEARTANG_GROW_ON);
	}

	public IntegerProperty getAgeProperty() {
		return AGE;
	}

	public int getMaxAge() {
		return 3;
	}

	protected int getAge(BlockState state) {
		return state.getValue(this.getAgeProperty());
	}

	public BlockState withAge(int age) {
		return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(age));
	}

	public boolean isMaxAge(BlockState state) {
		return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
	}

	public void randomTick(BlockState p_221050_, ServerLevel p_221051_, BlockPos p_221052_, RandomSource p_221053_) {
		if (!p_221051_.isAreaLoaded(p_221052_, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
		if (p_221051_.getRawBrightness(p_221052_, 0) >= 9) {
			int i = this.getAge(p_221050_);
			if (i < this.getMaxAge()) {
				float f = 1.0F;
				if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(p_221051_, p_221052_, p_221050_, p_221053_.nextInt((int)(25.0F / f) + 1) == 0)) {
					p_221051_.setBlock(p_221052_, this.getStateForAge(i + 1).setValue(WATERLOGGED, p_221050_.getValue(WATERLOGGED)), 2);
					net.minecraftforge.common.ForgeHooks.onCropsGrowPost(p_221051_, p_221052_, p_221050_);
				}
			}
		}
	}

	@Nullable
	public BlockState getStateForPlacement(BlockPlaceContext p_51454_) {
		FluidState fluidstate = p_51454_.getLevel().getFluidState(p_51454_.getClickedPos());
		boolean flag = fluidstate.getType() == Fluids.WATER;
		return super.getStateForPlacement(p_51454_).setValue(WATERLOGGED, flag);
	}

	public BlockState updateShape(BlockState p_51461_, Direction p_51462_, BlockState p_51463_, LevelAccessor p_51464_, BlockPos p_51465_, BlockPos p_51466_) {
		if (p_51461_.getValue(WATERLOGGED)) {
			p_51464_.scheduleTick(p_51465_, Fluids.WATER, Fluids.WATER.getTickDelay(p_51464_));
		}

		return super.updateShape(p_51461_, p_51462_, p_51463_, p_51464_, p_51465_, p_51466_);
	}

	protected int getBonemealAgeIncrease(Level worldIn) {
		return Mth.nextInt(worldIn.random, 1, 2);
	}

	protected ItemLike getBaseSeedId() {
		return LBItems.TEARTANG_SPORES.get();
	}

	public ItemStack getItem(LevelReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(this.getBaseSeedId());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(AGE).add(WATERLOGGED);
	}

	public FluidState getFluidState(BlockState p_51475_) {
		return p_51475_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_51475_);
	}

	public boolean canPlaceLiquid(BlockGetter p_154505_, BlockPos p_154506_, BlockState p_154507_, Fluid p_154508_) {
		return false;
	}

	@Override
	public boolean placeLiquid(LevelAccessor p_154520_, BlockPos p_154521_, BlockState p_154522_, FluidState p_154523_) {
		return false;
	}

	public void growCrops(Level level, BlockPos pos, BlockState state) {
		int i = this.getAge(state) + this.getBonemealAgeIncrease(level);
		int j = this.getMaxAge();
		if (i > j) {
			i = j;
		}

		level.setBlock(pos, this.getStateForAge(i).setValue(WATERLOGGED, state.getValue(WATERLOGGED)), 2);
	}

	public BlockState getStateForAge(int p_52290_) {
		return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(p_52290_));
	}

	public boolean isValidBonemealTarget(LevelReader p_255715_, BlockPos p_52259_, BlockState p_52260_, boolean p_52261_) {
		return !this.isMaxAge(p_52260_);
	}

	public boolean isBonemealSuccess(Level p_221045_, RandomSource p_221046_, BlockPos p_221047_, BlockState p_221048_) {
		return true;
	}

	public void performBonemeal(ServerLevel p_221040_, RandomSource p_221041_, BlockPos p_221042_, BlockState p_221043_) {
		this.growCrops(p_221040_, p_221042_, p_221043_);
	}
}
