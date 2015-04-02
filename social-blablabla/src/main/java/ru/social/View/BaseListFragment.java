package ru.social.View;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.social.API.WebAPI;
import ru.social.API.WebAPIImpl;
import ru.social.Json.Json;
import ru.social.R;
import ru.social.Service.Preferences.MyPreferences;
import ru.social.Service.Preferences.MyPreferencesImpl;


public abstract class BaseListFragment extends Fragment {
    protected MyPreferences myPreferences;
    private RecyclerView recyclerView;
    protected Json json;
    protected String token = "";
    protected WebAPI webAPI;

    public BaseListFragment(){
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        json = new Json();
        webAPI = new WebAPIImpl(getActivity());
        myPreferences = new MyPreferencesImpl(getActivity());
        View view = inflater.inflate(R.layout.base_list_fragment, null);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setScrollbarFadingEnabled(true);

        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setAdapter(recyclerView);
    }
    protected abstract void setAdapter(RecyclerView recyclerView);
    protected RecyclerView getRecyclerView(){
        return recyclerView;
    }
}
