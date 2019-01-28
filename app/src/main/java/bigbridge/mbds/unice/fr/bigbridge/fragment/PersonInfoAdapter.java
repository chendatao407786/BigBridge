package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.R;

import static bigbridge.mbds.unice.fr.bigbridge.R.string.name;

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.MyViewHolder> {
    private JSONObject mDataSet;
    private Context context;
    private PersonInfoFragment.IPersonListener iPersonListener;

    public PersonInfoAdapter(Context context, JSONObject mDataSet, PersonInfoFragment.IPersonListener iPersonListener) {
        this.context = context;
        this.mDataSet = mDataSet;
        this.iPersonListener = iPersonListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_list_adapter, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view, iPersonListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            String name = mDataSet.names().get(i).toString();
            myViewHolder.fieldName.setText(name);
            if(mDataSet.getString(name).equals("null")||mDataSet.getString(name).equals("0")){
                myViewHolder.value.setText("");
            }else {
                myViewHolder.value.setText(mDataSet.getString(name));
            }
            myViewHolder.image.setImageResource(R.drawable.ic_modify);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.names().length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView fieldName;
        TextView value;
        ImageView image;
        PersonInfoFragment.IPersonListener personListener;

        public MyViewHolder(@NonNull final View itemView, PersonInfoFragment.IPersonListener iPersonListener) {
            super(itemView);
            fieldName = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
            personListener = iPersonListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), fieldName.getText(), Toast.LENGTH_LONG).show();
                    String key = fieldName.getText().toString();
                    String valeur = value.getText().toString();
                    FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    ModifierFragment modifierFragment;
                    switch (key) {
                        case "BIRTHDAY":
                            modifierFragment = new ModifyDateFragment();
                            break;
                        default:
                            modifierFragment = new ModifyTextFragment();
                    }
                    transaction.replace(R.id.inscription, modifierFragment, "modifierFragment");
                    personListener.sendMsgs(key, valeur, modifierFragment);
                    transaction.commit();
                }
            });
            image = itemView.findViewById(R.id.iconModify);
        }
    }
}
