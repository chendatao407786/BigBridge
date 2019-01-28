package bigbridge.mbds.unice.fr.bigbridge.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.MyViewHolder>{
    private JSONObject mDataSet;
    private Context context;

    public MyProfileAdapter(Context context, JSONObject mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_profile_adapter, viewGroup, false);
        MyProfileAdapter.MyViewHolder myViewHolder = new MyProfileAdapter.MyViewHolder(view);
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

            switch (name) {
                case "NAME":
                    myViewHolder.image.setImageResource(R.drawable.ic_identity);
                    break;
                case "BIRTHDAY":
                    myViewHolder.image.setImageResource(R.drawable.ic_calendar);
                    break;
                case "SEX":
                    myViewHolder.image.setImageResource(R.drawable.ic_sex);
                    break;
                case "WEIGHT":
                    myViewHolder.image.setImageResource(R.drawable.ic_weight);
                    break;
                case "SPORT":
                    myViewHolder.image.setImageResource(R.drawable.ic_runningshoes);
                    break;
                case "HEART DISEASE":
                    myViewHolder.image.setImageResource(R.drawable.ic_heartdi);
                    break;
                case "ASTHMA":
                    myViewHolder.image.setImageResource(R.drawable.ic_asthma);
                    break;
                case "SMOKING":
                    myViewHolder.image.setImageResource(R.drawable.ic_smoking);
                    break;
                default:
                    myViewHolder.image.setImageResource(R.drawable.ic_greatthan);
            }
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

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            fieldName = itemView.findViewById(R.id.key);
            value = itemView.findViewById(R.id.value);
            image = itemView.findViewById(R.id.iconModify);
        }
    }
}
