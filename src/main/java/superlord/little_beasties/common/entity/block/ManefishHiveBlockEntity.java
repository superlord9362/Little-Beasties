package superlord.little_beasties.common.entity.block;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.init.LBBlockEntities;

public class ManefishHiveBlockEntity extends BlockEntity {
	
	public static final String MIN_OCCUPATION_TICKS = "MinOccupationTicks";
	public static final String ENTITY_DATA = "EntityData";
	public static final String TICKS_IN_HIVE = "TicksInHive";
	public static final String MANEFISH = "Manefish";
	public static final int MAX_OCCUPANTS = 5;
	private final List<ManefishHiveBlockEntity.ManefishData> stored = Lists.newArrayList();
	
	public ManefishHiveBlockEntity(BlockPos pos, BlockState state) {
		super(LBBlockEntities.MANEFISH_HIVE.get(), pos, state);
	}
	
	public void setChanged() {
		if (this.isFireNearby()) {
			this.emptyAllLivingFromHive((Player)null, this.level.getBlockState(this.getBlockPos()), ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY);
		}
		super.setChanged();
	}
	
	public boolean isFireNearby() {
		if (this.level == null) {
			return false;
		} else {
			for (BlockPos blockpos : BlockPos.betweenClosed(this.worldPosition.offset(-1, -1, -1), this.worldPosition.offset(1, 1, 1))) {
				if (this.level.getBlockState(blockpos).getBlock() instanceof FireBlock) {
					return true;
				}
			}
			return false;
		}
	}
	
	public boolean isEmpty() {
		return this.stored.isEmpty();
	}
	
	public boolean isFull() {
		return this.stored.size() == 5;
	}
	
	public void emptyAllLivingFromHive(@Nullable Player player, BlockState state, ManefishHiveBlockEntity.ManefishReleaseStatus status) {
		List<Entity> list = this.releaseAllOccupants(state, status);
		if (player != null) {
			for (Entity entity : list) {
				if (entity instanceof BlueManefish manefish) {
					if (player.position().distanceToSqr(entity.position()) <= 16) {
						manefish.setStayOutOfHiveCountdown(400);
					}
				}
			}
		}
	}
	
	private List<Entity> releaseAllOccupants(BlockState state, ManefishHiveBlockEntity.ManefishReleaseStatus status) {
		List<Entity> list = Lists.newArrayList();
		this.stored.removeIf((manefish) -> {
			return releaseOccupant(this.level, this.worldPosition, state, manefish, list, status);
		});
		if (!list.isEmpty()) {
			super.setChanged();
		}
		return list;
	}
	
	public void addOccupant(Entity entity) {
		this.addOccupantWithPresetTicks(entity, 0);
	}
	
	public int getOccupantCount() {
		return this.stored.size();
	}
	
