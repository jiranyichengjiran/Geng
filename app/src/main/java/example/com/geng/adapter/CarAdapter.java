package example.com.geng.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import example.com.geng.R;
import example.com.geng.bean.CarBean;
import example.com.geng.custom.CustomLay;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    Context context;
    List<CarBean.ResultBean> list;
    int shu = 0;

    public CarAdapter(Context context, List<CarBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shop_car, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.img.setImageURI(list.get(position).getPic());
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("¥" + list.get(position).getPrice() + ".00");
        holder.num.setText(list.get(position).getCount() + "");
        shu = list.get(position).getCount();
        holder.add_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shu++;
                list.get(position).setCount(shu);
                holder.num.setText(shu + "");
                sumCall.onClickJiaOrJian(list);
            }
        });
        holder.jian_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shu > 1) {
                    shu--;
                    list.get(position).setCount(shu);
                    holder.num.setText(shu + "");
                    sumCall.onClickJiaOrJian(list);
                } else {
                    Toast.makeText(context, "到底线了", Toast.LENGTH_SHORT).show();
                }
            }
        });


        holder.single_chick.setChecked(list.get(position).isCheck());
        holder.single_chick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setCheck(holder.single_chick.isChecked());
                selectCall.onAllOn(list);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;
        private final CheckBox single_chick;
        private final TextView add_num;
        private final TextView jian_num;
        private final EditText num;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.car_img);
            name = itemView.findViewById(R.id.car_name);
            price = itemView.findViewById(R.id.car_price);
            single_chick = itemView.findViewById(R.id.single_chilck);
            add_num = itemView.findViewById(R.id.add_num);
            jian_num = itemView.findViewById(R.id.jian_num);
            num = itemView.findViewById(R.id.car_num);
        }
    }

    public returnId returnId;

    public void setReturnId(CarAdapter.returnId returnId) {
        this.returnId = returnId;
    }

    //生成订单
    public interface returnId {
        void call(int id);
    }

    private SumCall sumCall;
    private SelectCall selectCall;

    public void getOnClick(SumCall sumCall) {
        this.sumCall = sumCall;
    }

    public interface SumCall {
        public void onClickJiaOrJian(List<CarBean.ResultBean> list);
    }

    public void getOnSelect(SelectCall selectCall) {
        this.selectCall = selectCall;
    }

    public interface SelectCall {
        public void onAllOn(List<CarBean.ResultBean> list);
    }


}

