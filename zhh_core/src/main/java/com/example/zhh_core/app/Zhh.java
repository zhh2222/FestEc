package com.example.zhh_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Zhh {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private  static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getZhhConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
