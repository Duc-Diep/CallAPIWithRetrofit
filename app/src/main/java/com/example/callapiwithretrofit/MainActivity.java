package com.example.callapiwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.callapiwithretrofit.api.ApiService;
import com.example.callapiwithretrofit.model.Account;
import com.example.callapiwithretrofit.model.AccountDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnGet,btnPost,btnPatch,btnDelete;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        btnGet.setOnClickListener(v -> {
            clickCall();
        });
        btnPost.setOnClickListener(v -> {
            sendPost();
        });
        btnPatch.setOnClickListener(v -> {
            sendPatch();
        });
        btnDelete.setOnClickListener(v -> {
            sendDelete();
        });
    }

    private void sendDelete() {
        ApiService.apiService.deleteAccount(57).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(MainActivity.this, "Delete Success", Toast.LENGTH_SHORT).show();
                String text = response.toString();
                tvResult.setText("Xóa thành công" + text);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Delete Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPatch() {
        AccountDTO accountDTO = new AccountDTO("ChangePass");
        ApiService.apiService.updateAccount(66,accountDTO).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(MainActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                String text = response.body().toString();
                    tvResult.setText(text);
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Patch Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendPost() {
        Account account = new Account(0,"Abcccccccc","Hello");
        ApiService.apiService.createAccount(account).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Toast.makeText(MainActivity.this, "Post Success", Toast.LENGTH_SHORT).show();
                Account acc = response.body();
                if (acc!=null)
                    tvResult.setText(acc.toString());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Post Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickCall() {
        ApiService.apiService.getAllAccount().enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                Toast.makeText(MainActivity.this, "Call Success", Toast.LENGTH_SHORT).show();
                List<Account> list = response.body();
                if (list!=null){
                    String text = "";
                    for (Account item: list){
                        text+=item.toString()+"\n";
                    }
                    tvResult.setText(text);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void mapping() {
        btnGet = findViewById(R.id.btnGet);
        tvResult = findViewById(R.id.tvResult);
        btnPost = findViewById(R.id.btnPost);
        btnPatch = findViewById(R.id.btnPatch);
        btnDelete = findViewById(R.id.btnDelete);
    }
}