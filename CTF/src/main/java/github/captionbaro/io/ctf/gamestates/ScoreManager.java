package github.captionbaro.io.ctf.gamestates;

import github.captionbaro.io.ctf.CTF;

public class ScoreManager {
    public int ScoreRed;
    public int ScoreBlue;
    private CTF plugin;

    public ScoreManager(CTF plugin){
        ScoreRed = 0;
        ScoreBlue = 0;
        this.plugin = plugin;
    }

    public void setScoreBlue(int scoreBlue) {
        ScoreBlue = scoreBlue;
    }

    public void setScoreRed(int scoreRed) {
        ScoreRed = scoreRed;
    }

    public int getScoreRed() {
        return ScoreRed;
    }

    public int getScoreBlue() {
        return ScoreBlue;
    }

}
