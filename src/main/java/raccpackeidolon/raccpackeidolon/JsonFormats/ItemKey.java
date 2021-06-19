package raccpackeidolon.raccpackeidolon.JsonFormats;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ItemKey implements IItemProvider {
  private static final Logger LOGGER = LogManager.getLogger();
  public String item;
  public Integer count = 1;

  @Override
  public Item asItem() {
    LOGGER.debug(String.format("Looking for item %s...", item));
    Item result;
    try {
      result = ForgeRegistries.ITEMS.getValue(new ResourceLocation(item));
      LOGGER.debug(String.format("Found %s!", item));
    } catch (Exception e) {
      result = null;
      LOGGER.error(String.format("Couldn't find item %s! %s", item, e));
    }
    return result;
  }

  public ItemStack asItemStack() {
    return new ItemStack(asItem(), count);
  }

  @Override
  public int hashCode() {
    return item.hashCode() + count.hashCode();
  }
}
