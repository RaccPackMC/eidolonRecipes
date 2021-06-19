package raccpackeidolon.raccpackeidolon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.loading.FMLPaths;
import raccpackeidolon.raccpackeidolon.JsonFormats.CrucibleRecipeJson;
import raccpackeidolon.raccpackeidolon.JsonFormats.WorktableRecipeJson;

public class RecipesLoader {
    File recipesDirectory;
    private static final Logger LOGGER = LogManager.getLogger();
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();

    public RecipesLoader() {
        recipesDirectory = new File(FMLPaths.CONFIGDIR.get().toFile(), "eidolon-raccpack");
        if (!recipesDirectory.exists()) {
            recipesDirectory.mkdirs();
        }
    }

    public void loadRecipes() {
        File crucibleDirectory = new File(recipesDirectory, "crucible");
        if (!crucibleDirectory.exists()) {
            crucibleDirectory.mkdirs();
        }
        File[] crucibleRecipes = crucibleDirectory.listFiles(filename -> {
            return filename.getName().endsWith(".json");
        });
        for (File recipeFile : crucibleRecipes) {
            loadCrucibleRecipe(recipeFile);
        }

        File worktableDirectory = new File(recipesDirectory, "worktable");
        if (!worktableDirectory.exists()) {
            worktableDirectory.mkdirs();
        }
        File[] worktableRecipes = worktableDirectory.listFiles(filename -> {
            return filename.getName().endsWith(".json");
        });
        for (File recipeFile : worktableRecipes) {
            loadWorktableRecipe(recipeFile);
        }
    }

    public void loadCrucibleRecipe(File file) {
        LOGGER.info(String.format("Importing crucible recipe file %s", file.getAbsolutePath()));
        try {
            String jsonString = new String(Files.readAllBytes(file.toPath()));
            CrucibleRecipeJson recipe = gson.fromJson(jsonString, CrucibleRecipeJson.class);
            recipe.register();
        } catch (IOException e) {
            LOGGER.error("Couldn't load crucible recipe", e);
        }
    }

    public void loadWorktableRecipe(File file) {
        LOGGER.info(String.format("Importing worktable recipe file %s", file.getAbsolutePath()));
        try {
            String jsonString = new String(Files.readAllBytes(file.toPath()));
            WorktableRecipeJson recipe = gson.fromJson(jsonString, WorktableRecipeJson.class);
            recipe.register();
        } catch (IOException e) {
            LOGGER.error("Couldn't load worktable recipe", e);
        }
    }
}
