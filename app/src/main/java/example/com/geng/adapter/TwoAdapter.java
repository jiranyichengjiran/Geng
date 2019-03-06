package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.TwoBean;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.ViewHolder> {
    Context context;
    List<TwoBean.ResultBean> list;

    public TwoAdapter(Context context, List<TwoBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.two, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.two.setText(list.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    twoCallBack.getName(list.get(position).getName());
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView two;

        public ViewHolder(View itemView) {
            super(itemView);
            two = itemView.findViewById(R.id.two_text);
        }
    }
    private twoCallBack twoCallBack;

    public void getName(twoCallBack twoCallBack) {
        this.twoCallBack = twoCallBack;
    }

    public interface twoCallBack{
        void getName(String name);
    }
}
