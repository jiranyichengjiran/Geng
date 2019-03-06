package example.com.geng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.com.geng.R;

public class MyQuanActivity extends AppCompatActivity {

    @BindView(R.id.fabu)
    TextView fabu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_quan);
        ButterKnife.bind(this);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyQuanActivity.this, FaBuActivity.class);
                startActivity(intent);
            }
        });
    }


}
