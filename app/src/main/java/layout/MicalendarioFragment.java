package layout;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vett.cryst.vetapp.LoginActivity;
import com.vett.cryst.vetapp.NavigatorActivity;
import com.vett.cryst.vetapp.R;
import com.vett.cryst.vetapp.Recordatorio;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MicalendarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MicalendarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MicalendarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //___________________________
    TextView txtdia, txthora;
    EditText editText2;
    Button buttonAnMas;
    private int mes, dia, anio, hora, minuto;


    private OnFragmentInteractionListener mListener;

    public MicalendarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MicalendarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MicalendarioFragment newInstance(String param1, String param2) {
        MicalendarioFragment fragment = new MicalendarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_micalendario, container, false);
        txtdia = (TextView) view.findViewById(R.id.txtdia);
        txthora = (TextView) view.findViewById(R.id.txthora);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        buttonAnMas = (Button) view.findViewById(R.id.buttonAnMas);
        //____LISENERS___
        txtdia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirAlertDia();
            }
        });

        txthora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                abrirAlertHora();
            }
        });

        buttonAnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = NavigatorActivity.correoLogin;
                String fechaString = Integer.toString(dia) + "/" + Integer.toString(mes) + "/" + Integer.toString(anio);
                String horaString = Integer.toString(hora) + ":" + Integer.toString(minuto);
                String mensaje = editText2.getText().toString();


                if(correo==null || fechaString.equals("0/0/0") || horaString.equals("0:0")||mensaje.equals("")){
                    Toast.makeText(getContext(), "Objeto no insertado", Toast.LENGTH_LONG).show();
                }else{
                    Recordatorio recordatorio = new Recordatorio(correo, fechaString, horaString, mensaje);
                    Toast.makeText(getContext(), "Recordatorio almacenado", Toast.LENGTH_LONG).show();
                    insertarObjeto(recordatorio);
                    funcionCambioFragment();
                }




            }
        });
        return view;
    }

    private void funcionCambioFragment(){
        CalendarioFragment nextFrag= new CalendarioFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_navigator, nextFrag,"mascota")
                .addToBackStack(null)
                .commit();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void abrirAlertHora() {

// custom dialog
        final Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.alertdialog_hora);
        // Custom Android Allert Dialog Title
        dialog.setTitle("Custom Dialog Example");
        final TimePicker tpickhorapubli = (TimePicker) dialog.findViewById(R.id.tpickhorapubli);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btnDialogCloseH);
        // Click cancel to dismiss android custom dialog box
        /*
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
*/
        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
               hora = tpickhorapubli.getHour();
                minuto = tpickhorapubli.getMinute();

                actualizarTextviewHORA(hora, minuto);
                dialog.dismiss();


            }
        });

        dialog.show();
    }

    public void abrirAlertDia() {

// custom dialog
        final Dialog dialog = new Dialog(this.getContext());
        dialog.setContentView(R.layout.alertdialog_calendario);
        // Custom Android Allert Dialog Title
        dialog.setTitle("Custom Dialog Example");
        final CalendarView calendarView = (CalendarView) dialog.findViewById(R.id.calendarView);
        Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btnDialogClose);

        //______Apartado poner fecha en edit text
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mes = month + 1;
                actualizarTextviewDIA(dayOfMonth, mes, year);
            }
        });

        // Click cancel to dismiss android custom dialog box
        /*
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
*/
        // Your android custom dialog ok action
        // Action for custom dialog ok button click
        dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                //String selectedDate = sdf.format(new Date(calendarView.getDate()));
                //actualizarTextviewDIA(selectedDate);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void actualizarTextviewHORA(int hora, int minuto) {
        txthora.setText("La hora seleccionada es: " + hora + ":" + minuto);
        this.hora = hora;
        this.minuto = minuto;
    }

    public void actualizarTextviewDIA(int dias, int mess, int anios) {
        txtdia.setText("El dia seleccionado es: " + dias + "/" + mess + "/" + anios);
        this.dia = dias;
        this.mes = mess;
        this.anio = anios;
    }

    public void insertarObjeto(Recordatorio o) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Recordatorios");

        //String clave = myRef.push().getKey();

        //o.setClave(clave);

        Map m = new HashMap<>();
        m.put(myRef.push().getKey(), o);
        myRef.updateChildren(m);
    }


}
