package gbq.com.myaccount.module.photo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import gbq.com.myaccount.R;

/**
 * 拍照上传或者调用系统相册
 *
 * @author Administrator
 */
public class PhotoActivity extends Activity {
    private String action_upload = "imageUploadInterface/upload2.do";
    private String filePath;
    private static final int MAX_COUNT = 100;
    private Button mback;
    @SuppressWarnings("unused")
    private Button mcomit;

    private ImageButton mcamera;

    @SuppressWarnings("unused")
    private TextView mtitle, mTextView, msign;

    private EditText mSuggest;

    @SuppressWarnings("unused")
    private CheckBox mtencent, msina, mqq, mrenren, mqzoon, mdouban;

    private Bitmap bitmap;

    private LinearLayout ll_imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_photo);
        ll_imgs = (LinearLayout) findViewById(R.id.ll_imgs);

        mback = (Button) findViewById(R.id.back);
        mcomit = (Button) findViewById(R.id.comit);
        mtitle = (TextView) findViewById(R.id.title);

        mSuggest = (EditText) findViewById(R.id.suggest);
        mSuggest.setSingleLine(false);
        mSuggest.setSelection(mSuggest.length());
        mTextView = (TextView) findViewById(R.id.count);

        mcamera = (ImageButton) findViewById(R.id.camera);

        msign = (TextView) findViewById(R.id.sign);

        mback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                PhotoActivity.this.finish();
            }
        });

    }
}
