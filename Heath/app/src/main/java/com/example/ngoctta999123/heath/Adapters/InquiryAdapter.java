package com.example.ngoctta999123.heath.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngoctta999123.heath.models.inquiries.FormTV;
import com.example.ngoctta999123.heath.R;

import java.util.List;

public class InquiryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<FormTV> TuVanList;

    public InquiryAdapter(Context context, int layout, List<FormTV> tuVanList) {
        this.context = context;
        this.layout = layout;
        TuVanList = tuVanList;
    }

    @Override
    public int getCount() {
        return TuVanList.size();
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
        TextView txttitle;
        TextView textContent;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txttitle = convertView.findViewById(R.id.txtId);
            holder.textContent = convertView.findViewById(R.id.txtContent);
            holder.imgHinh = convertView.findViewById(R.id.imageViewHinhtv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FormTV formTV = TuVanList.get(position);

        holder.txttitle.setText("Id: "+ formTV.getId());
        holder.textContent.setText("Nội dung: "+ formTV.getContent());
        if (formTV.getType() == 1) {
            holder.imgHinh.setImageResource(R.mipmap.dinh_duong);
        } else {
            holder.imgHinh.setImageResource(R.mipmap.kham_suc_khoe);
        }
        return convertView;
    }
}
