package com.adjoe.utils;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

import com.adjoe.user_present.ShowUserPresentJobService;

import static android.content.Context.JOB_SCHEDULER_SERVICE;
import static com.adjoe.utils.Constants.JOB_INTERVAL_MILLI;

public class JobHelper {

    public static void setJob(Context context) {
        JobScheduler jobScheduler = (JobScheduler) context
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(context,
                ShowUserPresentJobService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(JOB_INTERVAL_MILLI)
                .setPersisted(true).build();
        jobScheduler.schedule(jobInfo);

    }

}
