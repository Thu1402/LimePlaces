package com.limeplaces.app.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.limeplaces.app.R;
import com.limeplaces.app.data.AppData;
import com.limeplaces.app.model.Place;
import com.limeplaces.app.ui.Ui;

public class DetailFragment extends Fragment {
    private static final String PLACE_INDEX = "place_index";
    private boolean saved;

    public static DetailFragment newInstance(int index) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(PLACE_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle state) {
        int index = getArguments() == null ? 0 : getArguments().getInt(PLACE_INDEX, 0);
        Place place = AppData.PLACES.get(Math.min(index, AppData.PLACES.size() - 1));

        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        LinearLayout page = root.findViewById(R.id.detail_content);

        ImageView hero = new ImageView(requireContext());
        hero.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(this).load(place.imageUrl).centerCrop().into(hero);
        page.addView(hero, new LinearLayout.LayoutParams(-1, Ui.dp(requireContext(), 340)));

        LinearLayout body = new LinearLayout(requireContext());
        body.setOrientation(LinearLayout.VERTICAL);
        body.setPadding(Ui.dp(requireContext(), 20), Ui.dp(requireContext(), 22), Ui.dp(requireContext(), 20), Ui.dp(requireContext(), 20));
        TextView back = Ui.text(requireContext(), "‹  Quay lại", 11, Ui.color(requireContext(), R.color.violet), true);
        back.setOnClickListener(view -> requireActivity().onBackPressed());
        body.addView(back);
        body.addView(Ui.text(requireContext(), place.category.toUpperCase(), 10, Ui.color(requireContext(), R.color.violet), true), Ui.margin(-1, -2, 0, 20, 0, 0, requireContext()));
        body.addView(Ui.text(requireContext(), place.name, 28, Ui.color(requireContext(), R.color.ink), true), Ui.margin(-1, -2, 0, 5, 0, 0, requireContext()));
        body.addView(Ui.text(requireContext(), "⌖  " + place.address + "  ·  " + place.distance, 10, Ui.color(requireContext(), R.color.muted), false), Ui.margin(-1, -2, 0, 7, 0, 18, requireContext()));

        LinearLayout actions = Ui.row(requireContext());
        actions.setGravity(Gravity.CENTER);
        MaterialButton save = actionButton("Lưu");
        save.setOnClickListener(view -> {
            saved = !saved;
            save.setText(saved ? "Đã lưu" : "Lưu");
            Toast.makeText(requireContext(), saved ? "Đã lưu địa điểm" : "Đã bỏ lưu", Toast.LENGTH_SHORT).show();
        });
        MaterialButton share = actionButton("Chia sẻ");
        share.setOnClickListener(view -> showShareDialog(place));
        MaterialButton route = actionButton("Dẫn đường");
        route.setOnClickListener(view -> {
            Uri location = Uri.parse("geo:0,0?q=" + Uri.encode(place.name + ", " + place.address));
            startActivity(new Intent(Intent.ACTION_VIEW, location));
        });
        MaterialButton edit = actionButton("Chỉnh sửa");
        edit.setOnClickListener(view -> Toast.makeText(requireContext(), "Bạn có quyền chỉnh sửa địa điểm này", Toast.LENGTH_SHORT).show());
        actions.addView(save, weighted());
        actions.addView(share, weighted());
        actions.addView(route, weighted());
        actions.addView(edit, weighted());
        body.addView(actions);

        body.addView(Ui.text(requireContext(), "Về địa điểm này", 17, Ui.color(requireContext(), R.color.ink), true), Ui.margin(-1, -2, 0, 28, 0, 0, requireContext()));
        body.addView(Ui.text(requireContext(), "Một khoảng hồ xanh trong veo ẩn mình giữa đồi thông, lý tưởng để đi dạo, ngắm hoàng hôn và dành một buổi chiều thật chậm.", 12, Ui.color(requireContext(), R.color.muted), false), Ui.margin(-1, -2, 0, 9, 0, 0, requireContext()));

        TextView map = Ui.text(requireContext(), "VỊ TRÍ\n\nĐồi Thông, Đà Lạt\nChạm để mở bản đồ", 12, Ui.color(requireContext(), R.color.ink), true);
        map.setGravity(Gravity.CENTER);
        map.setBackground(Ui.background(Ui.color(requireContext(), R.color.mint), 20, requireContext()));
        map.setOnClickListener(view -> route.performClick());
        body.addView(map, Ui.margin(-1, 150, 0, 24, 0, 0, requireContext()));
        page.addView(body);
        return root;
    }

    private MaterialButton actionButton(String label) {
        MaterialButton button = Ui.softButton(requireContext(), label);
        button.setInsetTop(2);
        button.setMinHeight(2);
        return button;
    }

    private LinearLayout.LayoutParams weighted() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, Ui.dp(requireContext(), 48), 1);
        params.setMargins(Ui.dp(requireContext(), 3), 0, Ui.dp(requireContext(), 3), 0);
        return params;
    }

    private void showShareDialog(Place place) {
        String[] people = {"Minh Anh", "Quang Huy", "Thảo Vy", "Gia Linh"};
        boolean[] checked = {true, false, false, false};
        new AlertDialog.Builder(requireContext())
            .setTitle("Chia sẻ " + place.name)
            .setMultiChoiceItems(people, checked, (dialog, which, isChecked) -> checked[which] = isChecked)
            .setPositiveButton("Chia sẻ", (dialog, which) ->
                Toast.makeText(requireContext(), "Đã gửi địa điểm cho bạn bè", Toast.LENGTH_SHORT).show())
            .setNegativeButton("Hủy", null)
            .show();
    }
}
