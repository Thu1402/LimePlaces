package com.limeplaces.app.screens;

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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.R;
import com.limeplaces.app.ui.Ui;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        LinearLayout page = root.findViewById(R.id.profile_content);

        LinearLayout header = Ui.row(requireContext());
        header.addView(Ui.header(requireContext(), "Không gian của bạn", "Hồ sơ"), new LinearLayout.LayoutParams(0, -2, 1));
        MaterialButton settings = Ui.softButton(requireContext(), "Thiết lập");
        header.addView(settings, Ui.margin(100, 44, 0, 0, 0, 0, requireContext()));
        page.addView(header);

        LinearLayout profile = new LinearLayout(requireContext());
        profile.setGravity(Gravity.CENTER);
        profile.setOrientation(LinearLayout.VERTICAL);
        profile.setBackground(Ui.background(Ui.color(requireContext(), R.color.lime), 24, requireContext()));
        Ui.setPadding(profile, 20, 22);
        TextView avatar = Ui.text(requireContext(), "KL", 22, Ui.color(requireContext(), R.color.violet), true);
        avatar.setGravity(Gravity.CENTER);
        avatar.setBackground(Ui.background(Ui.color(requireContext(), R.color.violet_soft), 28, requireContext()));
        profile.addView(avatar, Ui.margin(76, 76, 0, 0, 0, 12, requireContext()));
        profile.addView(Ui.text(requireContext(), "Khánh Linh", 20, Ui.color(requireContext(), R.color.ink), true));
        profile.addView(Ui.text(requireContext(), "@khanhlinh · Đà Lạt", 10, Ui.color(requireContext(), R.color.muted), false));
        profile.addView(Ui.softButton(requireContext(), "Chỉnh sửa hồ sơ"), Ui.margin(160, 44, 0, 14, 0, 17, requireContext()));
        profile.addView(stats());
        page.addView(profile, Ui.margin(-1, -2, 0, 20, 0, 27, requireContext()));

        page.addView(Ui.text(requireContext(), "Bộ sưu tập của bạn", 17, Ui.color(requireContext(), R.color.ink), true));
        TextView collection = Ui.text(requireContext(), "♡   Những nơi muốn trở lại\n      8 địa điểm", 13, Ui.color(requireContext(), R.color.ink), true);
        collection.setGravity(Gravity.CENTER_VERTICAL);
        collection.setBackground(Ui.borderedBackground(Ui.color(requireContext(), R.color.surface), Ui.color(requireContext(), R.color.line), 18, requireContext()));
        Ui.setPadding(collection, 16, 16);
        page.addView(collection, Ui.margin(-1, 84, 0, 12, 0, 26, requireContext()));

        page.addView(Ui.text(requireContext(), "Hoạt động gần đây", 17, Ui.color(requireContext(), R.color.ink), true));
        page.addView(activity("Đã lưu Nép Coffee", "2 giờ trước"));
        page.addView(activity("Đã chia sẻ một nơi với Minh Anh", "Hôm qua"));
        page.addView(activity("Đã thêm Hiên Nhà Vàng", "3 ngày trước"));

        settings.setOnClickListener(view -> showSettings());
        return root;
    }

    private LinearLayout stats() {
        LinearLayout row = Ui.row(requireContext());
        String[][] values = {{"36", "Đã lưu"}, {"12", "Đã thêm"}, {"24", "Bạn bè"}};
        for (String[] value : values) {
            LinearLayout item = new LinearLayout(requireContext());
            item.setGravity(Gravity.CENTER);
            item.setOrientation(LinearLayout.VERTICAL);
            item.addView(Ui.text(requireContext(), value[0], 17, Ui.color(requireContext(), R.color.ink), true));
            item.addView(Ui.text(requireContext(), value[1], 8, Ui.color(requireContext(), R.color.muted), false));
            row.addView(item, new LinearLayout.LayoutParams(0, -2, 1));
        }
        return row;
    }

    private View activity(String title, String time) {
        LinearLayout item = new LinearLayout(requireContext());
        item.setOrientation(LinearLayout.VERTICAL);
        item.setPadding(0, Ui.dp(requireContext(), 12), 0, Ui.dp(requireContext(), 12));
        item.addView(Ui.text(requireContext(), title, 11, Ui.color(requireContext(), R.color.ink), true));
        item.addView(Ui.text(requireContext(), time, 8, Ui.color(requireContext(), R.color.muted), false));
        return item;
    }

    private void showSettings() {
        String[] options = {"Thông tin cá nhân", "Thông báo", "Quyền riêng tư", "Đăng xuất", "Xóa tài khoản"};
        new AlertDialog.Builder(requireContext())
            .setTitle("Thiết lập tài khoản")
            .setItems(options, (dialog, which) -> {
                if (which == 4) confirmDelete();
                else Toast.makeText(requireContext(), options[which], Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Đóng", null)
            .show();
    }

    private void confirmDelete() {
        new AlertDialog.Builder(requireContext())
            .setTitle("Xóa tài khoản?")
            .setMessage("Tất cả địa điểm đã lưu, bộ sưu tập và kết nối bạn bè sẽ bị xóa vĩnh viễn. Hành động này không thể hoàn tác.")
            .setPositiveButton("Xóa tài khoản", (dialog, which) ->
                Toast.makeText(requireContext(), "Yêu cầu xóa tài khoản đã được ghi nhận", Toast.LENGTH_LONG).show())
            .setNegativeButton("Giữ lại tài khoản", null)
            .show();
    }
}
