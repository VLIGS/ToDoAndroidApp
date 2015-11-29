package workshop.thoughtworks.com.myandroidapp;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = createAddTodoDialog();
                dialog.show();

            }
        });
        ListView todoListView = (ListView) findViewById(R.id.todoListView);
        todoAdapter = new TodoAdapter(this, new ArrayList<String>());
        todoListView.setAdapter(todoAdapter);
        todoAdapter.add("Bread");
        todoAdapter.add("Jelly");
        todoAdapter.add("Peanut butter");

    }


    //Setting up the FAB
    private AlertDialog createAddTodoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //An EditText is like a text field, it's a view which allows the user to edit some text
        final EditText editText = new EditText(this);

        //Set the main view of the dialog to be the EditText
        builder.setView(editText);

        //Set the text for the buttons and their actions
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                /*
                 * When add is clicked we add the user entered text
                 * to the list that the adapter is managing
                 */
                todoAdapter.add(editText.getText().toString());

            }
        });

    /*
     * We don't want to do anything when the user selects cancel,
     * so we just pass null as the onClickListener
     */
        builder.setNegativeButton("Cancel", null);

        //Return the created AlertDialog
        return builder.create();
    }



    class TodoAdapter extends ArrayAdapter<String>{

        public TodoAdapter(Context context, List<String> objects) {
            super(context, R.layout.todo_item, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item,parent,false);
            }
            CheckBox todoCheckBox = (CheckBox) convertView.findViewById(R.id.todoCheckbox);
            todoCheckBox.setText(getItem(position));
            return convertView;
        }
    }
}
