package com.myapplication.daggerjava2;


import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context  context;


    ContextModule(Context context){
        this.context = context;

    }

    @Provides
    @Named("ApplicationContext")
    Context getContext() {
        return context;
    }
}
