package ir.reservs.reservs.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.parceler.Parcels;

import ir.reservs.reservs.R;
import ir.reservs.reservs.model.Salon;


public class DetailActivity extends AppCompatActivity {
    private Salon salon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        salon = Parcels.unwrap(getIntent().getParcelableExtra("salon"));
    }
}
