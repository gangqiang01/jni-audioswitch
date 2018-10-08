package com.example.root.hitr181b;


import android.app.Activity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;



public class MyActivity extends Activity {

    private int led_flag = 0;
    private int air_flag = 0;
    private int fan_flag = 0;
    private int regri_flag = 0;
    private  ImageView ImageView_allon;
    private  ImageView ImageView_alloff;
    private  ImageView ImageView_led;
    private ImageView ImageView_air;
    private  ImageView ImageView_fan;
    private  ImageView ImageView_regri;
    private  ImageView ImageView_advcosensor;
    Timer GetVPMtatusTimer;




    JNI_AudioSwitch audiocontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView_allon = (ImageView)findViewById(R.id.allon);
        ImageView_alloff = (ImageView)findViewById(R.id.alloff);
        ImageView_led = (ImageView)findViewById(R.id.led);
        ImageView_air = (ImageView)findViewById(R.id.aircondition);
        ImageView_fan = (ImageView)findViewById(R.id.fan);
        ImageView_regri = (ImageView)findViewById(R.id.refrigerator);
        ImageView_advcosensor = (ImageView)findViewById(R.id.cosensor);




        audiocontrol = new JNI_AudioSwitch();

        ImageView_allon.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {

                audiocontrol.Set_AdvFan(false);
                audiocontrol.Set_AdvLed(false);
                audiocontrol.Set_AdvRegri(false);
                audiocontrol.Set_AdvAir(false);
                Toast toast = Toast.makeText(MyActivity.this,R.string.allopen,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        ImageView_alloff.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {
                audiocontrol.Set_AdvFan(true);
                audiocontrol.Set_AdvLed(true);
                audiocontrol.Set_AdvRegri(true);
                audiocontrol.Set_AdvAir(true);
                Toast toast = Toast.makeText(MyActivity.this,R.string.allclosed,Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        ImageView_air.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {

                switch(air_flag){
                    case 0:
                        audiocontrol.Set_AdvAir(false);
                        ImageView_air.setImageDrawable(getResources().getDrawable(R.drawable.airconditioner_reverse));
                        air_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvAir(true);
                        ImageView_air.setImageDrawable(getResources().getDrawable(R.drawable.aircondition));
                        air_flag = 0;
                        break;
                }

            }
        });
        ImageView_led.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {

                switch(led_flag){
                    case 0:
                        audiocontrol.Set_AdvLed(false);
                        ImageView_led.setImageDrawable(getResources().getDrawable(R.drawable.led_reverse));
                        led_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvLed(true);
                        ImageView_led.setImageDrawable(getResources().getDrawable(R.drawable.led));
                        led_flag = 0;
                        break;
                }

            }
        });
        ImageView_fan.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {

                switch(fan_flag){
                    case 0:
                        audiocontrol.Set_AdvFan(false);
                        ImageView_fan.setImageDrawable(getResources().getDrawable(R.drawable.fan_reverse));
                        fan_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvFan(true);
                        ImageView_fan.setImageDrawable(getResources().getDrawable(R.drawable.fan));
                        fan_flag = 0;
                        break;
                }

            }
        });
        ImageView_regri.setOnClickListener(new ImageView.OnClickListener()
        {
            public void onClick(View view) {

                switch(regri_flag){
                    case 0:
                        audiocontrol.Set_AdvRegri(false);
                        ImageView_regri.setImageDrawable(getResources().getDrawable(R.drawable.refrigerator_reverse));
                        regri_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvRegri(true);
                        ImageView_regri.setImageDrawable(getResources().getDrawable(R.drawable.refrigerator));
                        regri_flag = 0;
                        break;
                }

            }
        });

        GetVPMtatusTimer = new Timer();
        GetVPMtatusTimer.schedule(task,1000, 2000);


    }
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            boolean[] active = new boolean[1];

            audiocontrol.Get_AdvCosensor(active);
            if(active[0]) {
                Log.d("HIT-R181B", "cosensor_true");
                ImageView_advcosensor.setImageDrawable(getResources().getDrawable(R.drawable.cosensor));
                audiocontrol.Set_AdvBuzzer(false);
            }else{
                ImageView_advcosensor.setImageDrawable(getResources().getDrawable(R.drawable.cosensor_reverse));
                Log.d("HIT-R181B", "cosensor_false");
                audiocontrol.Set_AdvBuzzer(true);
            }

        }
    };
    TimerTask task = new TimerTask()
    {
        public void run()
        {
            Message msg =new Message();
            handler.sendMessage(msg);
        }
    };

}
