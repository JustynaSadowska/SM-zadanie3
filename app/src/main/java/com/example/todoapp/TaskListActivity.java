package com.example.todoapp;

import android.os.Bundle;
import android.content.Intent;
import androidx.fragment.app.Fragment;

public class TaskListActivity extends SingleFragmentActivity {

    public static final String KEY_EXTRA_TASK_NAME = "extra_task_name";
    public static final int REQUEST_UPDATE_TASK = 1;
    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_UPDATE_TASK) {
                if (data != null && data.hasExtra(KEY_EXTRA_TASK_NAME)) {
                    String updatedTaskName = data.getStringExtra(KEY_EXTRA_TASK_NAME);
                }
            }
        }
    }

}