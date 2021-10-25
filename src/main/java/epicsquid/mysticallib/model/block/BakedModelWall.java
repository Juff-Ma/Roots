package epicsquid.mysticallib.model.block;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import epicsquid.mysticallib.model.CustomModelBase;
import epicsquid.mysticallib.model.ModelUtil;
import epicsquid.mysticallib.model.parts.Cube;
import net.minecraft.block.WallBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.model.IModelState;

public class BakedModelWall extends BakedModelBlock {
  private Cube post, north, south, west, east;

  public BakedModelWall(@Nonnull IModelState state, @Nonnull VertexFormat format, @Nonnull Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter,
      @Nonnull CustomModelBase model) {
    super(format, bakedTextureGetter, model);
    TextureAtlasSprite[] texes = new TextureAtlasSprite[] { texwest, texeast, texdown, texup, texnorth, texsouth };
    post = ModelUtil.makeCube(format, 0.25, 0, 0.25, 0.5, 1, 0.5, null, texes, -1);
    north = ModelUtil.makeCube(format, 0.3125, 0, 0, 0.375, 0.875, 0.5, null, texes, -1);
    south = ModelUtil.makeCube(format, 0.3125, 0, 0.5, 0.375, 0.875, 0.5, null, texes, -1);
    west = ModelUtil.makeCube(format, 0, 0, 0.3125, 0.5, 0.875, 0.375, null, texes, -1);
    east = ModelUtil.makeCube(format, 0.5, 0, 0.3125, 0.5, 0.875, 0.375, null, texes, -1);
  }

  @Override
  @Nonnull
  public List<net.minecraft.client.renderer.model.BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, long rand) {
    List<net.minecraft.client.renderer.model.BakedQuad> quads = new ArrayList<>();
    getFaceQuads(quads, side, state);
    return quads;
  }

  private void getFaceQuads(@Nonnull List<BakedQuad> quads, @Nullable Direction side, @Nullable BlockState state) {
    if (state == null) {
      post.addToList(quads, side);
      west.addToList(quads, side);
      east.addToList(quads, side);
    } else {
      boolean up = state.getValue(WallBlock.UP);
      boolean cnorth = state.getValue(WallBlock.NORTH);
      boolean csouth = state.getValue(WallBlock.SOUTH);
      boolean cwest = state.getValue(WallBlock.WEST);
      boolean ceast = state.getValue(WallBlock.EAST);
      if (up) {
        post.addToList(quads, side);
      }
      if (cnorth) {
        north.addToList(quads, side);
      }
      if (csouth) {
        south.addToList(quads, side);
      }
      if (cwest) {
        west.addToList(quads, side);
      }
      if (ceast) {
        east.addToList(quads, side);
      }
    }
  }

}
