package com.example.ngoctta999123.heath.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngoctta999123.heath.R;
import com.example.ngoctta999123.heath.models.records.Record;

import java.util.List;

public class RecordAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Record> records;

    public RecordAdapter(Context context, int layout, List<Record> tuVanList) {
        this.context = context;
        this.layout = layout;
        records = tuVanList;
    }

    @Override
    public int getCount() {
        return records.size();
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
        TextView txtTenBenh;
        TextView txtChanDoan;
        ImageView imgHinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.txtTenBenh = convertView.findViewById(R.id.txtTenBenh);
            holder.txtChanDoan = convertView.findViewById(R.id.txtChanDoan);
            holder.imgHinh = convertView.findViewById(R.id.imgRecordItem);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Record record = records.get(position);

        holder.txtTenBenh.setText("Mô tả bệnh: "+ record.getInquiry().getContent());
        holder.txtChanDoan.setText("Chẩn đoán: "+ record.getDiagnose());
        if (record.getRecordType() == 1) {
            holder.imgHinh.setImageResource(R.mipmap.dinh_duong);
        } else {
            holder.imgHinh.setImageResource(R.mipmap.kham_suc_khoe);
        }
        return convertView;
    }
}
