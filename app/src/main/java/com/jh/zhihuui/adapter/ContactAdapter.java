package com.jh.zhihuui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.jh.zhihuui.R;
import com.jh.zhihuui.model.ContactList;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private List<ContactList> data;
    private Context context;


    public ContactAdapter(List<ContactList> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        // 设置显示
        holder.tv_des.setText(data.get(position).content);

        ContactList contactList = data.get(position);

        // 判断第一组与第二组状态
        if (contactList.getParentId() == 0){
            holder.img_head.setImageResource(R.drawable.icon_portrait);  // 跳到个人页面
        }else {
            holder.img_head.setImageResource(R.drawable.icon_organization);   // 这个是可以进行二级界面跳转的
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(data, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView img_head;
        private TextView tv_des;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_head = itemView.findViewById(R.id.iv_portrait);
            tv_des = itemView.findViewById(R.id.tv_des);
        }
    }

    // 添加点击监听

    public interface OnItemClickListener{
        void onItemClick(List<ContactList> data, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnIteClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
