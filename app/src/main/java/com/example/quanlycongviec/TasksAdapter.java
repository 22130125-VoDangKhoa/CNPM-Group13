package com.example.quanlycongviec;

import android.content.Context;
import android.view.*;
import android.widget.*;

import com.example.quanlycongviec.model.Task;

import java.util.ArrayList;

public class TasksAdapter extends BaseAdapter {

    Context context;
    ArrayList<Task> taskList;
    LayoutInflater inflater;
    TaskActionListener listener;

    public interface TaskActionListener {
        void onEdit(Task task);
        void onDelete(Task task);
    }

    public TasksAdapter(Context context, ArrayList<Task> taskList, TaskActionListener listener) {
        this.context = context;
        this.taskList = taskList;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return taskList.size(); }

    @Override
    public Object getItem(int position) { return taskList.get(position); }

    @Override
    public long getItemId(int position) { return taskList.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.task_item, null);

        TextView tvTask = view.findViewById(R.id.tvTaskName);
        ImageButton btnEdit = view.findViewById(R.id.btnEdit);
        ImageButton btnDelete = view.findViewById(R.id.btnDelete);

        Task task = taskList.get(position);
        tvTask.setText(task.getName());

        btnEdit.setOnClickListener(v -> listener.onEdit(task));
        btnDelete.setOnClickListener(v -> listener.onDelete(task));

        return view;
    }
}
