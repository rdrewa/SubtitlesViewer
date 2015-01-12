package pl.nemolab.subtitlesviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SubtitleAdapter extends ArrayAdapter<Subtitle> {
    private Context context;
    private Subtitle[] data;

    public SubtitleAdapter(Context context, int resource, Subtitle[] objects) {
        super(context, resource, objects);
    }

    public SubtitleAdapter(Context context, Subtitle[] subtitles) {
        super(context, R.layout.item_subtitle, subtitles);
        this.context = context;
        this.data = subtitles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder holder;
        if (itemView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            itemView = inflater.inflate(R.layout.item_subtitle, null);
            holder = new ViewHolder();
            holder.text = (TextView) itemView.findViewById(R.id.txtText);
            holder.start = (TextView) itemView.findViewById(R.id.txtStart);
            holder.stop = (TextView) itemView.findViewById(R.id.txtStop);
            itemView.setTag(holder);
        }
        holder = (ViewHolder) itemView.getTag();
        Subtitle subtitle = data[position];
        holder.text.setText(subtitle.getText());
        holder.start.setText(subtitle.getStart().getText());
        holder.stop.setText(subtitle.getStop().getText());
        return itemView;
    }

    private static class ViewHolder {
        public TextView text;
        public TextView start;
        public TextView stop;
    }
}
