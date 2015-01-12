package pl.nemolab.subtitlesviewer;

public class TimeCode {

    private String text;
    private int miliSecond;

    public TimeCode(String text, int miliSecond) {
        this.text = text;
        this.miliSecond = miliSecond;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMiliSecond() {
        return miliSecond;
    }

    public void setMiliSecond(int miliSecond) {
        this.miliSecond = miliSecond;
    }
}
