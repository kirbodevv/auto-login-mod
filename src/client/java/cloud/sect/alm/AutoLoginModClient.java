package cloud.sect.alm;

import cloud.sect.alm.event.ClientTickEvents;
import cloud.sect.alm.keybinding.LoginKeybinding;
import net.fabricmc.api.ClientModInitializer;

public class AutoLoginModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LoginKeybinding.register();
        ClientTickEvents.register();

        Config.loadConfig();
    }
}