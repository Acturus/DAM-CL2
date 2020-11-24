package com.cibertec.damcl2.Helpers;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UsersInterface {
    String JSONURL = "https://artacuri.dev/api/Rest/";

    @GET("Usuario")
    Call<String> getUsuario();

    @GET("Ubigeo")
    Call<String> getUbigeo();

    @FormUrlEncoded
    @POST("AddUsuario")
    Call<String> createNewUser(@Field("nom") String nombre,
                               @Field("pat") String apellidopa,
                               @Field("mat") String apellidoma,
                               @Field("ema") String email,
                               @Field("cel") String celu,
                               @Field("nac") String nac,
                               @Field("dir") String dir,
                               @Field("ubi") String ubi);

}