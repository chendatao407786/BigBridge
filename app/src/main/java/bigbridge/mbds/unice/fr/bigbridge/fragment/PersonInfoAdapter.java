package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import bigbridge.mbds.unice.fr.bigbridge.R;

public class PersonInfoAdapter extends RecyclerView.Adapter<PersonInfoAdapter.MyViewHolder>{
    private JSONObject mDataSet;
    public PersonInfoAdapter(JSONObject mDataSet){
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_list_adapter,viewGroup,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        try {
            String name = mDataSet.names().get(i).toString();
            myViewHolder.fieldName.setText(name);
            myViewHolder.value.setText(mDataSet.getString(name));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.names().length();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView fieldName;
        TextView value;
        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            fieldName = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),fieldName.getText(),Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}
