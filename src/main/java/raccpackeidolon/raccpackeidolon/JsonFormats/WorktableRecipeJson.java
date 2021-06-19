package raccpackeidolon.raccpackeidolon.JsonFormats;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import elucent.eidolon.recipe.WorktableRecipe;
import elucent.eidolon.recipe.WorktableRegistry;
import net.minecraft.item.ItemStack;

public class WorktableRecipeJson {
  public String type;
  public String[] pattern;
  public String reagents;
  public HashMap<Character, ItemKey> key;
  public ItemKey result;
  private static final Logger LOGGER = LogManager.getLogger();

  public void register() {
    final Object[] craftingShape = new Object[9];
    final Object[] reagentsList = new Object[4];

    for (int i = 0; i < 9; i++) {
      final char ch = pattern[i / 3].charAt(i % 3);
      if (ch == ' ') {
        craftingShape[i] = ItemStack.EMPTY;
      } else {
        craftingShape[i] = key.get(ch).asItem();
      }
    }

    for (int i = 0; i < 4; i++) {
      final char ch = reagents.charAt(i);
      if (ch == ' ') {
        reagentsList[i] = ItemStack.EMPTY;
      } else {
        reagentsList[i] = key.get(ch).asItem();
      }
    }
    WorktableRecipe recipe = new WorktableRecipe(craftingShape, reagentsList, result.asItemStack());
    recipe.setRegistryName("eidolonraccpack", String.format("worktable%d", hashCode()));
    LOGGER.info(recipe);
    WorktableRegistry.register(recipe);
  }

  @Override
  public int hashCode() {
    return this.type.hashCode() + this.pattern.hashCode() + this.reagents.hashCode() + this.key.hashCode()
        + this.result.hashCode();
  }
}
