package com.blundell.droidcondubai;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private static final String KEY_REPLY = "remoteInputKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.simple_notification_button).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showNotification();
                    }
                });

    }

    private void showNotification() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:24,54?q=dubai"));
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 123, intent, 0);

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_REPLY)
                .setLabel("Voice reply?")
                .setChoices(getResources().getStringArray(R.array.reply))
                .build();
        Intent replyIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingReplyIntent = PendingIntent.getActivity(MainActivity.this, 321, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "MAP", pendingIntent).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        Notification notification = builder
                .setContentTitle("Hello Droidcon")
                .setContentText("TEST")
                .setSmallIcon(R.mipmap.ic_launcher)
//                                .addAction(action)
                .extend(new NotificationCompat.WearableExtender().addAction(action))
                .build();
        NotificationManagerCompat notificationManger = NotificationManagerCompat.from(MainActivity.this);
        notificationManger.notify(0, notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
