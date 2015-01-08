package pl.nemolab.subtitlesviewer;

import java.sql.Time;

public class Subtitle {
    private int id;
    private String content;
    private int start;
    private int stop;

    public Subtitle(int id, String content, int start, int stop) {
        this.id = id;
        this.content = content;
        this.start = start;
        this.stop = stop;
    }
}
