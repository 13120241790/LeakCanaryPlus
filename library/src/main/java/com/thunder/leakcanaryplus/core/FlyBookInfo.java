package com.thunder.leakcanaryplus.core;

import android.text.TextUtils;

class FlyBookInfo {

    private String msg_type = "text"; //文本类型

    private ContentInfo content;

    public ContentInfo getContent() {
        return content;
    }

    public String getMsgType() {
        return msg_type;
    }

    public void setContent(ContentInfo content) {
        this.content = content;
    }

    public static class ContentInfo {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text, String devicesIP) {
            this.text = "发现新的内存泄漏请及时处理 :\n" +
                    text + (TextUtils.isEmpty(devicesIP) ? "\n" : "\n Devices IP : " + devicesIP +
                    "\n" + "May be download address : " + devicesIP + ":2007") +
                    "\n “A small leak will sink a great ship.” - Benjamin Franklin \n “千里之堤,溃于蚁穴.” -《韩非子·喻老》";
        }
    }
}
