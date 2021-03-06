package com.vladmarica.betterpingdisplay;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Config {
  private static final int DEFAULT_PING_TEXT_COLOR = 0xA0A0A0;
  private static final String DEFAULT_PING_TEXT_FORMAT = "%dms";

  private boolean autoColorPingText;
  private boolean renderPingBars;
  private int textColor = DEFAULT_PING_TEXT_COLOR;
  private String textFormatString = DEFAULT_PING_TEXT_FORMAT;

  public Config(ConfigData confileFileFormat) {
    if (confileFileFormat.pingTextColor.startsWith("#")) {
      try {
        textColor = Integer.parseInt(confileFileFormat.pingTextColor.substring(1), 16);
      }
      catch (NumberFormatException ex) {
        BetterPingDisplayMod.LOGGER.error("Config option 'pingTextColor' is invalid - it must be a hex color code");
      }
    }
    else {
      BetterPingDisplayMod.LOGGER.error("Config option 'pingTextColor' is invalid - it must be a hex color code");
    }

    if (confileFileFormat.pingTextFormatString.contains("%d")) {
      textFormatString = confileFileFormat.pingTextFormatString;
    }
    else {
      BetterPingDisplayMod.LOGGER.error("Config option 'pingTextFormatString' is invalid - it needs to contain %d");
    }

    autoColorPingText = confileFileFormat.autoColorPingText;
    renderPingBars = confileFileFormat.renderPingBars;
  }

  public Config() {
    this(new ConfigData());
  }

  public int getTextColor() {
    return this.textColor;
  }

  public String getTextFormatString() {
    return this.textFormatString;
  }

  public boolean shouldAutoColorPingText() {
    return this.autoColorPingText;
  }

  public boolean shouldRenderPingBars() {
    return this.renderPingBars;
  }

  public static ConfigData loadConfigFile(File configFile) throws IOException {
    FileReader reader = null;
    try {
      Gson gson = new Gson();
      reader = new FileReader(configFile);
      return gson.fromJson(reader, ConfigData.class);
    }
    finally {
      if (reader != null) {
        reader.close();
      }
    }
  }

  public static void writeConfigFile(File configFile, ConfigData data) throws IOException {
    FileWriter writer = null;
    try {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      writer = new FileWriter(configFile);
      writer.write(gson.toJson(data));
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }

  public static class ConfigData implements Serializable {
    @Expose
    private boolean autoColorPingText = true;

    @Expose
    private boolean renderPingBars = false;

    @Expose
    private String pingTextColor = "#A0A0A0";

    @Expose
    private String pingTextFormatString = "%dms";
  }
}
