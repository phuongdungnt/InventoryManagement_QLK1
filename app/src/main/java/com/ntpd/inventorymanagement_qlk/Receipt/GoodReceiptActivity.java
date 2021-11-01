package com.ntpd.inventorymanagement_qlk.Receipt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.ntpd.inventorymanagement_qlk.R;

public class GoodReceiptActivity extends AppCompatActivity {
    ImageView icback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_receipt);
    }
}