package com.example.du_an_1.Adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.Fragment.Frag_load;
import com.example.du_an_1.Fragment.QuanLySanPham_KD_Fragment;
import com.example.du_an_1.Fragment.QuanLySanPham_NKD_Fragment;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FoodAdapter_ql extends RecyclerView.Adapter<FoodAdapter_ql.ViewHolder>{
    ArrayList<Food> food;
    Context context;
    int maLoai;
    List<Food> list;
    NavigationQuanLy navigationQuanLy;
    Food_DAO food_dao;
    ArrayList<Type_Of_Food> listLoaiFood;
    LoaiFood_SpinerAdapter spinnerAdapter;
    Type_Of_Food_DAO type_of_food_dao;
    QuanLySanPham_NKD_Fragment quanLySanPham_nkd_fragment;
    QuanLySanPham_KD_Fragment quanLySanPham_kd_fragment;
    private ViewHolder currentViewHolder;
    int trangthai = 0;
    public FoodAdapter_ql(Context context, List<Food> list,  int trangthai) {
        this.context = context;
        this.list = list;
        navigationQuanLy = (NavigationQuanLy) context;
        this.trangthai = trangthai;
        food_dao = new Food_DAO(context);
        quanLySanPham_nkd_fragment = new QuanLySanPham_NKD_Fragment();
        quanLySanPham_kd_fragment = new QuanLySanPham_KD_Fragment();
    }

    @NonNull
    @Override
    public FoodAdapter_ql.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_food, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter_ql.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_ma_sp.setText(list.get(position).getMaFood());
        holder.tv_loai_sp.setText("Mã loại: "+list.get(position).getMaLoai());
        holder.tv_title.setText(list.get(position).getTenFood());
        holder.tv_fee.setText(list.get(position).getGiaFood() + "");
        holder.img_pic.setImageURI(list.get(position).hienthi(context));
        currentViewHolder = holder; // Lưu trữ holder hiện tại
        // Lưu trữ hình ảnh hiện tại của đối tượng đang được hiển thị
        byte[] currentImage = list.get(position).getHinhAnh();
        holder.setCurrentImage(currentImage);

        if (trangthai ==1){
            holder.tv_btn_delete.setText("Delete");
            holder.tv_btn_update.setText("Restore");
        }else {
            holder.tv_btn_delete.setText("Hide");
            holder.tv_btn_update.setText("Update");
        }

        holder.tv_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    xoa();
                } else {
                    ngungKD();
                }
            }
            private void xoa() {
                Food DTO_sp = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Xác nhận muốn xóa vĩnh viễn sản phẩm này");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (food_dao.Deletevinhvien(DTO_sp) > 0) {
                            locSP(trangthai);
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }

            private void ngungKD() {
                Food DTO_sp = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Xác nhận muốn ngừng kinh doanh sản phẩm này");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (food_dao.DeleteRow(DTO_sp) > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.tv_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    khoiphuc();
                } else {
                    update(position);
                }
            }

            private void khoiphuc() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn khôi phục sản phẩm này ?");
                builder.setTitle("Thông báo");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Food sp = list.get(position);
                        if (food_dao.KhoiphucRow(sp) > 0) {
                            Toast.makeText(context, "Khôi phục thành công", Toast.LENGTH_SHORT).show();
                            locSP(trangthai);
                        } else {
                            Toast.makeText(context, "Khôi phục thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    public void update(int position) {
        Food DTO_sp = list.get(position);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_them_sp, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        // Ánh xạ
        TextView title = view.findViewById(R.id.tv_tilte_sp);
        title.setText("Update Sản phẩm");

        EditText ed_idSp = view.findViewById(R.id.txtIdSanPhamThem);
        EditText ed_tenSp = view.findViewById(R.id.txtTenSanPhamThem);
        Spinner sp_loaiSp = view.findViewById(R.id.spLoaiThem);
        Button btn_themsp = view.findViewById(R.id.btnSaveThem);
        EditText ed_giaSp = view.findViewById(R.id.txtGiaSp);
        EditText ed_mota = view.findViewById(R.id.txtMota);
        Button btn_themAnh = view.findViewById(R.id.btnlayanh);
        Button back = view.findViewById(R.id.btnCancelThem);

        final Type_Of_Food[] getID = {new Type_Of_Food()};
        getID[0].setId(list.get(position).getMaLoai());
        Calendar lich = Calendar.getInstance();
        int ngay = lich.get(Calendar.DAY_OF_MONTH);
        int thang = lich.get(Calendar.MONTH);
        int nam = lich.get(Calendar.YEAR);

        ed_idSp.setText(DTO_sp.getMaFood());
        ed_tenSp.setText(DTO_sp.getTenFood());
        ed_giaSp.setText(DTO_sp.getGiaFood()+"");
        ed_mota.setText(DTO_sp.getMoTa());
        sp_loaiSp.setId(DTO_sp.getMaLoai());
        Log.e(TAG, "update: "+ed_tenSp.getText().toString() );
        Log.e(TAG, "update: "+ DTO_sp.getMaFood());

        btn_themAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationQuanLy.importAnh();
            }
        });



        listLoaiFood = new ArrayList<Type_Of_Food>();
        type_of_food_dao = new Type_Of_Food_DAO(context);
        listLoaiFood = (ArrayList<Type_Of_Food>) type_of_food_dao.getAll();

        spinnerAdapter = new LoaiFood_SpinerAdapter(context, listLoaiFood);

        sp_loaiSp.setAdapter(spinnerAdapter);
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
                Toast.makeText(dialog.getContext(), "Chọn " + listLoaiFood.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_themsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_idSp.getText().toString().isEmpty() && !ed_idSp.getText().toString().isEmpty() && !ed_tenSp.getText().toString().isEmpty() && !ed_giaSp.getText().toString().isEmpty() && !ed_mota.getText().toString().isEmpty()) {
                    DTO_sp.setMaFood(ed_idSp.getText().toString());
                    DTO_sp.setMoTa(ed_mota.getText().toString());
                    DTO_sp.setTenFood(ed_tenSp.getText().toString());
                    DTO_sp.setMaLoai(sp_loaiSp.getSelectedItemPosition());
                    DTO_sp.setGiaFood(Integer.parseInt(ed_giaSp.getText().toString()));
                    // Kiểm tra xem ảnh có thay đổi không
                    if (navigationQuanLy.getAnh() != null) {
                        DTO_sp.setHinhAnh(navigationQuanLy.getAnh());
                    } else {
                        // Nếu không có sự thay đổi, sử dụng ảnh hiện tại
                        DTO_sp.setHinhAnh(currentViewHolder.getCurrentImage());
                    }

                    if (food_dao.Update(DTO_sp) > 0) {
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();

                        FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.framelayout, new Frag_load()).commit();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(context, "Sửa  thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(dialog.getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_fee, tv_btn_delete, tv_ma_sp, tv_btn_update, tv_loai_sp;
        ImageView img_pic;
        private byte[] currentImage;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ma_sp = itemView.findViewById(R.id.tv_ma_sp);
            tv_title = itemView.findViewById(R.id.tv_title_food);
            tv_fee = itemView.findViewById(R.id.tv_fee);
            tv_btn_delete = itemView.findViewById(R.id.tv_btn_delete);
            tv_btn_update = itemView.findViewById(R.id.tv_btn_update);
            tv_loai_sp = itemView.findViewById(R.id.tv_loai_sp);
            img_pic = itemView.findViewById(R.id.img_pic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
        public void setCurrentImage(byte[] currentImage) {
            this.currentImage = currentImage;
        }

        public byte[] getCurrentImage() {
            return currentImage;
        }


    }
    public void locSP(int check) {
        if (check == 0) {
            list.clear();
            list.addAll(food_dao.getAll(trangthai));
            notifyDataSetChanged();
        } else if (check == 1) {
            list.clear();
            list.addAll(food_dao.getAll(trangthai));
            notifyDataSetChanged();
        }
    }
}
