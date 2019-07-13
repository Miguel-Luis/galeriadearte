package com.example.galeriadearte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.galeriadearte.Interface.MostrarPintura;
import com.example.galeriadearte.models.Pintura;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView pinturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pinturas = findViewById(R.id.jsonPinturas);
        getPinturas();
    }

    private void getPinturas() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://themoviles130514.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MostrarPintura mostrarPintura = retrofit.create(MostrarPintura.class);

        Call<List<Pintura>> call = mostrarPintura.getPintura();

        call.enqueue(new Callback<List<Pintura>>() {
            @Override
            public void onResponse(Call<List<Pintura>> call, Response<List<Pintura>> response) {
                if(!response.isSuccessful()) {
                    pinturas.setText("Codigo: " + response.code());
                    return;
                }

                List<Pintura> pinturaLista = response.body();

                for(Pintura pintura: pinturaLista) {
                    String content = "";
                    content += "ID: " + pintura.getId_pintura() + "\n";
                    content += "Nombre: " + pintura.getNombre_pintura() + "\n";
                    content += "Descripcion: " + pintura.getDescripcion_pintura() + "\n";
                    content += "Categoria: " + pintura.getNombre_categoria() + "\n";
                    content += "Autor: " + pintura.getNombre_autor() + "\n\n";

                    pinturas.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Pintura>> call, Throwable t) {
                pinturas.setText(t.getMessage());
            }
        });
    }
}
