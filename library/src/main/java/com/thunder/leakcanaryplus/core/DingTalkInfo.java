package com.thunder.leakcanaryplus.core;


class DingTalkInfo {

    private String msgtype = "text";

    private TextBean text;

    public void setText(TextBean text) {
        this.text = text;
    }

    public static class TextBean {

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = "发现新的内存泄漏请及时处理 :\n" + content +
                    "\n “A small leak will sink a great ship.” - Benjamin Franklin \n “千里之堤,溃于蚁穴.” -《韩非子·喻老》";
        }
    }

}
