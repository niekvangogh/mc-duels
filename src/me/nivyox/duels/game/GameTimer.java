package me.nivyox.duels.game;

import me.nivyox.duels.utils.ChatMessages;

/**
 * Created by Niek on 26-1-2017.
 */
public class GameTimer implements Runnable {
    private final Game game;
    private int endTime = 0;

    public GameTimer(Game game) {
        this.game = game;
        time = 10;
    }

    public int getTime() {
        return this.time;
    }

    private int time;

    @Override
    public void run() {
        game.getScoreboardManager().update();
        switch (game.getState()) {
            case COUNTDOWN:
                if (time == 0) {
                    game.setState(GameState.GAME);
                    return;
                }
                game.broadcast(ChatMessages.game_countdown.replace("%%TIME%%", String.valueOf(time)));
                time--;
                return;
            case GAME:
                game.getScoreboardManager().update();
                time++;
                break;
            case END:
                if (endTime == 0) {
                    this.endTime = time;
                }
                if (time - endTime == 5) {
                    game.endGame(EndReason.OPPONENT_KILLED);
                }
                time++;
                return;
        }
        return;
    }

    public void setTime(int time) {
        this.time = time;
    }
}