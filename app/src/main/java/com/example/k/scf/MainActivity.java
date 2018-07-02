package com.example.k.scf;

import android.content.Context;
import android.media.session.MediaSession;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

public class MainActivity extends AppCompatActivity {

    private EditText mInputSearch;
    private ImageButton mButtonSearch;

    private RecyclerView mListSearch;

    private DatabaseReference mSearchDatabse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchDatabse = FirebaseDatabase.getInstance().getReference("Places");

        mInputSearch = (EditText) findViewById(R.id.inputSearch);
        mButtonSearch = (ImageButton) findViewById(R.id.butonSearch);

        mListSearch = (RecyclerView) findViewById(R.id.listSearch);
        mListSearch.setHasFixedSize(true);
        mListSearch.setLayoutManager(new LinearLayoutManager(this));

        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mInputSearch.getText().toString();

                firebaseSearch(searchText);

            }
        });


    }

    private void firebaseSearch(String searchText) {

        Query firebaseQuery = mSearchDatabse.orderByChild("name").startAt(searchText.toUpperCase()).endAt(searchText.toLowerCase() + "\uf8ff");

        FirebaseRecyclerAdapter<Search, SearchViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Search, SearchViewHolder>(

                Search.class,
                R.layout.list_layout,
                SearchViewHolder.class,
                firebaseQuery


        ) {
            @Override
            protected void populateViewHolder(SearchViewHolder viewHolder, Search model, int position) {

                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getDescription(), model.getImage());

            }
        };
        mListSearch.setAdapter(firebaseRecyclerAdapter);

    }


    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public SearchViewHolder(View itemView) {

            super(itemView);

            mView = itemView;

        }


        public void setDetails(Context ctx, String name, String desc, String image){

            TextView nameP = (TextView) mView.findViewById(R.id.nameSearch);
            TextView descP = (TextView) mView.findViewById(R.id.descSearch);
            ImageView imageP = (ImageView) mView.findViewById(R.id.imageSearch);

            nameP.setText(name);
            descP.setText(desc);

            Picasso.get().load(image).into(imageP);



        }
    }

}
