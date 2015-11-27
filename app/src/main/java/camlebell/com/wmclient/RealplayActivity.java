package camlebell.com.wmclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lib.decoder.VideoDecoder;
import com.wmclient.clientsdk.Constants;
import com.wmclient.clientsdk.StreamPlayer;

import java.io.File;
import java.text.SimpleDateFormat;

import camlebell.com.MyApplcation;
import camlebell.com.base.ToolbarBaseActivity;
import camlebell.com.myapplication.R;

public class RealplayActivity extends ToolbarBaseActivity {


	private StreamPlayer streamPlayer = null;
	private SurfaceView surfaceView;
	private SurfaceView glSurfaceView;

	private Button photoCatchBtn;
	private Button soundBtn;
	private Button ptzBtn;
	private Button storageBtn;
	private Button playbackBtn;
//	private Button msgBtn;

	private TextView soundTip;
	private TextView storageTip;
	private TextView cloudTip;

	private ImageView ptzLeftIv;
	private ImageView ptzRightIv;
	private ImageView ptzTopIv;
	private ImageView ptzBottomIv;
	private ImageView camarmImageView;

	//private Dialog mProgressDialog;

	private String deviceName = "";
	private int playerId = Constants.WMPLAYERID_INVALID;

	private int deviceId = 0;
	private int channelId = 1;
	private int deviceType = 0;
	private int ptzSpeed = 2;

	private boolean isRecording = false;
	private boolean bOpenSound = false;
	private boolean bPtzOpen = false;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case RealStreamPlayer.REALPLAY_RESPONSE: {
					playerId = msg.arg1;

					if(Constants.WMPLAYERID_INVALID != playerId) {
						camarmImageView.setVisibility(View.GONE);

						//mProgressDialog.dismiss();

						if(glSurfaceView != null) {
							glSurfaceView.setVisibility(View.VISIBLE);
						}
						else {
							surfaceView.setVisibility(View.VISIBLE);
						}
					}
					else {
						//销毁播放器
						MyApplcation.getInstance().GetSdkInterface().DestroyPlayer(streamPlayer);
						finish();
					}

					break;
				}

				case RealStreamPlayer.REALPLAY_STOP: {
					//销毁播放器
					MyApplcation.getInstance().GetSdkInterface().DestroyPlayer(streamPlayer);
					finish();

					break;
				}
			}

			super.handleMessage(msg);
		}
	};

	private RealStreamPlayer mRealplayer = new RealStreamPlayer(mHandler);
	//private IntercomSdk mIntercomSdk = new IntercomSdk(mHandler, RealplayActivity.this);
	//private IntercomSdk mIntercomSdk = new IntercomSdk(null, null);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getContentView() {
		return R.layout.realplay_activity;
	}

	@Override
	protected void findViews() {

		surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
		surfaceView.setVisibility(View.VISIBLE);

		camarmImageView = (ImageView)findViewById(R.id.surfaceImage);
		//camarmImageView.setVisibility(View.VISIBLE);

		photoCatchBtn = (Button)findViewById(R.id.real_button_photo);
		photoCatchBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				catchPhoto();
			}
		});

		playbackBtn= (Button)findViewById(R.id.real_button_back);
		playbackBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Intent intent = new Intent(getApplicationContext(), ReplayActivity.class);
				//startActivity(intent);
			}
		});

		soundTip = (TextView)findViewById(R.id.real_volumn_info);
		soundBtn = (Button)findViewById(R.id.real_button_voice);
		soundBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				soundControl();
			}
		});

