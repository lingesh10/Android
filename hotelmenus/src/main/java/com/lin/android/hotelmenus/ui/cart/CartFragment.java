package com.lin.android.hotelmenus.ui.cart;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lin.android.hotelmenus.R;
import com.lin.android.hotelmenus.ui.home.HomeFragment;
import com.lin.android.hotelmenus.ui.home.HomeViewModel;

public class CartFragment extends Fragment {

    private CartViewModel cartViewModel;
    private AppCompatActivity mContext;

    public static CartFragment newInstance(Bundle bundle) {
        CartFragment f = new CartFragment();
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = (AppCompatActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mContext != null) {
            mContext.setTitle("Cart");
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cartViewModel =
                ViewModelProviders.of(this).get(CartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        final TextView textView = root.findViewById(R.id.text_cart);
        cartViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}