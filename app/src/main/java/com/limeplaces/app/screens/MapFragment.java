package com.limeplaces.app.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputLayout;
import com.limeplaces.app.MainActivity;
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.ui.PlaceCardView;
import com.limeplaces.app.ui.Ui;

public class MapFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        FrameLayout root = (FrameLayout) inflater.inflate(R.layout.fragment_map, container, false);
        root.addView(new MapCanvasView(requireContext()), 0, new FrameLayout.LayoutParams(-1, -1));

        TextInputLayout search = Ui.input(requireContext(), "Bạn muốn đi đâu?", false);
        FrameLayout.LayoutParams searchParams = new FrameLayout.LayoutParams(-1, Ui.dp(requireContext(), 58));
        searchParams.setMargins(Ui.dp(requireContext(), 16), Ui.dp(requireContext(), 24), Ui.dp(requireContext(), 16), 0);
        root.addView(search, searchParams);

        LinearLayout results = new LinearLayout(requireContext());
        results.setOrientation(LinearLayout.VERTICAL);
        Ui.setPadding(results, 16, 14);
        results.setBackground(Ui.background(Ui.color(requireContext(), R.color.cream), 24, requireContext()));
        results.addView(Ui.text(requireContext(), "12 địa điểm quanh đây", 12, Ui.color(requireContext(), R.color.ink), true));
        PlaceCardView card = new PlaceCardView(requireContext(), AppData.PLACES.get(0), true,
            view -> ((MainActivity) requireActivity()).showDetail(0));
        results.addView(card, Ui.margin(-1, -2, 0, 12, 0, 0, requireContext()));
        View add = Ui.softButton(requireContext(), "+  Thêm địa điểm tại vị trí này");
        add.setOnClickListener(view -> ((MainActivity) requireActivity()).openAddPlace());
        results.addView(add, Ui.margin(-1, 48, 0, 10, 0, 0, requireContext()));
        FrameLayout.LayoutParams resultParams = new FrameLayout.LayoutParams(-1, Ui.dp(requireContext(), 220));
        resultParams.gravity = android.view.Gravity.BOTTOM;
        root.addView(results, resultParams);
        return root;
    }
}
