package com.example.novopayassignment.ui.main;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.novopayassignment.APIClient;
import com.example.novopayassignment.APIInterface;
import com.example.novopayassignment.Data;
import com.example.novopayassignment.NewsSourceList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PageViewModel extends ViewModel {

    public MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public MutableLiveData<List<NewsSourceList>> newsSourceLiveData = new MutableLiveData<>() ;
    APIInterface apiInterface;
    public PageViewModel() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getNewsSources() {
        Call<Data> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";
                Data resource = response.body();
                newsSourceLiveData.postValue(resource.newsSourceListList);
                Log.d("Response", ""+resource.newsSourceListList.get(0).source.name);
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                call.cancel();
                Log.d("Response", "Failure " + t.getMessage());
            }
        });
    }
}