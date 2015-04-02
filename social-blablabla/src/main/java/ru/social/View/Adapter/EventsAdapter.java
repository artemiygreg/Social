package ru.social.View.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ru.social.Model.Event;
import ru.social.R;
import ru.social.Service.Image.ImageService;
import ru.social.View.BitmapAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private List<Event> listEvent;
    private Activity activity;
    private OnItemClickListener onItemClickListener;
    private ImageService imageService;
    private Map<Integer, Bitmap> mapProfile = new HashMap();
    private BitmapAdapter adapterProfile = new BitmapAdapter() {
        @Override
        public void cache(Integer key, Bitmap value) {
            mapProfile.put(key, value);
        }
    };

    public EventsAdapter(List<Event> listEvent, Activity activity){
        this.listEvent = listEvent;
        this.activity = activity;
        imageService = new ImageService(activity);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        public TextView title, date;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            view = v;
            title = (TextView)v.findViewById(R.id.title);
            date = (TextView)v.findViewById(R.id.textViewDate);
            imageView = (ImageView)v.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getPosition());
            }
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Event event = listEvent.get(position);
        holder.title.setText(event.getTitle());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
