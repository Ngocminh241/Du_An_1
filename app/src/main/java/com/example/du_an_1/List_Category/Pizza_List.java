package com.example.du_an_1.List_Category;

import static android.widget.Toast.LENGTH_SHORT;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.CategoryAdapter;
import com.example.du_an_1.Adapter.FoodAdapter;
import com.example.du_an_1.Adapter.LoaiFood_SpinerAdapter;
import com.example.du_an_1.Cart_Activity;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.Domain.CategoryDomain;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Pizza_List extends AppCompatActivity {
    private RecyclerView recyclerViewCategoryList, rcv_pizza;
    private RecyclerView.Adapter adapter1, adapter_2;
    TextView tv_title_food;
    FoodAdapter adapter_sp;
    List<Food> list;
    Dialog dialog;
    FloatingActionButton floatingCart, floatingAdd;
    LoaiFood_SpinerAdapter spinnerAdapter;
    int maLoai;
    ArrayList<Type_Of_Food> listFood;
    String id_sp;
    int maloai, giaSp;
    String tensp;
    byte[] anh;
    String tenLoai, moTa;;
    Uri selectedImage;
    Food food;
    Food_DAO food_dao;
    ArrayList<Type_Of_Food> listLoaiFood;
    Type_Of_Food_DAO type_of_food_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
        floatingAdd = findViewById(R.id.float_add_btn);
        tv_title_food = findViewById(R.id.tv_title_food);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        tv_title_food.setText(title);

        recyclerViewPizza();
        recyclerViewCategory();
        bottomNavigation();

        floatingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int drawableResourceId = R.drawable.anh_spl;
//                Uri drawableUri = new Uri.Builder()
//                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
//                        .authority(getResources().getResourcePackageName(drawableResourceId))
//                        .appendPath(getResources().getResourceTypeName(drawableResourceId))
//                        .appendPath(getResources().getResourceEntryName(drawableResourceId))
//                        .build();
//                selectedImage = drawableUri;
                showDialogAdd();
            }
        });
    }
    private void bottomNavigation(){
        floatingCart = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);

        floatingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pizza_List.this, Cart_Activity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pizza_List.this, MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recycler_categories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza", "cat_1"));
        categoryDomains.add(new CategoryDomain("Burger", "cat_2"));
        categoryDomains.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryDomains.add(new CategoryDomain("Drink", "cat_4"));
        categoryDomains.add(new CategoryDomain("Donut", "cat_5"));

        adapter1 = new CategoryAdapter(categoryDomains);
        recyclerViewCategoryList.setAdapter(adapter1);
        adapter1.getItemViewType(R.id.categoryPic);

    }

    private void recyclerViewPizza(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rcv_pizza = findViewById(R.id.rcv_pizza);
        rcv_pizza.setLayoutManager(linearLayoutManager);
        ArrayList<Food> food = new ArrayList<>();
        food.add(new Food("F002",1,"Pizza sauce", 970,null,"abcd"));
        food.add(new Food("F003",2,"Pizza sauce", 3970,null,"abcd"));
        food.add(new Food("F004",1,"Pizza sauce_2", 1970,null,"abcd"));
        adapter_2 = new FoodAdapter(food);
        rcv_pizza.setAdapter(adapter_2);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == this.RESULT_OK && data != null) {
            selectedImage = data.getData();
            Toast.makeText(this, "Chọn ảnh thành công", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDialogAdd() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        LayoutInflater inflater = ((Activity) this).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_them_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
//        dialog.setContentView(R.layout.dialog_them_sp);
        //ánh xạ
        EditText ed_idSp = view.findViewById(R.id.txtIdSanPhamThem);
        Spinner sp_loaiSp = view.findViewById(R.id.spLoaiThem);
        EditText ed_tenSp = view.findViewById(R.id.txtTenSanPhamThem);
        EditText ed_giaSp = view.findViewById(R.id.txtGiaSp);
        EditText ed_mota = view.findViewById(R.id.txtMota);
        Button btn_themsp = view.findViewById(R.id.btnSaveThem);
        Button btn_themAnh = view.findViewById(R.id.btnlayanh);
        final Type_Of_Food[] getID = {new Type_Of_Food()};
//        int ngay = lich.get(Calendar.DAY_OF_MONTH);
//        int thang = lich.get(Calendar.MONTH);
//        int nam = lich.get(Calendar.YEAR);
        listFood = new ArrayList<Type_Of_Food>();
        type_of_food_dao = new Type_Of_Food_DAO(Pizza_List.this);
        listLoaiFood = (ArrayList<Type_Of_Food>) type_of_food_dao.getAll();

        spinnerAdapter = new LoaiFood_SpinerAdapter(this, listLoaiFood);
        sp_loaiSp.setAdapter(spinnerAdapter);
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
//                final int[] i = {0};
//                if (i[0] == 0) {
//                    getID[0] = list.get(position);
//                    ed_loaiSp.setText(getID[0].getTenLoai());
//                    dialogLoaiSP.dismiss();
//                }
                maLoai = listLoaiFood.get(position).getId();
                Toast.makeText(Pizza_List.this, "Chọn " + listLoaiFood.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        ed_loaiSp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final int[] i = {0};
//                AlertDialog.Builder builder = new AlertDialog.Builder(dialog.getContext());
//                LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
//                View view = inflater.inflate(R.layout.dialog_them_loaihang, null);
//                builder.setView(view);
//                Dialog dialogLoaiSP = builder.create();
//                dialogLoaiSP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialogLoaiSP.show();
//                ListView listLoaiHang = view.findViewById(R.id.lis_loaiSP);
//                EditText themLoai = view.findViewById(R.id.txt_themLoai);
//                ImageButton add = view.findViewById(R.id.ibtn_addLoai);
//                loaiHang = new DAO_LoaiHang(getContext());
//                listHang = loaiHang.getAll();
//                adapterLoaiSP = new Adapter_loaiSP(getContext(), listHang);
////                layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
////                listLoaiHang.setLayoutManager(layoutManager);
//                listLoaiHang.setAdapter(adapterLoaiSP);
//                themLoai.setVisibility(View.GONE);
//                add.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        themLoai.setVisibility(View.VISIBLE);
//                        add.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (!themLoai.getText().toString().isEmpty()) {
//                                    dto_loaiHang = new DTO_LoaiHang();
//                                    dto_loaiHang.setTenLoai(themLoai.getText().toString());
//                                    if (loaiHang.AddRow(dto_loaiHang) > 0) {
//                                        listHang.clear();
//                                        listHang.addAll(loaiHang.getAll());
//                                        adapterLoaiSP.notifyDataSetChanged();
//                                        Toast.makeText(getContext(), "Thêm thành công", LENGTH_SHORT).show();
//                                        dialogLoaiSP.dismiss();
//                                        getID[0] = listHang.get(listHang.size() - 1);
//                                        ed_loaiSp.setText(getID[0].getTenLoai());
//                                    } else {
//                                        Toast.makeText(getContext(), "Thêm thất bại", LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Toast.makeText(getContext(), "Vui lòng nhập đủ thông tin", LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                });
//                // Xử lý sự kiện click của người dùng
//                listLoaiHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        if (i[0] == 0) {
//                            getID[0] = listHang.get(position);
//                            ed_loaiSp.setText(getID[0].getTenLoai());
//                            dialogLoaiSP.dismiss();
//                        }
//                    }
//                });
//            }
//        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_idSp.getText().toString().isEmpty() && !ed_idSp.getText().toString().isEmpty() && !ed_tenSp.getText().toString().isEmpty() && !ed_giaSp.getText().toString().isEmpty() && !ed_mota.getText().toString().isEmpty()) {
                    id_sp = ed_idSp.getText().toString();
                    maloai = getID[0].getId();
                    tensp = ed_tenSp.getText().toString();
                    giaSp = Integer.parseInt(ed_giaSp.getText().toString());
                    moTa = ed_mota.getText().toString();
                    anh = getAnh(selectedImage);
                    openDialog_tb(dialog);
                } else {
                    Toast.makeText(Pizza_List.this, "Vui lòng nhập đầy đủ thông tin", LENGTH_SHORT).show();
                }
            }
        });
        view.findViewById(R.id.btnCancelThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public byte[] getAnh(Uri selectedImage) {
        // Max allowed size in bytes
        int maxSize = 1024 * 1024; // 1MB
        try {
            InputStream inputStream = this.getContentResolver().openInputStream(selectedImage);
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
            inputStream = this.getContentResolver().openInputStream(selectedImage);
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
    public void openDialog_tb(Dialog dialog1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Pizza_List.this);
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                if (checkten() == 0) {
//                    if (id_sp != "") {
                        if (themSP() > 0) {
                            dialog.dismiss();
                            Toast.makeText(Pizza_List.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            dialog1.dismiss();
//                        } else {
//                            Toast.makeText(Pizza_List.this, "Mã sản phẩm đã tồn tại vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(Pizza_List.this, "Không để trống mã sản phẩm", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Pizza_List.this, "Tên sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
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

    private int checkten() {
        int a = 0;
        for (Food sp : list) {
            if (tensp.equals(sp.getTenFood())) {
                return 2;
            }
        }
        return a;
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
//        checkten();
        a = food_dao.ADDSanPham(food);
        list.clear();
        list.addAll(food_dao.getAll());
        adapter_sp.notifyDataSetChanged();
        return a;
    }
}