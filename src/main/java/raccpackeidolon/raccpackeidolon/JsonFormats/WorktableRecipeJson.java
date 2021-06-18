package raccpackeidolon.raccpackeidolon.JsonFormats;

import java.util.HashMap;

import elucent.eidolon.recipe.WorktableRecipe;
import elucent.eidolon.recipe.WorktableRegistry;
import net.minecraft.item.ItemStack;

public class WorktableRecipeJson {
  public String type;
  public String[] pattern;
  public String reagents;
  public HashMap<Character, ItemKey> key;
  public ItemKey result;

  public void register() {
    Object[] craftingShape = new Object[9];
    Object[] reagentsList = new Object[4];

    for (int i = 0; i < 9; i++) {
      char ch = pattern[i / 3].charAt(i % 3);
      if (ch == ' ') {
        craftingShape[i] = ItemStack.EMPTY;
      } else {
        craftingShape[i] = key.get(ch).asItem();
      }
    }

    for (int i = 0; i < 4; i++) {
      char ch = reagents.charAt(i);
      if (ch == ' ') {
        reagentsList[i] = ItemStack.EMPTY;
      } else {
        reagentsList[i] = key.get(ch).asItem();
      }
    }

    WorktableRegistry.register(new WorktableRecipe(craftingShape, reagentsList, result.asItemStack()));
  }
}
