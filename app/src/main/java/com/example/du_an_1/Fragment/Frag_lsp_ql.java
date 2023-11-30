package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.du_an_1.Adapter.Adapter_tab_lsp;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Frag_lsp_ql extends Fragment {
    ViewPager2 pager2l;
    TabLayout tabLayoutl;
    TabLayoutMediator mediator;
    Adapter_tab_lsp adapter_tab_lsp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lsp_ql, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter_tab_lsp = new Adapter_tab_lsp(this);
        pager2l = view.findViewById(R.id.viewPage2_lsp);
        pager2l.setAdapter(adapter_tab_lsp);
        tabLayoutl = view.findViewById(R.id.tabLayout_lsp);
        mediator = new TabLayoutMediator(tabLayoutl, pager2l, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Đang Kinh Doanh");
                } else {
                    tab.setText("Ngừng Kinh Doanh");
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
