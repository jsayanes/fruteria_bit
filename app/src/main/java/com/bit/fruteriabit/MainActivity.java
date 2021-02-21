package com.bit.fruteriabit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bit.fruteriabit.entities.Fruta;
import com.bit.fruteriabit.models.FrutaViewModel;
import com.bit.fruteriabit.repositories.FrutaFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//clase MainActivity
public class MainActivity extends AppCompatActivity {

   private FrutaViewModel frutaViewModel;
    private static final int NEW_FRUTA_REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFrutas);
        final FrutaListAdapter adapter = new FrutaListAdapter(new FrutaListAdapter.FrutaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        frutaViewModel = new ViewModelProvider(this, new FrutaFactory(getApplication())).get(FrutaViewModel.class);

        frutaViewModel.getFrutas().observe(this,frutas ->{
            adapter.submitList(frutas);
        });

        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,AgregarFrutaActivity.class);
            startActivityForResult(intent, NEW_FRUTA_REQ_CODE);

        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==NEW_FRUTA_REQ_CODE && resultCode==RESULT_OK){
            Fruta fruta = new Fruta();
            fruta.setNombre(data.getStringExtra(AgregarFrutaActivity.EXTRA_MSG));
            frutaViewModel.insert(fruta);
        } else {
            Context context;
            CharSequence text;
            Toast.makeText(getApplicationContext(),R.string.no_guardado,Toast.LENGTH_LONG).show();
        }
    }
}