	public void addOccupantWithPresetTicks(Entity entity, int count) {
		if (this.stored.size() < 5) {
			entity.stopRiding();
			entity.ejectPassengers();
			CompoundTag tag = new CompoundTag();
			entity.save(tag);
			this.storeManefish(tag, count);
			if (this.level != null) {
				BlockPos pos = this.getBlockPos();
				this.level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, this.getBlockState()));
			}
			entity.discard();
			super.setChanged();
		}
	}
	
	public void storeManefish(CompoundTag tag, int count) {
		this.stored.add(new ManefishHiveBlockEntity.ManefishData(tag, count, 600));
	}
	
	private static boolean releaseOccupant(Level level, BlockPos pos, BlockState state, ManefishHiveBlockEntity.ManefishData manefishData, @Nullable List<Entity> list, ManefishHiveBlockEntity.ManefishReleaseStatus status) {
		if ((level.isNight() || level.isRaining()) && status != ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY) {
			return false;
		} else {
			CompoundTag tag = manefishData.entityData.copy();
			tag.put("HivePos", NbtUtils.writeBlockPos(pos));
			BlockPos blockPos = pos.relative(Direction.getRandom(level.random));
			boolean flag = !level.getBlockState(blockPos).getCollisionShape(level, blockPos).isEmpty();
			if (flag && status != ManefishHiveBlockEntity.ManefishReleaseStatus.EMERGENCY) {
				return false;
			} else {
				Entity entity = EntityType.loadEntityRecursive(tag, level, (p_58740_) -> {
					return p_58740_;
				});
				if (entity != null) {
					if (!(entity instanceof BlueManefish)) {
						return false;
					} else {
						if (entity instanceof BlueManefish manefish) {
							setManefishReleaseData(manefishData.ticksInHive, manefish);
							if (list != null) {
								list.add(manefish);
							}
							float f = entity.getBbWidth();
							double d3 = flag ? 0.0D : 0.55D + (double)(f / 2);
							double d0 = (double)pos.getX() + 0.5D + d3 * (double)1;
							double d1 = (double)pos.getY() + 0.5D - (double)(entity.getBbHeight() / 2);
							double d2 = (double)pos.getZ() + 0.5D + d3 * (double)1;
							entity.moveTo(d0, d1, d2, entity.getYRot(), entity.getXRot());
						}
						level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(entity, level.getBlockState(pos)));
						return level.addFreshEntity(entity);
					}
				} else {
					return false;
				}
			}
		}
	}
	
	private static void setManefishReleaseData(int data, BlueManefish manefish) {
	}
	
	private static void tickOccupants(Level world, BlockPos pos, BlockState state, List<ManefishHiveBlockEntity.ManefishData> data) {
		boolean flag = false;
		ManefishHiveBlockEntity.ManefishData manefishHiveBlockEntity$manefishData;
		for (Iterator<ManefishHiveBlockEntity.ManefishData> iterator = data.iterator(); iterator.hasNext(); ++manefishHiveBlockEntity$manefishData.ticksInHive) {
			manefishHiveBlockEntity$manefishData = iterator.next();
			if (manefishHiveBlockEntity$manefishData.ticksInHive > manefishHiveBlockEntity$manefishData.minOccupationTicks) {
				ManefishHiveBlockEntity.ManefishReleaseStatus manefishHiveBlockEntity$manefishReleaseStatus = ManefishHiveBlockEntity.ManefishReleaseStatus.MANEFISH_RELEASED;
				if (releaseOccupant(world, pos, state, manefishHiveBlockEntity$manefishData, (List<Entity>)null, manefishHiveBlockEntity$manefishReleaseStatus)) {
					flag = true;
					iterator.remove();
				}
			}
		}
		if (flag) {
				setChanged(world, pos, state);
		}
	}
	
	public static void serverTick(Level world, BlockPos pos, BlockState state, ManefishHiveBlockEntity hive) {
		tickOccupants(world, pos, state, hive.stored);
	}
	
	public void load(CompoundTag tag) {
		super.load(tag);
		this.stored.clear();
		ListTag listTag = tag.getList("Manefish", 10);
		for (int i = 0; i < listTag.size(); ++i) {
			CompoundTag compoundTag = listTag.getCompound(i);
			ManefishHiveBlockEntity.ManefishData manefishHiveBlockEntity$manefishData = new ManefishHiveBlockEntity.ManefishData(compoundTag.getCompound("EntityData"), compoundTag.getInt("TicksInHive"), compoundTag.getInt("MinOccupationTick"));
			this.stored.add(manefishHiveBlockEntity$manefishData);
		}
	}
	
	public ListTag writeManefish() {
		ListTag listTag = new ListTag();
		
		for (ManefishHiveBlockEntity.ManefishData manefishHiveBlockEntity$manefishData : this.stored) {
			CompoundTag tag = manefishHiveBlockEntity$manefishData.entityData.copy();
			tag.remove("UUID");
			CompoundTag compoundTag = new CompoundTag();
			compoundTag.put("EntityData", tag);
			compoundTag.putInt("TicksInHive", manefishHiveBlockEntity$manefishData.ticksInHive);
			compoundTag.putInt("MinOccupationTicks", manefishHiveBlockEntity$manefishData.minOccupationTicks);
			listTag.add(compoundTag);
		}
		return listTag;
	}
	
	static class ManefishData {
		final CompoundTag entityData;
		int ticksInHive;
		final int minOccupationTicks;
		
		ManefishData(CompoundTag tag, int ticksInHive, int minOccupationTicks) {
			this.entityData = tag;
			this.ticksInHive = ticksInHive;
			this.minOccupationTicks = minOccupationTicks;
		}
	}
	
	public static enum ManefishReleaseStatus{
		MANEFISH_RELEASED,
		EMERGENCY;
	}
	
}
