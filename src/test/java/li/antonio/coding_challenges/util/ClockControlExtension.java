package li.antonio.coding_challenges.util;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.Clock;
import java.util.function.Function;

/**
 * Sets the clock before running test, reset it afterward.
 */
public final class ClockControlExtension implements AfterTestExecutionCallback, BeforeTestExecutionCallback {
    private Clock previousValues;
    private final Function<Clock, Clock> clockSetter;
    private final Clock clock;

    public ClockControlExtension(final Clock clock, final Function<Clock, Clock> clockSetter) {
        this.clockSetter = clockSetter;
        this.clock = clock;
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) {
        previousValues = clockSetter.apply(clock);
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) {
        clockSetter.apply(previousValues);
    }

}
