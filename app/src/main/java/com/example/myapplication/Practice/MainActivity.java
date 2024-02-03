package com.example.myapplication.Practice;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Constants.WebConstants;
import com.example.myapplication.DayDreamSoft.FirstActivity;
import com.example.myapplication.DayDreamSoft.SecondActivity;
import com.example.myapplication.Matrix.MatrixActivity;
import com.example.myapplication.PersonalData.LiveDataSample.PersonalLiveData;
import com.example.myapplication.Practice.API_Interface.API_Interface;
import com.example.myapplication.Practice.Adapter.MyPagerAdapter_Native;
import com.example.myapplication.Practice.Adapter.User_Adapter;
import com.example.myapplication.Practice.Frag.PostData_Fragment;
import com.example.myapplication.Practice.model.ConnectionModel;
import com.example.myapplication.Practice.model.Datum;
import com.example.myapplication.Practice.model.Example;
import com.example.myapplication.Practice.sample.Product;
import com.example.myapplication.Practice.sample.BrandCategory;
import com.example.myapplication.Practice.sample.Brand;
import com.example.myapplication.Practice.sample.Category;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMain2Binding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.myapplication.Practice.API_Interface.API_Client.getInstanceAPI;

public class MainActivity extends DemoClass implements User_Adapter.CategoryListener {

    private static final String TAG = "SSA";
    RecyclerView rv_Llist;
    private List<Datum> list_user;
    TextView tvConnectivityStatus;
    ViewPager viewPager;
    boolean isOffline = true;
    IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    public API_Interface api_interface;
    ProgressDialog progressDialog;
    private User_Adapter user_adapter;
    private MyPagerAdapter_Native myPagerAdapter_native;
    private LiveDataConstant viewModelProvider;
    private @NonNull
    ActivityMain2Binding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
//        setContentView(R.layout.activity_main2);
        activityMainBinding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        UI(activityMainBinding.getRoot());
        internetSetup();
        viewModelProvider = new ViewModelProvider(this).get(LiveDataConstant.class);

        PersonalLiveData vasuLiveData = new ViewModelProvider(this).get(PersonalLiveData.class);
        vasuLiveData.getVasuResponse().observe(this, new Observer<com.example.myapplication.PersonalData.Model.Example>() {
            @Override
            public void onChanged(com.example.myapplication.PersonalData.Model.Example example) {

                Log.d(TAG, "onChanged: " + example.toString());
            }
        });

