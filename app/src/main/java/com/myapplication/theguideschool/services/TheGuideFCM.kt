package com.myapplication.theguideschool.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.myapplication.theguideschool.R
import com.myapplication.theguideschool.datalayer.AppDB
import com.myapplication.theguideschool.datalayer.NotificationDB
import com.myapplication.theguideschool.ui.HostActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class TheGuideFCM : FirebaseMessagingService() {

    private val TAG: String = "TheGuideFCM"
    private val compositeDisposable = CompositeDisposable()
    private val time = SimpleDateFormat("dd/MM/yyyy hh:mm a").format(Date())


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO(developer): Handle FCM messages here.
        Log.d(TAG, "From: " + remoteMessage.from)
        if (remoteMessage.data.isEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
//                scheduleJob();
//                saveNotification(remoteMessage.notification?.title.toString(), remoteMessage.notification?.body.toString(), time)
            } else {
                // Handle message within 10 seconds
                //handleNow();
//                saveNotification(remoteMessage.notification?.title.toString(), remoteMessage.notification?.body.toString(), time)
            }

        }
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification?.body)
            displayNotification(
                remoteMessage.notification?.title.toString(),
                remoteMessage.notification?.body.toString()
            )

        }
    }

    override fun onNewToken(p0: String) {
        val token1 = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "Refreshed token: " + token1)
        Log.d(TAG, "Refreshed token: " + p0)
    }

    private fun displayNotification(title: String, message: String) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = "The Guide"
        val description = "Notifications"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel("guideNotifications", name, importance)
            notificationChannel.description = description
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val intent = Intent(this, HostActivity::class.java)
        intent.putExtra("fragment", 1)
        val builder = NotificationCompat.Builder(this, "guideNotifications")
        builder.setContentTitle(title)
        builder.setContentText(message)
        builder.setSmallIcon(R.drawable.ic_add_alert_black_24dp)
        builder.setAutoCancel(true)
        builder.setContentIntent(
            PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        )
        saveNotification(title, message, time)
        notificationManager.notify(0, builder.build())
    }

    private fun saveNotification(title: String, body: String, time: String) {
        compositeDisposable.add(
            Observable.create<Unit> {
                try {
                    val db = AppDB.getAppDataBase(this)
                    val notificationDB = db?.notificationDB()
                    with(notificationDB) {
                        this?.insertall(
                            NotificationDB(
                                0,
                                title,
                                body,
                                time
                            )
                        )
                    }
                    it.onNext(Unit)
                } catch (e: Exception) {
                    it.onError(e)
                    Log.e("DB Notification", e.message.toString())
                }
                it.onComplete()
            }.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

}