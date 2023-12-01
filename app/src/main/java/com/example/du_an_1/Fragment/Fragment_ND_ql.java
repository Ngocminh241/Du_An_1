package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.du_an_1.Adapter.Adapter_tab_ND;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment_ND_ql extends Fragment {
    ViewPager2 pager2l;
    TabLayout tabLayoutl;
    TabLayoutMediator mediator;
    Adapter_tab_ND adapter_tab_nd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__n_d_ql, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_tab_nd = new Adapter_tab_ND(this);
        pager2l = view.findViewById(R.id.viewPage2_ND);
        pager2l.setAdapter(adapter_tab_nd);
        tabLayoutl = view.findViewById(R.id.tabLayout_ND);
        mediator = new TabLayoutMediator(tabLayoutl, pager2l, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang Hoạt Động");
                } else {
                    tab.setText("Ngừng Ngừng Hoạt Động");
                }
            }
        });

        mediator.attach();
//        tabLayoutl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                adapter_tab_lsp.LoailoadData();
//                if (tab.getPosition() == 0) {
//                    FragmentManager manager = getActivity().getSupportFragmentManager();
//                    manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });


    }

    public TabLayout getTabLayout() {
        return tabLayoutl;
    }
}
