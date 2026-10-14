package com.limeplaces.app.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputLayout;
import com.limeplaces.app.MainActivity;
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.ui.PlaceCardView;
import com.limeplaces.app.ui.Ui;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayout page = root.findViewById(R.id.home_content);

        page.addView(Ui.header(requireContext(), "Thứ sáu, 24 tháng 5", "Đi đâu hôm nay?"));
        TextInputLayout search = Ui.input(requireContext(), "Tìm địa điểm...", false);
        page.addView(search, Ui.margin(-1, -2, 0, 22, 0, 18, requireContext()));

        LinearLayout mood = new LinearLayout(requireContext());
        mood.setOrientation(LinearLayout.VERTICAL);
        mood.setPadding(Ui.dp(requireContext(), 22), Ui.dp(requireContext(), 22), Ui.dp(requireContext(), 22), Ui.dp(requireContext(), 22));
        mood.setBackground(Ui.background(Ui.color(requireContext(), R.color.lime), 24, requireContext()));
        mood.addView(Ui.text(requireContext(), "GỢI Ý CHO BẠN", 10, Ui.color(requireContext(), R.color.muted), true));
        mood.addView(Ui.text(requireContext(), "Trốn phố một chút?", 21, Ui.color(requireContext(), R.color.ink), true), Ui.margin(-1, -2, 0, 5, 0, 0, requireContext()));
        mood.addView(Ui.text(requireContext(), "8 địa điểm xanh mát trong bán kính 5 km.", 11, Color.rgb(91, 106, 68), false), Ui.margin(-1, -2, 0, 7, 0, 0, requireContext()));
        TextView discover = Ui.text(requireContext(), "Khám phá ngay  →", 11, Ui.color(requireContext(), R.color.violet), true);
        mood.addView(discover, Ui.margin(-1, -2, 0, 12, 0, 0, requireContext()));
        page.addView(mood, Ui.margin(-1, -2, 0, 0, 0, 28, requireContext()));

        page.addView(sectionTitle("Nổi bật gần bạn", "Xem tất cả"));
        HorizontalScrollView horizontal = new HorizontalScrollView(requireContext());
        horizontal.setHorizontalScrollBarEnabled(false);
        LinearLayout featured = new LinearLayout(requireContext());
        featured.setOrientation(LinearLayout.HORIZONTAL);
        for (int index = 0; index < 2; index++) {
            final int position = index;
            PlaceCardView card = new PlaceCardView(requireContext(), AppData.PLACES.get(index), false, view -> open(position));
            featured.addView(card, Ui.margin(278, -2, 0, 0, 12, 0, requireContext()));
        }
        horizontal.addView(featured);
        page.addView(horizontal, Ui.margin(-1, -2, 0, 14, 0, 28, requireContext()));

        page.addView(sectionTitle("Mới được lưu", "Xem tất cả"));
        for (int index = 1; index < AppData.PLACES.size(); index++) {
            final int position = index;
            page.addView(new PlaceCardView(requireContext(), AppData.PLACES.get(index), true, view -> open(position)),
                Ui.margin(-1, -2, 0, 0, 0, 12, requireContext()));
        }
        return root;
    }

    private LinearLayout sectionTitle(String title, String action) {
        LinearLayout row = Ui.row(requireContext());
        row.addView(Ui.text(requireContext(), title, 17, Ui.color(requireContext(), R.color.ink), true));
        row.addView(Ui.spacer(requireContext()));
        row.addView(Ui.text(requireContext(), action, 10, Ui.color(requireContext(), R.color.violet), true));
        return row;
    }

    private void open(int position) {
        ((MainActivity) requireActivity()).showDetail(position);
    }
}
