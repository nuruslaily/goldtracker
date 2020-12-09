package com.example.goldtracker.Screen.Home;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.goldtracker.R;
import com.example.goldtracker.databinding.FragmentHomeBinding;
import com.example.goldtracker.models.Profile;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dexter.withContext(requireContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        Toast.makeText(getContext(), "Permission Rationale", Toast.LENGTH_SHORT).show();
                    }
                })
                .check();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false);
        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnedtProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profile = viewModel.getProfileLiveData().getValue();
                assert profile != null;
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToProfileFragment(profile);
                Navigation.findNavController(requireView()).navigate(action);
            }
        });

        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        viewModel.getProfileLiveData().observe(getViewLifecycleOwner(), new Observer<Profile>() {
            @Override
            public void onChanged(Profile profile) {
                if(profile!=null){
                    binding.imgProfile.setImageURI(Uri.parse(profile.getImage()));
                }
            }
        });

        binding.viewPager.setAdapter(new HomeViewPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0:
                        tab.setText("Wallet");
                        break;
                    case 1:
                        tab.setText("Transaction");
                        break;
                    case 2:
                        tab.setText("Buysell");
                        break;
                }
            }
        }
        );

        tabLayoutMediator.attach();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK )
        {
            try {
                assert data != null;
                Uri imageUri = data.getData();
                Profile profile = viewModel.getProfileLiveData().getValue();
                if(profile!=null){
                    profile.setImage(imageUri.toString());
                    viewModel.update(profile);
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}