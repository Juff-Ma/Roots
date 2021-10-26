package epicsquid.roots.entity.ritual;

import epicsquid.mysticallib.network.PacketHandler;
import epicsquid.mysticallib.util.ItemUtil;
import epicsquid.mysticallib.util.Util;
import epicsquid.roots.config.RitualConfig;
import epicsquid.roots.init.ModDamage;
import epicsquid.roots.init.ModRecipes;
import epicsquid.roots.network.fx.MessageRampantLifeInfusionFX;
import epicsquid.roots.recipe.AnimalHarvestFishRecipe;
import epicsquid.roots.ritual.RitualAnimalHarvest;
import epicsquid.roots.ritual.RitualRegistry;
import epicsquid.roots.util.types.WeightedRegistry;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.potion.Effects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EntityRitualAnimalHarvest extends EntityRitualBase {

  private RitualAnimalHarvest ritual;
  private Set<Class<? extends LivingEntity>> harvestClasses;

  public EntityRitualAnimalHarvest(World worldIn) {
    super(worldIn);
    this.getDataManager().register(lifetime, RitualRegistry.ritual_animal_harvest.getDuration() + 20);
    this.harvestClasses = ModRecipes.getAnimalHarvestClasses();
    this.ritual = (RitualAnimalHarvest) RitualRegistry.ritual_animal_harvest;
  }

  @Override
  public void onUpdate() {
    super.onUpdate();

    if (this.ticksExisted % ritual.interval == 0) {
      for (int i = 0; i < ritual.count; i++) {
        if (doHarvest()) break;
      }
    }
  }

  private boolean doHarvest() {
    List<MobEntity> entityList = Util.getEntitiesWithinRadius(world, (entity) -> harvestClasses.contains(entity.getClass()), getPosition(), ritual.radius_x, ritual.radius_y, ritual.radius_z);
    if (RitualConfig.animalHarvestDoFish) {
      List<BlockPos> waterSourceBlocks = Util.getBlocksWithinRadius(world, getPosition(), ritual.radius_x, ritual.radius_y, ritual.radius_z, (p) -> {
        BlockState state = world.getBlockState(p);
        return (state.getMaterial() == Material.WATER && state.getPropertyKeys().contains(BlockLiquid.LEVEL) && state.get(BlockLiquid.LEVEL) == 0);
      });
      WeightedRegistry<AnimalHarvestFishRecipe> recipes = new WeightedRegistry<>(ModRecipes.getFishRecipes());
      if (!recipes.isEmpty() && !waterSourceBlocks.isEmpty() && (rand.nextFloat() <= ritual.fish_chance) || entityList.isEmpty()) {
        AnimalHarvestFishRecipe recipe = recipes.getRandomItem(rand);
        if (!waterSourceBlocks.isEmpty()) {
          BlockPos pos = waterSourceBlocks.get(rand.nextInt(waterSourceBlocks.size()));
          if (!world.isRemote && recipe != null) {
            ItemStack stack = recipe.getItemStack().copy();
            // Protection against those nasty "must be positive" errors
            stack.setCount(ritual.fish_count + Math.max(0, (rand.nextInt(ritual.fish_additional) - 2)));
            ItemUtil.spawnItem(world, pos.add(0, 1, 0), stack);
          }
          PacketHandler.sendToAllTracking(new MessageRampantLifeInfusionFX(pos.getX(), pos.getY() + 1, pos.getZ()), this);
        }
        return true;
      }
    }
    if (entityList.isEmpty()) return false;
    MobEntity entity = entityList.get(Util.rand.nextInt(entityList.size()));

    boolean didDrops = false;

    if (!world.isRemote) {
      entity.captureDrops = true;
      entity.capturedDrops.clear();
      int looting = Util.rand.nextFloat() <= ritual.looting_chance ? ritual.looting_value : 0;
      dropLoot(entity, looting);
      entity.captureDrops = false;
      if (!ForgeHooks.onLivingDrops(entity, DamageSource.GENERIC, entity.capturedDrops, looting, false)) {
        for (ItemEntity item : entity.capturedDrops) {
          item.motionY = 0;
          item.motionX = 0;
          item.motionZ = 0;
          world.addEntity(item);
          didDrops = true;
        }
      }
    }

    if (didDrops) {
      entity.addPotionEffect(new EffectInstance(Effects.GLOWING, ritual.glowing, 0, false, false));
    }
    return didDrops;
  }

  private static Method getLootTable = null;

  private ResourceLocation getLootTable(MobEntity entity) {
    if (getLootTable == null) {
      getLootTable = ObfuscationReflectionHelper.findMethod(MobEntity.class, "func_184647_J", ResourceLocation.class);
      getLootTable.setAccessible(true);
    }

    try {
      return (ResourceLocation) getLootTable.invoke(entity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      return null;
    }
  }

  private void dropLoot(MobEntity entity, int looting) {
    ResourceLocation resourcelocation = entity.deathLootTable;

    if (resourcelocation == null) {
      resourcelocation = getLootTable(entity);
    }

    if (resourcelocation != null) {
      LootTable loottable = entity.world.getLootTableManager().getLootTableFromLocation(resourcelocation);
      entity.deathLootTable = null;
      FakePlayer fakePlayer = FakePlayerFactory.getMinecraft((ServerWorld) entity.world);
      LootContext context = new LootContext(looting, (ServerWorld) entity.world, entity.world.getLootTableManager(), entity, fakePlayer, ModDamage.HARVEST_RITUAL_DAMAGE);

      for (ItemStack itemstack : loottable.generateLootForPools(entity.deathLootTableSeed == 0L ? entity.rand : new Random(entity.deathLootTableSeed), context)) {
        entity.entityDropItem(itemstack, 0.0F);
      }
    }
  }
}