        Type type = new TypeToken<com.example.myapplication.Practice.sample.Example>() {
        }.getType();
        com.example.myapplication.Practice.sample.Example example = new Gson().fromJson(loadJSONFromAsset(), type);
        try {
            List<Product> listProduct = example.getAllproducts();
            List<Category> listCategory = new ArrayList<>();
            List<Integer> listId = new ArrayList<>();
            for (Product allproduct : listProduct) {

                if (TextUtils.isEmpty(allproduct.getCategoryId())) {
                    continue;
                }
                int value = Integer.valueOf(allproduct.getCategoryId());
                if (listId.contains(value)) {
                    continue;
                }
                listId.add(value);
                listCategory.add(new Category(allproduct.getCategoryId(), new ArrayList<>()));
            }

            for (Category allproduct : listCategory) {
                int value1 = Integer.valueOf(allproduct.getCategory_id());
                List<Product> list = allproduct.getProduct_list();
                for (Product allproduct1 : listProduct) {
                    if (TextUtils.isEmpty(allproduct1.getCategoryId())) {
                        continue;
                    }
                    int value2 = Integer.valueOf(allproduct1.getCategoryId());
                    if (value1 == value2) {
                        allproduct1.setMainImage("https://www.patanjaliayurved.net/assets/product_images/400x500/" + allproduct1.getMainImage());
                        allproduct1.setDetailImage("https://www.patanjaliayurved.net/assets/product_images/400x300/" + allproduct1.getDetailImage());
                        allproduct1.setCartImage("https://www.patanjaliayurved.net/assets/product_images/70x56/" + allproduct1.getCartImage());

                        list.add(allproduct1);
                    }
                }
                allproduct.setProduct_list(list);
            }
            for (Category allproduct : listCategory) {

                String name = allproduct.getProduct_list().get(0).getUrl();
                name = name.trim().replace("https://www.patanjaliayurved.net/product/", "");
                int a = name.indexOf("/");
                String category = name.substring(0, a);
                String remain = name.substring(a + 1);
                String sub_category = remain.substring(0, remain.indexOf("/"));
                String product_name = remain.replace(sub_category + "/", "");
                if (product_name.contains("/")) {
                    product_name = product_name.substring(0, product_name.indexOf("/"));
                    allproduct.setProduct_name(product_name);
                }
                allproduct.setCategory_name(category);
                allproduct.setSub_category_name(sub_category);
            }
            /*List<Category> cat_wise_all_list = new ArrayList<>();

            for (Category allproduct : listCategory) {
                if (!check_category_added(allproduct.getCategory_name(), cat_wise_all_list, 0)) {
                    cat_wise_all_list.add(new Category(allproduct.getCategory_name()));
                }
            }*/

            Brand brandModel = new Brand("Patanjali", listCategory);
            ArrayList<Brand> list = new ArrayList<Brand>();
            list.add(brandModel);
            BrandCategory brandCategory = new BrandCategory(list);

            String s = new Gson().toJson(brandCategory);

            Log.d(TAG, listCategory.size() + "onCreate: " + s);

        }
        catch (Exception ex) {
            Log.d(TAG, "onCreate: " + ex.toString());
        }
    }

    private boolean check_category_added(String category_name, List<Category> cat_wise_all_list, int i) {
        boolean response = false;
        if (cat_wise_all_list != null) {
            if (cat_wise_all_list.isEmpty())
                return response;
        }

        for (Category category : cat_wise_all_list) {
            if (i == 0 && category.getCategory_name().equals(category_name)) {
                response = true;
                break;
            } else if (i == 1 && category.getSub_category_name().equals(category_name)) {
                response = true;
                break;
            } else if (i == 2 && category.getProduct_name().equals(category_name)) {
                response = true;
                break;
            }
        }
        return response;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void internetSetup() {
        ConnectionLiveData connectionLiveData = new ConnectionLiveData(getApplicationContext());
        connectionLiveData.observe(this, new Observer<ConnectionModel>() {
            @Override
            public void onChanged(@Nullable ConnectionModel connection) {
                if (connection.getIsConnected()) {
                    switch (connection.getType()) {
                        case 0:
                            Toast.makeText(MainActivity.this, String.format("Wifi turned ON"), Toast.LENGTH_SHORT).show();
                            isOffline = false;
                            tvConnectivityStatus.setText("Online");
                            if (list_user == null || list_user.size() == 0)
                                viewModelProvider.getList_Post(1).observe(MainActivity.this, obsererUser);
                            break;
                        case 1:
                            isOffline = false;
                            tvConnectivityStatus.setText("Online");
                            Toast.makeText(MainActivity.this, String.format("Mobile data turned ON"), Toast.LENGTH_SHORT).show();
                            if (list_user == null || list_user.size() == 0)
                                viewModelProvider.getList_Post(1).observe(MainActivity.this, obsererUser);
                            break;
                    }
                } else {
                    isOffline = true;
                    tvConnectivityStatus.setText("Offline");
                    Toast.makeText(MainActivity.this, String.format("Connection turned OFF"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Observer<? super List<Datum>> obsererUser = new Observer<List<Datum>>() {
        @Override
        public void onChanged(List<Datum> data) {
            if (list_user != null && !list_user.isEmpty()) {
                list_user.clear();
                list_user = null;
            }
            list_user = new ArrayList<>();
            if (data != null && data.size() != 0)
                list_user.addAll(data);
            user_adapter = new User_Adapter(MainActivity.this, list_user, MainActivity.this);
            rv_Llist.setAdapter(user_adapter);
            user_adapter.selectedCatgory = 0;
            setPager();
        }
    };

    private void fetch_Users(int page) {
        api_interface = getInstanceAPI();

        Call<Example> datumCall = api_interface.getAllUser(String.valueOf(page));
        progressDialog.show();

        datumCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                list_user = response.body().getData();
                user_adapter = new User_Adapter(MainActivity.this, list_user, MainActivity.this);
                rv_Llist.setAdapter(user_adapter);
                user_adapter.selectedCatgory = 0;
                setPager();
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void setPager() {
//        cleanBackStack(viewPager.getId());
        if (list_user == null || list_user.isEmpty())
            return;
        List<Fragment> listFRag = new ArrayList<>();
        for (Datum datum : list_user) {
            Log.d("TAG", "onResponse: " + datum.toString());
            listFRag.add(PostData_Fragment.newInstance(String.valueOf(datum.getId()), datum.getName()));
        }
        if (myPagerAdapter_native != null) {
            myPagerAdapter_native.clear();
        }
        myPagerAdapter_native = new MyPagerAdapter_Native(getSupportFragmentManager(),
                MainActivity.this, listFRag);

        viewPager.setAdapter(myPagerAdapter_native);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                user_adapter.selectedCatgory = position;
                user_adapter.notifyDataSetChanged();
                rv_Llist.scrollToPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void UI(View root) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        rv_Llist = root.findViewById(R.id.rv_Llist);
        tvConnectivityStatus = root.findViewById(R.id.tvConnectivityStatus);
        viewPager = root.findViewById(R.id.viewPager);
//        rv_Llist=activityMainBinding.rvList;
//        rv_Llist=activityMainBinding.rvList;
        rv_Llist.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

    }

    @Override
    public void onClickItem(int position) {
        viewPager.setCurrentItem(position);
       /* if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();


        Call<Post_Example> post_exampleCall = api_interface.getSpecific_User_Post(list_user.get(position).getId().toString());
        post_exampleCall.enqueue(new Callback<Post_Example>() {
            @Override
            public void onResponse(Call<Post_Example> call, Response<Post_Example> response) {

//                PostAdapter postAdapter = new PostAdapter(MainActivity.this, response.body().getData(), null);
//                viewPager.setAdapter(postAdapter);
                user_adapter.selectedCatgory = 0;
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Post_Example> call, Throwable t) {
                progressDialog.dismiss();
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerReceiver(broadcastInternet, intentFilter);
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        unregisterReceiver(broadcastInternet);
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    private BroadcastReceiver broadcastInternet = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (WebConstants.isNetworkAvailable(context)) {
                    isOffline = false;
                    tvConnectivityStatus.setText("Online");
                } else {
                    isOffline = true;
                    tvConnectivityStatus.setText("Offline");
                }
            }
        }
    };

    public void fecth1(View view) {
        viewModelProvider.getList_Post(1).observe(this, obsererUser);
    }

    public void fecth2(View view) {
        viewModelProvider.getList_Post(2).observe(this, obsererUser);
    }

    public void fecth3(View view) {
        viewModelProvider.getList_Post(3).observe(this, obsererUser);
    }


    public void onCLickMatrix(View view) {
        startActivity(new Intent(MainActivity.this, FirstActivity.class));
    }
}