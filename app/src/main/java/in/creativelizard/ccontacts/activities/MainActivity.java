package in.creativelizard.ccontacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import in.creativelizard.ccontacts.data.DatabaseHandler;
import in.creativelizard.ccontacts.items.ContactItem;
import in.creativelizard.ccontacts.adapters.ContactListAdapter;
import in.creativelizard.ccontacts.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlContacts;
    private ContactListAdapter contactListAdapter;
    private ArrayList<ContactItem> contactItems;
    private RecyclerView.LayoutManager layoutManager;
    private DatabaseHandler dbl;
    private LinearLayout llEmptyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(dbl.getAllContactList().size()>0){
            llEmptyList.setVisibility(View.GONE);
            loadList();
        }else {
            llEmptyList.setVisibility(View.VISIBLE);
        }

    }

    private void loadList() {
        contactItems.clear();
        contactItems.addAll(dbl.getAllContactList());
        contactListAdapter.notifyDataSetChanged();
    }

    private void initialize() {
        llEmptyList = findViewById(R.id.llEmptyList);
        dbl = new DatabaseHandler(this);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        contactItems = new ArrayList<>();
        rlContacts = findViewById(R.id.rlContacts);
        contactListAdapter = new ContactListAdapter(contactItems,this);
        rlContacts.setLayoutManager(layoutManager);
        rlContacts.setAdapter(contactListAdapter);
    }

    public void clkAdd(View view){
        startActivity(new Intent(getBaseContext(),AddContactActivity.class));
    }
}
