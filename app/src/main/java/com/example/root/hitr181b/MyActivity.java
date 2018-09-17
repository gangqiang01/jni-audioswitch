package com.example.root.hitr181b;


import android.app.Activity;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;



public class MyActivity extends Activity {

    private int led_flag = 0;
    private int air_flag = 0;
    private int fan_flag = 0;
    private int regri_flag = 0;
    private int advcosensor_flag = 0;
    private  Button Button_allon;
    private  Button Button_alloff;
    private  Button Button_led;
    private  Button Button_air;
    private  Button Button_fan;
    private  Button Button_regri;
    private  Button Button_advcosensor;
    Timer GetVPMtatusTimer;




    JNI_AudioSwitch audiocontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button_allon = (Button)findViewById(R.id.allon);
        Button_alloff = (Button)findViewById(R.id.alloff);
        Button_led = (Button)findViewById(R.id.led);
        Button_air = (Button)findViewById(R.id.aircondition);
        Button_fan = (Button)findViewById(R.id.fan);
        Button_regri = (Button)findViewById(R.id.refrigerator);
        Button_advcosensor = (Button)findViewById(R.id.cosensor);




        audiocontrol = new JNI_AudioSwitch();

        Button_allon.setOnClickListener(new Button.OnClickListener()
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
        Button_alloff.setOnClickListener(new Button.OnClickListener()
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
        Button_air.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {

                switch(air_flag){
                    case 0:
                        audiocontrol.Set_AdvAir(false);
                        Button_air.setActivated(true);
                        air_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvAir(true);
                        Button_air.setActivated(false);
                        air_flag = 0;
                        break;
                }

            }
        });
        Button_led.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {

                switch(led_flag){
                    case 0:
                        audiocontrol.Set_AdvLed(false);
                        Button_led.setActivated(true);
                        led_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvLed(true);
                        Button_led.setActivated(false);
                        led_flag = 0;
                        break;
                }

            }
        });
        Button_fan.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {

                switch(fan_flag){
                    case 0:
                        audiocontrol.Set_AdvFan(false);
                        Button_fan.setActivated(true);
                        fan_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvFan(true);
                        Button_fan.setActivated(false);
                        fan_flag = 0;
                        break;
                }

            }
        });
        Button_regri.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View view) {

                switch(regri_flag){
                    case 0:
                        audiocontrol.Set_AdvRegri(false);
                        Button_regri.setActivated(true);
                        regri_flag = 1;
                        break;
                    case 1:
                        audiocontrol.Set_AdvRegri(true);
                        Button_regri.setActivated(false);
                        regri_flag = 0;
                        break;
                }

            }
        });

        GetVPMtatusTimer = new Timer();
        GetVPMtatusTimer.schedule(task,1000, 1000);


    }
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            boolean[] active = new boolean[1];

            audiocontrol.Get_AdvCosensor(active);

//            System.out.printf("cosensor is d%",active[0]);
            if(active[0]) {
                Log.d("HIT-R181B", "cosensor_true");
                Button_advcosensor.setActivated(false);
            }else{
                Button_advcosensor.setActivated(true);
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
