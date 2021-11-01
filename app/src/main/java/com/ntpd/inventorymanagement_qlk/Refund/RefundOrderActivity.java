package com.ntpd.inventorymanagement_qlk.Refund;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ntpd.inventorymanagement_qlk.R;
import com.ntpd.inventorymanagement_qlk.start.MainActivity;

public class RefundOrderActivity extends AppCompatActivity {
    ImageView icback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_order);

        icback=findViewById(R.id.ic_back);

        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent tentback = new Intent(RefundOrderActivity.this, MainActivity.class);
                startActivity(tentback);
            }
        });
    }
}