package com.example.joan.broadcastreciverbateria;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button btnReadBattery;
    TextView txtBateria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadBattery = (Button) findViewById(R.id.btnReadBattery);
        txtBateria = (TextView) findViewById(R.id.txtBateria);

        btnReadBattery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                btnReadBattery.setText(readBattery());
            }


            public String readBattery(){

                StringBuilder sb = new StringBuilder();
                int status;

                IntentFilter batteryIntentFilter = new IntentFilter((Intent.ACTION_BATTERY_CHANGED));
                Intent batteryIntent = registerReceiver(null, batteryIntentFilter);

                boolean present1 = batteryIntent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
                sb.append("PRESENT: "+present1 + "\n");

                status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                if(status == BatteryManager.BATTERY_STATUS_CHARGING){
                    sb.append("BATTERY_STATUS_CHARGING\n");
                }
                if(status == BatteryManager.BATTERY_STATUS_FULL){
                    sb.append("BATTERY_STATUS_FULL\n");
                }

                int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
                if(plugged == BatteryManager.BATTERY_PLUGGED_USB);
                    sb.append("BATTERY_PLUGGED_USB\n");

                return sb.toString();

            }
        });
    }

}
