package com.vett.cryst.vetapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;


public class LoginActivity extends AppCompatActivity {
    EditText txtcorreo;
    EditText txtcontrasena;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button button;
    TextView lblregistro;
    //Base de datos
    private FirebaseAuth auth;
    boolean yaSeHaQuejado;
    //public static String nombreUsuarioactual;
    public static boolean registrado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //______________________________________________
        auth = FirebaseAuth.getInstance();
        txtcorreo = (EditText) findViewById(R.id.txtcorreo);
        txtcontrasena = (EditText) findViewById(R.id.txtcontrasena);
        button = (Button) findViewById(R.id.button);
        lblregistro = (TextView) findViewById(R.id.lblregistro);

//______________________________________________________
        lblregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registro = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registro);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = txtcorreo.getText().toString();
                String contrasena = txtcontrasena.getText().toString();
                if (correo.equals("") || contrasena.equals("")) {
                    Toast.makeText(LoginActivity.this, "Inserte los campos vacíos", Toast.LENGTH_SHORT).show();
                } else {
                    hagoLogIn(correo, contrasena);
                }
            }
        });
        //_______________________________________________________
        if (auth.getCurrentUser() != null) {
            //NavigatorActivity.correoLogin= auth.getCurrentUser().getEmail().toString();
            finish();
            Intent intent = new Intent(this, NavigatorActivity.class);
            startActivity(intent);
        }

    }


    public void hagoLogIn(final String correo, final String contrasena) {
        yaSeHaQuejado = false;


        auth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                boolean aux = task.isSuccessful();//encuentra usuario

                if (aux && !yaSeHaQuejado) {
                    //consigoNombre(correo);
                    //NavigatorActivity.correoLogin = txtcorreo.getText().toString();

                    Intent navigator = new Intent(getApplicationContext(), NavigatorActivity.class);
                    startActivity(navigator);
                } else if (!yaSeHaQuejado)
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña erroneos",
                            Toast.LENGTH_SHORT).show();


            }
        });
    }

    /*
    private void consigoNombre(final String correo) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Usuarios");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable i = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterador = i.iterator();
                while (iterador.hasNext()) {
                    Usuario p = iterador.next().getValue(Usuario.class);
                    if(p.getCorreo().equals(correo)){
                        nombreUsuarioactual = p.getNombre();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    */

    /*
    public String getTxtcorreo() {
        return txtcorreo.getText().toString();
    }*/
}
