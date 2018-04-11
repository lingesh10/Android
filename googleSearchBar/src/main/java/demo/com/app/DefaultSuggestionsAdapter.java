package demo.com.app;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/* Created by Lingeshwaran.N on 06-04-2018. */
public class DefaultSuggestionsAdapter extends RecyclerView.Adapter<DefaultSuggestionsAdapter.SuggestionHolder> {

    private String[] data;

    DefaultSuggestionsAdapter(String[] data) {
        this.data = data;
    }

    @NonNull
    @Override
    public SuggestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_last_request, parent, false);
        return new SuggestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionHolder holder, int position) {
        holder.text.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class SuggestionHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private ImageView iv_delete;

        SuggestionHolder(final View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "itemView");
                }
            });
            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "itemDelete");
                }
            });
        }
    }
}
