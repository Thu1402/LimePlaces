package com.limeplaces.app.screens;

import android.os.Bundle;
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
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.ui.Ui;

public class FriendsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        LinearLayout page = root.findViewById(R.id.friends_content);
        page.addView(Ui.header(requireContext(), "Cùng khám phá", "Bạn bè"));
        page.addView(Ui.input(requireContext(), "Tìm tên hoặc @username", false), Ui.margin(-1, -2, 0, 22, 0, 18, requireContext()));

        LinearLayout tabs = Ui.row(requireContext());
        MaterialButton all = Ui.primaryButton(requireContext(), "Bạn bè  24");
        MaterialButton requests = Ui.softButton(requireContext(), "Lời mời  2");
        tabs.addView(all, new LinearLayout.LayoutParams(0, Ui.dp(requireContext(), 48), 1));
        LinearLayout.LayoutParams tabParams = new LinearLayout.LayoutParams(0, Ui.dp(requireContext(), 48), 1);
        tabParams.setMargins(Ui.dp(requireContext(), 8), 0, 0, 0);
        tabs.addView(requests, tabParams);
        page.addView(tabs);

        TextView section = Ui.text(requireContext(), "TẤT CẢ BẠN BÈ", 10, Ui.color(requireContext(), R.color.muted), true);
        page.addView(section, Ui.margin(-1, -2, 0, 26, 0, 8, requireContext()));
        LinearLayout list = new LinearLayout(requireContext());
        list.setOrientation(LinearLayout.VERTICAL);
        showFriends(list);
        page.addView(list);

        all.setOnClickListener(view -> showFriends(list));
        requests.setOnClickListener(view -> showRequests(list));
        return root;
    }

    private void showFriends(LinearLayout list) {
        list.removeAllViews();
        for (String[] friend : AppData.FRIENDS) {
            LinearLayout row = friendRow(friend[0], friend[1], friend[3]);
            TextView more = Ui.text(requireContext(), "•••", 14, Ui.color(requireContext(), R.color.muted), true);
            row.addView(more);
            list.addView(row);
        }
    }

    private void showRequests(LinearLayout list) {
        list.removeAllViews();
        for (int index = 2; index < AppData.FRIENDS.length; index++) {
            String[] friend = AppData.FRIENDS[index];
            LinearLayout row = friendRow(friend[0], friend[1], "Muốn kết nối và chia sẻ địa điểm với bạn");
            MaterialButton accept = Ui.softButton(requireContext(), "Chấp nhận");
            accept.setOnClickListener(view -> {
                row.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Đã chấp nhận lời mời", Toast.LENGTH_SHORT).show();
            });
            row.addView(accept, Ui.margin(100, 44, 8, 0, 0, 0, requireContext()));
            list.addView(row);
        }
    }

    private LinearLayout friendRow(String initials, String name, String status) {
        LinearLayout row = Ui.row(requireContext());
        row.setPadding(0, Ui.dp(requireContext(), 10), 0, Ui.dp(requireContext(), 10));
        TextView avatar = Ui.text(requireContext(), initials, 12, Ui.color(requireContext(), R.color.violet), true);
        avatar.setGravity(android.view.Gravity.CENTER);
        avatar.setBackground(Ui.background(Ui.color(requireContext(), R.color.violet_soft), 24, requireContext()));
        row.addView(avatar, Ui.margin(48, 48, 0, 0, 12, 0, requireContext()));
        LinearLayout info = new LinearLayout(requireContext());
        info.setOrientation(LinearLayout.VERTICAL);
        info.addView(Ui.text(requireContext(), name, 12, Ui.color(requireContext(), R.color.ink), true));
        info.addView(Ui.text(requireContext(), status, 9, Ui.color(requireContext(), R.color.muted), false));
        row.addView(info, new LinearLayout.LayoutParams(0, -2, 1));
        return row;
    }
}
