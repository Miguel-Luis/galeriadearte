package com.example.galeriadearte.Interface;

import com.example.galeriadearte.models.Pintura;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MostrarPintura {
    @GET("controllers/pintura/mostrar_pintura.php")
    Call<List<Pintura>> getPintura();
}
