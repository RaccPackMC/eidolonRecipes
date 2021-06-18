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
    return ForgeRegistries.ITEMS.getValue(new ResourceLocation(item));
  }

  public ItemStack asItemStack() { return new ItemStack(asItem(), count); }
}
