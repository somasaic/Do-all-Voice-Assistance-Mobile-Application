package org.dicio.dicio_android.skills.search;

import static org.dicio.dicio_android.Sections.getSection;
import static org.dicio.dicio_android.Sections.isSectionAvailable;
import static org.dicio.dicio_android.SectionsGenerated.search;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import org.dicio.dicio_android.R;
import org.dicio.skill.Skill;
import org.dicio.skill.SkillContext;
import org.dicio.skill.SkillInfo;
import org.dicio.skill.chain.ChainSkill;
import org.dicio.skill.standard.StandardRecognizer;

public class SearchInfo extends SkillInfo {

    public SearchInfo() {
        super("search", R.string.skill_name_search, R.string.skill_sentence_example_search,
                R.drawable.ic_search_white, true);
    }

    @Override
    public boolean isAvailable(final SkillContext context) {
        return isSectionAvailable(search);
    }

    @Override
    public Skill build(final SkillContext context) {
        final ChainSkill.Builder builder = new ChainSkill.Builder()
                .recognize(new StandardRecognizer(getSection(search)));

        // Qwant was once available as a second search engine; restore this if adding a new engine
        /*final String searchEngine = context.getPreferences().getString(
                ctx().android().getString(R.string.pref_key_search_engine), "");
        if (searchEngine.equals(ctx()
                .getString(R.string.pref_val_search_engine_duckduckgo))) {
        }*/

        builder.process(new DuckDuckGoProcessor());

        return builder.output(new SearchOutput());
    }

    @Nullable
    @Override
    public PreferenceFragmentCompat getPreferenceFragment() {
        return new Preferences();
    }

    public static class Preferences extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(final Bundle savedInstanceState, final String rootKey) {
            addPreferencesFromResource(R.xml.pref_search);
        }
    }
}
