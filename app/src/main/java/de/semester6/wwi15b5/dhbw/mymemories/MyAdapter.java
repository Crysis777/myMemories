package de.semester6.wwi15b5.dhbw.mymemories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import de.semester6.wwi15b5.dhbw.mymemories.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<String> texte;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        TextView itemText;
        Button playButton;
        Button editButton;
        Button shareButton;

        public ViewHolder(View itemView) {
            super(itemView);

            itemText = (TextView) itemView.findViewById(R.id.info_text);
            playButton = (Button) itemView.findViewById(R.id.play_button);
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
        return new ViewHolder(itemView1);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemText.setText(texte.get(position).toString());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return MainMemoBrowse.text.size();
    }
}