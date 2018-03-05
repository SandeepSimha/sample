package com.example.sancheru.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PopupWindow pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String firstWord = "General promotions & deals \n";
        String secondWord = "This includes news updates, sneak peeks and exclusive national deals.";

        final Spannable spannable = new SpannableString(firstWord + secondWord);
        final SpannableStringBuilder sb = new SpannableStringBuilder(firstWord + secondWord);

        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);
        final StyleSpan iss = new StyleSpan(android.graphics.Typeface.ITALIC);
        sb.setSpan(bss, 0, firstWord.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(iss, 0, secondWord.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        sb.setSpan(new RelativeSizeSpan(1f), 0, firstWord.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView txt = findViewById(R.id.text);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initiatePopupWindow();
                Tooltip.Builder builder = new Tooltip.Builder(v, R.style.AppTheme)
                        .setCancelable(true)
                        .setDismissOnClick(false)
                        .setCornerRadius(20f)
                        .setTextColor(getResources().getColor(R.color.colorAccent))
                        .setGravity(Gravity.TOP)
                        .setText(sb);
                builder.show();
            }
        });

    }

    private void initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) MainActivity.this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            //View layout = inflater.inflate(R.layout.custom_popup_layout,  (ViewGroup) findViewById(R.id.popup_element));
            View layout = null;
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 300, 470, true);

            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

           /* TextView mResultText = (TextView) layout.findViewById(R.id.server_status_text);
            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            //makeBlack(cancelButton);
            cancelButton.setOnClickListener(cancel_button_click_listener);*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}
