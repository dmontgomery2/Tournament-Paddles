package controller;

import controller.pages.Options;
import settings.GlobalSettings;

public class SettingsUpdater {

  public void updateSettings(GlobalSettings settings, Options options) {
    settings.setPlayerName(options.getPlayerName());
    settings.setPointsToWin(options.getPointsToWin());
    settings.setDifficulty(options.getDifficulty());
    settings.setPaddleSize(options.getPaddleSize());
  }

}
