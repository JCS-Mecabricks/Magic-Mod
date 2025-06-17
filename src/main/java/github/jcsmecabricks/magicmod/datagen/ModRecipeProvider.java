package github.jcsmecabricks.magicmod.datagen;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import github.jcsmecabricks.magicmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.COMBAT, ModItems.WIZARD_STAFF)
                        .input('I', Items.DIAMOND)
                        .input('S', Items.STICK)
                        .pattern("SIS")
                        .pattern(" S ")
                        .pattern(" S ")
                        .criterion(hasItem(Items.DIAMOND), conditionsFromItem(Items.DIAMOND))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.BROOM)
                        .input('I', ModItems.WAND)
                        .input('S', Items.STICK)
                        .input('W', Items.WHEAT)
                        .pattern("  I")
                        .pattern(" S ")
                        .pattern("W  ")
                        .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                        .criterion(hasItem(ModItems.WAND), conditionsFromItem(ModItems.WAND))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.EXHIBIT_STAND)
                        .input('S', Items.IRON_INGOT)
                        .pattern("SSS")
                        .pattern(" S ")
                        .pattern("SSS")
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WIZARD_LAMP)
                        .input('I', Items.AMETHYST_SHARD)
                        .input('S', Items.GLOWSTONE)
                        .pattern(" I ")
                        .pattern("ISI")
                        .pattern(" I ")
                        .criterion(hasItem(Items.GLOWSTONE), conditionsFromItem(Items.GLOWSTONE))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ModItems.WAND)
                        .input('S', ModItems.WIZARD_STAFF)
                        .input('I', Items.AMETHYST_SHARD)
                        .pattern(" I ")
                        .pattern("ISI")
                        .pattern(" I ")
                        .criterion(hasItem(ModItems.WIZARD_STAFF), conditionsFromItem(ModItems.WIZARD_STAFF))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .offerTo(exporter);

                createShapeless(RecipeCategory.COMBAT, ModFluids.MYSTIC_WATER_BUCKET)
                        .input(ModItems.WIZARD_STAFF)
                        .input(Items.WATER_BUCKET)
                        .criterion(hasItem(ModItems.WIZARD_STAFF), conditionsFromItem(ModItems.WIZARD_STAFF))
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(exporter);
            }
            private static String hasTag(TagKey<Item> tag) {
                return "has_" + tag.id().toString();
            }
        };
    }

    @Override
    public String getName() {
        return "Magic Mod Recipes";
    }
}