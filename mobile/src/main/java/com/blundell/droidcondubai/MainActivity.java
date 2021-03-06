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
import android.widget.Toast;

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
                        Notification notification = buildNotification();
                        show(notification);
                    }
                });

        Bundle remoteInput = RemoteInput.getResultsFromIntent(getIntent());
        if (remoteInput != null) {
            CharSequence reply = remoteInput.getCharSequence(KEY_REPLY);
            Toast.makeText(this, reply, Toast.LENGTH_LONG).show();
        }
    }

    private Notification buildNotification() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setData(Uri.parse("geo:24,54?q=dubai"));
        PendingIntent pendingMapIntent = PendingIntent.getActivity(MainActivity.this, 123, mapIntent, 0);
        NotificationCompat.Action mapAction = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Map", pendingMapIntent).build();

        RemoteInput remoteInput = new RemoteInput.Builder(KEY_REPLY)
                .setLabel("Voice reply?")
                .setChoices(getResources().getStringArray(R.array.reply))
                .build();
        Intent replyIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent pendingReplyIntent = PendingIntent.getActivity(MainActivity.this, 321, replyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Reply", pendingReplyIntent)
                .addRemoteInput(remoteInput)
                .build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        NotificationCompat.WearableExtender wearNotificationExtras = new NotificationCompat.WearableExtender().addAction(mapAction).addAction(replyAction);

        return builder
                .setContentTitle("Hello Droidcon")
                .setContentText("TEST")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(mapAction)
                .extend(wearNotificationExtras)
                .build();
    }

    private void show(Notification notification) {
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
