package com.example.composeapplication

import android.app.Activity
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.math.roundToInt

class StepDetectorService : Service(), SensorEventListener {
    private val stepsKey = "FSteps"
    private val todayDateKey = "TodayDate"
    private val takenStepsKey = "Steps"

    companion object {
        lateinit var callback: StepsCallback
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val countSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (countSensor != null) {
            Toast.makeText(this, "Step Detecting Start", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL)
            //show taken steps as notification
            //GeneralHelper.updateNotification(this, this, PrefsHelper.getInt(stepsKey))
            callback.subscribeSteps(PrefsHelper.getInt(stepsKey))

        } else {
            Toast.makeText(this, "Sensor Not Detected", Toast.LENGTH_SHORT).show()
        }

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        //TODO("Not yet implemented")
        return null
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        if (PrefsHelper.getString(todayDateKey) != GeneralHelper.getToadyDate()) {
            PrefsHelper.putInt(takenStepsKey, p0!!.values[0].roundToInt())
            PrefsHelper.putString(todayDateKey, GeneralHelper.getToadyDate())
        } else {
            val storeSteps = PrefsHelper.getInt(takenStepsKey)
            val sensorSteps = p0!!.values[0].roundToInt()
            val finalSteps = sensorSteps - storeSteps
            if (finalSteps > 0) {
                PrefsHelper.putInt(stepsKey, finalSteps)

                //show taken steps as notification
                //GeneralHelper.updateNotification(this, this, finalSteps)
                callback?.subscribeSteps(finalSteps)
            }
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("SERVICE", p0.toString())
    }

    object subscribe {
        fun register(stepsCallback: StepsCallback) {
            callback = stepsCallback
        }
    }

}