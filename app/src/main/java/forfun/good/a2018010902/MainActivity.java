package forfun.good.a2018010902;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String apple = "applegood";
    NotificationChannel channelapple;
    NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) //如果版本大於26就跑這個
        {
            regChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void regChannel()
    {

            channelapple = new NotificationChannel  //限制版本26以上才能用
            (
                    apple, "channel apple", NotificationManager.IMPORTANCE_HIGH
            );//設定顯示層級 high會有聲音跟彈出
        channelapple.setDescription("健康的人");//顯示在通知channel中
        channelapple.enableLights(true);
        channelapple.enableVibration(true);
        nm.createNotificationChannel(channelapple);

}



    @TargetApi(Build.VERSION_CODES.O)
    @SuppressWarnings("deprecation") //舊版API不要通知

    public void CL1 (View v)
    {
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >=26)
        {
             builder = new Notification.Builder(MainActivity.this, apple);
        }
        else
        {
            builder = new Notification.Builder(MainActivity.this);
        }

        Intent it = new Intent(MainActivity.this, InfoActivity.class);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP|
        Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pi;
        pi = PendingIntent.getActivity(MainActivity.this,
                123,it,
                PendingIntent.FLAG_CANCEL_CURRENT);


        builder.setContentTitle("問候");//顯示標題
        builder.setContentText("快點吃水果");//顯示內容
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true); //點擊後自動消失
        builder.setContentIntent(pi); //執行
        Notification notify = builder.build();
        nm.notify(2,notify);
    }

}
