package org.dicio.dicio_android.skills.fallback.text;

import org.dicio.dicio_android.R;
import org.dicio.dicio_android.output.graphical.GraphicalOutputUtils;
import org.dicio.skill.FallbackSkill;

import java.util.List;

public class TextFallback extends FallbackSkill {

    @Override
    public void setInput(final String input,
                         final List<String> inputWords,
                         final List<String> normalizedWordKeys) {
    }

    @Override
    public void cleanup() {
    }

    @Override
    public void processInput() {
    }

    @Override
    public void generateOutput() {
        final String noMatchString = ctx().android().getString(R.string.eval_no_match);
        ctx().getSpeechOutputDevice().speak(noMatchString);
        ctx().getGraphicalOutputDevice().display(GraphicalOutputUtils.buildSubHeader(
                ctx().android(), noMatchString));
    }
}
