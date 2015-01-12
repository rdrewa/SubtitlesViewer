package pl.nemolab.subtitlesviewer;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SubtitleProcessorTask extends AsyncTask<Void, Void, Void> {

    private TextView txtIndicator;
    private ListView lstSubtitles;
    private String infoStart;
    private String infoStop;
    private String filePath;
    private List<Subtitle> subtitleList;
    private Context context;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTxtIndicator(TextView txtIndicator) {
        this.txtIndicator = txtIndicator;
    }

    public void setLstSubtitles(ListView lstSubtitles) {
        this.lstSubtitles = lstSubtitles;
    }

    public void setInfoStart(String infoStart) {
        this.infoStart = infoStart;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setInfoStop(String infoStop) {
        this.infoStop = infoStop;
    }

    @Override
    protected void onPreExecute() {
        txtIndicator.setText(infoStart);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            SrtParser parser = new SrtParser();
            subtitleList = parser.parseFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        txtIndicator.setText(infoStop);
        Subtitle[] subtitles = subtitleList.toArray(new Subtitle[subtitleList.size()]);
        SubtitleAdapter adapter = new SubtitleAdapter(context, subtitles);
        lstSubtitles.setAdapter(adapter);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            txtIndicator.setEnabled(false);
        }
    }
}
