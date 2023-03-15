package dev.naman.lldassignments.designpatterns.singleton.configurationmanager.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationManagerTest {
    @TempDir
    Path tempDir;

    Path tempConfigFile;

    @BeforeEach
    void setUp() throws IOException {
        tempConfigFile = Files.createTempFile(tempDir, "config", ".properties");
        Files.writeString(tempConfigFile, "key1=value1\nkey2=value2");
    }

    @Test
    void testSingletonInstance() {
        ConfigurationManager instance1 = ConfigurationManager.getInstance();
        ConfigurationManager instance2 = ConfigurationManager.getInstance();
        assertSame(instance1, instance2, "Both instances should be the same");
    }

    @Test
    void testLoadConfigurationFromFile() throws IOException {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.loadConfigurationFromFile(tempConfigFile.toString());
        assertEquals("value1", configManager.getSetting("key1"));
        assertEquals("value2", configManager.getSetting("key2"));
    }

    @Test
    void testGetAndSetConfigurationSettings() {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.setSetting("key3", "value3");
        assertEquals("value3", configManager.getSetting("key3"));
    }

    @Test
    void testSaveConfigurationToFile() throws IOException {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.loadConfigurationFromFile(tempConfigFile.toString());
        configManager.setSetting("key3", "value3");
        configManager.saveConfigurationToFile(tempConfigFile.toString());
        configManager.loadConfigurationFromFile(tempConfigFile.toString());
        assertEquals("value3", configManager.getSetting("key3"));
    }
}
