package epicsquid.roots.potion;

import epicsquid.mysticallib.util.AABBUtil;
import epicsquid.roots.Roots;
import epicsquid.roots.init.ModSounds;
import epicsquid.roots.modifiers.instance.staff.ModifierSnapshot;
import epicsquid.roots.modifiers.instance.staff.StaffModifierInstanceList;
import epicsquid.roots.spell.SpellAugment;
import epicsquid.roots.spell.SpellExtension;
import epicsquid.roots.util.EntityUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionAnimalSense extends Effect {
  private ResourceLocation texture = new ResourceLocation(Roots.MODID, "textures/gui/potions.png");

  public PotionAnimalSense() {
    super(false, 0x1dd720);
    setPotionName("Animal Sense");
    setBeneficial();
    setIconIndex(6, 0);
  }

  @Override
  public boolean isReady(int duration, int amplifier) {
    return true;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public int getStatusIconIndex() {
    Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    return super.getStatusIconIndex();
  }

  @Override
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity.world.isRemote) return;

    if (entity instanceof PlayerEntity) {
      ModifierSnapshot mods = StaffModifierInstanceList.fromSnapshot(entity.getEntityData(), SpellExtension.instance);
      int[] radius = SpellExtension.instance.getRadiusAnimals();
      AxisAlignedBB aabb = AABBUtil.buildFromEntity(entity).grow(radius[0], radius[1], radius[2]);
      for (LivingEntity mob : entity.world.getEntitiesWithinAABB(LivingEntity.class, aabb, o -> EntityUtil.isFriendly(o, SpellExtension.instance))) {
        mob.addPotionEffect(new EffectInstance(Effects.GLOWING, 10, 0, false, false));
      }
    }
  }

  @Override
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }

  @Override
  public boolean shouldRenderInvText(EffectInstance effect) {
    return true;
  }

  @Override
  public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
    super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    if (SpellAugment.instance.shouldPlaySound()) {
      entityLivingBaseIn.playSound(ModSounds.Spells.SENSE_ANIMALS_EFFECT_END, SpellAugment.instance.getSoundVolume(), 1);
    }
  }
}
