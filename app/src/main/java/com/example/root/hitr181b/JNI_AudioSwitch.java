package com.example.root.hitr181b;

/**
 * Created by root on 2014/10/24.
 */

public class JNI_AudioSwitch{

    public native int   JNI_Set_AdvLed(boolean bActive);
    public native int   JNI_Set_AdvAir(boolean bActive);
    public native int    JNI_Set_AdvFan(boolean bActive);
    public native int    JNI_Set_AdvRegri(boolean bActive);
    public native int    JNI_Get_AdvCosensor(boolean [] bActive);
    public native int    JNI_Set_AdvBuzzer(boolean bActive);



    static{
        System.loadLibrary("SUSILib");

    }


    public int  Set_AdvLed(boolean bActive){

        return  JNI_Set_AdvLed(bActive);
    }

    public int   Set_AdvAir(boolean bActive){


        return  JNI_Set_AdvAir(bActive);
    }

    public int  Set_AdvFan(boolean bActive){

        return   JNI_Set_AdvFan(bActive);
    }

    public int  Set_AdvRegri(boolean bActive){
       return   JNI_Set_AdvRegri(bActive);
    }

    public int  Get_AdvCosensor(boolean [] bActive){
        return   JNI_Get_AdvCosensor(bActive);
    }

    public int  Set_AdvBuzzer(boolean bActive){
        return    JNI_Set_AdvBuzzer(bActive);
    }
}
