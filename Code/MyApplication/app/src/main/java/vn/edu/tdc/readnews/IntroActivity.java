package vn.edu.tdc.readnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


    public class IntroActivity extends AppCompatActivity {
        private ImageView img;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.intro_layout);
            getSupportActionBar().hide();
//        tv = (TextView) findViewById(R.id.textView);
            img = (ImageView) findViewById(R.id.imgView);
            Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition);
//        tv.startAnimation(animation);
            img.startAnimation(animation);

            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        Intent intent = new Intent(IntroActivity.this,ListSiteActivity.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }
}
