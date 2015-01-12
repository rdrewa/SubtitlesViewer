package pl.nemolab.subtitlesviewer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by senator on 2015-01-08.
 */
public class SrtParser {

    public List<Subtitle> parseFile(String filePath) throws IOException, ParseException {
        InputStream inputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        Map<Integer, Subtitle> subtitleMap = new TreeMap<>();
        List<Subtitle> subtitleList = new ArrayList<>();
        String line = reader.readLine();
        while (line != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                line = line.replaceAll("\\s+", "");
                Number number = NumberFormat.getInstance().parse(line);
                int num = number.intValue();
//                int num;
//                num = (int) line;
                line = reader.readLine();
                TimeCode start = prepareStart(line);
                TimeCode stop = prepareStop(line);
                String text = prepareText(reader);
                Subtitle subtitle = new Subtitle(num, text, start, stop);
                subtitleMap.put(start.getMiliSecond(), subtitle);
                subtitleList.add(subtitle);
                line = reader.readLine();
            }
        }
        return subtitleList;
    }

    private String prepareText(BufferedReader reader) throws IOException {
        StringBuffer sb = new StringBuffer();
        String text = "";
        String line = reader.readLine();
        while (line != null && !line.isEmpty()) {
//            if (sb.length() > 1) {
//                sb.append()
//            }
            sb.append(line);
            line = reader.readLine();
        }
        return sb.toString();
    }

    private TimeCode prepareStart(String line) {
        String string = line.substring(0,12);
        int milisecond = parseTime(string);
        return new TimeCode(string.substring(0, 8), milisecond);
    }

    private TimeCode prepareStop(String line) {
        String string = line.substring(line.length() - 12, line.length());
        int milisecond = parseTime(string);
        return new TimeCode(string.substring(0, 8), milisecond);
    }

    private int parseTime(String time) {
        int h, m, s, ms;
        h = Integer.parseInt(time.substring(0, 2));
        m = Integer.parseInt(time.substring(3, 5));
        s = Integer.parseInt(time.substring(6, 8));
        ms = Integer.parseInt(time.substring(9, 12));
        return ms + (s * 1000) + (m * 60000) + (h * 360000);
    }
}
