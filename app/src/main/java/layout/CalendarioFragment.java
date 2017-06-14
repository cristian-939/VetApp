package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vett.cryst.vetapp.AdapterCategory;
import com.vett.cryst.vetapp.LoginActivity;
import com.vett.cryst.vetapp.Mascota;
import com.vett.cryst.vetapp.NavigatorActivity;
import com.vett.cryst.vetapp.R;
import com.vett.cryst.vetapp.Recordatorio;
import com.vett.cryst.vetapp.RecordatorioCorto;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CalendarioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CalendarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //________________________________
    private FirebaseDatabase data;
    ListView lv;
    ArrayList<Recordatorio> recordatorioArraylist;
    ArrayList<RecordatorioCorto> fechhorarecord;
    AdapterCategory adapter;
    FloatingActionButton fab;

    private OnFragmentInteractionListener mListener;

    public CalendarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarioFragment newInstance(String param1, String param2) {
        CalendarioFragment fragment = new CalendarioFragment();
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

        View view = inflater.inflate(R.layout.fragment_calendario, container, false);
        lv = (ListView) view.findViewById(R.id.listview);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        recordatorioArraylist = new ArrayList<Recordatorio>();
        fechhorarecord = new ArrayList<RecordatorioCorto>();
        adapter = new AdapterCategory(this.getActivity(), fechhorarecord);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcionCambioFragment();
            }
        });
        cargarFirebase();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void funcionCambioFragment(){
        MicalendarioFragment nextFrag= new MicalendarioFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_navigator, nextFrag,"mascota")
                .addToBackStack(null)
                .commit();
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

    private void cargarFirebase() {
        data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Recordatorios");
        String email = NavigatorActivity.correoLogin;
        Query consulta = ref.orderByChild("correo").equalTo(email);
        consulta.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                               Iterable i = dataSnapshot.getChildren();
                                               Iterator<DataSnapshot> iterador = i.iterator();
                                               recordatorioArraylist.clear();
                                               while (iterador.hasNext()) {
                                                   Recordatorio recordatorio = iterador.next().getValue(Recordatorio.class);
                                                   recordatorioArraylist.add(recordatorio);
                                                  /* viewdueño.setText(mascota.getNombre().toString());
                                                   viewraza.setText(mascota.getRaza().toString());
                                                   viewmascota.setText(mascota.getDueño().toString());
                                                   viewedad.setText(mascota.getEdad().toString());
                                                   viewsexo.setText(mascota.getSexo());*/

                                               }
                                               rellenarListview();
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       }

        );
    }

    private void rellenarListview() {
        fechhorarecord.clear();
        for (Recordatorio m:recordatorioArraylist  ) {
            RecordatorioCorto a = new RecordatorioCorto(m.getFecha().toString() + " " + m.getHora(), m.getMensaje().toString());
            fechhorarecord.add(a);
        }
        lv.setAdapter(adapter);

    }

}
