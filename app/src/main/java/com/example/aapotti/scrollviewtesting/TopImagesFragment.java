package com.example.aapotti.scrollviewtesting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopImagesFragment extends Fragment {


    private ImageView topImage;

    public TopImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_top_images, container, false);

        topImage = (ImageView)view.findViewById(R.id.topImage);

        switch (getArguments().getInt("pageNumber"))
        {
            case 1:
                topImage.setImageResource(R.drawable.kymiring1);
                break;

            case 2:
                topImage.setImageResource(R.drawable.kymiring2);
                break;

            case 3:
                topImage.setImageResource(R.drawable.kymiring3);
                break;

            case 4:
                topImage.setImageResource(R.drawable.kymiring4);
                break;

            case 5:
                topImage.setImageResource(R.drawable.kymiring5);
                break;
        }

        return view;
    }

}
