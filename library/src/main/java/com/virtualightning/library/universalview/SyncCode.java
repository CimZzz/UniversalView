package com.virtualightning.library.universalview;

/**
 * Created by CimZzz on 2018/11/26.<br>
 * Project Name : UniversalView<br>
 * Since : UniversalView_0.0.1<br>
 * Description:<br>
 */
public class SyncCode {
    public static final int CODE_DIFFERENT = -1;
    public static final int CODE_UNDEFINED = -2;
    private int code;
    private int recordCode;
    private int flag;

    public SyncCode() {
        code = 0;
        flag = 0;
    }

    public synchronized void setFlag(int syncCode, int flag) {
        if(code == syncCode)
            this.flag = flag;
    }

    public synchronized void appendFlag(int syncCode, int appendFlag) {
        if(code == syncCode)
            this.flag = flag | appendFlag;
    }

    public synchronized int nextSyncCode() {
        code ++;
        if(code == Integer.MAX_VALUE)
            code = 0;

        this.flag = CODE_UNDEFINED;
        return code;
    }

    public synchronized int nextSyncCode(int flag) {
        code ++;
        if(code == Integer.MAX_VALUE)
            code = 0;

        this.flag = flag;

        return code;
    }

    public synchronized int getCode() {
        return code;
    }

    public synchronized int getRecordCode() {
        return recordCode;
    }

    public synchronized void recordCode() {
        this.recordCode = getCode();
    }

    public synchronized boolean checkRecordSync(int syncCode) {return recordCode == syncCode; }

    public synchronized boolean checkSync(int syncCode) {return code == syncCode; }
    public synchronized int checkSyncCode(int syncCode) {
        return code == syncCode ? flag : CODE_DIFFERENT;
    }
}
