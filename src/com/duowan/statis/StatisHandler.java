package com.duowan.statis;

import android.os.Handler;
import android.os.Message;

import com.duowan.statis.constant.StatisInfo;
import com.duowan.statis.ui.StatisApplication;
import com.duowan.util.ToastShowUtil;

public class StatisHandler extends Handler {
	public static final int STATUS_START_CODE = 100;
	public static final int STATUS_STOP_CODE = 200;
	public static final int STATUS_STOP_ALL_CODE = 300;
	StatisManager mStatisManager;

	public StatisHandler(StatisManager statisManager) {
		this.mStatisManager = statisManager;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case STATUS_START_CODE:
			if (msg != null && msg.obj instanceof StatisInfo) {
				StatisInfo statisInfo = (StatisInfo) msg.obj;
				SingleFileStatis statisingSet = mStatisManager
						.getFromStatisingSet(statisInfo.getEventid());
				if (statisingSet != null) {
					ToastShowUtil.showMsgLong(StatisApplication.getInstance(),
							"STATUS_START_CODE");
				}
			}
			break;
		case STATUS_STOP_CODE:
			if (msg != null && msg.obj instanceof StatisInfo) {
				StatisInfo statisInfo = (StatisInfo) msg.obj;
				if (mStatisManager.removeFromStatisingSet(statisInfo
						.getEventid())) {
					ToastShowUtil.showMsgLong(StatisApplication.getInstance(),
							"STATUS_STOP_CODE");
				}
			}
			break;
		default:
			break;
		}

	}

}
