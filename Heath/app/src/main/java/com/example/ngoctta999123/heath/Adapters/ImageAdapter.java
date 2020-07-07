package com.example.ngoctta999123.heath.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.models.records.Record;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Bitmap> bitmaps;

    public ImageAdapter(Context context, int layout, List<Bitmap> bit) {
        this.context = context;
        this.layout = layout;
        bitmaps = bit;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgHinh = convertView.findViewById(R.id.imgItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bitmap bitmap = bitmaps.get(position);
        holder.imgHinh.setImageBitmap(bitmap);

        return convertView;
    }
}
