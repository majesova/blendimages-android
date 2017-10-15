package com.tarina.blenddemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ImageView backgroundImage;
    ToggleButton toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundImage = (ImageView)findViewById(R.id.imgBackground);
        toggle = (ToggleButton)findViewById(R.id.toggle);
        Toast.makeText(MainActivity.this, "Tiene la original", Toast.LENGTH_SHORT).show();

        //backgroundImage.setImageBitmap(mergedImages);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.background_sin_textura);
                    Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.background_textura);
                    Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage);
                    backgroundImage.setImageBitmap(mergedImages);
                    Toast.makeText(MainActivity.this, "Blend aplicado", Toast.LENGTH_SHORT).show();
                }else{
                    Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.background_textura);
                    backgroundImage.setImageBitmap(bigImage);
                    Toast.makeText(MainActivity.this, "Se puso la original", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 0f, 0f, null);
        return result;
    }


}
