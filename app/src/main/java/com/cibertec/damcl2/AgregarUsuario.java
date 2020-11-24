package com.cibertec.damcl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.cibertec.damcl2.Helpers.UsersInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AgregarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_usuario);


    }

    public void registrar(View view) {
        String nombres = ((EditText)findViewById(R.id.editTextTextPersonName)).getText().toString();
        String apePaterno = ((EditText)findViewById(R.id.editTextTextPersonName2)).getText().toString();
        String apeMaterno = ((EditText)findViewById(R.id.editTextTextPersonName3)).getText().toString();
        String email = ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
        String celular = ((EditText)findViewById(R.id.editTextPhone)).getText().toString();
        String fechaNac = ((EditText)findViewById(R.id.editTextDate)).getText().toString();
        String direccion = ((EditText)findViewById(R.id.editTextTextMultiLine)).getText().toString();
        String ubigeo = ((EditText)findViewById(R.id.autoCompleteTextView)).getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UsersInterface api = retrofit.create(UsersInterface.class);

        Call<String> call = api.createNewUser(nombres, apeMaterno, apeMaterno, email, celular, fechaNac, direccion, ubigeo);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    AlertDialog alertDialog = new AlertDialog.Builder(AgregarUsuario.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("El usuario fue registrado con éxito");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent i = new Intent(AgregarUsuario.this,MainActivity.class);
                                    startActivity(i);
                                }
                            });
                    alertDialog.show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call,@NonNull Throwable t) {}
        });
    }
}