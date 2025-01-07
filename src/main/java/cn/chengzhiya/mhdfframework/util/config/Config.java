package cn.chengzhiya.mhdfframework.util.config;

import cn.chengzhiya.mhdfframework.MHDFPlugin;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
public abstract class Config {
    private final File parent;
    private final String fileName;
    private YamlConfiguration data;

    public Config(File parent, String fileName) {
        this.parent = parent;
        this.fileName = fileName;
    }

    public Config(String file) {
        this(MHDFPlugin.instance.getDataFolder(), file);
    }

    public File getFile() {
        return new File(getParent(), getFileName());
    }

    public void saveDefaultData() {
        FileUtil.saveResource(fileName, fileName, false);
    }

    public void loadData() {
        this.data = YamlConfiguration.loadConfiguration(getFile());
    }

    public YamlConfiguration getData() {
        if (this.data == null) {
            loadData();
        }

        return this.data;
    }

    public void saveData() {
        try {
            this.data.save(getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
