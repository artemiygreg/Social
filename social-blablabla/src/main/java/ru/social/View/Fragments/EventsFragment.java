package ru.social.View.Fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import org.json.JSONObject;
import ru.social.API.Vars;
import ru.social.Interfaces.JsonResponse;
import ru.social.JsonResponse.ResponseObject;
import ru.social.Model.Event;
import ru.social.View.Activity.DetailEventActivity;
import ru.social.View.Adapter.EventsAdapter;
import ru.social.View.BaseListFragment;

import java.util.List;

/**
 * Created by Admin on 26.03.15.
 */
public class EventsFragment extends BaseListFragment {
    private EventsAdapter adapter;

    public EventsFragment() {
        super();
    }

    @Override
    protected void setAdapter(final RecyclerView recyclerView) {
        webAPI.getListEvents(token, new JsonResponse() {
            @Override
            public void success(JSONObject jsonResponse) {

            }

            @Override
            public void failed() {
                final List<Event> list = json.makeListEventFromJson(ResponseObject.events());
                adapter = new EventsAdapter(list, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new EventsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("position", "" + position);
                        Intent intent = new Intent(getActivity(), DetailEventActivity.class);
                        intent.putExtra(Vars.ID, (int)list.get(position).getId());
                        intent.putExtra(Vars.TOKEN, myPreferences.getToken());
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
