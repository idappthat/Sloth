package me.koltensturgill.sloth;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.koltensturgill.sloth.Model.Note;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    class NoteViewHolder extends  RecyclerView.ViewHolder{

        private final TextView noteTitleView;
        private final TextView noteBodyView;

        private NoteViewHolder(View itemView){
            super(itemView);
            noteTitleView = itemView.findViewById(R.id.tv_note_title);
            noteBodyView = itemView.findViewById(R.id.tv_note_body);
        }
    }

    private final LayoutInflater mInflater;
    // Cached list of notes
    private List<Note> mNotes;

    NoteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the views
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // Bind our items to the text views
        if(mNotes != null){
            Note curr = mNotes.get(position);
            holder.noteTitleView.setText(Converter.dateToString(curr.getCreated_at()));
            holder.noteBodyView.setText(curr.getBody());
        } else {
            holder.noteBodyView.setText("Add a new note first!");
        }
    }

    void setNotes(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mNotes != null){
            return mNotes.size();
        }else{
            return 0;
        }
    }
}
