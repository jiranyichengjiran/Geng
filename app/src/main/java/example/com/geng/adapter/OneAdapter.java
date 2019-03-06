package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.OneBean;

public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {
    Context context;
    List<OneBean.ResultBean> list;

    public OneAdapter(Context context, List<OneBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.one, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.one.setText(list.get(position).getName());
        final String id = list.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneCallBack.getOneId(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView one;

        public ViewHolder(View itemView) {
            super(itemView);
            one = itemView.findViewById(R.id.one_text);

        }
    }
    private oneCallBack oneCallBack;

    public void getId(oneCallBack oneCallBack) {
        this.oneCallBack = oneCallBack;
    }

    public interface oneCallBack{
        void getOneId(String id);
    }
}
