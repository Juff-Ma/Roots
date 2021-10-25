package epicsquid.roots.init;

import epicsquid.mysticallib.block.BlockDoorBase;
import epicsquid.mysticallib.event.RegisterContentEvent;
import epicsquid.mysticallib.item.ItemBase;
import epicsquid.mysticallib.item.ItemBowlBase;
import epicsquid.mysticallib.item.ItemFoodBase;
import epicsquid.mysticallib.item.ItemSeedBase;
import epicsquid.mysticallib.material.MaterialTypes;
import epicsquid.roots.Roots;
import epicsquid.roots.api.CreateToolEvent;
import epicsquid.roots.item.*;
import epicsquid.roots.item.living.*;
import epicsquid.roots.item.materials.Materials;
import epicsquid.roots.item.runed.*;
import epicsquid.roots.item.terrastone.*;
import epicsquid.roots.item.wildwood.ItemWildwoodArmor;
import epicsquid.roots.item.wildwood.ItemWildwoodBow;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModItems {

  // All mod items
  public static Item guide, pestle, herb_pouch, component_pouch, apothecary_pouch, fey_pouch, spell_dust, staff, living_pickaxe, living_axe, living_shovel, living_hoe, living_sword, runic_shears, gold_knife, diamond_knife, iron_knife, stone_knife, wood_knife, wildwood_quiver, wildwood_bow,
      sylvan_helmet, sylvan_chestplate, sylvan_leggings, sylvan_boots, wildwood_helmet, wildwood_chestplate, wildwood_leggings, wildwood_boots, wooden_shears, firestarter,
      petals, flour, living_arrow, runic_dust, cooked_pereskia, wildroot_stew, fey_leather, wildewheet_bread, glass_eye, mystic_feather, strange_ooze, gramary, spirit_bag, reliquary;

  public static Item runed_axe, runed_hoe, runed_pickaxe, runed_shovel, runed_sword, runed_dagger;

  public static Item terrastone_axe, terrastone_hoe, terrastone_pickaxe, terrastone_shovel, terrastone_sword;

  public static Item moonglow_leaf, pereskia, terra_spores, terra_moss, spirit_herb, wildewheet,
      baffle_cap, bark_oak, bark_birch, bark_spruce, bark_jungle, bark_dark_oak, bark_acacia, bark_wildwood;

  public static Item moonglow_seed, pereskia_bulb, spirit_herb_seed, wildewheet_seed, cloud_berry, infernal_bulb,
      stalicripe, dewgonia, wildroot;

  public static Item ritual_healing_aura, ritual_heavy_storms, ritual_divine_protection, ritual_fire_storm, ritual_spreading_forest, ritual_windwall, ritual_warding_protection, ritual_germination, ritual_purity, ritual_frost_lands, ritual_animal_harvest, ritual_summon_creatures, ritual_wildroot_growth, ritual_overgrowth, ritual_flower_growth, ritual_transmutation, ritual_gathering, ritual_grove_supplication, spell_icon, spell_modifier;

  public static Item creative_pouch, fey_fire;

  public static Item life_essence, salmon;

  public static List<Item> barks;

  // Auto-populated
  public static List<Item> knives = new ArrayList<>();

  // TODO: Refactor this out of this file
  //Armor Materials
  public static final ArmorItem.ArmorMaterial sylvanArmorMaterial = EnumHelper.addArmorMaterial("SYLVAN", Roots.MODID + ":sylvan", 12, new int[]{2, 4, 5, 3}, 20, SoundEvents.BLOCK_SNOW_PLACE, 0F);
  public static final ArmorItem.ArmorMaterial wildwoodArmorMaterial = EnumHelper.addArmorMaterial("WILDWOOD", Roots.MODID + ":wildwood", 20, new int[]{2, 6, 7, 2}, 20, net.minecraft.util.SoundEvents.BLOCK_WOOD_PLACE, 1F);

  /**
   * Register all items
   */
  public static void registerItems(@Nonnull RegisterContentEvent event) {
    event.addItem(guide = new ItemGuide("roots_guide")).setCreativeTab(Roots.tab);
    event.addItem(moonglow_seed = new ItemSeedBase("moonglow_seed", ModBlocks.moonglow, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(moonglow_leaf = new ItemBase("moonglow_leaf").setCreativeTab(Roots.tab));
    event.addItem(pereskia_bulb = new ItemSeedBase("pereskia_bulb", ModBlocks.pereskia, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(pereskia = new ItemBase("pereskia").setCreativeTab(Roots.tab)); // 0xff8cc2
    event.addItem(terra_moss = new ItemBase("terra_moss").setCreativeTab(Roots.tab));
    event.addItem(spirit_herb_seed = new ItemSeedBase("spirit_herb_seed", ModBlocks.spirit_herb, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(spirit_herb = new ItemBase("spirit_herb").setCreativeTab(Roots.tab));
    event.addItem(wildroot = new ItemSeedBase("wildroot", ModBlocks.wildroot, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(wildewheet = new ItemBase("wildewheet").setCreativeTab(Roots.tab));
    event.addItem(wildewheet_seed = new ItemSeedBase("wildewheet_seed", ModBlocks.wildewheet, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(cloud_berry = new ItemSeedBase("cloud_berry", ModBlocks.cloud_berry, Blocks.DIRT).setCreativeTab(Roots.tab));
    event.addItem(infernal_bulb = new ItemSeedBase("infernal_bulb", ModBlocks.infernal_bulb, Blocks.MAGMA).setCreativeTab(Roots.tab));
    event.addItem(dewgonia = new ItemSeedBase("dewgonia", ModBlocks.dewgonia, net.minecraft.block.Blocks.SAND).setCreativeTab(Roots.tab));
    event.addItem(stalicripe = new ItemSeedBase("stalicripe", ModBlocks.stalicripe, Blocks.STONE).setCreativeTab(Roots.tab));
    event.addItem(terra_spores = new ItemTerraSpore("terra_spores").setCreativeTab(Roots.tab));
    event.addItem(petals = new ItemBase("petals").setCreativeTab(Roots.tab));
    event.addItem(runic_dust = new ItemBase("runic_dust").setCreativeTab(Roots.tab));

    // Barks and Knifes
    event.addItem(bark_oak = new ItemBase("bark_oak").setCreativeTab(Roots.tab));
    event.addItem(bark_spruce = new ItemBase("bark_spruce").setCreativeTab(Roots.tab));
    event.addItem(bark_birch = new ItemBase("bark_birch").setCreativeTab(Roots.tab));
    event.addItem(bark_jungle = new ItemBase("bark_jungle").setCreativeTab(Roots.tab));
    event.addItem(bark_dark_oak = new ItemBase("bark_dark_oak").setCreativeTab(Roots.tab));
    event.addItem(bark_acacia = new ItemBase("bark_acacia").setCreativeTab(Roots.tab));
    event.addItem(bark_wildwood = new ItemBase("bark_wildwood").setCreativeTab(Roots.tab));

    barks = Arrays.asList(bark_oak, bark_spruce, bark_birch, bark_jungle, bark_dark_oak, bark_acacia, bark_wildwood);

    event.addItem(fey_leather = new ItemBase("fey_leather") {
      @Override
      @SuppressWarnings("deprecation")
      public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
      }
    }.setCreativeTab(Roots.tab));
    event.addItem(mystic_feather = new ItemBase("mystic_feather") {
      @Override
      @SuppressWarnings("deprecation")
      public Rarity getRarity(ItemStack stack) {
        return Rarity.RARE;
      }
    }.setCreativeTab(Roots.tab));
    event.addItem(strange_ooze = new ItemBase("strange_ooze") {
      @Override
      @SuppressWarnings("deprecation")
      public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
      }
    }.setCreativeTab(Roots.tab));
    event.addItem(salmon = new ItemSalmon("salmon_of_knowledge").setCreativeTab(Roots.tab));

    event.addItem(pestle = new ItemPestle("pestle").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(herb_pouch = new ItemHerbPouch("herb_pouch").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(component_pouch = new ItemPouch("component_pouch").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(creative_pouch = new ItemCreativePouch("creative_pouch").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(apothecary_pouch = new ItemApothecaryPouch("apothecary_pouch").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(fey_pouch = new ItemFeyPouch("fey_pouch").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(spell_dust = new ItemSpellDust("spell_dust").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(flour = new ItemBase("flour").setCreativeTab(Roots.tab));
    event.addItem(staff = new ItemStaff("staff").setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(gramary = new ItemBase("gramary") {
      @SuppressWarnings("deprecation")
      @Override
      public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
      }
    }.setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(spirit_bag = new ItemUndeadDrop("spirit_bag", ItemUndeadDrop.DropType.POUCH)).setCreativeTab(Roots.tab);
    event.addItem(reliquary = new ItemUndeadDrop("reliquary", ItemUndeadDrop.DropType.RELIQUARY)).setCreativeTab(Roots.tab);


    event.addItem(runed_pickaxe = CreateToolEvent.createTool("runed_pickaxe", () -> new ItemRunedPickaxe(Materials.RUNIC, "runed_pickaxe")).setMaxStackSize(1).setCreativeTab(Roots.tab));
    event.addItem(runed_axe = CreateToolEvent.createTool("runed_axe", () -> new ItemRunedAxe(Materials.RUNIC, "runed_axe")).setMaxStackSize(1).setCreativeTab(Roots.tab));
    event.addItem(runed_shovel = CreateToolEvent.createTool("runed_shovel", () -> new ItemRunedShovel(Materials.RUNIC, "runed_shovel")).setMaxStackSize(1).setCreativeTab(Roots.tab));
    event.addItem(runed_hoe = CreateToolEvent.createTool("runed_hoe", () -> new ItemRunedHoe(Materials.RUNIC, "runed_hoe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(runed_sword = CreateToolEvent.createTool("runed_sword", () -> new ItemRunedSword(Materials.RUNIC, "runed_sword")).setCreativeTab(Roots.tab).setMaxStackSize(1));

    MaterialTypes.addMaterial("runed", Materials.RUNIC, null, Materials.RUNIC.getAttackDamage() - 4, -1.3f);
    event.addItem(runed_dagger = new ItemRunedKnife("runed_dagger", Materials.RUNIC).setCreativeTab(Roots.tab).setMaxStackSize(1));

    event.addItem(terrastone_pickaxe = CreateToolEvent.createTool("terrastone_pickaxe", () -> new ItemTerrastonePickaxe(Materials.TERRASTONE, "terrastone_pickaxe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(terrastone_axe = CreateToolEvent.createTool("terrastone_axe", () -> new ItemTerrastoneAxe(Materials.TERRASTONE, "terrastone_axe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(terrastone_shovel = CreateToolEvent.createTool("terrastone_shovel", () -> new ItemTerrastoneShovel(Materials.TERRASTONE, "terrastone_shovel")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(terrastone_hoe = CreateToolEvent.createTool("terrastone_hoe", () -> new ItemTerrastoneHoe(Materials.TERRASTONE, "terrastone_hoe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(terrastone_sword = CreateToolEvent.createTool("terrastone_sword", () -> new ItemTerrastoneSword(Materials.TERRASTONE, "terrastone_sword")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    //event.addItem(terrastone_knife = new ItemMageKnife("terrastone_knife", Materials.TERRASTONE).setCreativeTab(Roots.tab).setMaxStackSize(1));

    event.addItem(living_pickaxe = CreateToolEvent.createTool("living_pickaxe", () -> new ItemLivingPickaxe(Materials.LIVING, "living_pickaxe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(living_axe = CreateToolEvent.createTool("living_axe", () -> new ItemLivingAxe(Materials.LIVING, "living_axe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(living_shovel = CreateToolEvent.createTool("living_shovel", () -> new ItemLivingShovel(Materials.LIVING, "living_shovel")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(living_hoe = CreateToolEvent.createTool("living_hoe", () -> new ItemLivingHoe(Materials.LIVING, "living_hoe")).setCreativeTab(Roots.tab).setMaxStackSize(1));
    event.addItem(living_sword = CreateToolEvent.createTool("living_sword", () -> new ItemLivingSword(Materials.LIVING, "living_sword")).setCreativeTab(Roots.tab).setMaxStackSize(1));

    event.addItem(living_arrow = CreateToolEvent.createTool("living_arrow", () -> new ItemLivingArrow("living_arrow").setCreativeTab(Roots.tab)));
    event.addItem(wooden_shears = CreateToolEvent.createTool("wooden_shears", () -> new ItemWoodenShears("wooden_shears").setCreativeTab(Roots.tab)));
    event.addItem(firestarter = CreateToolEvent.createTool("fire_starter", () -> new ItemFireStarter("fire_starter").setCreativeTab(Roots.tab)));
    event.addItem(wildwood_quiver = CreateToolEvent.createTool("wildwood_quiver", () -> new ItemQuiver("wildwood_quiver").setCreativeTab(Roots.tab)));
    event.addItem(wildwood_bow = CreateToolEvent.createTool("wildwood_bow", () -> new ItemWildwoodBow("wildwood_bow").setCreativeTab(Roots.tab)));

    event.addItem(sylvan_helmet = new ItemSylvanArmor(sylvanArmorMaterial, EquipmentSlotType.HEAD, "sylvan_helmet").setMaxStackSize(1));
    event.addItem(sylvan_chestplate = new ItemSylvanArmor(sylvanArmorMaterial, EquipmentSlotType.CHEST, "sylvan_chestplate").setMaxStackSize(1));
    event.addItem(sylvan_leggings = new ItemSylvanArmor(sylvanArmorMaterial, EquipmentSlotType.LEGS, "sylvan_leggings").setMaxStackSize(1));
    event.addItem(sylvan_boots = new ItemSylvanArmor(sylvanArmorMaterial, EquipmentSlotType.FEET, "sylvan_boots").setMaxStackSize(1));
    event.addItem(wildwood_helmet = new ItemWildwoodArmor(wildwoodArmorMaterial, EquipmentSlotType.HEAD, "wildwood_helmet").setMaxStackSize(1));
    event.addItem(wildwood_chestplate = new ItemWildwoodArmor(wildwoodArmorMaterial, EquipmentSlotType.CHEST, "wildwood_chestplate").setMaxStackSize(1));
    event.addItem(wildwood_leggings = new ItemWildwoodArmor(wildwoodArmorMaterial, EquipmentSlotType.LEGS, "wildwood_leggings").setMaxStackSize(1));
    event.addItem(wildwood_boots = new ItemWildwoodArmor(wildwoodArmorMaterial, EquipmentSlotType.FEET, "wildwood_boots").setMaxStackSize(1));

    event.addItem(runic_shears = CreateToolEvent.createTool("runic_shears", () -> new ItemRunicShears("runic_shears").setCreativeTab(Roots.tab)));

    MaterialTypes.addMaterial("vanilla:wood", ToolMaterial.WOOD, null, ToolMaterial.WOOD.getAttackDamage(), -1.7f);
    MaterialTypes.addMaterial("vanilla:stone", ToolMaterial.STONE, null, ToolMaterial.STONE.getAttackDamage(), -1.7f);
    MaterialTypes.addMaterial("vanilla:iron", ToolMaterial.IRON, ArmorItem.ArmorMaterial.IRON, ToolMaterial.IRON.getAttackDamage(), -1.5f);
    MaterialTypes.addMaterial("vanilla:diamond", ToolMaterial.DIAMOND, ArmorItem.ArmorMaterial.DIAMOND, ToolMaterial.DIAMOND.getAttackDamage(), -1.0f);
    MaterialTypes.addMaterial("vanilla:gold", ToolMaterial.GOLD, ArmorItem.ArmorMaterial.GOLD, ToolMaterial.GOLD.getAttackDamage(), -1.0f);

    event.addItem(wood_knife = CreateToolEvent.createTool("wood_knife", () -> new ItemDruidKnife("wood_knife", ToolMaterial.WOOD).setCreativeTab(Roots.tab)));
    event.addItem(stone_knife = CreateToolEvent.createTool("stone_knife", () -> new ItemDruidKnife("stone_knife", ToolMaterial.STONE).setCreativeTab(Roots.tab)));
    event.addItem(iron_knife = CreateToolEvent.createTool("iron_knife", () -> new ItemDruidKnife("iron_knife", ToolMaterial.IRON).setCreativeTab(Roots.tab)));
    event.addItem(diamond_knife = CreateToolEvent.createTool("diamond_knife", () -> new ItemDruidKnife("diamond_knife", ToolMaterial.DIAMOND).setCreativeTab(Roots.tab)));
    event.addItem(gold_knife = CreateToolEvent.createTool("gold_knife", () -> new ItemDruidKnife("gold_knife", ToolMaterial.GOLD).setCreativeTab(Roots.tab)));

    event.addItem(cooked_pereskia = new ItemFoodBase("cooked_pereskia", 5, false).setCreativeTab(Roots.tab));
    event.addItem(wildroot_stew = new ItemBowlBase("wildroot_stew", 6, false).setCreativeTab(Roots.tab));

    event.addItem(wildewheet_bread = new ItemFoodBase("wildewheet_bread", 7, false).setCreativeTab(Roots.tab));

    event.addItem(life_essence = new ItemLifeEssence("life_essence").setCreativeTab(Roots.tab));

    event.addItem(glass_eye = new ItemGlassEye("glass_eye").setCreativeTab(Roots.tab));

    // Rituals
    event.addItem(ritual_healing_aura = new ItemBase("ritual_healing_aura").setCreativeTab(null));
    event.addItem(ritual_heavy_storms = new ItemBase("ritual_heavy_storms").setCreativeTab(null));
    event.addItem(ritual_divine_protection = new ItemBase("ritual_divine_protection").setCreativeTab(null));
    event.addItem(ritual_fire_storm = new ItemBase("ritual_fire_storm").setCreativeTab(null));
    event.addItem(ritual_spreading_forest = new ItemBase("ritual_spreading_forest").setCreativeTab(null));
    event.addItem(ritual_windwall = new ItemBase("ritual_windwall").setCreativeTab(null));
    event.addItem(ritual_warding_protection = new ItemBase("ritual_warding_protection").setCreativeTab(null));
    event.addItem(ritual_germination = new ItemBase("ritual_germination").setCreativeTab(null));
    event.addItem(ritual_purity = new ItemBase("ritual_purity").setCreativeTab(null));
    event.addItem(ritual_frost_lands = new ItemBase("ritual_frost_lands").setCreativeTab(null));
    event.addItem(ritual_animal_harvest = new ItemBase("ritual_animal_harvest").setCreativeTab(null));
    event.addItem(ritual_summon_creatures = new ItemBase("ritual_summon_creatures").setCreativeTab(null));
    event.addItem(ritual_wildroot_growth = new ItemBase("ritual_wildroot_growth").setCreativeTab(null));
    event.addItem(ritual_overgrowth = new ItemBase("ritual_overgrowth").setCreativeTab(null));
    event.addItem(ritual_flower_growth = new ItemBase("ritual_flower_growth").setCreativeTab(null));
    event.addItem(ritual_transmutation = new ItemBase("ritual_transmutation").setCreativeTab(null));
    event.addItem(ritual_gathering = new ItemBase("ritual_gathering").setCreativeTab(null));
    event.addItem(ritual_grove_supplication = new ItemBase("ritual_grove_supplication").setCreativeTab(null));
    event.addItem(spell_icon = new ItemSpellIcon("spell_icon").setCreativeTab(Roots.tab));
    event.addItem(spell_modifier = new ItemSpellModifier("spell_modifier").setCreativeTab(Roots.tab));

    event.addItem(fey_fire = new ItemBase("fey_fire").setCreativeTab(null));

    // KEEP AT END
    registerSeedDrops();
  }

  /**
   * Register item oredicts here
   */
  public static void registerOredict() {
    OreDictionary.registerOre("dustBlaze", Items.BLAZE_POWDER);
    for (FlowerBlock.EnumFlowerType type : FlowerBlock.EnumFlowerType.values()) {
      OreDictionary.registerOre("allFlowers", new ItemStack(type.getBlockType().getBlock(), 1, type.getMeta()));
    }
    for (DoublePlantBlock.EnumPlantType type : DoublePlantBlock.EnumPlantType.values()) {
      if (type == DoublePlantBlock.EnumPlantType.FERN || type == DoublePlantBlock.EnumPlantType.GRASS) continue;

      OreDictionary.registerOre("allTallFlowers", new ItemStack(Blocks.DOUBLE_PLANT, 1, type.getMeta()));
    }

    for (Item bark : barks) {
      OreDictionary.registerOre("rootsBark", bark);
    }
    OreDictionary.registerOre("rootsBarkWildwood", ModItems.bark_wildwood);

    for (Block rune : ModBlocks.runestoneBlocks) {
      OreDictionary.registerOre("runestone", rune);
    }
    for (Block runed : ModBlocks.runedObsidianBlocks) {
      OreDictionary.registerOre("runedObsidian", runed);
    }
    for (Block runed : ModBlocks.runedWoodBlocks) {
      OreDictionary.registerOre("runedWood", runed);
    }

    OreDictionary.registerOre("wildroot", ModItems.wildroot);
    OreDictionary.registerOre("pestleStone", new ItemStack(Blocks.STONE, 1, OreDictionary.WILDCARD_VALUE));
    OreDictionary.registerOre("logWood", ModBlocks.wildwood_log);
    OreDictionary.registerOre("plankWood", ModBlocks.wildwood_planks);
    OreDictionary.registerOre("stairWood", ModBlocks.wildwood_stairs);
    OreDictionary.registerOre("slabWood", ModBlocks.wildwood_slab);
    OreDictionary.registerOre("doorWood", new ItemStack(((BlockDoorBase) ModBlocks.wildwood_door).getItemBlock()));
    OreDictionary.registerOre("foodBread", Items.BREAD);
    OreDictionary.registerOre("foodBread", ModItems.wildewheet_bread);
    OreDictionary.registerOre("foodFlour", ModItems.flour);
    OreDictionary.registerOre("dustWheat", ModItems.flour);
    OreDictionary.registerOre("treeLeaves", ModBlocks.wildwood_leaves);
    OreDictionary.registerOre("leather", ModItems.fey_leather);
    OreDictionary.registerOre("feyLeather", ModItems.fey_leather);
    OreDictionary.registerOre("feather", ModItems.mystic_feather);
    OreDictionary.registerOre("slimeball", ModItems.strange_ooze);
    OreDictionary.registerOre("feyFeather", ModItems.mystic_feather);
    OreDictionary.registerOre("spiderEye", Items.SPIDER_EYE);
    OreDictionary.registerOre("eye", ModItems.glass_eye);
    OreDictionary.registerOre("eye", Items.SPIDER_EYE);
    OreDictionary.registerOre("eye", Items.FERMENTED_SPIDER_EYE);
    OreDictionary.registerOre("tallgrass", new ItemStack(Blocks.TALLGRASS, 1, TallGrassBlock.EnumType.GRASS.getMeta()));
    OreDictionary.registerOre("tallgrass", new ItemStack(Blocks.TALLGRASS, 1, TallGrassBlock.EnumType.FERN.getMeta()));
    OreDictionary.registerOre("mushroom", new ItemStack(Item.getItemFromBlock(Blocks.RED_MUSHROOM)));
    OreDictionary.registerOre("mushroom", new ItemStack(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM)));
    OreDictionary.registerOre("blockMushroom", new ItemStack(Item.getItemFromBlock(Blocks.RED_MUSHROOM_BLOCK)));
    OreDictionary.registerOre("blockMushroom", new ItemStack(Item.getItemFromBlock(Blocks.BROWN_MUSHROOM_BLOCK)));

    OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.DEFAULT_META));
    OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.MOSSY_META));
    OreDictionary.registerOre("stonebrick", new ItemStack(Blocks.STONEBRICK, 1, BlockStoneBrick.CHISELED_META));
    OreDictionary.registerOre("stonebrick", new ItemStack(net.minecraft.block.Blocks.STONEBRICK, 1, BlockStoneBrick.CRACKED_META));

    OreDictionary.registerOre("mossyCobblestone", new ItemStack(Blocks.MOSSY_COBBLESTONE));
    OreDictionary.registerOre("pyreFireStarters", new ItemStack(Items.FLINT_AND_STEEL, 1, OreDictionary.WILDCARD_VALUE));
  }

  private static void registerSeedDrops() {
    MinecraftForge.addGrassSeed(new ItemStack(terra_spores, 1), 5);
    MinecraftForge.addGrassSeed(new ItemStack(wildroot, 1), 7);
  }
}
