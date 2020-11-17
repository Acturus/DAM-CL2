package com.cibertec.damcl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.cibertec.damcl2.Helpers.TableViewAdapter;
import com.cibertec.damcl2.Helpers.UserRow;
import com.cibertec.damcl2.Helpers.UsersInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(Html.fromHtml("<b>CIBERTEC</b>"));

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);

        UsersCall();
    }

    private void UsersCall(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UsersInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UsersInterface api = retrofit.create(UsersInterface.class);

        Call<String> call = api.getUsuario();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String jsonresponse = response.body();
                        procesarUsuarios(jsonresponse);
                    }
                    else {
                        Log.i("onEmptyResponse", "La petición no devolvió nada");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call,@NonNull Throwable t) {}
        });
    }

    private void procesarUsuarios(String jsonresponse) {

        Log.d("Response", jsonresponse);

        List<UserRow> usuarios = new ArrayList<>();

        try {
            JSONArray dataArray  = new JSONArray(jsonresponse);

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject dataobj = dataArray.getJSONObject(i);

                usuarios.add(new UserRow(
                        dataobj.getString("nombres"),
                        dataobj.getString("apaterno"),
                        dataobj.getString("amaterno"),
                        dataobj.getString("email"),
                        dataobj.getString("celular"),
                        dataobj.getString("fechaNacimiento"),
                        dataobj.getString("ubicacion"),
                        dataobj.getString("fechaRegistro")
                ));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        TableViewAdapter adapter = new TableViewAdapter(usuarios);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void agregar(View view) {
        Intent i = new Intent(this,AgregarUsuario.class);
        startActivity(i);
    }
}