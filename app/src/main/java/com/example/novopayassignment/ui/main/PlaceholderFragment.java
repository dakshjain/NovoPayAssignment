package com.example.novopayassignment.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.novopayassignment.MainActivity;
import com.example.novopayassignment.NewsSourceList;
import com.example.novopayassignment.R;
import com.example.novopayassignment.WebActivity;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private NewsSourceList newsSourceList;

    int index = 0;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(getActivity()).get(PageViewModel.class);

        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);

        newsSourceList = pageViewModel.newsSourceLiveData.getValue().get(pageViewModel.mIndex.getValue());
        setUI(root);


        return root;
    }

    private void setUI(View root) {

        final TextView textView = root.findViewById(R.id.txt_news_source);
        final TextView author = root.findViewById(R.id.txt_author);
        final TextView date = root.findViewById(R.id.txt_date);
        final TextView content = root.findViewById(R.id.txt_content);
        final TextView description = root.findViewById(R.id.txt_description);
        final TextView title = root.findViewById(R.id.txt_title);

        final ImageView imageView = root.findViewById(R.id.img_source);

        textView.setText(newsSourceList.source.name);
        author.setText(newsSourceList.author);
        date.setText(newsSourceList.publishedAt.substring(0, 10));
        content.setText(newsSourceList.content);
        description.setText(newsSourceList.descripiton);
        title.setText(newsSourceList.title);
        Glide.with(PlaceholderFragment.this).load(newsSourceList.urlToImage).into(imageView);

        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                Bundle b = new Bundle();
                b.putString("url", newsSourceList.url); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                }
        });
    }
}