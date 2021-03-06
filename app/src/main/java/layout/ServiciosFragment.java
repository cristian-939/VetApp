package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vett.cryst.vetapp.AdaptadorServicios;
import com.vett.cryst.vetapp.R;
import com.vett.cryst.vetapp.Servicio;

import java.util.ArrayList;


public class ServiciosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View v;

    public ServiciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServiciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiciosFragment newInstance(String param1, String param2) {
        ServiciosFragment fragment = new ServiciosFragment();
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
         v =inflater.inflate(R.layout.fragment_servicios, container, false);
        iniciarRelaciones();
        return v;
    }
    ArrayList<Servicio> servicios;
    private RecyclerView recycler_servicios;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager lm;

    private void iniciarRelaciones() {
        servicios = new ArrayList<Servicio>();
        //poner Servicios

        servicios.add(new Servicio("Medicina general",R.drawable.iconomedicinageneral));
        servicios.add(new Servicio("Cirugía",R.drawable.cirugiageneral));
        servicios.add(new Servicio("Dermatología",R.drawable.dermatologia));
        servicios.add(new Servicio("Endocrinología",R.drawable.endocrino));
        servicios.add(new Servicio("Oncología",R.drawable.onco));
        servicios.add(new Servicio("Ecografía",R.drawable.eco));
        servicios.add(new Servicio("Radiología digital",R.drawable.radio));
        servicios.add(new Servicio("Análisis clínicos",R.drawable.anali));
        servicios.add(new Servicio("Hospitalización",R.drawable.hospi));
        recycler_servicios = (RecyclerView) v.findViewById(R.id.recyclerservicios);
        lm = new LinearLayoutManager(getContext());
        recycler_servicios.setLayoutManager(lm);
        adaptador = new AdaptadorServicios(servicios);
        recycler_servicios.setAdapter(adaptador);



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
