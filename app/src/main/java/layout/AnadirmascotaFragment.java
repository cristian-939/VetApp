package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnadirmascotaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnadirmascotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnadirmascotaFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //_______________________________
    DatabaseReference myRef;
    DatabaseReference miMascot;
    EditText editNombre;
    EditText editRaza;
    EditText editEdad;
    EditText editSexo;
    EditText editEspecie;
    Button buttonAnMas;


    private OnFragmentInteractionListener mListener;

    //__________________________________
    Mascota mascota;


    public AnadirmascotaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnadirmascotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnadirmascotaFragment newInstance(String param1, String param2) {
        AnadirmascotaFragment fragment = new AnadirmascotaFragment();
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

    private void guardarFirebase() {
        FirebaseDatabase data = FirebaseDatabase.getInstance();

        myRef = data.getReference("mascotas");
        miMascot = myRef.push();
        crearobj();
        editNombre.setText("");
        editRaza.setText("");
        editEdad.setText("");
        editSexo.setText("");
        editEspecie.setText("");
        miMascot.setValue(mascota);

    }

    private void crearobj() {
        String userCorreo = NavigatorActivity.correoLogin;
        mascota = new Mascota();
        mascota.setId(miMascot.getKey());
        mascota.setNombre(editNombre.getText().toString());
        mascota.setRaza(editRaza.getText().toString());
        mascota.setCorreoDueno(userCorreo);
        mascota.setSexo(editSexo.getText().toString());
        mascota.setEdad(editEdad.getText().toString());
        mascota.setEspecie(editEspecie.getText().toString());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_anadirmascota, container, false);
        editNombre = (EditText) view.findViewById(R.id.editNombre);
        editRaza = (EditText) view.findViewById(R.id.editRaza);
        editEdad = (EditText) view.findViewById(R.id.editEdad);
        editSexo = (EditText) view.findViewById(R.id.editSexo);
        editEspecie = (EditText) view.findViewById(R.id.editEspecie);
        buttonAnMas = (Button) view.findViewById(R.id.buttonAnMas);
        buttonAnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editNombre.getText().toString().equals("") || editRaza.getText().toString().equals("") ||editEdad.getText().toString().equals("") ||editSexo.getText().toString().equals("") ||editEspecie.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Mascota no creada, faltan campos", Toast.LENGTH_LONG).show();
                }else{
                    guardarFirebase();
                    funcionCambioFragment();
                }

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void funcionCambioFragment(){
        MascotaFragment nextFrag= new MascotaFragment();
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







}