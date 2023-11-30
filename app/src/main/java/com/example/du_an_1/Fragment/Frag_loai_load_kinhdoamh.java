package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.google.android.material.tabs.TabLayout;

public class Frag_loai_load_kinhdoamh extends Fragment {
    private TabLayout tabLayout;

    public Frag_loai_load_kinhdoamh() {
    }

    public Frag_loai_load_kinhdoamh(TabLayout tabLayout){
        this.tabLayout=tabLayout;
    }

    public void setTabLayout(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_load, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationQuanLy quanLy = (NavigationQuanLy) getActivity();
        FragmentManager manager = quanLy.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout, LoailoadFrag()).commit();
    }

    private Frag_lsp_ql LoailoadFrag() {
        Frag_lsp_ql frag_lsp_ql = new Frag_lsp_ql();
        Frag_loai_load_kinhdoamh frag_load_kinhdoamh = new Frag_loai_load_kinhdoamh();
        frag_load_kinhdoamh.setTabLayout(frag_lsp_ql.getTabLayout());
        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }
        return frag_lsp_ql;
    }
}
