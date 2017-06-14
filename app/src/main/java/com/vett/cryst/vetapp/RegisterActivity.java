package com.vett.cryst.vetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText txtccorreo;
    EditText txtccontrasena;
    EditText txtrepcontrasena;
    Button button;
    //Base de datos
    private FirebaseAuth auth;
    private DatabaseReference ref;
    //tengo que dar de alta en firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //____________________________________

        auth = FirebaseAuth.getInstance();
        txtccorreo = (EditText) findViewById(R.id.txtccorreo);
        txtccontrasena = (EditText) findViewById(R.id.txtccontrasena);
        txtrepcontrasena = (EditText) findViewById(R.id.txtrepcontrasena);
        button = (Button) findViewById(R.id.button);
//____________________________________________
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String correo = txtccorreo.getText().toString();
                final String contrasena = txtccontrasena.getText().toString();
                String contrasenaREP = txtrepcontrasena.getText().toString();
                if (correo.isEmpty() || contrasena.isEmpty() || contrasenaREP.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();

                } else if (!contrasena.equals(contrasenaREP)) {
                    Toast.makeText(RegisterActivity.this, "Contraseñas distintas", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(correo, contrasena)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    boolean aux = task.isSuccessful();
                                    if (aux) {
                                        Toast.makeText(RegisterActivity.this, "Registrado", Toast.LENGTH_SHORT).show();

                                        Usuario a = new Usuario(correo, contrasena);
                                        guardoUsuario(a);
                                        iniciarMain();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Error al insertar usuario", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                private void iniciarMain() {
                                    Intent i = new Intent(getApplicationContext(), NavigatorActivity.class);
                                    startActivity(i);

                                }
                            });


                }


            }
        });


    }


    public void guardoUsuario(Usuario a) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Usuarios");


        Map m = new HashMap<>();
        m.put(a.getCorreo(), a);
        myRef.updateChildren(m);

        txtccorreo.setText("");
        txtccontrasena.setText("");
        txtrepcontrasena.setText("");


    }

}
