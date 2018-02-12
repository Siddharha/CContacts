package in.creativelizard.ccontacts.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import in.creativelizard.ccontacts.R;
import in.creativelizard.ccontacts.data.DatabaseHandler;

public class AddContactActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseHandler dbl;
    private EditText etName,etNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        initialize();
    }

    private void initialize() {
        dbl = new DatabaseHandler(this);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        etName = findViewById(R.id.etName);
        etNumber = findViewById(R.id.etNumber);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuDone:
                insurtContactDate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insurtContactDate() {

        String sName = etName.getText().toString();
        String sNumber = etNumber.getText().toString();

        if(sName.isEmpty() ||sNumber.isEmpty()){
            Toast.makeText(this, "Please Enter Name and Number to Add Contact!", Toast.LENGTH_SHORT).show();
        }else {

            if(dbl.insertContact(sName,sNumber) == 1){
                Toast.makeText(this, "Contact Added!", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Faild To add Contact to Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
