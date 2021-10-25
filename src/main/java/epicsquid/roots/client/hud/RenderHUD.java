package epicsquid.roots.client.hud;

import epicsquid.roots.Roots;
import epicsquid.roots.block.BlockPyre;
import epicsquid.roots.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Roots.MODID)
public class RenderHUD {
  private RenderHUD() {
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent(priority = EventPriority.HIGHEST)
  public static void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
    if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

    Minecraft mc = Minecraft.getMinecraft();
    if (mc.currentScreen instanceof ChatScreen) return;

    RayTraceResult trace = mc.objectMouseOver;
    if (trace == null || trace.typeOfHit != RayTraceResult.Type.BLOCK) return;

    BlockState state = mc.world.getBlockState(trace.getBlockPos());
    Block block = state.getBlock();

    if (block == ModBlocks.mortar) {
      RenderMortar.render(mc, trace.getBlockPos(), state, event);
    } else if (block instanceof BlockPyre) {
      RenderPyre.render(mc, trace.getBlockPos(), state, event);
    }
  }
}
