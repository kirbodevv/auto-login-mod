package cloud.sect.alm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {
    private static String command = "";

    public static void loadConfig() {
        try {
            File configFile = new File("config/alm.json");
            if (!configFile.exists()) {
                JsonObject obj = new JsonObject();
                obj.addProperty(
                        "command",
                        command
                );
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

    public static String getCommand() {
        return command;
    }

    public static void setCommand(String command) {
        Config.command = command;
        //TODO: save to file
    }
}
