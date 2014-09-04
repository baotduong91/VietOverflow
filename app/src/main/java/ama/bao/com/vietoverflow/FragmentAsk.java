package ama.bao.com.vietoverflow;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentAsk extends Fragment {

    public FragmentAsk() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ask, container, false);
        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(btnOnClickListener);

        return rootView;
    }

    Button.OnClickListener btnOnClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            //submit question
            Toast.makeText(getActivity(), "Your question has been posted!", Toast.LENGTH_SHORT).show();
        }
    };
}