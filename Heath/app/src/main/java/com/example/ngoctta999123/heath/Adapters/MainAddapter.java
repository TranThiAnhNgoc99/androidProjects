package com.example.ngoctta999123.heath.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngoctta999123.heath.models.Item;
import com.example.ngoctta999123.heath.R;

import java.util.List;

public class MainAddapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private List<Item> itemList;

    public MainAddapter(Context context, int layout, List<Item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            //anhs xa view
            holder.txtTen     = (TextView) convertView.findViewById(R.id.textviewTen);
            holder.imgHinh   = (ImageView) convertView.findViewById(R.id.imgeViewHinh);

           convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //gan gtri
        Item item = itemList.get(position);

        holder.txtTen.setText(item.getTen());
        holder.imgHinh.setImageResource(item.getHinh());

        return convertView;
    }
}