/*		msgBtn=(Button) findViewById(R.id.real_button_msg);
		msgBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), MsgActivity.class);
				startActivity(intent);

			}
		});
*/
		ptzLeftIv = (ImageView)findViewById(R.id.realplay_left);
		ptzLeftIv.setOnTouchListener(new Button.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					return ptzLeftControl(1);
				}
				else if(event.getAction() == MotionEvent.ACTION_DOWN) {
					return ptzLeftControl(0);
				}

				return true;
			}
		});

		ptzRightIv = (ImageView)findViewById(R.id.realplay_right);
		ptzRightIv.setOnTouchListener(new Button.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					return ptzRightControl(1);
				}
				else if(event.getAction() == MotionEvent.ACTION_DOWN) {
					return ptzRightControl(0);
				}

				return true;
			}
		});

		ptzTopIv = (ImageView)findViewById(R.id.realplay_top);
		ptzTopIv.setOnTouchListener(new Button.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					return ptzTopControl(1);
				}
				else if(event.getAction() == MotionEvent.ACTION_DOWN) {
					return ptzTopControl(0);
				}

				return true;
			}
		});

		ptzBottomIv = (ImageView)findViewById(R.id.realplay_bottom);
		ptzBottomIv.setOnTouchListener(new Button.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					return ptzBottomControl(1);
				}
				else if(event.getAction() == MotionEvent.ACTION_DOWN) {
					return ptzBottomControl(0);
				}

				return true;
			}
		});

		cloudTip = (TextView)findViewById(R.id.text_cloud_tip);
		ptzBtn = (Button)findViewById(R.id.real_button_cloud);
		ptzBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				ptzControl();
			}
		});

		storageTip = (TextView)findViewById(R.id.text_video);
		storageBtn = (Button)findViewById(R.id.real_button_video);
		storageBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				storageControl();
			}
		});

		//mIntercomSdk.setAudioDataListener(this);

	}

	@Override
	protected void iniData() {
		setTitle("视频监控");



	}

	@Override
	protected void setListener() {

	}

	@Override
	protected void onDestroy() {
		stopPlay();
		super.onDestroy();
	}

	@Override
	protected void onStart() {
		super.onStart();

		Object showObj = null;
		Intent intent = getIntent();

		deviceId = intent.getIntExtra("deviceId", 0);
		channelId = intent.getIntExtra("channelId", 1);
		deviceType = intent.getIntExtra("deviceType", 0);
		deviceName = intent.getStringExtra("deviceName");

		if(deviceId <= 0) {
			finish();
		}

//		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//			TextView channelText = (TextView)findViewById(R.id.realplay_channel);
//			channelText.setText(deviceName + "（通道" + channelId + "）");
//		}

		//指定播放的显示对象，如果雄迈设备需要创建特定的SurfaceView，如果是其它设备可以直接使用普通的SurfaceView
		if(Constants.DEVICE_TYPE_XM_DEV == deviceType) {
			RelativeLayout layout = (RelativeLayout) this.findViewById(R.id.videoLayout);

			LayoutParams lp = surfaceView.getLayoutParams();
			glSurfaceView = new VideoDecoder(this);
			glSurfaceView.setVisibility(View.GONE);

			layout.removeViewAt(0);
			layout.addView(glSurfaceView, 0, lp);

			showObj = glSurfaceView;
		}
		else {
			showObj = surfaceView.getHolder();
		}

		//创建播放器，并开始启动实时预览的码流获取
		streamPlayer = MyApplcation.getInstance().GetSdkInterface().CreatePlayer(deviceType, showObj);
		mRealplayer.startplay(deviceId, channelId, streamPlayer);
		//		playerId = ClientApp.getInstance().GetSdkInterface().startRealPlay(deviceId, 0x01000000 + channelId, streamPlayer);
		//		if(Constants.WMPLAYERID_INVALID == playerId) {
		//			ClientApp.getInstance().GetSdkInterface().DestroyPlayer(streamPlayer);
		//			finish();
		//		}
		//		else {
		//			camarmImageView.setVisibility(View.GONE);
		//			if(glSurfaceView != null) {
		//				glSurfaceView.setVisibility(View.VISIBLE);
		//			}
		//			else {
		//				surfaceView.setVisibility(View.VISIBLE);
		//			}
		//		}

		//other
		isRecording = false;
		bOpenSound = false;
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			stopPlay();
		}

		return super.onKeyDown(keyCode, event);
	}

	private void stopPlay() {
		if(playerId != Constants.WMPLAYERID_INVALID) {
			//停止实时预览码流获取
			mRealplayer.stopPlay();
			//ClientApp.getInstance().GetSdkInterface().stopRealPlay(playerId);
			//playerId = Constants.WMPLAYERID_INVALID;
		}

		if(streamPlayer != null) {
			MyApplcation.getInstance().GetSdkInterface().DestroyPlayer(streamPlayer);
		}
	}

	private void catchPhoto() {
		if(playerId == Constants.WMPLAYERID_INVALID || null == streamPlayer) {
			return;
		}

		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getApplicationContext(), "无效的输入!", Toast.LENGTH_SHORT).show();
			return;
		}

		String directoryName = Environment.getExternalStorageDirectory() + File.separator + "wmclient-photo";
		File directory = new File(directoryName);
		if(!directory.exists() && !directory.mkdir()) {
			Toast.makeText(getApplicationContext(), "创建存储目录失败!", Toast.LENGTH_SHORT).show();
			return;
		}

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String fileName = directoryName + File.separator + deviceName + "-" + channelId + "-" +
				sDateFormat.format(new java.util.Date());

		if(Constants.success == streamPlayer.saveSnapshot(fileName)) {
			Intent imgViewIntent = new Intent(getApplicationContext(), ImageViewActivity.class);
			imgViewIntent.putExtra("imagePath", fileName + ".jpg");

			startActivity(imgViewIntent);
		}
	}

	private void soundControl() {
		if(playerId == Constants.WMPLAYERID_INVALID || null == streamPlayer) {
			return;
		}

		//control sound
		if(!bOpenSound) {
			//打开声音
			int nRet = streamPlayer.OpenSound();
			if(nRet != Constants.success) {
				return;
			}
			//if(mRealplayer.startVoiceTalk()) {
			//	bOpenSound=true;

			//	soundBtn.setBackgroundResource(R.drawable.real_img_voiceon);
			//	soundTip.setText(R.string.real_button_voice_on);
			//}
		}
		else {
			//关闭声音
			int nRet = streamPlayer.CloseSound();
			if(nRet != Constants.success) {
				return;
			}
			//mRealplayer.stopVoiceTalk();

			//soundBtn.setBackgroundResource(R.drawable.real_img_voiceoff);
			//soundTip.setText(R.string.real_button_voice_off);

			bOpenSound=false;
		}

		//mIntercomSdk.onStart();
	}

	private void ptzControl() {
		if(playerId == Constants.WMPLAYERID_INVALID) {
			return;
		}

		if(bPtzOpen) {
			ptzBtn.setBackgroundResource(R.mipmap.real_img_cloud01);
			cloudTip.setText(R.string.real_button_cloud_on);
			bPtzOpen=false;

			ptzLeftIv.setVisibility(ImageView.GONE);
			ptzRightIv.setVisibility(ImageView.GONE);
			ptzTopIv.setVisibility(ImageView.GONE);
			ptzBottomIv.setVisibility(ImageView.GONE);
		}
		else {
			ptzBtn.setBackgroundResource(R.mipmap.real_img_cloud02);
			cloudTip.setText(R.string.real_button_cloud_off);
			bPtzOpen=true;

			ptzLeftIv.setVisibility(ImageView.VISIBLE);
			ptzRightIv.setVisibility(ImageView.VISIBLE);
			ptzTopIv.setVisibility(ImageView.VISIBLE);
			ptzBottomIv.setVisibility(ImageView.VISIBLE);
		}
	}

	private void storageControl() {
		if(playerId == Constants.WMPLAYERID_INVALID) {
			return;
		}

		//control storage
		if(!isRecording) {
			if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				Toast.makeText(getApplicationContext(), "无效的输入!", Toast.LENGTH_SHORT).show();
				return;
			}

			String directoryName = Environment.getExternalStorageDirectory() + File.separator + "wmclient-file";
			File directory = new File(directoryName);
			if(!directory.exists() && !directory.mkdir()) {
				Toast.makeText(getApplicationContext(), "创建存储目录失败!", Toast.LENGTH_SHORT).show();
				return;
			}

			SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd-hh-mm-ss");
			String fileName = directoryName + File.separator + deviceName + "-" + channelId + "-" +
					sDateFormat.format(new java.util.Date());

			//启动本地存储
			int nRet = MyApplcation.getInstance().GetSdkInterface().startRecord(playerId, fileName);
			if(nRet != Constants.success) {
				Toast.makeText(getApplicationContext(), "开始录像失败!", Toast.LENGTH_SHORT).show();
				return;
			}

			storageBtn.setBackgroundResource(R.mipmap.real_img_video02);
			storageTip.setText(R.string.video_storage_off);

			isRecording = true;
		}
		else {
			//停止本地存储
			int nRet = MyApplcation.getInstance().GetSdkInterface().stopRecord(playerId);
			if(nRet != Constants.success) {
				Toast.makeText(getApplicationContext(), "停止录像失败!", Toast.LENGTH_SHORT).show();
				return;
			}

			storageBtn.setBackgroundResource(R.mipmap.real_img_video01);
			storageTip.setText(R.string.video_storage_on);

			isRecording = false;
		}
	}

	private boolean ptzLeftControl(int bStop) {
		//云台控制
		MyApplcation.getInstance().GetSdkInterface().ptzControl(deviceId, channelId, Constants.WMPTZCommand_LEFT,
				bStop, ptzSpeed);
		return true;
	}

	private boolean ptzRightControl(int bStop) {
		//云台控制
		MyApplcation.getInstance().GetSdkInterface().ptzControl(deviceId, channelId, Constants.WMPTZCommand_RIGHT,
				bStop, ptzSpeed);
		return true;
	}

	private boolean ptzTopControl(int bStop) {
		//云台控制
		MyApplcation.getInstance().GetSdkInterface().ptzControl(deviceId, channelId, Constants.WMPTZCommand_UP,
				bStop, ptzSpeed);
		return true;
	}

	private boolean ptzBottomControl(int bStop) {
		//云台控制
		MyApplcation.getInstance().GetSdkInterface().ptzControl(deviceId, channelId, Constants.WMPTZCommand_DOWN,
				bStop, ptzSpeed);
		return true;
	}

//	@Override
//	public void onData(byte[] data, int len) {
//		Log.e("clientsdk.jar", "voice data callback , size:" + len);
//	}

}
