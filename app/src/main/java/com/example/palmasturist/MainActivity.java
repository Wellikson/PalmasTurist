package com.example.palmasturist;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private  static final int CODIGO_SOLICITACAO = 1;
    private  static final  String PERMISSAO = Manifest.permission.ACCESS_FINE_LOCATION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_praia_graciosa, R.id.nav_girassois,R.id.nav_praia_arnos,
                R.id.nav_praia_buriti,R.id.nav_praia_caju,R.id.nav_praia_prata,R.id.nav_povos_indigenas,
                R.id.nav_cesamar)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        solicitarPermissao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private  void solicitarPermissao(){
        int temPermissao = ContextCompat.checkSelfPermission(this,PERMISSAO);
        if(temPermissao!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{PERMISSAO},CODIGO_SOLICITACAO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0){
           if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this,PERMISSAO)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Atenção")
                            .setMessage("A permissão é  necessária para ....")
                            .setCancelable(false)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{PERMISSAO}
                                            ,CODIGO_SOLICITACAO);
                                }
                            })
                            .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "E necessário a Permissão para funcionar",
                                            Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    finish();
                }
            }
        }
    }
}