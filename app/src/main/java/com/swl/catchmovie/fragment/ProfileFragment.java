package com.swl.catchmovie.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.swl.catchmovie.ProfileActivity;
import com.swl.catchmovie.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    public ProfileFragment() {
        // Required empty public constructor


    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView txtOpen = (TextView) view.findViewById(R.id.viewProfile);
        txtOpen.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v){
              Intent intent = new Intent(getActivity(), ProfileActivity.class);
              //intent.putExtra("Testing","catchmovie321");
              startActivity(intent);
          }
        });
        return view;
    }

    @Override
    public void onClick (View v) {

        Intent i = new Intent(getActivity(), ProfileActivity.class);
        startActivity(i);
        //((Activity) getActivity()).overridePendingTransition(0, 0);

    }
}
