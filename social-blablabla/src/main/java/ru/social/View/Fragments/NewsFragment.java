package ru.social.View.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import ru.social.Enumirations.TagTabs;
import ru.social.R;


/**
 * Created by Admin on 26.03.15.
 */
public class NewsFragment extends Fragment {
    private ArticlesFragment articlesFragment;
    private EventsFragment eventsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        final TabHost tabHost = (TabHost)view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec(TagTabs.EVENTS.name());
        final View tab1 = createTabView(getActivity().getString(R.string.events));
        tabSpec1.setContent(R.id.tab1);
        tabSpec1.setIndicator(tab1);
        tabHost.addTab(tabSpec1);

        final View tab2 = createTabView(getActivity().getString(R.string.articles));
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec(TagTabs.ARTICLES.name());
        tabSpec2.setContent(R.id.tab2);
        tabSpec2.setIndicator(tab2);
        tabHost.addTab(tabSpec2);

        tabHost.setCurrentTab(0);
        selectedTab(tab1);
        unSelectedTab(tab2);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                TagTabs tag = TagTabs.valueOf(tabId);
                switch (tag){
                    case ARTICLES:
                        selectedTab(tab2);
                        unSelectedTab(tab1);
                        break;
                    case EVENTS:
                        selectedTab(tab1);
                        unSelectedTab(tab2);
                        break;
                }
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        articlesFragment = new ArticlesFragment();
        eventsFragment = new EventsFragment();
        addFragment();
    }
    private void addFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.tab1, eventsFragment);
        fragmentTransaction.add(R.id.tab2, articlesFragment);
        fragmentTransaction.commit();
    }
    private View createTabView(String titleTab){
        View view = getActivity().getLayoutInflater().inflate(R.layout.layout_tab, null, false);
        TextView title = (TextView)view.findViewById(R.id.title);
        title.setText(titleTab);

        return view;
    }
    private void selectedTab(View view){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 6);
        TextView title = (TextView)view.findViewById(R.id.title);
        LinearLayout divider = (LinearLayout)view.findViewById(R.id.divider);
        title.setTextColor(getResources().getColor(R.color.primary));
//        title.setTypeface(Typeface.DEFAULT_BOLD);
        divider.setLayoutParams(params);
    }
    private void unSelectedTab(View view){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        TextView title = (TextView)view.findViewById(R.id.title);
        LinearLayout divider = (LinearLayout)view.findViewById(R.id.divider);
        title.setTextColor(getResources().getColor(R.color.textMenu));
//        title.setTypeface(Typeface.DEFAULT);
        divider.setLayoutParams(params);
    }
}
