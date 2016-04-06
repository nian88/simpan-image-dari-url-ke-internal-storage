package id.niandev.savekemmc;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView ivimage;
    String url="https://scontent.cdninstagram.com/hphotos-xpa1/l/t51.2885-15/s320x320/e15/10899160_682979101834467_1817313740_n.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivimage = (ImageView) findViewById(R.id.imv);
        Picasso.with(getApplicationContext())
                .load(url)
                .resize(520, 520)
               // .placeholder(R.drawable.ic_loading).error(R.drawable.ic_error_gambar)
                .into(ivimage);
    }
    public void simpan(View v){
        Picasso.with(getApplicationContext()).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/dia");
                    if (!myDir.exists()) {
                        myDir.mkdirs();
                    }
                    String name = new Date().toString() + ".jpg";
                    myDir = new File(myDir, name);
                    FileOutputStream out = new FileOutputStream(myDir);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });

    }
}
