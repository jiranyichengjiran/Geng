package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.MyFoot;

public class MyFootAdapter extends XRecyclerView.Adapter<MyFootAdapter.ViewHolder> {
    Context context;
    List<MyFoot.ResultBean> list;

    public MyFootAdapter(Context context, List<MyFoot.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.myfoot, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.img.setImageURI(list.get(position).getMasterPic());
        holder.name.setText(list.get(position).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.foot_img);
            name = itemView.findViewById(R.id.foot_name);
        }
    }
}
