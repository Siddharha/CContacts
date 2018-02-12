package in.creativelizard.ccontacts.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import in.creativelizard.ccontacts.items.ContactItem;
import in.creativelizard.ccontacts.adapters.ContactListAdapter;
import in.creativelizard.ccontacts.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlContacts;
    private ContactListAdapter contactListAdapter;
    private ArrayList<ContactItem> contactItems;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        loadList();
    }

    private void loadList() {
        for(int x=0;x<10;x++){
            ContactItem contactItem = new ContactItem();

            contactItem.setName("Sid - "+x);
            contactItem.setNumber("956475191"+x);

            contactItems.add(contactItem);
        }
        contactListAdapter.notifyDataSetChanged();
    }

    private void initialize() {
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
