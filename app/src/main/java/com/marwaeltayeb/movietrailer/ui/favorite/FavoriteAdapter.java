package com.marwaeltayeb.movietrailer.ui.favorite;

import static com.marwaeltayeb.movietrailer.utils.Constant.IMAGE_URL;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.marwaeltayeb.movietrailer.R;
import com.marwaeltayeb.movietrailer.data.model.Movie;
import com.marwaeltayeb.movietrailer.databinding.MovieItemBinding;

public class FavoriteAdapter extends ListAdapter<Movie, FavoriteAdapter.FavoriteHolder> {

    private final Context mContext;

    private final FavoriteAdapter.FavoriteAdapterOnClickHandler clickHandler;

    public interface FavoriteAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public FavoriteAdapter(Context mContext, FavoriteAdapter.FavoriteAdapterOnClickHandler clickHandler) {
        super(DIFF_CALLBACK);
        this.mContext = mContext;
        this.clickHandler = clickHandler;
    }

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieId().equals(newItem.getMovieId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getMovieTitle().equals(newItem.getMovieTitle()) &&
                    oldItem.getMovieDescription().equals(newItem.getMovieDescription());
        }
    };

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        MovieItemBinding movieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new FavoriteHolder(movieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, int position) {
        Movie movie = getItem(position);
        holder.bind(movie);
    }

    class FavoriteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final MovieItemBinding binding;

        public FavoriteHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // Register a callback to be invoked when this view is clicked.
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            clickHandler.onClick(getCurrentList().get(position));
        }

        public void bind(Movie movie) {
            if (movie != null) {
                binding.movieTitle.setText(movie.getMovieTitle());
                binding.movieRating.setText(movie.getMovieVote());

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.no_preview)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform();

//                Glide.with(mContext)
//                        .load(IMAGE_URL + movie.getMoviePoster())
//                        .apply(options)
//                        .into(binding.moviePoster);
                Glide.with(mContext)
                        .load(IMAGE_URL )
                        .apply(options)
                        .into(binding.moviePoster);
            }
        }
    }
}
