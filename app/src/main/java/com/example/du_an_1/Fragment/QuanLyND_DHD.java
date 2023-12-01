package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.Adapter_ND_QL;
import com.example.du_an_1.Adapter.LoaiFood_SpinerAdapter;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySanPham_KD_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyND_DHD extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyND_DHD() {
        // Required empty public constructor
    }

    FloatingActionButton fab;
    NavigationQuanLy navigationQuanLy;
    List<User> list;
    User_DAO user_dao;
    Dialog dialog;

    User user;
    RecyclerView recyclerView;
    Adapter_ND_QL adapter_nd_ql;
    Uri selectedImage;
    LoaiFood_SpinerAdapter spinnerAdapter;

    ArrayList<Type_Of_Food> listLoaiFood;
    Type_Of_Food_DAO type_of_food_dao;

    RecyclerView.LayoutManager layoutManager;
    Calendar lich = Calendar.getInstance();
    int maLoai;
    public static QuanLyND_DHD newInstance(String param1, String param2) {
        QuanLyND_DHD fragment = new QuanLyND_DHD();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        user_dao = new User_DAO(dialog.getContext());
        list = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taoDoiTuong();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_n_d__d_h_d, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton floatThem = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.rcv_ND);
        navigationQuanLy = (NavigationQuanLy) getContext();
        list = user_dao.getAllA(0);
        adapter_nd_ql = new Adapter_ND_QL(view.getContext(), list,0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_nd_ql);
        adapter_nd_ql.notifyDataSetChanged();
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gán ảnh
                showDialogAdd(getActivity());
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    String maDN, ten, sdt, mk;


    private void showDialogAdd(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nd);
        //ánh xạ
        EditText ed_ten = dialog.findViewById(R.id.txt_ten_ND);
        EditText ed_sdt = dialog.findViewById(R.id.txt_SDT);
        EditText ed_maDN = dialog.findViewById(R.id.txt_MaDN);
        EditText ed_mk = dialog.findViewById(R.id.txt_pass);
        EditText ed_rmk = dialog.findViewById(R.id.txt_cf_pass);

        Button btn_them = dialog.findViewById(R.id.btnSaveThem);


        final Type_Of_Food[] getID = {new Type_Of_Food()};
//        int ngay = lich.get(Calendar.DAY_OF_MONTH);
//        int thang = lich.get(Calendar.MONTH);
//        int nam = lich.get(Calendar.YEAR);
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_ten.getText().toString().isEmpty() && !ed_sdt.getText().toString().isEmpty() && !ed_maDN.getText().toString().isEmpty() && !ed_mk.getText().toString().isEmpty() && !ed_rmk.getText().toString().isEmpty()) {
                    maDN = ed_maDN.getText().toString();
                    ten = ed_ten.getText().toString();
                    sdt = ed_sdt.getText().toString();
                    mk = ed_mk.getText().toString();
                    openDialog_tb(dialog);
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin và kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.findViewById(R.id.btnCancelThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public void openDialog_tb(Dialog dialog1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    if (maDN != "") {
                        if (themSP() > 0) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Mã Đăng nhập đã tồn tại vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Không để trống mã sản phẩm", Toast.LENGTH_SHORT).show();
                    }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void loadData() {
        list.clear();
        list.addAll(user_dao.getAllA(0));
        adapter_nd_ql.notifyDataSetChanged();
    }
    public long themSP() {
        long a = 0;
        user = new User();
        user.setMaDN(maDN);
        user.setsDT(sdt);
        user.setHoTen(ten);
        user.setMatKhau(mk);
        user.setVaiTro(0);
        int check = 1;
        a = user_dao.insert(user);
        list.clear();
        list.addAll(user_dao.getAllA(0));
        adapter_nd_ql.notifyDataSetChanged();
        return a;
    }

    public List<User> loc(List<User> list) {
        List<User> listCheck = new ArrayList<>();
        for (User sp : list) {
            if (sp.getVaiTro() == 0) {
                listCheck.add(sp);
            }
        }
        return listCheck;
    }
}