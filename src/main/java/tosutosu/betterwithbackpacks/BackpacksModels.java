package tosutosu.betterwithbackpacks;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.util.collection.NamespaceID;
import org.jetbrains.annotations.NotNull;
import turniplabs.halplibe.util.ModelEntrypoint;

import static tosutosu.betterwithbackpacks.BetterWithBackpacks.MOD_ID;

public class BackpacksModels implements ModelEntrypoint {
    @Override
    public void initBlockModels(BlockModelDispatcher dispatcher) {}

    @Override
    public void initItemModels(ItemModelDispatcher dispatcher) {
        // fuck you halplibe <3
        dispatcher.addDispatch(setIcon(new ItemModelStandard(ModItems.leatherBackpack, null), NamespaceID.getTemp(MOD_ID, "item/leather_backpack")));
        dispatcher.addDispatch(setIcon(new ItemModelStandard(ModItems.goldBackpack, null), NamespaceID.getTemp(MOD_ID, "item/gold_backpack")));
        dispatcher.addDispatch(setIcon(new ItemModelStandard(ModItems.ironBackpack, null), NamespaceID.getTemp(MOD_ID, "item/iron_backpack")));
        dispatcher.addDispatch(setIcon(new ItemModelStandard(ModItems.diamondBackpack, null), NamespaceID.getTemp(MOD_ID, "item/diamond_backpack")));
    }

    public static <T extends ItemModelStandard> T setIcon(T model, @NotNull String texture) {
        model.icon = TextureRegistry.getTexture(texture);
        return model;
    }

    public static <T extends ItemModelStandard> T setIcon(T model, @NotNull NamespaceID texture) {
        model.icon = TextureRegistry.getTexture(texture);
        return model;
    }

    @Override
    public void initEntityModels(EntityRendererDispatcher dispatcher) {}

    @Override
    public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {}

    @Override
    public void initBlockColors(BlockColorDispatcher dispatcher) {}
}
