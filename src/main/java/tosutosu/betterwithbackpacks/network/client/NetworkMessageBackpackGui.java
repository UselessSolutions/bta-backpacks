package tosutosu.betterwithbackpacks.network.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.jspecify.annotations.NonNull;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;
import tosutosu.betterwithbackpacks.gui.guiscreen.GuiBackpack;
import turniplabs.halplibe.helper.EnvironmentHelper;
import turniplabs.halplibe.helper.network.NetworkMessage;
import turniplabs.halplibe.helper.network.UniversalPacket;

public class NetworkMessageBackpackGui implements NetworkMessage {
    private int instanceId;
    private int height;
    private int width;

    public NetworkMessageBackpackGui(int instanceId, int width, int height) {
        this.instanceId = instanceId;
        this.width = width;
        this.height = height;
    }

    @Override
    public void encodeToUniversalPacket(@NonNull UniversalPacket packet) {

    }

    @Override
    public void decodeFromUniversalPacket(@NonNull UniversalPacket packet) {

    }

    @Override
    public void handle(NetworkContext context) {
        if (!EnvironmentHelper.isMultiplayerServer()) {
            clientHandler();
        }
    }

    @Environment(EnvType.CLIENT)
    private void clientHandler() {
//        ContainerBackpack container = new ContainerBackpack(instanceId);

//        Minecraft.getMinecraft().displayScreen(new GuiBackpack(container, width, height));
    }
}
