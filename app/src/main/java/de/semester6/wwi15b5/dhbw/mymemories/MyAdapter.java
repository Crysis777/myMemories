package de.semester6.wwi15b5.dhbw.mymemories;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String LOG_TAG = "AudioPlayTest";
    private ArrayList<String> texte;
    private boolean bStartPlaying = true;
    private MediaPlayer bPlayer = null;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView itemText;
        ImageView editView;
        ImageView playView;
        ImageView viewShare;

        public ViewHolder(View itemView) {
            super(itemView);


            itemText = itemView.findViewById(R.id.info_text);
            editView = itemView.findViewById(R.id.imageViewEdit);
            playView = itemView.findViewById(R.id.imageViewPlay);
            viewShare = itemView.findViewById(R.id.imageViewShare);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset) {
        texte = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_element, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView1.setLayoutParams(lp);
        return new ViewHolder(itemView1);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemText.setText(texte.get(position).toString());
        final View.OnClickListener onClickListenerEdit = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.equals(holder.editView)){
                    onClickEdit(position, texte.get(position).toString(), view);
                } else if(view.equals(holder.playView)){
                    onClickPlay(position, texte.get(position).toString(), view, holder);
                } else if(view.equals(holder.viewShare)){
                    onClickSend(position, texte.get(position).toString(), view, holder);
                }
            }
        };
        holder.editView.setOnClickListener(onClickListenerEdit);
        holder.playView.setOnClickListener(onClickListenerEdit);
        holder.viewShare.setOnClickListener(onClickListenerEdit);
    }

    //TODO Implement Sharing funktionality to other applications
    public void onClickSend(int pos, String memoText, View view, ViewHolder holder){
        Context context = view.getContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, "To be continued...", duration).show();
    }

    public void onClickPlay(int pos, String memoText, View view, ViewHolder holder){
        onPlayB(bStartPlaying, memoText, view);
        if (bStartPlaying) {
            //Change button to pause icon
            holder.playView.setImageResource(R.drawable.baseline_pause_black_36dp);
        } else {
            //Change button to play icon
            holder.playView.setImageResource(R.drawable.baseline_play_arrow_black_36dp);
        }
        bStartPlaying = !bStartPlaying;
    }

    private void onPlayB(boolean start, String memoText, View view) {
        if (start) {
            startPlayingB(memoText, view);
        } else {
            stopPlayingB();
        }
    }

    private void startPlayingB(String memoText, View view) {
        bPlayer = new MediaPlayer();
        String bFileName = null;
        String Temp = view.getContext().getDir("memos", MODE_PRIVATE).getAbsolutePath();
        bFileName = Temp + "/" + memoText + ".mp4";
        try {
            bPlayer.setDataSource(bFileName);
            bPlayer.prepare();
            bPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlayingB() {
        bPlayer.release();
        bPlayer = null;
    }

    public void onClickEdit(int pos, String memoText, View view){
        Intent intent = new Intent(view.getContext(), EditActivity.class);
        intent.putExtra("memoText", memoText);
        view.getContext().startActivity(intent);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return MainMemoBrowse.textArray.size();
    }
}