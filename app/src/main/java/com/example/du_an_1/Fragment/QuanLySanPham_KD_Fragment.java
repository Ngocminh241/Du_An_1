package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.FoodAdapter_ql;
import com.example.du_an_1.Adapter.LoaiFood_SpinerAdapter;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySanPham_KD_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLySanPham_KD_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLySanPham_KD_Fragment() {
        // Required empty public constructor
    }

    FloatingActionButton fab;
    NavigationQuanLy navigationQuanLy;
    List<Food> list;
    Food_DAO dao_food;
    Dialog dialog;

    Food food;
    RecyclerView recyclerView;
    FoodAdapter_ql adapter_food;
    Uri selectedImage;
    LoaiFood_SpinerAdapter spinnerAdapter;

    ArrayList<Type_Of_Food> listLoaiFood;
    Type_Of_Food_DAO type_of_food_dao;

    RecyclerView.LayoutManager layoutManager;
    Calendar lich = Calendar.getInstance();
    int maLoai;
    public static QuanLySanPham_KD_Fragment newInstance(String param1, String param2) {
        QuanLySanPham_KD_Fragment fragment = new QuanLySanPham_KD_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        dao_food = new Food_DAO(dialog.getContext());
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
        return inflater.inflate(R.layout.fragment_quan_ly_san_pham, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton floatThem = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.rcv_SP);
        navigationQuanLy = (NavigationQuanLy) getContext();
        list = dao_food.getAll(0);
        adapter_food = new FoodAdapter_ql(view.getContext(), list,0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_food);
        adapter_food.notifyDataSetChanged();
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gán ảnh
                int drawableResourceId = R.drawable.anh_spl;
                Uri drawableUri = new Uri.Builder()
                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                        .authority(getResources().getResourcePackageName(drawableResourceId))
                        .appendPath(getResources().getResourceTypeName(drawableResourceId))
                        .appendPath(getResources().getResourceEntryName(drawableResourceId))
                        .build();
                selectedImage = drawableUri;
                showDialogAdd(getActivity());
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    String id_sp;
    int maloai, giaSp;
    String tensp;
    byte[] anh;
    String tenLoai, moTa;;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == navigationQuanLy.RESULT_OK && data != null) {
            selectedImage = data.getData();
            Toast.makeText(navigationQuanLy, "Chọn ảnh thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void showDialogAdd(final Context context) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_sp);
        //ánh xạ
        EditText ed_idSp = dialog.findViewById(R.id.txtIdSanPhamThem);
        Spinner sp_loaiSp = dialog.findViewById(R.id.spLoaiThem);

        EditText ed_tenSp = dialog.findViewById(R.id.txtTenSanPhamThem);
        EditText ed_giaSp = dialog.findViewById(R.id.txtGiaSp);
        EditText ed_mota = dialog.findViewById(R.id.txtMota);
        Button btn_themsp = dialog.findViewById(R.id.btnSaveThem);
        Button btn_themAnh = dialog.findViewById(R.id.btnlayanh);


        listLoaiFood = new ArrayList<Type_Of_Food>();
        type_of_food_dao = new Type_Of_Food_DAO(context);
        listLoaiFood = (ArrayList<Type_Of_Food>) type_of_food_dao.getAll();

        spinnerAdapter = new LoaiFood_SpinerAdapter(context, listLoaiFood);

        sp_loaiSp.setAdapter(spinnerAdapter);


        final Type_Of_Food[] getID = {new Type_Of_Food()};
//        int ngay = lich.get(Calendar.DAY_OF_MONTH);
//        int thang = lich.get(Calendar.MONTH);
//        int nam = lich.get(Calendar.YEAR);
        btn_themAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
        sp_loaiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai = listLoaiFood.get(position).getId();
                Toast.makeText(dialog.getContext(), "Chọn " + listLoaiFood.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_idSp.getText().toString().isEmpty() && !ed_idSp.getText().toString().isEmpty() && !ed_tenSp.getText().toString().isEmpty() && !ed_giaSp.getText().toString().isEmpty() && !ed_mota.getText().toString().isEmpty()) {
                    id_sp = ed_idSp.getText().toString();
                    maloai = sp_loaiSp.getSelectedItemPosition();
                    tensp = ed_tenSp.getText().toString();
                    giaSp = Integer.parseInt(ed_giaSp.getText().toString());
                    moTa = ed_mota.getText().toString();
                    anh = getAnh(selectedImage);
                    openDialog_tb(dialog);
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
                if (checkten() == 0) {
                    if (id_sp != "") {
                if (themSP() > 0) {
                    dialog.dismiss();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    dialog1.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Mã sản phẩm đã tồn tại vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Không để trống mã sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Tên sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
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
    public byte[] getAnh(Uri selectedImage) {
        // Max allowed size in bytes
        int maxSize = 1024 * 1024; // 1MB
        try {
            InputStream inputStream = getContext().getContentResolver().openInputStream(selectedImage);
            // Đọc ảnh vào một đối tượng Bitmap
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            // Tính toán tỷ lệ nén cần áp dụng để đảm bảo kích thước không vượt quá maxSize
            int scale = 1;
            while ((options.outWidth * options.outHeight) * (1 / Math.pow(scale, 2)) > maxSize) {
                scale++;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            inputStream.close();
            // Đọc ảnh lại với tỷ lệ nén
            inputStream = getContext().getContentResolver().openInputStream(selectedImage);
            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
            // Chuyển đổi Bitmap thành byte array
            ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteBuffer); // Thay đổi định dạng và chất lượng nén tùy theo nhu cầu
            byte[] imageData = byteBuffer.toByteArray();
            return imageData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void loadData() {
        list.clear();
        list.addAll(dao_food.getAll(0));
        adapter_food.notifyDataSetChanged();
    }
    public long themSP() {
        long a = 0;
        food = new Food();
        food.setMaFood(id_sp);
        food.setMaLoai(maloai);
        food.setTenFood(tensp);
        food.setGiaFood(giaSp);
        food.setMoTa(moTa);
        food.setHinhAnh(anh);
        int maND = 1;
        checkten();
        a = dao_food.ADDSanPham(food);
        list.clear();
        list.addAll(dao_food.getAll(0));
        adapter_food.notifyDataSetChanged();
        return a;
    }
    private int checkten() {
        int a = 0;
        for (Food sp : list) {
            if (tensp.equals(sp.getTenFood())) {
                return 2;
            }
        }
        return a;
    }
    public List<Food> loc(List<Food> list) {
        List<Food> listCheck = new ArrayList<>();
        for (Food sp : list) {
            if (sp.getTrangthai() == 0) {
                listCheck.add(sp);
            }
        }
        return listCheck;
    }
}