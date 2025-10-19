package cloud.sect.alm.keybinding;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class LoginKeybinding {
    private static KeyBinding loginKeyBinding;

    public static void register() {
        loginKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "alm.key.autocmd.send",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F9,
                "alm.category.autocmd"
        ));
    }

    public static KeyBinding get() {
        return loginKeyBinding;
    }
}
