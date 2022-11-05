package settings;

class GlobalSettings {
    private int pointsToWin;
    private int difficulty;
    private int paddleSize;

    private int setting4;

    public int getPointsToWin() {
        return pointsToWin;
    }

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getPaddleSize() {
        return paddleSize;
    }

    public void setPaddleSize(int paddleSize) {
        this.paddleSize = paddleSize;
    }

    public void setSetting4(int setting4) {
        this.setting4 = setting4;
    }
}
