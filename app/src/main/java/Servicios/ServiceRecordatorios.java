package Servicios;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vett.cryst.vetapp.LoginActivity;
import com.vett.cryst.vetapp.Mascota;
import com.vett.cryst.vetapp.NavigatorActivity;
import com.vett.cryst.vetapp.R;
import com.vett.cryst.vetapp.Recordatorio;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static java.security.AccessController.getContext;

public class ServiceRecordatorios extends Service {

    private FirebaseDatabase data;

    private NotificationManager notificationManager;
    private FirebaseAuth auth;
    int contador = 0;
    boolean flag = false;


    public ServiceRecordatorios() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //Peticion
        String correo = NavigatorActivity.correoLogin;

        //Bucle para repeticion de peticion

        //Comprobacion de valores
        // Clase en la que está el código a ejecutarç

        //---------------------------------------------------------------------------------------
        TimerTask timerTask = new TimerTask() {
            public void run() {
                flag=false;
                recogerFechas();
                Log.v("-----------------", "????????????????????????????????????????????????????????????");
                //flag=true;
            }
        };
        //timerTask.run();
        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos
        timer.scheduleAtFixedRate(timerTask, 0, 3600000);
//------------------------------------------------------------------------------------------


        //Crea el servicio cuando se cierra la aplicación
        return START_STICKY;

    }


    public void recogerFechas() {
        if(flag==false){
            procesoNotificacion();

        }



    }

    public void procesoNotificacion(){
        //Empiezo busqueda de fecha por dia proximo
        auth = FirebaseAuth.getInstance();
        data = FirebaseDatabase.getInstance();
        String email = auth.getCurrentUser().getEmail().toString();

        Calendar c1 = Calendar.getInstance();
        int diahoy = Integer.parseInt(String.valueOf(c1.get(Calendar.DATE))) + 1;
        int meshoy = Integer.parseInt(String.valueOf(c1.get(Calendar.MONTH))) + 1;

        final String fechamirar = diahoy + "/" + meshoy + "/" + c1.get(Calendar.YEAR);
        Log.v("funciona1 " + fechamirar, "DIA DE HOY BITCH");
        DatabaseReference ref = data.getReference("Recordatorios");
        Query consulta = ref.orderByChild("correo").equalTo(email);
        consulta.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable i = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterador = i.iterator();
                while (iterador.hasNext()) {
                    Recordatorio recorda = iterador.next().getValue(Recordatorio.class);
                    Log.v("_______________________", recorda.toString());
                    String fechaFirebase = recorda.getFecha().toString();
                    String cambioFecha = null;
                    if (fechaFirebase.charAt(0) == '0') {
                        cambioFecha = fechaFirebase.substring(1, fechaFirebase.length());
                    } else {
                        cambioFecha = recorda.getFecha().toString();
                    }
                    Log.v("__________FC___________", cambioFecha);
                    if (cambioFecha.equals(fechamirar)) {
                        contador++;
                        notification1(contador, R.mipmap.ic_launcher, "Recordatiorio Alcarria", recorda.getMensaje().toString());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.v("PIÑATA De ErOr", "HOLI CARACOLI SOY UN ERROR");
            }
        });
    }

    public void notification1(int id, int iconId, String titulo, String contenido) {

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(iconId)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle(titulo)
                .setContentText(contenido)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));

        notificationManager.notify(id, builder.build());

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }
}
