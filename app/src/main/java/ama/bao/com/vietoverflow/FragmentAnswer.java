package ama.bao.com.vietoverflow;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class FragmentAnswer extends Fragment {

    public FragmentAnswer() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_answer, container, false);

        Button submit = (Button) rootView.findViewById(R.id.submit);
        submit.setOnClickListener(btnOnClickListener);

        TextView questionData = (TextView) rootView.findViewById(R.id.question);
        TextView prevAnsData = (TextView) rootView.findViewById(R.id.prevAnswer);

        if (isNetworkAvailable()) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // The connection URL
            String url = "http://www.csupomona.edu/~lannguyen/solve";

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(url, String.class, "");

            //Log.d("ABC", result);
            questionData.setText("QUESTION:\n" + result.substring(0, 100));
            prevAnsData.setText("ANSWER:\n" + result.substring(result.length()-500, result.length()));
            questionData.setMovementMethod(new ScrollingMovementMethod());
            prevAnsData.setMovementMethod(new ScrollingMovementMethod());
        }

        else
            Toast.makeText(getActivity(), "Please make sure you have a working internet connection.", Toast.LENGTH_SHORT).show();

        return rootView;
    }

    Button.OnClickListener btnOnClickListener  = new Button.OnClickListener() {
        @Override
        public void onClick(View view){
            //submit answer
            Toast.makeText(getActivity(), "Your answer has been posted!", Toast.LENGTH_SHORT).show();
        }
    };

    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}