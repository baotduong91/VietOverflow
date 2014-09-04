package ama.bao.com.vietoverflow;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentMain extends Fragment {

    private Button askBtn;
    private Button answerBtn;
    private TextView startTxt;

    public FragmentMain() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        askBtn = (Button) rootView.findViewById(R.id.askBtn);
        askBtn.setOnClickListener(btnOnClickListener);

        answerBtn = (Button) rootView.findViewById(R.id.answerBtn);
        answerBtn.setOnClickListener(btnOnClickListener);

        startTxt = (TextView) rootView.findViewById(R.id.startTxt);

        return rootView;
    }

    Button.OnClickListener btnOnClickListener  = new Button.OnClickListener() {
        @Override
        public void onClick(View view){
            Fragment newFragment;

            startTxt.setText("");
            if (view == askBtn) {
                newFragment = new FragmentAsk();
                askBtn.setBackgroundResource(R.drawable.buttonselected);
                answerBtn.setBackgroundColor(Color.parseColor("#ffffd230"));
            }

            else if (view == answerBtn) {
                newFragment = new FragmentAnswer();
                answerBtn.setBackgroundResource(R.drawable.buttonselected);
                askBtn.setBackgroundColor(Color.parseColor("#ffffd230"));
            }

            else
                newFragment = new FragmentMain();

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.myFrag, newFragment);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    };

}