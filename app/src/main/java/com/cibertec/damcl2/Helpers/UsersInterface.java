package com.cibertec.damcl2.Helpers;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersInterface {
    String JSONURL = "https://artacuri.dev/api/Rest/";

    @GET("Usuario")
    Call<String> getUsuario();

    @GET("Ubigeo")
    Call<String> getUbigeo();
}