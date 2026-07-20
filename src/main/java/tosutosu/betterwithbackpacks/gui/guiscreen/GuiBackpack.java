package tosutosu.betterwithbackpacks.gui.guiscreen;

import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.client.render.renderer.GLRenderer;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.opengl.GL11;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;

public class GuiBackpack extends ScreenContainerAbstract {
    private int GUIx;
    private int GUIy;
    private int rows;
    private int slotsNum;
    private final ContainerBackpack backpack;
    public GuiBackpack(final Player player, final ItemStack stack) {
        super(new ContainerBackpack(player.inventory, stack));
        this.backpack = (ContainerBackpack) this.inventorySlots;
    }

    @Override
    public void init() {
        this.GUIx = (this.width - this.xSize) / 2;
        this.GUIy = (this.height - this.ySize) / 2;
        this.slotsNum = this.backpack.backpackInventory.getContainerSize();
        this.rows = (int) Math.ceil(this.slotsNum /9d);
        super.init();
    }
    @Override
    protected void drawGuiContainerForegroundLayer() {
        drawStringNoShadow(fontRenderer, this.backpack.backpackInventory.getNameTranslationKey(), 8, 6, BetterWithBackpacks.GUI_LABEL_COLOR);
        drawStringNoShadow(fontRenderer, "Inventory", 8, this.ySize - 96 + 2, BetterWithBackpacks.GUI_LABEL_COLOR);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(final float partialTick) {
        GLRenderer.setColor3f(1f,1f, 1f);
        this.mc.textureManager.loadTexture("/assets/betterwithbackpacks/gui/backpack.png").bind();
        drawTexturedModalRect(this.GUIx, this.GUIy, 0, 0, this.xSize, this.ySize);

        for (int i = 0; i < this.rows; i++) {
            if (i == this.rows - 1) {
                drawTexturedModalRect(this.GUIx + 7, this.GUIy + 17 + 18 * i, 0, 166, 18 * (this.slotsNum - (9 * i)), 18);
            } else {
                drawTexturedModalRect(this.GUIx + 7, this.GUIy + 17 + 18 * i, 0, 166, 162, 18);
            }
        }
    }
}
