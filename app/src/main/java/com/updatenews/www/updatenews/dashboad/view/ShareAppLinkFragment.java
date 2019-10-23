package com.updatenews.www.updatenews.dashboad.view;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.updatenews.www.updatenews.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareAppLinkFragment extends Fragment {
    private static final String EMAIL = "email";
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean isLoggedIn;
    private Button customButton;
    private ShareButton shareButton;

    public ShareAppLinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_share_app_link, container, false);
        // Initialize facebook SDK.
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
         isLoggedIn = accessToken != null && !accessToken.isExpired();

        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentTitle("Yloglite")
                .setContentDescription("share our app on facebook")
                .setContentUrl(Uri.parse("https://www.facebook.com/ylogapp?fref=ts"))
                .setShareHashtag(new ShareHashtag.Builder()
                        .setHashtag("#YLogApp")
                        .build())
                .build();

        shareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
        // Set the content you want to share.
        shareButton.setShareContent(content);

        loginButton = (LoginButton)view.findViewById(R.id.login_button);
        customButton = (Button)view.findViewById(R.id.customBtn);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // onClickOperation();
                //facebookHashKey();
            }
        });
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void facebookHashKey() {

        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo("com.updatenews.www.updatenews", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashCode  = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                System.out.println("Print the hashKey for Facebook :"+hashCode);
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
    void onClickOperation(){
       LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }
}
