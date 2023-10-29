package com.example.todoapp;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.UUID;
public class TaskFragment extends Fragment {
    private EditText nameField;
    private Task task;
    private CheckBox doneCheckBox;
    private Button dateButton;
    private static final String KEY_EXTRA_TASK_NAME = "extra_task_name", ARG_TASK_ID = "task_id";

    public static TaskFragment newInstance(UUID taskId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taskId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_task,container,false);
       nameField = view.findViewById(R.id.task_name);
       doneCheckBox = view.findViewById((R.id.task_done));
       dateButton = view.findViewById(R.id.task_date);

       nameField.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            task.setName(s.toString());
            Intent result = new Intent();
            result.putExtra(KEY_EXTRA_TASK_NAME, task.getName());
            getActivity().setResult(RESULT_OK, result);
            }
            @Override
            public void afterTextChanged (Editable s){
            }
        });
        dateButton.setText(task.getDate().toString());
        dateButton.setEnabled(false);

        doneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setDone(isChecked);
            }
        });
        nameField.setText(task.getName());
        dateButton.setText(task.getDate().toString());
        doneCheckBox.setChecked(task.isDone());
        return view;
    }


    public TaskFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        task = TaskStorage.getInstance().getTask(taskId);
    }
}
