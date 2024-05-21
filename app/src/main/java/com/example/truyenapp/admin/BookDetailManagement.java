package com.example.truyenapp.admin;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.ChapterAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.ChapterResponse;
import com.example.truyenapp.utils.AuthenticationManager;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.adapter.admin.QLChapterAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailManagement extends AppCompatActivity implements View.OnClickListener {

    ImageView thumbnail;
    TextView bookId;
    EditText bookAuthor, bookDescription, bookCategory, edt_linkanh, bookStatus, bookName;
    Button editBtn, saveBtn, cancelEditBtn, deleteStoryBtn;
    int id;
    private RecyclerView rcv;
    private QLChapterAdapter adapter;
    private BookAPI bookAPI;
    private UserAPI userAPI;
    private ChapterAPI chapterAPI;
    private BookResponse bookResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_detail_management);
        connectAPI();

        JWTToken token = SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class);
        if (AuthenticationManager.isAdmin(token, userAPI)) {
            Toast.makeText(this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
            finish();
        }

        init();
        Intent intent = getIntent();
        id = intent.getIntExtra("bookId", 1);
        setEnable(false);
        setOnClickListener();
        getBook();
    }

    private void connectAPI() {
        userAPI = RetrofitClient.getInstance(this).create(UserAPI.class);
        bookAPI = RetrofitClient.getInstance(this).create(BookAPI.class);
        chapterAPI = RetrofitClient.getInstance(this).create(ChapterAPI.class);
    }

    // This method is used to fetch a book's details from the server
    private void getBook() {
        // Call the getBook method from the bookAPI interface, passing the book's id
        bookAPI.getBook(id).enqueue(new Callback<BookResponse>() {
            // This method is called when the server responds to our request
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // If the response is successful, store the book's details in the bookResponse object
                    bookResponse = response.body();
                    // Set the data in the UI
                    setData();
                    // Set up the RecyclerView for the chapters
                    recyclerViewQLChapter();
                } else {
                    // If the response is not successful, show a toast message indicating that the book's details could not be fetched
                    Toast.makeText(BookDetailManagement.this, "Failed to get book", Toast.LENGTH_SHORT).show();
                }
            }

            // This method is called when the request to the server fails
            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                // Show a toast message indicating that the request failed
                Toast.makeText(BookDetailManagement.this, "Failed to get book", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // This method is used to set up the RecyclerView for displaying chapters of a book
    private void recyclerViewQLChapter() {
        // Create a new LinearLayoutManager
        // This will position the items in your RecyclerView vertically
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        // Set the RecyclerView's layout manager to the LinearLayoutManager we just created
        rcv.setLayoutManager(linearLayoutManager);

        // Call the getChaptersByBook method from the chapterAPI interface, passing the book's id
        // This method is asynchronous and uses Retrofit's enqueue method to send the request
        chapterAPI.getChaptersByBook(id).enqueue(new Callback<List<ChapterResponse>>() {
            // This method is called when the server responds to our request
            @Override
            public void onResponse(Call<List<ChapterResponse>> call, Response<List<ChapterResponse>> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // If the response is successful, store the list of chapters in the list object
                    List<ChapterResponse> list = response.body();

                    // Create a new QLChapterAdapter with the list of chapters
                    // This adapter will be used to display each chapter in the RecyclerView
                    adapter = new QLChapterAdapter(BookDetailManagement.this, list);

                    // Set the RecyclerView's adapter to the QLChapterAdapter we just created
                    rcv.setAdapter(adapter);
                } else {
                    // If the response is not successful, show a toast message indicating that the chapters could not be fetched
                    Toast.makeText(BookDetailManagement.this, "Failed to get chapters", Toast.LENGTH_SHORT).show();
                }
            }

            // This method is called when the request to the server fails
            @Override
            public void onFailure(Call<List<ChapterResponse>> call, Throwable t) {
                // Show a toast message indicating that the request failed
                Toast.makeText(BookDetailManagement.this, "Failed to get chapters", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        Glide.with(this).load(bookResponse.getThumbnail()).into(thumbnail);
        bookName.setText(bookResponse.getName());
        bookAuthor.setText(bookResponse.getAuthor());
        bookDescription.setText(bookResponse.getDescription());
        bookId.setText(String.valueOf(bookResponse.getId()));
        edt_linkanh.setText(bookResponse.getThumbnail());
        bookStatus.setText(bookResponse.getStatus().equals(SystemConstant.STATUS_FULL) ? "Hoàn thành" : "Đang cập nhật");

        StringBuilder categories = new StringBuilder();
        for (String category : bookResponse.getCategoryNames()) {
            categories.append(category).append(", ");
        }
        categories.deleteCharAt(categories.length() - 2);
        bookCategory.setText(categories.toString());
    }

    private void setOnClickListener() {
        editBtn.setOnClickListener(this);
        cancelEditBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        deleteStoryBtn.setOnClickListener(this);

    }

    private void init() {
        thumbnail = findViewById(R.id.img_qlt);
        editBtn = findViewById(R.id.bt_chinhsuatruyen);
        bookId = findViewById(R.id.tv_qlt_id);
        bookAuthor = findViewById(R.id.edt_qlt_tacgia);
        bookDescription = findViewById(R.id.edt_qlt_mota);
        bookCategory = findViewById(R.id.edt_qlt_theloai);
        edt_linkanh = findViewById(R.id.edt_qlt_linkanh);
        bookStatus = findViewById(R.id.edt_qlt_trangthai);
        bookName = findViewById(R.id.edt_qlt_tentruyen);
        saveBtn = findViewById(R.id.bt_xacnhantruyen);
        cancelEditBtn = findViewById(R.id.bt_huychinhsuatruyen);
        rcv = findViewById(R.id.rcv_quanlychapter);
        deleteStoryBtn = findViewById(R.id.bt_deleteStory);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_chinhsuatruyen:
                setEnable(true);
                break;
            case R.id.bt_deleteStory:
                showDeleteConfirmationDialog();
                break;
            case R.id.bt_huychinhsuatruyen:
                setEnable(false);
                break;
            case R.id.bt_xacnhantruyen:
                String tentruyen = bookName.getText().toString();
                String tacgia = bookAuthor.getText().toString();
                String mota = bookDescription.getText().toString();
                String theloai = bookCategory.getText().toString();
                String linkanh = edt_linkanh.getText().toString();
                String trangthai = bookStatus.getText().toString();
                int tt = Integer.parseInt(trangthai);
                if (!tentruyen.isEmpty() && !tacgia.isEmpty() && !mota.isEmpty() && !theloai.isEmpty() && !linkanh.isEmpty() && !trangthai.isEmpty()) {
                    if (tt == 1 || tt == 0) {
//                        db.updateTruyen(story.getId(), tentruyen, tacgia, mota, theloai, linkanh, tt, key_search);
                        Toast.makeText(this, "Đã cập nhật thông tin truyện", Toast.LENGTH_SHORT).show();
                        reload();
                    } else {
                        Toast.makeText(this, "Trạng thái = 0 hoặc = 1", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // This method is used to delete a book
    private void handleDeleteStory() {
        // Call the deleteBook method from the bookAPI interface, passing the book's id
        bookAPI.deleteBook(bookResponse.getId()).enqueue(new Callback<APIResponse<Void>>() {
            // This method is called when the server responds to our request
            @Override
            public void onResponse(Call<APIResponse<Void>> call, Response<APIResponse<Void>> response) {
                // Check if the response is successful
                if (response.isSuccessful()) {
                    // If the response is successful, show a toast message indicating that the book was deleted
                    Toast.makeText(BookDetailManagement.this, "Đã xóa truyện", Toast.LENGTH_SHORT).show();
                    // Create a new Intent to start the BookManagement activity
                    Intent intent = new Intent(BookDetailManagement.this, BookManagement.class);
                    // Start the BookManagement activity
                    startActivity(intent);
                } else {
                    // If the response is not successful, show a toast message indicating that the book could not be deleted
                    Toast.makeText(BookDetailManagement.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
                }
            }

            // This method is called when the request to the server fails
            @Override
            public void onFailure(Call<APIResponse<Void>> call, Throwable t) {
                // Show a toast message indicating that the request failed
                Toast.makeText(BookDetailManagement.this, "Failed to delete book", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // This method is used to show a confirmation dialog when the user attempts to delete a book
    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set the message to be displayed in the dialog
        builder.setMessage("Bạn có chắc muốn xóa cuốn sách này?");

        // Set the positive (Yes) button and its click listener
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // When the Yes button is clicked, call the handleDeleteStory method to delete the book
                handleDeleteStory();
            }
        });

        // Set the negative (No) button and its click listener
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // When the No button is clicked, close the dialog
                dialog.dismiss();
            }
        });

        // Create the AlertDialog using the builder
        AlertDialog alertDialog = builder.create();

        // Show the dialog
        alertDialog.show();
    }


    private void setEnable(boolean isEnable) {
        if (isEnable) {
            edt_linkanh.setEnabled(true);
            bookStatus.setEnabled(true);
            bookName.setEnabled(true);
            bookDescription.setEnabled(true);
            bookCategory.setEnabled(true);
            bookAuthor.setEnabled(true);
            editBtn.setVisibility(View.GONE);
            cancelEditBtn.setVisibility(View.VISIBLE);
            saveBtn.setVisibility(View.VISIBLE);
            deleteStoryBtn.setVisibility(View.VISIBLE);
        } else {
            edt_linkanh.setEnabled(false);
            bookStatus.setEnabled(false);
            bookName.setEnabled(false);
            bookDescription.setEnabled(false);
            bookCategory.setEnabled(false);
            bookAuthor.setEnabled(false);
            editBtn.setVisibility(View.VISIBLE);
            cancelEditBtn.setVisibility(View.GONE);
            saveBtn.setVisibility(View.GONE);
            deleteStoryBtn.setVisibility(View.GONE);
        }
    }


    private void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
