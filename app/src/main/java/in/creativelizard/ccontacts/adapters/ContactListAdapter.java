package in.creativelizard.ccontacts.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import in.creativelizard.ccontacts.items.ContactItem;
import in.creativelizard.ccontacts.R;

/**
 * Created by siddhartha on 12/2/18.
 */

public class ContactListAdapter extends  RecyclerView.Adapter<ContactListAdapter.MyViewHolder>  {

    private ArrayList<ContactItem> contactItems;
    private Context context;

    public ContactListAdapter(ArrayList<ContactItem> contactItems, Context context) {
        this.contactItems = contactItems;
        this.context = context;
    }

    @Override
    public ContactListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.cintact_row,parent,false);

        return new ContactListAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ContactListAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(contactItems.get(position).getName());
        holder.tvNumber.setText(contactItems.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName,tvNumber;
         public MyViewHolder(View itemView) {
             super(itemView);

             tvName = itemView.findViewById(R.id.tvName);
             tvNumber = itemView.findViewById(R.id.tvNumber);
         }
     }
}
