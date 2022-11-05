package menus;

import settings.Settings;

public class SettingsUpdater {

    public void updateSettings(Settings settings, OptionsMenu options){
        settings.setNumberOfPlayers(options.getNumberOfPlayers());
        settings.setPointsToWin(options.getPointsToWin());
        settings.setDifficulty(options.getDifficulty());
    }

}
