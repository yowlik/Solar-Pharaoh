package com.plarium.rai

import android.app.Application
import android.content.Context
import com.onesignal.OneSignal
import com.orhanobut.hawk.Hawk
import com.plarium.rai.black.Advac
import com.plarium.rai.black.CNST
import com.plarium.rai.black.CNST.ONESIGNAL_APP_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Appfa  : Application() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        GlobalScope.launch(Dispatchers.IO) {
            applyDeviceId(context = applicationContext)
        }
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)


    }

    private suspend fun applyDeviceId(context: Context) {
        val advertisingInfo = Advac(context)
        val idInfo = advertisingInfo.getAdvertisingId()
        Hawk.put(CNST.MAIN_ID, idInfo)
    }
}