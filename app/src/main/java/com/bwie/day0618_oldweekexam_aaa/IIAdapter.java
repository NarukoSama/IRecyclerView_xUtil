package com.bwie.day0618_oldweekexam_aaa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by
 * Chenxin
 * on 2017/6/18.
 */

public class IIAdapter extends RecyclerView.Adapter<IIAdapter.IViewHolder> {
    Context context;
    List<String> list;

    public IIAdapter(Context context, List<String> list) {

        this.context = context;
        this.list = list;

    }

    @Override
    public IIAdapter.IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.lv_item, null);

        IViewHolder viewHolder = new IViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IIAdapter.IViewHolder holder, int position) {

        String s = list.get(position);

//        holder.tv_content.setText(dataBean.getContent());
//        holder.tv_updatetime.setText(dataBean.getUpdatetime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class IViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_content;
        private TextView tv_updatetime;

        public IViewHolder(View itemView) {
            super(itemView);

            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_updatetime = (TextView) itemView.findViewById(R.id.tv_updatetime);

        }

    }
}
