package com.example.goldtracker.Screen.Wallet;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.goldtracker.R;
import com.example.goldtracker.Screen.Home.HomeViewModel;
import com.example.goldtracker.Screen.Home.HomeViewModelFactory;
import com.example.goldtracker.Screen.Profile.ProfileViewModel;
import com.example.goldtracker.databinding.FragmentHomeBinding;
import com.example.goldtracker.databinding.FragmentWalletBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class WalletFragment extends Fragment {
    private FragmentWalletBinding binding;
    private RequestQueue mQueue;
    private WalletViewModel viewModel;
    private String buy;
    private String sell;
    private String date;
    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setupScrape();
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_wallet, container, false);
        WalletViewModelFactory walletViewModelFactory = new WalletViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, walletViewModelFactory).get(WalletViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public void setupScrape(){
        mQueue = Volley.newRequestQueue(getContext());
        String url = "https://www.tokopedia.com/emas/api/gold/price";
        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("data");
                            // Retrieves the string labeled "price_id" and "date" from
                            //the response JSON Object
                            //and converts them into javascript objects
                            date = obj.getString("date");
                            buy = String.valueOf(obj.getInt("buy"));
                            sell = String.valueOf(obj.getInt("sell"));
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );
        // Adds the JSON object request "obreq" to the request queue
        mQueue.add(obreq);

    }
}
