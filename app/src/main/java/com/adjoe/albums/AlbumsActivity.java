package com.adjoe.albums;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import com.adjoe.R;
import com.adjoe.utils.AlbumEntity;
import com.adjoe.utils.JobHelper;
import java.util.Collections;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity implements AlbumsContract.View {

    private AlbumsPresenter presenter;
    private ListView albumsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new AlbumsPresenter(this);
        presenter.onViewCreated();
        albumsList = findViewById(R.id.albumsList);
    }

    @Override
    public void onDataEmpty() {
        runOnUiThread(() -> Toast.makeText(this, getString(R.string.data_empty), Toast.LENGTH_LONG).show());
    }

    @Override
    public void onError(String error) {
        runOnUiThread(() -> Toast.makeText(this, error, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onDataLoaded(List<AlbumEntity> albums) {
        Collections.sort(albums);
        runOnUiThread(() -> albumsList.setAdapter(new AlbumsAdapter(this, albums)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroyed();
    }

    @Override
    public void setJobSchedule() {
        JobHelper.setJob(this);
    }
}
