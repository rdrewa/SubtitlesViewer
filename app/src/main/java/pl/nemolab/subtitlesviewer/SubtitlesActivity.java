package pl.nemolab.subtitlesviewer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class SubtitlesActivity extends ActionBarActivity {

    private TextView txtFileName, txtStatus;
    private ListView lstSubtitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtitles);
        txtFileName = (TextView) findViewById(R.id.txtFileName);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        lstSubtitles = (ListView) findViewById(R.id.lstSubtitles);
        Bundle extras = getIntent().getExtras();
        String fileName = extras.getString(MainActivity.SUBTITLE_NAME);
        String filePath = extras.getString(MainActivity.SUBTITLE_PATH);
        txtFileName.setText(fileName);
        SubtitleProcessorTask subtitleProcessor = new SubtitleProcessorTask();
        subtitleProcessor.setTxtIndicator(txtStatus);
        subtitleProcessor.setInfoStart(getString(R.string.info_processor_start));
        subtitleProcessor.setInfoStop(getString(R.string.info_processor_stop));
        subtitleProcessor.setFilePath(filePath);
        subtitleProcessor.setLstSubtitles(lstSubtitles);
        subtitleProcessor.setContext(this);
        subtitleProcessor.execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subtitles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
