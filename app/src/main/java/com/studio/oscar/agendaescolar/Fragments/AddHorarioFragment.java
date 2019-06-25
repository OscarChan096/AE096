package com.studio.oscar.agendaescolar.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.studio.oscar.agendaescolar.R;

public class AddHorarioFragment extends DialogFragment {
    public interface OnAddHorarioListener{
        void Agregar(String hr, String tohr);
    }

    AddHorarioFragment.OnAddHorarioListener dialogLis;

    private static final String TAG = DialogoInicio.class.getSimpleName();

    public AddHorarioFragment() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return createLoginDialogo();
    }

    /**
     * Crea un diálogo con personalizado para comportarse
     * como formulario de login
     *
     * @return Diálogo
     */
    public AlertDialog createLoginDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Agregar hora");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.add_hora, null);

        builder.setView(v);

        final EditText hr = v.findViewById(R.id.hora);
        final EditText tohr = v.findViewById(R.id.tohr);
        Button guardar = v.findViewById(R.id.btnsavehr);

        guardar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogLis.Agregar(hr.getText().toString(), tohr.getText().toString());
                        dismiss();
                    }
                }

        );

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;
        if (context instanceof Activity){
            activity=(Activity) context;
        }

        try {
            dialogLis = (AddHorarioFragment.OnAddHorarioListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(
                    activity.toString() +
                            " no implementó OnSimpleDialogListener");

        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                dialogLis = (AddHorarioFragment.OnAddHorarioListener) activity;

            } catch (ClassCastException e) {
                throw new ClassCastException(
                        activity.toString() +
                                " no implementó OnSimpleDialogListener");

            }
        }
    }
}
