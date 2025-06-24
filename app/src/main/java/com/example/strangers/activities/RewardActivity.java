package com.example.strangers.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.strangers.R;
import com.example.strangers.databinding.ActivityRewardBinding;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.LoadAdError;
//import com.google.android.gms.ads.OnUserEarnedRewardListener;
//import com.google.android.gms.ads.rewarded.RewardItem;
//import com.google.android.gms.ads.rewarded.RewardedAd;
//import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class RewardActivity extends AppCompatActivity {

    ActivityRewardBinding binding;
    //private RewardedAd mRewardedAd;
    private RewardedAd rewardedAd;
    private final String TAG = "RewardActivity";
    FirebaseDatabase database;
    String currentUid;
    int coins= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRewardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        currentUid = FirebaseAuth.getInstance().getUid();
        loadAd();

        database.getReference().child("profiles")
                .child(currentUid)
                .child("coins")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        coins = snapshot.getValue(Integer.class);
                        binding.coins.setText(String.valueOf(coins));
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

        binding.video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = RewardActivity.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Log.d(TAG, "The user earned the reward.");
                            // Handle the reward.
                            coins = coins + 200;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);

                            binding.video1Icon.setImageResource(R.drawable.check);
                            Toast toast = Toast.makeText(getApplicationContext(), "Coins Added Successfully ", Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 5000);
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");

                }

            }
        });
        binding.video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = RewardActivity.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Log.d(TAG, "The user earned the reward.");
                            // Handle the reward.
                            coins = coins + 300;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);

                            binding.video2Icon.setImageResource(R.drawable.check);
                            Toast toast = Toast.makeText(getApplicationContext(), "Coins Added Successfully ", Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 5000);
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");

                }

            }
        });
        binding.video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = RewardActivity.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Log.d(TAG, "The user earned the reward.");
                            // Handle the reward.
                            coins = coins + 400;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);

                            binding.video3Icon.setImageResource(R.drawable.check);
                            Toast toast = Toast.makeText(getApplicationContext(), "Coins Added Successfully ", Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 5000);
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");

                }

            }
        });
        binding.video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = RewardActivity.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Log.d(TAG, "The user earned the reward.");
                            // Handle the reward.
                            coins = coins + 500;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);

                            binding.video4Icon.setImageResource(R.drawable.check);
                            Toast toast = Toast.makeText(getApplicationContext(), "Coins Added Successfully ", Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 5000);
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");

                }

            }
        });
        binding.video5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rewardedAd != null) {
                    Activity activityContext = RewardActivity.this;
                    rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Log.d(TAG, "The user earned the reward.");
                            // Handle the reward.
                            coins = coins + 1000;
                            database.getReference().child("profiles")
                                    .child(currentUid)
                                    .child("coins")
                                    .setValue(coins);

                            binding.video5Icon.setImageResource(R.drawable.check);

                            Toast toast = Toast.makeText(getApplicationContext(), "Coins Added Successfully ", Toast.LENGTH_SHORT);
                            toast.show();

                            new Handler(Looper.getMainLooper()).postDelayed(toast::cancel, 5000);
                        }
                    });
                } else {
                    Log.d(TAG, "The rewarded ad wasn't ready yet.");

                }

            }
        });



    }

    void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, "ca-app-pub-3940256099942544/5224354917",
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.
                        Log.d(TAG, loadAdError.toString());
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        Log.d(TAG, "Ad failed to load: ");
                        rewardedAd = ad;

                    }
                });
    }


}