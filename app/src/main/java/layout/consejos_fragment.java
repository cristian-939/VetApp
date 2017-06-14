package layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vett.cryst.vetapp.Adaptador;
import com.vett.cryst.vetapp.Consejo;
import com.vett.cryst.vetapp.ConsejoImagenStr;
import com.vett.cryst.vetapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link consejos_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class consejos_fragment extends Fragment {
    private RecyclerView recycler_consejos;
    private RecyclerView.Adapter adaptador;
    private RecyclerView.LayoutManager lm;
    private ArrayList<ConsejoImagenStr> consejos;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public consejos_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment consejos_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static consejos_fragment newInstance(String param1, String param2) {
        consejos_fragment fragment = new consejos_fragment();
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consejos_fragment, container, false);
        consejos = new ArrayList<>();
        int i1 = R.drawable.uno;
        int i2 =  R.drawable.dos;
        int i3 = R.drawable.tres;
        int i4 = R.drawable.cuatro;
        int i5 =  R.drawable.cinco;
        int i6 = R.drawable.seis;
        int i7 = R.drawable.siete;
        int i8 =  R.drawable.ocho;
        int i9 = R.drawable.nueve;
        int i10 = R.drawable.diez;
        int i11 =  R.drawable.once;
        int i12 = R.drawable.doce;

        consejos.add(new ConsejoImagenStr("Cuidados básicos del cachorro", "Qué debemos saber cuando una nueva mascota llega a casa",i1));
        consejos.add(new ConsejoImagenStr("Diabetes", "¿Es mi mascota diabética? Cosas que deben alertarme",i2));
        consejos.add(new ConsejoImagenStr("Golpe de calor", "Qué hacer y cómo actuar",i3));
        consejos.add(new ConsejoImagenStr("Vacunación y desparasitación", "El por qué debemos de tener a nuestras mascotas protegidas",i4));
        consejos.add(new ConsejoImagenStr("Leishmaniosis", "Qué es, cómo se transmite y cómo prevenirla",i5));
        consejos.add(new ConsejoImagenStr("¿Qué es la filariosis?", "Qué es, cómo se transmite y cómo prevenirla",i6));
        consejos.add(new ConsejoImagenStr("Lista de tóxicos", "Alimentos y plantas tóxicas para nuestras mascotas.",i7));
        consejos.add(new ConsejoImagenStr("Orugas", "Una gran problemática",i8));
        consejos.add(new ConsejoImagenStr("Por qué recomendamos la castración", "Beneficios vs riesgos",i9));
        consejos.add(new ConsejoImagenStr("¿Qué  hago si mi mascota vomita?", "Pautas básicas ",i10));
        consejos.add(new ConsejoImagenStr("Síndrome de suching:", "Información básica de la enfermedad",i11));
        consejos.add(new ConsejoImagenStr("Traqueítis infecciosa canina", "Cómo  prevenir esta enfermedad",i12));

        recycler_consejos = (RecyclerView) view.findViewById(R.id.recycler);
        lm = new LinearLayoutManager(getContext());
        recycler_consejos.setLayoutManager(lm);
        adaptador = new Adaptador(consejos);
        recycler_consejos.setAdapter(adaptador);
        return view;
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
