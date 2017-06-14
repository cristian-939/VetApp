package layout;

import android.app.FragmentManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MascotaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MascotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MascotaFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MascotaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MascotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MascotaFragment newInstance(String param1, String param2) {
        MascotaFragment fragment = new MascotaFragment();
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


    private FloatingActionButton floatanadir;
    private TextView viewdueño, viewraza, viewmascota, viewsexo, viewedad, viewEspecie;
    private Mascota mascota;
    private Spinner spinmascotas;
    private FirebaseAuth aut;
    private FirebaseDatabase data;
    private ArrayList<Mascota> listamascotas;
    private ArrayAdapter<Mascota> adaptador_spinner;
    private ArrayList<String> nombresmascotas;
    private Button button2;
    private String nombreMASCOTA, id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_mascota, container, false);
        floatanadir = (FloatingActionButton) view.findViewById(R.id.floatanadir);
        viewdueño = (TextView) view.findViewById(R.id.ViewNombre);
        viewEspecie = (TextView) view.findViewById(R.id.viewEspecie);
        viewmascota = (TextView) view.findViewById(R.id.viewMascota);
        viewsexo = (TextView) view.findViewById(R.id.viewSexo);
        viewedad = (TextView) view.findViewById(R.id.viewEdad);
        spinmascotas = (Spinner) view.findViewById(R.id.spinmascotas);
        button2 = (Button) view.findViewById(R.id.button2);
        listamascotas = new ArrayList<>();
        nombresmascotas = new ArrayList<>();
        adaptador_spinner = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, nombresmascotas);
        spinmascotas.setAdapter(adaptador_spinner);

        //cargo el firebase
        cargarFirebase();

        // Inflate the layout for this fragment
        //inflater.inflate(R.layout.fragment_mascota, container, false)

        floatanadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcionCambioFragment();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nombreMASCOTA.equals(null)){
                    Toast.makeText(getContext(),"Debes seleccionar para borrar", Toast.LENGTH_SHORT).show();
                }else{


                    // Toast.makeText(getContext(),"NO ESTA PASANDO NADA", Toast.LENGTH_SHORT).show();
                    FirebaseDatabase ref = FirebaseDatabase.getInstance();
                    //Query applesQuery = ref.child("mascotas").orderByChild("nombre").equalTo(nombreMASCOTA);
                    Query applesQuery = ref.getReference("mascotas").orderByKey().equalTo(id);

                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                appleSnapshot.getRef().removeValue();
                                viewdueño.setText("");
                                viewEspecie.setText("");
                                viewmascota.setText("");
                                viewsexo.setText("");
                                viewedad.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            //Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });//manito

                }

            }
        });



        spinmascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String items = spinmascotas.getSelectedItem().toString();
                mascota = listamascotas.get(i);
                viewdueño.setText(mascota.getNombre().toString());
                viewmascota.setText(mascota.getRaza().toString());
                viewedad.setText(mascota.getEdad().toString());
                viewsexo.setText(mascota.getSexo().toString());
                viewEspecie.setText(mascota.getEspecie().toString());

                id = mascota.getId().toString();

                nombreMASCOTA = viewdueño.getText().toString();

                //Toast.makeText(getContext(),"Has Seleccionado: " + id, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        return view;
    }

    private void funcionCambioFragment(){
        AnadirmascotaFragment nextFrag= new AnadirmascotaFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_navigator, nextFrag,"mascota")
                .addToBackStack(null)
                .commit();
    }


    private void cargarFirebase() {
        data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("mascotas");
        String email = NavigatorActivity.correoLogin;
        Query consulta = ref.orderByChild("correoDueno").equalTo(email);
        consulta.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               Iterable i = dataSnapshot.getChildren();
                                               Iterator<DataSnapshot> iterador = i.iterator();
                                               listamascotas.clear();
                                               while (iterador.hasNext()) {
                                                   Mascota mascota = iterador.next().getValue(Mascota.class);
                                                   listamascotas.add(mascota);
                                                  /* viewdueño.setText(mascota.getNombre().toString());
                                                   viewraza.setText(mascota.getRaza().toString());
                                                   viewmascota.setText(mascota.getDueño().toString());
                                                   viewedad.setText(mascota.getEdad().toString());
                                                   viewsexo.setText(mascota.getSexo());*/

                                               }
                                               rellenarSpinner();
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       }

        );
    }

    private void rellenarSpinner() {
        nombresmascotas.clear();
        for (Mascota m:listamascotas  ) {
            nombresmascotas.add(m.getNombre().toString());
        }
        spinmascotas.setAdapter(adaptador_spinner);

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
}
