package cloud.sect.alm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class AutoLoginModClient implements ClientModInitializer {
    private static KeyBinding keyBinding;
    private static String command = "say введите значение по умолчанию"; // значение по умолчанию

    @Override
    public void onInitializeClient() {
        loadConfig();

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.autocmd.send",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F9,
                "category.autocmd"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                if (client.player != null) {
                    client.player.networkHandler.sendChatCommand(command);
                }
            }
        });
    }

    private void loadConfig() {
        try {
            File configFile = new File("config/autocmd.json");
            if (!configFile.exists()) {
                // создаём дефолтный конфиг
                JsonObject obj = new JsonObject();
                obj.addProperty("command", "say введите значение по умолчанию");
                configFile.getParentFile().mkdirs();
                try (FileWriter writer = new FileWriter(configFile)) {
                    new Gson().toJson(obj, writer);
                }
            }
            try (FileReader reader = new FileReader(configFile)) {
                JsonObject obj = new Gson().fromJson(reader, JsonObject.class);
                command = obj.get("command").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}