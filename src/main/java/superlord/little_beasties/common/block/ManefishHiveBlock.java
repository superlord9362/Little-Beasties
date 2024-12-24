package superlord.little_beasties.common.block;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.entity.vehicle.MinecartTNT;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.block.ManefishHiveBlockEntity;
import superlord.little_beasties.init.LBBlockEntities;

public class ManefishHiveBlock extends BaseEntityBlock {
	
	public ManefishHiveBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}
	
	private void spookNearbyManefish(Level level, BlockPos pos) {
		List<BlueManefish> list = level.getEntitiesOfClass(BlueManefish.class, (new AABB(pos)).inflate(8, 8, 8));
		if (!list.isEmpty()) {
			for (BlueManefish fish : list) {
				fish.setPuffing(true);
			}
		}
	}

	
	@SuppressWarnings("deprecation")
	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos newPos) {
		if (level.getBlockState(newPos).getBlock() instanceof FireBlock) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof ManefishHiveBlockEntity) {
				ManefishHiveBlockEntity hiveBlockEntity = (ManefishHiveBlockEntity)blockEntity;
				hiveBlockEntity.emptyAllLivingFromHive((Player)null, state, ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY);
			}
		}
		return super.updateShape(state, direction, newState, level, pos, newPos);
	}
	
	@SuppressWarnings("deprecation")
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		super.playerDestroy(world, player, pos, state, blockEntity, stack);
		if (!world.isClientSide() && blockEntity instanceof ManefishHiveBlockEntity hiveBlockEntity) {
			if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0) {
				hiveBlockEntity.emptyAllLivingFromHive(player, state, ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY);
				world.updateNeighbourForOutputSignal(pos, this);
				this.spookNearbyManefish(world, pos);
			}
		}
	}
	
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}
	
	@Nullable
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new ManefishHiveBlockEntity(pos, state);
	}
	
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide() ? null : createTickerHelper(type, LBBlockEntities.MANEFISH_HIVE.get(), ManefishHiveBlockEntity::serverTick);
	}
	
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		if (!level.isClientSide() && player.isCreative() && level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) {
			BlockEntity blockEntity = level.getBlockEntity(pos);
			if (blockEntity instanceof ManefishHiveBlockEntity hiveBlockEntity) {
				ItemStack itemstack = new ItemStack(this);
				boolean flag = !hiveBlockEntity.isEmpty();
				if (flag) {
					if (flag) {
						CompoundTag compoundTag = new CompoundTag();
						compoundTag.put("Manefish", hiveBlockEntity.writeManefish());
						BlockItem.setBlockEntityData(itemstack, LBBlockEntities.MANEFISH_HIVE.get(), compoundTag);
					}
					CompoundTag compoundTag1 = new CompoundTag();
					itemstack.addTagElement("BlockStateTag", compoundTag1);
					ItemEntity itemEntity = new ItemEntity(level, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemstack);
					itemEntity.setDefaultPickUpDelay();
					level.addFreshEntity(itemEntity);
				}
			}
		}
		super.playerWillDestroy(level, pos, state, player);
	}
	
	@SuppressWarnings("deprecation")
	public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
		Entity entity = builder.getOptionalParameter(LootContextParams.THIS_ENTITY);
		if (entity instanceof PrimedTnt || entity instanceof Creeper || entity instanceof WitherSkull || entity instanceof WitherBoss || entity instanceof MinecartTNT) {
			BlockEntity blockEntity = builder.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
			if (blockEntity instanceof ManefishHiveBlockEntity hiveBlockEntity) {
				hiveBlockEntity.emptyAllLivingFromHive((Player)null, state, ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY);
			}
		}
		return super.getDrops(state, builder);
	}
	
}
