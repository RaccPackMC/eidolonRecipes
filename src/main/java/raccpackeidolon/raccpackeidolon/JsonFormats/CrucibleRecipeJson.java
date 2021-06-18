package raccpackeidolon.raccpackeidolon.JsonFormats;

import javax.annotation.Nullable;

import elucent.eidolon.recipe.CrucibleRecipe;
import elucent.eidolon.recipe.CrucibleRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrucibleRecipeJson {
    private class Step {
        @Nullable
        ItemKey[] items;

        @Nullable
        Integer stirs;
    }

    public String type;
    public Step[] steps;
    public ItemKey result;

    public void register() {
        CrucibleRecipe recipe = new CrucibleRecipe(result.asItemStack());
        for (Step step : steps) {
            if (step.items != null) {
                Object[] itemsList = new Item[step.items.length];
                for (int i = 0; i < itemsList.length; i++) {
                    itemsList[i] = step.items[i].asItem();
                }
                if (step.stirs == null) {
                    recipe.addStep(itemsList);
                } else {
                    recipe.addStirringStep(step.stirs, itemsList);
                }
            } else {
                if (step.stirs != null) {
                    recipe.addStep(step.stirs);
                }
            }
        }
        CrucibleRegistry.register(recipe);
    }
}
