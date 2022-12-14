package com.cursoandroid.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.atmconsultoria.databinding.ActivityMainBinding;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        // Onclic do botão compartilhar
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse( "tel:014997568439" ));*/

                Intent intent = new Intent( Intent.ACTION_SEND);
                intent.putExtra( Intent.EXTRA_EMAIL, new String[]{ "antendimento@atmconsultoria.com.br" } );
                intent.putExtra( Intent.EXTRA_SUBJECT, "Contato pelo App" );
                intent.putExtra( Intent.EXTRA_TEXT, "Mensagem automatica" );

               intent.setType( "message/rfc822" );
               //intent.setType( "text/plain" );

               startActivity( Intent.createChooser( intent, "Compartilhar" ) );


            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_clientes, R.id.nav_servicos, R.id.nav_contato, R.id.nav_sobre

        )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail(){
        Intent intent = new Intent( Intent.ACTION_DIAL, Uri.parse("tel:014997568439") );
        startActivity( intent );
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}