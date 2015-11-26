package camlebell.com.wmclient;

import android.os.Handler;
import android.os.Message;

import com.wmclient.clientsdk.Constants;
import com.wmclient.clientsdk.StreamPlayer;

import camlebell.com.MyApplcation;

public class RealStreamPlayer {
	private Handler mHandler;	
	private int mStatus = INVALID_STATUS;
	
	private int mDeviceId;
	private int mChannelId;
	private StreamPlayer mPlayer;
	
	private int playerId = Constants.WMPLAYERID_INVALID;
	
	public final static int REALPLAY_RESPONSE = 0;
	public final static int REALPLAY_STOP = 1;
	public final static int INVALID_STATUS = 0;
	public final static int STARTUP_STATUS = 1;
	public final static int STOPDOWN_STATUS = 2;
	public final static int RUNNING_STATUS = 3;
	
	public RealStreamPlayer(Handler handler) 
	{
		mHandler = handler;
		mThread.start();
	}
	
	private Thread mThread = new Thread(new Runnable(){  
        @Override  
        public void run() {            
            while(true) {                 
                try {                  	
                	if(mStatus == STARTUP_STATUS) {
                		//启动实时流码流获取
                		playerId = MyApplcation.getInstance().GetSdkInterface().startRealPlay(mDeviceId, mChannelId, mPlayer);
                		
                		Message msg = new Message();   		
                		msg.what = REALPLAY_RESPONSE;
                		msg.arg1 = playerId;
                		mHandler.sendMessage(msg);                		
                		
                		mStatus = RUNNING_STATUS;          		              		
                	}
                	else if(mStatus == STOPDOWN_STATUS) {
                		if(playerId != Constants.WMPLAYERID_INVALID) {
                			//停止实时流码流获取
                			MyApplcation.getInstance().GetSdkInterface().stopRealPlay(playerId);
                			
                			Message msg = new Message();   		
                    		msg.what = REALPLAY_STOP;
                    		msg.arg1 = playerId;
                    		mHandler.sendMessage(msg);
                			
                			mStatus = INVALID_STATUS;
                			
                			break;
                		}
                	}
                	
                    Thread.sleep(10);  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                    break;  
                }  
            }  
        }  
    });
	
	public void startplay(int deviceId, int channelId, StreamPlayer player)
	{
		mDeviceId = deviceId;
		mChannelId = channelId;
		mPlayer = player;
		
		mStatus = STARTUP_STATUS;	
	}	
	
	public void stopPlay() {		
		mStatus = STOPDOWN_STATUS;
		
//		try {
//			mThread.join();   
//		} catch (InterruptedException e) {  
//            e.printStackTrace();    
//        }  
	}
}
