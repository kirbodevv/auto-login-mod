package cloud.sect.alm.event;

import cloud.sect.alm.Config;
import cloud.sect.alm.keybinding.LoginKeybinding;
import net.minecraft.text.Text;

public class ClientTickEvents {
    public static void register() {
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (LoginKeybinding.get().wasPressed()) {
                if (client.player != null) {
                    String command = Config.getCommand();
                    if (command.isEmpty()) {
                        client.player.sendMessage(Text.translatable("alm.default_command_text"), false);
                        continue;
                    }
                    client.player.networkHandler.sendChatCommand(command);
                }
            }
        });
    }
}
