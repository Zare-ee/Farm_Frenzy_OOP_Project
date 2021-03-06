package model.animal.domestic;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.util.Duration;

public class DeathAnimation extends Transition {
    private final Domestic domestic;

    public DeathAnimation(Domestic domestic) {
        this.domestic = domestic;
        setCycleDuration(Duration.millis(1000.0));
        setCycleCount(1);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double v) {
        domestic.updateDeath(v);
    }
}
