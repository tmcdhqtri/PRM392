package com.example.exercise8;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
public class MainActivity1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ImageView ivPicture = (ImageView)
                findViewById(R.id.ivPicture1);
        ivPicture.setImageResource(R.drawable.tower);
        ivPicture.setContentDescription("Description:" +
                " The tower of London");
    }
}
