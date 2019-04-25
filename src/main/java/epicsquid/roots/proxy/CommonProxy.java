package epicsquid.roots.proxy;

import epicsquid.roots.Roots;
import epicsquid.roots.effect.EffectManager;
import epicsquid.roots.init.HerbRegistry;
import epicsquid.roots.init.ModItems;
import epicsquid.roots.integration.harvest.HarvestIntegration;
import epicsquid.roots.integration.jer.JERIntegration;
import epicsquid.roots.recipe.recipes.RunicCarvingRecipes;
import epicsquid.roots.ritual.RitualRegistry;
import epicsquid.roots.rune.RuneRegistry;
import epicsquid.roots.spell.SpellRegistry;
import epicsquid.roots.spell.modules.ModuleRegistry;
import epicsquid.roots.util.OfferingUtil;
import epicsquid.roots.world.village.ComponentDruidHut;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

public class CommonProxy {
  public void preInit(FMLPreInitializationEvent event) {
  }

  public void init(FMLInitializationEvent event) {
    HerbRegistry.init();
    RitualRegistry.init();
    ModuleRegistry.init();
    SpellRegistry.init();
    OfferingUtil.init();
    EffectManager.init();
    RuneRegistry.init();
    RunicCarvingRecipes.initRecipes();
    if (Loader.isModLoaded("jeresources")) {
      JERIntegration.init();
    }
    //MapGenStructureIO.registerStructureComponent(ComponentDruidHut.class, Roots.MODID + ":" + "druidhut");
    //VillagerRegistry.instance().registerVillageCreationHandler(new ComponentDruidHut.CreationHandler());
  }

  public void postInit(FMLPostInitializationEvent event) {
  }

  public void loadComplete(FMLLoadCompleteEvent event) {
    if (Loader.isModLoaded("harvest")) {
      HarvestIntegration.init();
    }
  }
}
