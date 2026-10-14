package com.limeplaces.app.screens;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.MainActivity;
import com.limeplaces.app.R;
import com.limeplaces.app.ui.Ui;

public class AddPlaceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_add_place, container, false);
        LinearLayout page = root.findViewById(R.id.add_place_content);
        page.addView(Ui.header(requireContext(), "Địa điểm mới", "Chia sẻ một nơi hay"));

        TextView imagePicker = Ui.text(requireContext(), "▧\n\nThêm hình ảnh\nChọn từ thư viện hoặc chụp ảnh mới", 12, Ui.color(requireContext(), R.color.violet), true);
        imagePicker.setGravity(Gravity.CENTER);
        imagePicker.setBackground(Ui.borderedBackground(Ui.color(requireContext(), R.color.surface), Ui.color(requireContext(), R.color.line), 22, requireContext()));
        imagePicker.setOnClickListener(view -> Toast.makeText(requireContext(), "Mở thư viện ảnh hoặc máy ảnh", Toast.LENGTH_SHORT).show());
        page.addView(imagePicker, Ui.margin(-1, 185, 0, 22, 0, 24, requireContext()));

        page.addView(Ui.input(requireContext(), "Tên địa điểm", false), Ui.margin(-1, -2, 0, 0, 0, 14, requireContext()));
        page.addView(Ui.input(requireContext(), "Mô tả địa điểm", true), Ui.margin(-1, -2, 0, 0, 0, 18, requireContext()));

        MaterialButton location = Ui.softButton(requireContext(), "⌖  Chọn vị trí trên bản đồ");
        page.addView(location, Ui.margin(-1, 52, 0, 0, 0, 22, requireContext()));

        page.addView(Ui.text(requireContext(), "MÀU NHẬN DIỆN", 10, Ui.color(requireContext(), R.color.muted), true));
        LinearLayout colors = Ui.row(requireContext());
        int[] values = {R.color.lime_strong, R.color.violet, R.color.peach, R.color.mint, R.color.danger};
        for (int value : values) {
            View dot = new View(requireContext());
            dot.setBackground(Ui.background(Ui.color(requireContext(), value), 22, requireContext()));
            colors.addView(dot, Ui.margin(38, 38, 0, 12, 13, 0, requireContext()));
        }
        page.addView(colors);

        MaterialButton save = Ui.primaryButton(requireContext(), "Lưu địa điểm");
        save.setOnClickListener(view -> {
            save.setText("Đã lưu địa điểm");
            save.setBackgroundColor(Color.rgb(103, 136, 53));
            Toast.makeText(requireContext(), "Địa điểm mới đã được cập nhật", Toast.LENGTH_SHORT).show();
            view.postDelayed(() -> ((MainActivity) requireActivity()).returnHome(), 700);
        });
        page.addView(save, Ui.margin(-1, 54, 0, 26, 0, 0, requireContext()));
        return root;
    }
}
