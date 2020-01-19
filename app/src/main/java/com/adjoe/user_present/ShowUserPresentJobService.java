package com.adjoe.user_present;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.adjoe.utils.NotificationHelper;
import com.adjoe.utils.SharedPrefHelper;

public class ShowUserPresentJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Long lastTimePresent = SharedPrefHelper.getLong(this);
        if (lastTimePresent > 0) {
            long presentDuration = System.currentTimeMillis() - lastTimePresent;
            long minutes = (presentDuration / 1000) / 60;
            NotificationHelper.addNotification(
                    this, "User presentation", minutes + " Minutes"
            );
        }

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
