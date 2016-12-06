package gbq.com.myaccount.module.news.web;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 新闻详情支持者
 * Created by gbq on 2016-12-5.
 */

class NewsDetailPresenter {
	private INewsDetailCtrl mCtrl;
	private WebView mWebView;

	NewsDetailPresenter(INewsDetailCtrl ctrl) {
		mCtrl = ctrl;
	}

	void initWebView(final WebView webView, String url) {
		mCtrl.showProcess();
		mWebView = webView;
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		webView.addJavascriptInterface(new AndroidJavaScript(), "nubia_cdc");
		webView.setWebViewClient(webClient);
		webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
				final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
				builder.setTitle("")
						.setMessage(message)
						.setPositiveButton("确定", null);
				// 不需要绑定按键事件
				// 屏蔽keycode等于84之类的按键
				builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
					public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
						return true;
					}
				});
				// 禁止响应按back键的事件
				builder.setCancelable(false);
				AlertDialog dialog = builder.create();
				dialog.show();
				result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
				return true;
			}
		});
		webView.setOnKeyListener(new View.OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
					handler.sendEmptyMessage(1);
					return true;
				}
				return false;
			}
		});
		mCtrl.closeProcess();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message message) {
			switch (message.what) {
				case 1: {
					webViewGoBack();
				}
				break;
			}
		}
	};

	private void webViewGoBack() {
		mWebView.goBack();
	}

	private WebViewClient webClient = new WebViewClient() {
		@Override
		public void onReceivedError(WebView view, int errorCode,
									String description, String failingUrl) {

		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);

		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}
	};
}
