package gbq.com.myaccount.base.util;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;

public class PhoneUtil {

	public static String getMac() {
		String macSerial = null;
		String str = "";
		try {
			Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			for (; null != str; ) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// 去空格
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		return macSerial;
	}

	public static String getTimeMillis(){
		return String.valueOf(System.currentTimeMillis());
	}

	public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName) {
		File dir = new File(path);
		if (!dir.exists()) {
			if (!dir.mkdirs())
				return;
		}

		File photoFile = new File(path, photoName + ".png");
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(photoFile);
			if (photoBitmap != null) {
				if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
					fileOutputStream.flush();
					// fileOutputStream.close();
				}
			}
		} catch (IOException e) {
			if (photoFile.delete())
				Log.d("PhoneUtil", "delete failure");
			e.printStackTrace();
		} finally {
			try {
				assert fileOutputStream != null;
				fileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
