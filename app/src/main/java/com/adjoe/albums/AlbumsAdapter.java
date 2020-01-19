package com.adjoe.albums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adjoe.R;
import com.adjoe.utils.AlbumEntity;

import java.util.List;

public class AlbumsAdapter extends BaseAdapter {
    private Context context;
    private List<AlbumEntity> items;

    AlbumsAdapter(Context context, List<AlbumEntity> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_item, parent, false);
        }
        AlbumEntity album = (AlbumEntity) getItem(position);

        ((TextView) convertView.findViewById(R.id.title)).setText(album.getTitle());
        ((TextView) convertView.findViewById(R.id.id)).setText("album ID : " + album.getId());


        return convertView;
    }
}
