package epicsquid.mysticallib.hax;

import java.lang.reflect.Type;

import javax.annotation.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.client.renderer.model.BlockFaceUV;
import net.minecraft.util.JSONUtils;

public class BFUVDeserializer implements JsonDeserializer<net.minecraft.client.renderer.model.BlockFaceUV> {
  @Override
  public BlockFaceUV deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
    JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
    float[] afloat = this.parseUV(jsonobject);
    int i = this.parseRotation(jsonobject);
    return new net.minecraft.client.renderer.model.BlockFaceUV(afloat, i);
  }

  protected int parseRotation(JsonObject object) {
    int i = JSONUtils.getInt(object, "rotation", 0);

    if (i >= 0 && i % 90 == 0 && i / 90 <= 3) {
      return i;
    } else {
      throw new JsonParseException("Invalid rotation " + i + " found, only 0/90/180/270 allowed");
    }
  }

  @Nullable
  private float[] parseUV(JsonObject object) {
    if (!object.has("uv")) {
      return null;
    } else {
      JsonArray jsonarray = JSONUtils.getJsonArray(object, "uv");

      if (jsonarray.size() != 4) {
        throw new JsonParseException("Expected 4 uv values, found: " + jsonarray.size());
      } else {
        float[] afloat = new float[4];

        for (int i = 0; i < afloat.length; ++i) {
          afloat[i] = JSONUtils.getFloat(jsonarray.get(i), "uv[" + i + "]");
        }

        return afloat;
      }
    }
  }
}
