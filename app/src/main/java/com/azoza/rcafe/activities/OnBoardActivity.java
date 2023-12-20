package com.azoza.rcafe.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azoza.rcafe.R;
import com.azoza.rcafe.adapters.SliderAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class OnBoardActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;

    Button button1;
    SliderAdapter sliderAdapter;

    TextView[] dots;

    Animation animation;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //status bar Hiding
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_board);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
            finish();
        }


        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        button1 = findViewById(R.id.get_started_btn);
        addDots(0);

        viewPager.addOnPageChangeListener(changeListener);

        //Call Adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardActivity.this,SignUpActivity.class));
                finish();
            }
        });

    }
    private void addDots(int position){

        String htmlString = getString(R.string._8226);
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i =0; i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);

        }
        if(dots.length>0){
           dots[position].setTextColor(ContextCompat.getColor(this, R.color.gold));
        }
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if(position == 0){
                button1.setVisibility(View.INVISIBLE);
            }else if(position == 1){
                button1.setVisibility(View.INVISIBLE);
            }else{
                animation = AnimationUtils.loadAnimation(OnBoardActivity.this,R.anim.slide_animation);
                button1.setAnimation(animation);
                button1.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}