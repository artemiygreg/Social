package ru.social.View.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ru.social.R;

/**
 * Created by Admin on 27.03.15.
 */
public class MenuAdapter extends ArrayAdapter<String> {
    private Context context;
    private ViewHolder holder;
    private String[] titles;
    private Drawable[] icons;

    public MenuAdapter(Context context, String[] title) {
        super(context, R.layout.layout_item_menu, title);
        this.context = context;
        titles = title;
        icons = new Drawable[]{getIcon(R.drawable.ic_profile),
                               getIcon(R.drawable.ic_event),
                               getIcon(R.drawable.ic_news)};
    }
    public class ViewHolder {
        public View view;
        public TextView title;
        public ImageView icon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.layout_item_menu, null);
            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.title = (TextView) view.findViewById(R.id.title);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        holder.icon.setImageDrawable(icons[position]);
        holder.title.setText(titles[position]);

        return view;
    }
    private Drawable getIcon(int id){
        return context.getResources().getDrawable(id);
    }
}
