package com.example.lab4dqt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DelegateAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Delegate> listDelegate;

    public DelegateAdapter(Context context, int layout, List<Delegate> listDelegate) {
        this.context = context;
        this.layout = layout;
        this.listDelegate = listDelegate;
    }

    @Override
    public int getCount() {
        return listDelegate.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtName;
        TextView txtRole;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);

            viewHolder = new ViewHolder();
            viewHolder.txtName = view.findViewById(R.id.txtName);
            viewHolder.txtRole = view.findViewById(R.id.txtRole);
            view.setTag(viewHolder);
        } else{
            viewHolder = (ViewHolder)view.getTag();
        }

        Delegate delegate = listDelegate.get(i);
        viewHolder.txtName.setText(delegate.getName() + "("+delegate.getParty()+")");
        viewHolder.txtRole.setText(delegate.getNameRole());

        return view;
    }
}
