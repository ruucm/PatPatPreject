package com.exam.tab;
 
import com.exam.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
 
public class FragmentTab1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.states, container, false);
        View tv = v.findViewById(R.id.text02);
        ((TextView) tv).setText("coolkim.tistory.com");
        return v;
    }
}
