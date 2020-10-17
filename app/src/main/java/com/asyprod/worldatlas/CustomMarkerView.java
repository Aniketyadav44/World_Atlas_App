package com.asyprod.worldatlas;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;

public class CustomMarkerView extends MarkerView {

    ArrayList<String> mXLabels;

    private TextView tvContent;
    public CustomMarkerView (Context context, int layoutResource, ArrayList<String> xLabels) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);
        mXLabels = xLabels;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText( mXLabels.get((int)(e.getX())) +"("+ e.getY()+")"); // set the entry-value as the display text
    }


}