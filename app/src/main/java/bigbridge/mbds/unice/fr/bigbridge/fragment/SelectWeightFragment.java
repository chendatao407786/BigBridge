package bigbridge.mbds.unice.fr.bigbridge.fragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bigbridge.mbds.unice.fr.bigbridge.R;

/**
 * A simple {@link android.support.v4.app.DialogFragment} subclass.
 */
public class SelectWeightFragment extends DialogFragment {


    public SelectWeightFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayList<CharSequence> weight = new ArrayList<>();
        for(int i = 5;i<200;i++){
            weight.add(String.valueOf(i));
        }
        CharSequence[] weigthArray = weight.toArray(new CharSequence[weight.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Select your weight")
                .setItems(weigthArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_weight, container, false);
    }

}
