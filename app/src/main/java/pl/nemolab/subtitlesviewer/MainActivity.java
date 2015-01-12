package pl.nemolab.subtitlesviewer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    public static String SUBTITLE_NAME = "SUBTITLE_NAME";
    public static String SUBTITLE_PATH = "SUBTITLE_PATH";
    private Button btnFindSubtitles;
    private ListView lstFoundSubtitles;
    private List<String> fileList = new ArrayList<String>();
    private Map<String, String> fileMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFindSubtitles = (Button) findViewById(R.id.btnFindSubtitles);
        lstFoundSubtitles = (ListView) findViewById(R.id.lstFoundSubtitles);
        lstFoundSubtitles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = fileList.get(position);
                String filePath = fileMap.get(fileName);
                Toast.makeText(getApplicationContext(), fileName + "\n" + filePath, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SubtitlesActivity.class);
                intent.putExtra(SUBTITLE_NAME, fileName);
                intent.putExtra(SUBTITLE_PATH, filePath);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void obtainSrtFilesList(View view) {
        File sdcard = Environment.getExternalStorageDirectory();
        File dirs = new File(sdcard.getAbsolutePath() + "/srt/");
        File[] files = null;
        if (dirs.exists()) {
            files = dirs.listFiles();
        }
        if (files == null || files.length == 0) {
            return;
        }
        for (File file : files) {
            fileList.add(file.getName());
            fileMap.put(file.getName(), file.getPath());
        }
        int listItem = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, listItem, fileList);
        lstFoundSubtitles.setAdapter(adapter);
    }

}
